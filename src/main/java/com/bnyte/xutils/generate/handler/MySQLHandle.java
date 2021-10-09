package com.bnyte.xutils.generate.handler;

import com.bnyte.xutils.generate.datasource.MySQLDruidDataSource;
import com.bnyte.xutils.generate.entity.MySQL;
import com.bnyte.xutils.generate.pojo.MySQLTableData;
import com.bnyte.xutils.generate.util.StringUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * mysql处理器
 * @auther bnyte
 * @date 2021-10-08 14:53
 * @email bnytezz@gmail.com
 */
public class MySQLHandle {

    public static List<MySQLTableData> getMysqlTableDataList (MySQL mySQL) throws SQLException {
        MySQLDruidDataSource druidDataSource = new MySQLDruidDataSource(mySQL);
        Connection connection = druidDataSource.getJdbcMySQLConnection();
        String sql = "select column_name, column_comment, column_type from information_schema.columns where table_schema = ? and table_name = ?";
        PreparedStatement  statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, mySQL.getDatabase());
        statement.setString(2, mySQL.getTable());

        ResultSet resultSet = statement.executeQuery();

        List<MySQLTableData> mySQLTableDataList = new LinkedList<>();

        while (resultSet.next()) {
            MySQLTableData mySQLTableData = new MySQLTableData();
            mySQLTableData.setColumnName(StringUtils.transSqlStyleToMiniHump(resultSet.getString(1)));
            mySQLTableData.setColumnComment(resultSet.getString(2));
            mySQLTableData.setColumnType(StringUtils.typeTransformMySQLToJava(resultSet.getString(3)));
            mySQLTableData.setWriteName("set" + StringUtils.transMiniHumpToBigHump(mySQLTableData.getColumnName()));
            mySQLTableData.setReadName("get" + StringUtils.transMiniHumpToBigHump(mySQLTableData.getColumnName()));
            mySQLTableData.setTableName(StringUtils.transSqlStyleToBigHump(mySQL.getTable()));
            mySQLTableDataList.add(mySQLTableData);
        }

        druidDataSource.closeJdbcMySQLConnection(connection);
        return mySQLTableDataList;
    }

}
