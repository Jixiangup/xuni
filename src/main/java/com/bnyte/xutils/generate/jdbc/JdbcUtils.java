package com.bnyte.xutils.generate.jdbc;

import com.bnyte.xutils.generate.datasource.MySQLDruidDataSource;
import com.bnyte.xutils.generate.entity.MySQL;
import com.bnyte.xutils.generate.handler.MySQLHandle;
import com.bnyte.xutils.generate.pojo.MySQLTableData;
import com.bnyte.xutils.generate.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * 基于德鲁伊的jdbc数据库连接池
 * @auther bnyte
 * @date 2021-09-28 13:19
 * @email bnytezz@gmail.com
 */
public class JdbcUtils {


    /**
     * 从数据库中查询一个集合
     * @param connection 连接池对象
     * @param sql sql语句
     * @param args sql参数
     * @return 数据库的字段名、字段类型、字段备注的数据库信息集合
     */
    public static List<MySQLTableData> queryMySQLTableDataList (Connection connection, String sql, MySQL mySQL, Object ... args) {
        List<MySQLTableData> dataList = new LinkedList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MySQLTableData mySQLTableData = new MySQLTableData();
                mySQLTableData.setColumnName(StringUtils.transSqlStyleToMiniHump(resultSet.getString(1)));
                mySQLTableData.setColumnComment(resultSet.getString(2));
                mySQLTableData.setColumnType(StringUtils.typeTransformMySQLToJava(resultSet.getString(3)));
                mySQLTableData.setWriteName("set" + StringUtils.transMiniHumpToBigHump(mySQLTableData.getColumnName()));
                mySQLTableData.setReadName("get" + StringUtils.transMiniHumpToBigHump(mySQLTableData.getColumnName()));
                mySQLTableData.setTableName(StringUtils.transSqlStyleToBigHump(mySQL.getTable()));
                dataList.add(mySQLTableData);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

}
