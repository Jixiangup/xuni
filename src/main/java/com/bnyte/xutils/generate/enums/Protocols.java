package com.bnyte.xutils.generate.enums;

/**
 * @auther bnyte
 * @date 2021-09-29 11:30
 * @email bnytezz@gmail.com
 */
public enum Protocols {
    HTTP("http"),
    HTTPS("https"),
    JDBC_MYSQL("jdbc:mysql");

    private String value;

    Protocols(String value) {
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
        return "Protocols{" +
                "value='" + value + '\'' +
                '}';
    }
}
