package com.bnyte.xutils.generate.entity;

/**
 * MySQL实体类
 * @auther bnyte
 * @date 2021-09-28 13:21
 * @email bnytezz@gmail.com
 */
public class MySQL {

    /**
     * 驱动器, 默认：com.mysql.cj.jdbc.Driver
     */
    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    /**
     * 数据库所在ip: 默认localhost
     */
    private String ip = "localhost";

    /**
     *  数据库所在端口
     */
    private Integer port = 3306;

    /**
     * 数据库登录用户名
     */
    private String username;

    /**
     * 数据库登录密码
     */
    private String password;

    /**
     * 库名
     */
    private String database;

    /**
     * 表名
     */
    private String table;

    public MySQL() {
    }

    public MySQL(String driverClassName, String ip, Integer port, String username, String password, String database, String table) {
        this.driverClassName = driverClassName;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
        this.table = table;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "MySQL{" +
                "driverClassName='" + driverClassName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                ", table='" + table + '\'' +
                '}';
    }
}
