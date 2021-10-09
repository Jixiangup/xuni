package com.bnyte.xutils.generate.util;

import com.bnyte.xutils.generate.enums.Protocols;

/**
 * @auther bnyte
 * @date 2021-09-29 11:01
 * @email bnytezz@gmail.com
 */
public class MySQLUtils {

    /**
     * 返回jdbc://mysql格式的数据库链接地址
     * @param ip ip
     * @param port 端口
     * @param database 库名
     * @return 返回jdbc://mysql格式的数据库链接地址
     */
    public static String getJdbcMySQLUrl(String ip, Integer port, String database) {
        return Protocols.JDBC_MYSQL.getValue() + "://" + ip + ":" + port + "/" + database;
    }

    /**
     * 使用https地址链接数据库
     * @param ip ip
     * @param port 端口
     * @param database 库名
     * @return 返回https的链接地址
     */
    public static String getHttpsUrl (String ip, Integer port, String database) {
        return Protocols.HTTPS.getValue() + "://" + ip + ":" + port + "/" + database;
    }

}
