package com.bnyte.xutils.generate.pojo;

/**
 * MySQL查询出来的数据，包含 列名、备注、属性类型
 * @auther bnyte
 * @date 2021-10-08 10:33
 * @email bnytezz@gmail.com
 */
public class MySQLTableData {

    private String columnName;
    private String columnComment;
    private String columnType;
    private String tableName;
    private String writeName;
    private String readName;

    public MySQLTableData() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWriteName() {
        return writeName;
    }

    public void setWriteName(String writeName) {
        this.writeName = writeName;
    }

    public String getReadName() {
        return readName;
    }

    public void setReadName(String readName) {
        this.readName = readName;
    }

    @Override
    public String toString() {
        return "MySQLTableData{" +
                "columnName='" + columnName + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", columnType='" + columnType + '\'' +
                ", tableName='" + tableName + '\'' +
                ", writeName='" + writeName + '\'' +
                ", readName='" + readName + '\'' +
                '}';
    }
}
