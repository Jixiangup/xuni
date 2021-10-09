package com.bnyte.xutils.generate.enums;

/**
 * @auther bnyte
 * @date 2021-10-09 09:28
 * @email bnytezz@gmail.com
 */
public enum GenerateType {

    /** controller控制层 */
    CONTROLLER("controller"),
    /** service层 */
    SERVICE("service"),
    /** 实体类 */
    ENTITY("entity"),
    /** mapper映射xml文件 */
    MAPPER("mapper"),
    /** 默认全部生成, 包含 entity,controller,service,mapper */
    DEFAULT("DEFAULT");

    private String value;

    GenerateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenerateType{" +
                "value='" + value + '\'' +
                '}';
    }
}
