package com.bnyte.xutils.generate.enums;

/**
 * @auther bnyte
 * @date 2021-10-09 14:25
 * @email bnytezz@gmail.com
 */
public enum GenerateTemplate {

    /** controller控制层 */
    CONTROLLER("controller.ftl", "Controller", ".java"),

    /** service层 */
    SERVICE("service.ftl", "Service", ".java"),

    /** service层 */
    SERVICE_IMPL("serviceImpl.ftl", "ServiceImpl", ".java"),

    /** 实体类 */
    ENTITY("entity.ftl", null, ".java"),

    /** mapper映射xml文件 */
    MAPPER("mapperJava.ftl", "Mapper", ".java"),

    /** mapper映射xml文件 */
    MAPPER_IMPL("mapperXml.ftl", "Mapper", ".xml")
    ;


    private String filename;
    private String javaFile;
    private String filetype;

    GenerateTemplate(String filename, String javaFile, String filetype) {
        this.filename = filename;
        this.javaFile = javaFile;
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getJavaFile() {
        return javaFile;
    }

    public void setJavaFile(String javaFile) {
        this.javaFile = javaFile;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
}
