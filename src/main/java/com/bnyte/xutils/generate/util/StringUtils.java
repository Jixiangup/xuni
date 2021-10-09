package com.bnyte.xutils.generate.util;

import com.bnyte.xutils.generate.exception.MySQLGenerateException;

import java.util.Locale;

/**
 * @auther bnyte
 * @date 2021-10-09 10:19
 * @email bnytezz@gmail.com
 */
public class StringUtils {
    /**
     * 数据类型转化JAVA数据类型
     * @param sqlType：类型名称
     * @return 返回当前数据库中的数据类型在Java中的数据类型
     */
    public static String typeTransformMySQLToJava(String sqlType) {
        if ( sqlType == null || sqlType.trim().length() == 0 )
            return sqlType;

        sqlType = sqlType.toLowerCase();
        if (sqlType.contains("("))
            sqlType = sqlType.substring(0, sqlType.indexOf("("));

        switch(sqlType){
            case "nvarchar":
            case "char":
            case "varchar":
            case "text":
            case "nchar":
                return "String";
            case "blob":
            case "image":
                return "byte[]";
            case "integer":
            case "id":
            case "bigint":
                return "Long";
            case "tinyint":
            case "smallint":
            case "mediumint":
                return "Integer";
            case "bit":
            case "boolean":
                return "Boolean";
            case "float":
                return "Float";
            case "double":
            case "money":
            case "smallmoney":
                return "Double";
            case "date":
            case "datetime":
            case "year":
            case "time":
            case "timestamp":
                return "java.util.Date";
            case "numeric":
            case "real":
            case "decimal":
                return "java.math.BigDecimal";
            default:
                throw new MySQLGenerateException("转化失败：未发现的类型" + sqlType);
        }
    }

    /**
     * 数据库约束风格转换为Java小驼峰
     * @param sqlStyle 数据库约束风格字符串
     * @return 返回Java约束的小驼峰风格字符串
     */
    public static String transSqlStyleToMiniHump(String sqlStyle) {
        String result = "";
        String frontStr = "";
        String endStr = "";
        int indexOf = sqlStyle.indexOf("_");
        if (indexOf != -1) {
            frontStr = sqlStyle.substring(0, indexOf);
            if (indexOf != sqlStyle.length() - 1) {
                endStr = sqlStyle.substring(indexOf + 1);
                String c = (endStr.charAt(0) + "").toUpperCase(Locale.ROOT);
                endStr = c + endStr.substring(1);
            }
        } else {
            return sqlStyle;
        }
        result = frontStr + endStr;
        if (result.contains("_")) {
            return transSqlStyleToMiniHump(result);
        }
        return result;
    }

    /**
     * 将小驼峰转换为大驼峰
     * @param miniHump 小驼峰字符串
     * @return 返回大驼峰字符串
     */
    public static String transMiniHumpToBigHump (String miniHump) {
        String firstStr = (miniHump.charAt(0) + "").toUpperCase(Locale.ROOT);
        return firstStr + (miniHump.substring(1));
    }

    /**
     * 将数据库中下划线规范的字符串转换为大驼峰
     * @param sqlStyle 下划线风格字符串
     * @return 返回大驼峰字符串
     */
    public static String transSqlStyleToBigHump (String sqlStyle) {
        String miniHump = transSqlStyleToMiniHump(sqlStyle);
        String firstStr = (miniHump.charAt(0) + "").toUpperCase(Locale.ROOT);
        return firstStr + (miniHump.substring(1));
    }

    /**
     * 当前字符串是否有字符串(是否为空)
     * @param obj 需要判断的字符串
     * @return true为有字符串(不为空), false为没有字符串(为空)
     */
    public static boolean hasText(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof String)) {
            return false;
        }

        String tmp = String.valueOf(obj);

        return tmp != null && tmp.trim().length() != 0;
    }

    /**
     * 通过传入以'.'分割的路径字符串获取到当前的包路径，如：'com.bnyte.entity.User', 调用该方法时则会获取到'com.bnyte.entity'字符串的返回
     * @param javaPath 以'.'分割的路径字符串
     * @return 返回以.分割的包路径，如果没有"."作为分割则会返回默认引入的包,则是"java.lang"
     */
    public static String getPackageName (String javaPath) {
        int lastIndexOf = javaPath.lastIndexOf(".");
        if (lastIndexOf != -1) {
            return javaPath.substring(0, lastIndexOf);
        }
        return "java.lang";
    }

    /**
     * 通过传入以'.'分割的路径字符串获取到当前的类名，如：'com.bnyte.entity.User', 调用该方法时则会获取到'User'字符串的返回
     * @param javaPath 以'.'分割的路径字符串
     * @return 返回以.分割的类名
     */
    public static String getClassName (String javaPath) {
        int lastIndexOf = javaPath.lastIndexOf(".");
        if (lastIndexOf != -1) {
            return javaPath.substring(lastIndexOf + 1);
        }
        return javaPath;
    }
}
