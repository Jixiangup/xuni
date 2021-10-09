package com.bnyte.xutils.generate.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.bnyte.xutils.generate.entity.MySQL;
import com.bnyte.xutils.generate.util.MySQLUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @auther bnyte
 * @date 2021-09-29 10:34
 * @email bnytezz@gmail.com
 */
public class MySQLDruidDataSource {

    private MySQL mySQL;
    private Connection jdbcMySQLConnection;

    public MySQLDruidDataSource(MySQL mySQL) {
        this.mySQL = mySQL;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
    }

    /**
     * 创建jdbcMySQL的连接池
     */
    public void createJdbcMySQLConnection() {
        if (this.jdbcMySQLConnection == null) {
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUrl(MySQLUtils.getJdbcMySQLUrl(this.mySQL.getIp(), this.mySQL.getPort(), this.mySQL.getDatabase()));
            druidDataSource.setUsername(this.mySQL.getUsername());
            druidDataSource.setPassword(this.mySQL.getPassword());
            druidDataSource.setDriverClassName(this.mySQL.getDriverClassName());
            Connection connection = null;
            try {
                connection = druidDataSource.getConnection();
                this.jdbcMySQLConnection = connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取jdbcMySQL的连接池
     * @return jdbcMySQL连接池对象
     */
    public Connection getJdbcMySQLConnection() {
        if (this.jdbcMySQLConnection == null) {
            this.createJdbcMySQLConnection();
        }
        return this.jdbcMySQLConnection;
    }

    /**
     * 设置jdbcMySQL连接池
     * @param connection 连接池对象
     */
    public void setJdbcMySQLConnection(Connection connection) {
        this.jdbcMySQLConnection = connection;
    }

    /**
     * 关闭连接池
     * @param connection 当前连接池对象
     */
    public void closeJdbcMySQLConnection (Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
