package com.bnyte.xutils.generate.handler;

import com.bnyte.xutils.generate.datasource.MySQLDruidDataSource;
import com.bnyte.xutils.generate.entity.MySQL;
import com.bnyte.xutils.generate.jdbc.JdbcUtils;
import com.bnyte.xutils.generate.pojo.MySQLTableData;
import com.bnyte.xutils.generate.util.CollectionUtils;
import com.bnyte.xutils.generate.util.StringUtils;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * mysql处理器
 * @auther bnyte
 * @date 2021-10-08 14:53
 * @email bnytezz@gmail.com
 */
public class MySQLHandle {

    private static List<MySQLTableData> mySQLTableDataList =  null;

    private static final String queryTableInfo = "select column_name, column_comment, column_type from information_schema.columns where table_schema = ? and table_name = ?";

    private static MySQLDruidDataSource druidDataSource;

    private static Connection connection;

    /**
     * 获取指定库及指定表下面的所有 列名、列备注、列类型
     * @param mySQL
     * @return
     * @throws SQLException
     */
    public static List<MySQLTableData> getMysqlTableDataList (MySQL mySQL) throws SQLException {
        if (CollectionUtils.isEmpty(mySQLTableDataList)) {

            if (MySQLHandle.druidDataSource == null) {
                MySQLHandle.druidDataSource = new MySQLDruidDataSource(mySQL);
            }
            if (connection == null) {
                MySQLHandle.connection = druidDataSource.getJdbcMySQLConnection();
            }

            List<MySQLTableData> mySQLTableDataList = JdbcUtils.queryMySQLTableDataList(connection, MySQLHandle.queryTableInfo, mySQL, mySQL.getDatabase(), mySQL.getTable());

            druidDataSource.closeJdbcMySQLConnection(MySQLHandle.connection);
            MySQLHandle.mySQLTableDataList = mySQLTableDataList;
            return MySQLHandle.mySQLTableDataList;
        }
        return MySQLHandle.mySQLTableDataList;
    }

}
