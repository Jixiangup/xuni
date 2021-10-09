package com.bnyte.xutils.generate.enums;

/**
 * @auther bnyte
 * @date 2021-10-09 14:25
 * @email bnytezz@gmail.com
 */
public enum GenerateTemplate {

    /** controller控制层 */
    CONTROLLER("controller.ftl"),

    /** service层 */
    SERVICE("service.ftl"),

    /** service层 */
    SERVICE_IMPL("serviceImpl.ftl"),

    /** 实体类 */
    ENTITY("entity.ftl"),

    /** mapper映射xml文件 */
    MAPPER("mapper.ftl"),

    /** mapper映射xml文件 */
    MAPPERImpl("mapperImpl.ftl")
    ;


    private String filename;

    GenerateTemplate(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
