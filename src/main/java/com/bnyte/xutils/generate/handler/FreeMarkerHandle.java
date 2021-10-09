package com.bnyte.xutils.generate.handler;

import com.bnyte.xutils.generate.config.FreeMarkerConfig;
import com.bnyte.xutils.generate.entity.MySQL;
import com.bnyte.xutils.generate.enums.GenerateTemplate;
import com.bnyte.xutils.generate.enums.GenerateType;
import com.bnyte.xutils.generate.exception.GenerateTypeException;
import com.bnyte.xutils.generate.pojo.MySQLTableData;
import com.bnyte.xutils.generate.util.FileUtils;
import com.bnyte.xutils.generate.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @auther bnyte
 * @date 2021-10-08 16:24
 * @email bnytezz@gmail.com
 */
public class FreeMarkerHandle {

    public static void builder(String filepath, MySQL mySQL, String packageName, GenerateType generateType) throws Exception {
        StringBuilder packageNameBuilder = new StringBuilder(packageName);
        if (generateType == null) {
            throw new GenerateTypeException("generate type can not be empty, you can use GenerateType's field");
        }

        if (generateType == GenerateType.DEFAULT) {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        packageNameBuilder.append(".").append(GenerateType.CONTROLLER.getValue());
                        // 调用生成
                        builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.CONTROLLER);
                        break;
                    case 1:
                        packageNameBuilder.append(".").append(GenerateType.SERVICE.getValue());
                        // 调用生成
                        builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.SERVICE);
                        builder(filepath, mySQL, packageNameBuilder.append(".impl").toString(), true, GenerateTemplate.SERVICE_IMPL);
                        break;
                    case 2:
                        packageNameBuilder.append(".").append(GenerateType.ENTITY.getValue());
                        builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.ENTITY);
                        // 调用生成
                        break;
                    case 3:
                        packageNameBuilder.append(".").append(GenerateType.MAPPER.getValue());
                        builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.MAPPER);
                        builder(filepath, mySQL, packageNameBuilder.append(".impl").toString(), true, GenerateTemplate.MAPPERImpl);
                        // 调用生成
                        break;
                }
                packageNameBuilder = new StringBuilder(packageName);
            }
        } else {
            packageNameBuilder.append(".").append(generateType.getValue());
            switch (generateType) {
                case ENTITY:
                    builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.ENTITY);
                    break;
                case CONTROLLER:
                    builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.CONTROLLER);
                    break;
                case SERVICE:
                    builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.SERVICE);
                    break;
                case MAPPER:
                    builder(filepath, mySQL, packageNameBuilder.toString(), false, GenerateTemplate.MAPPER);
                    break;
            }
        }
    }

    protected static void builder(String filepath, MySQL mySQL, String packageName, boolean isImpl, GenerateTemplate generateTemplate) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22); // 指定版本

//        cfg.setDirectoryForTemplateLoading(new File(FreeMarkerHandle.class.getClassLoader().getResource("templates").getPath())); // 指定模板所在文件路径
//        cfg.setDirectoryForTemplateLoading(new File(templates)); // 指定模板所在文件路径
        cfg.setClassForTemplateLoading(FreeMarkerConfig.class, "templates");
//        cfg.setDirectoryForTemplateLoading();
        cfg.setDefaultEncoding("UTF-8"); // 指定编码
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


        List<String> imports = new ArrayList<>();
        Map<String, Object> root = new HashMap<>();
        List<MySQLTableData> mySQLTableDataList = MySQLHandle.getMysqlTableDataList(mySQL);

        for (MySQLTableData mySQLTableData : mySQLTableDataList) {
            String columnType = mySQLTableData.getColumnType();
            String tmpPackageName = StringUtils.getPackageName(columnType);
            String className = StringUtils.getClassName(columnType);
            if (!tmpPackageName.equals("java.lang")) {
                mySQLTableData.setColumnType(className);
                imports.add(tmpPackageName);
            }
        }

        root.put("tableDataList", mySQLTableDataList);
        root.put("className", StringUtils.transSqlStyleToBigHump(mySQL.getTable()));
        root.put("packageName", packageName);
        root.put("imports", imports);


        Template temp = cfg.getTemplate(generateTemplate.getFilename());

        // 先递归创建包
        FileUtils.createPackageFolders(filepath, packageName);

        String all = packageName.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
        String javaFile = filepath + File.separator + all + File.separator + StringUtils.transSqlStyleToBigHump(mySQL.getTable()) + ".java";
        // 通过包路径循环创建文件夹

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(javaFile)));
        temp.process(root, outputStreamWriter);
        System.out.println("[bnyte-generate-BnyteGenerate] generate complete");
    }

    public static void main(String[] args) throws Exception {
        builder("E:\\idea-workspace\\xutils\\src\\main\\java",
                    new MySQL("com.mysql.cj.jdbc.Driver", "192.168.1.172", 3306, "root", "curdata3508", "test_use_db", "t_hyl_request"),
                    "com.bnyte.xutils.generate",
                    GenerateType.ENTITY);
//        createPackageFolders("E:\\idea-workspace\\xmall\\xmall-generate\\src\\main\\java", "ce.shi");
    }


}
