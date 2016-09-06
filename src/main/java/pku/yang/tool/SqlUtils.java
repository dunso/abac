package pku.yang.tool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import pku.yang.model.DbTableName;
import pku.yang.model.Table2;

/**
 * Created by LinkedME07 on 16/6/15.
 */
@Component
public class SqlUtils {

    private static Jdbc jdbc;

    public void setJdbc(Jdbc jdbc) {
		this.jdbc = jdbc;
		url = jdbc.toString();
	}

    private static String url;

    private static String PACKAGE = "pku.yang.model";

    public SqlUtils() {}

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(jdbc.getDriverClass());
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            setErrorMsg("40003", "数据库连接失败！请确认网络畅通！");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            setErrorMsg("40003", "数据库连接失败！请核查连接地址是否正确！");
            e.printStackTrace();
        }
        return conn;
    }


    public static Integer update(String sql,String optType) {
        int result = -1;
        Connection conn = getConn();

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                result = stmt.executeUpdate(sql);
                if("save".equals(optType)){
                    PreparedStatement prest = conn.prepareStatement("select last_insert_id()", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = prest.executeQuery();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }else{
                    return result;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close(conn, null, null);
            }
        }
        return result;
    }



    public static List<Object> query(String sql, String tableName, List<Object> params) {
        ResultSet rs = null;
        Connection conn = getConn();
        PreparedStatement prest = null;
        List<Object> result = new ArrayList<Object>();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
                prest = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                if (params != null) {
                    for (int i = 0; i < params.size(); i++) {
                        Object o = params.get(i);
                        if (o instanceof Integer) {
                            prest.setInt(i + 1, (Integer) o);
                        } else if (o instanceof String) {
                            prest.setString(i + 1, String.valueOf(o));
                        }
                    }
                }
                rs = prest.executeQuery();
                while (rs.next()) {
                    // ResultSetMetaData m = rs.getMetaData();
                    if ("table_config".equals(tableName)) {
                        DbTableName dbTableName = new DbTableName();
                        dbTableName.setDbName(rs.getString(1));
                        dbTableName.setTableName(rs.getString(2));
                        result.add(dbTableName);
                    } else if ("column_config".equals(tableName)) {
                        String column = rs.getString(1);
                        result.add(column);
                    } else if (tableName == null) {
                        String column = rs.getString(1);
                        result.add(column);
                    } else {
                        String tableCode = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
                        Object entity = setObject(rs, tableCode);
                        result.add(entity);
                    }
                }
            } catch (Exception e) {
                setErrorMsg("40003", "数据库操作失败");
                e.printStackTrace();
            } finally {
                close(conn, prest, rs);

            }
        }
        return result;
    }

    private static Object setObject(ResultSet rs, String tableCode) {
        Class<?> clazz = null;
        Object o = null;
        try {
            clazz = Class.forName(PACKAGE + "." + tableCode);
            o = clazz.newInstance();
        } catch (Exception e) {
            setErrorMsg("40003", "数据库查询结果无法实例化对象，请核查对象名是否正确");
            e.printStackTrace();
        }

        Field[] fields = clazz.getDeclaredFields();
        String arrtibuteName = "";
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            arrtibuteName = String.valueOf(fields[i]);
            arrtibuteName = arrtibuteName.substring(arrtibuteName.lastIndexOf(".") + 1);
            String columnName = String.valueOf(getColumnNameByColumnCode(tableCode, arrtibuteName));

            try {
                Object value = rs.getObject(columnName);
                if (value instanceof String) {
                    fields[i].set(o, rs.getString(columnName));
                } else if (value instanceof Integer) {
                    fields[i].set(o, rs.getInt(columnName));
                }
            } catch (Exception e) {
                if ("table2".equals(arrtibuteName)) {
                    try {
                        int value = rs.getInt("table2id");
                        List<Object> entieys = getObjectsByColumnCodeAndValue("table2", "table2id", String.valueOf(value));
                        if (entieys.size() == 0) {
                            // ERROR信息
                        } else {
                            Table2 table2 = (Table2) entieys.get(0);
                            fields[i].set(o, table2);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                continue;
            }
        }
        return o;
    }

    public static void close(Connection conn, PreparedStatement prest, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (prest != null) {
                prest.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Object> getDBTable(String dbTableType) {
        List<Object> params = new ArrayList<>();
        params.add(dbTableType);
        return query("SELECT db_name,table_name FROM table_config where type = ? and is_valid = '1'", "table_config", params);
    }

    public static Object getColumnCodeByColumnDisplay(String tableName, String columnDisplay) {
        List<Object> params = new ArrayList<Object>();
        params.add(tableName);
        params.add(columnDisplay);
        List<Object> result = query("SELECT column_code FROM column_config where table_name = ? and column_display = ? and is_valid = 1",
                "column_config", params);
        return result.size() > 0 ? result.get(0) : null;
    }

    public static Object getColumnNameByColumnDisplay(String tableName,String columnDisplay){
        List<Object> result = new ArrayList<Object>();
        List<Object> params = new ArrayList<Object>();
        params.add(tableName);
        params.add(columnDisplay);
        result = query("SELECT column_name FROM column_config where table_name = ? and column_display = ? and is_valid = 1","column_config",params);
        return result.size()>0 ? result.get(0):"";
    }

    public static Object getColumnDisplayByColumnCode(String tableName, String columnCode) {
        List<Object> result = new ArrayList<Object>();
        List<Object> params = new ArrayList<Object>();
        params.add(tableName);
        params.add(columnCode);
        result = query("SELECT column_display FROM column_config where table_name = ? and column_code = ? and is_valid = 1",
                "column_config", params);
        return result.size() > 0 ? result.get(0) : "";
    }

    public static Object getColumnNameByColumnCode(String tableName, String columnCode) {
        List<Object> result = new ArrayList<Object>();
        List<Object> params = new ArrayList<Object>();
        params.add(tableName);
        params.add(columnCode);
        result = query("SELECT column_name FROM column_config where table_name = ? and column_code = ? and is_valid = 1", "column_config",
                params);
        return result.size() > 0 ? result.get(0) : "";
    }
    /*
     * public static Object getColumn(String table,String column){ List<Object> result = new
     * ArrayList<Object>(); List<Object> params = new ArrayList<Object>(); params.add(table);
     * params.add(column); result = query(
     * "SELECT column_name FROM column_config where table_name = ? and column_mapping = ? and is_valid = 1"
     * ,"column_config",params); return result.size()>0 ? result.get(0):""; }
     */

    public static Object getColumnValueBySingleCondition(String tableName, String queryColumeCode, String condColumnCode,
            String condColumnValue) {
        List<Object> result = new ArrayList<Object>();
        String queryColumnName = String.valueOf(getColumnNameByColumnCode(tableName, queryColumeCode));
        String condColumnName = String.valueOf(getColumnNameByColumnCode(tableName, condColumnCode));
        StringBuilder sql = new StringBuilder("SELECT ").append(queryColumnName).append(" FROM ").append(tableName).append(" WHERE ")
                .append(condColumnName).append(" = ").append(condColumnValue);
        //System.out.println(sql);
        result = query(sql.toString(), null, null);
        return result.size() > 0 ? result.get(0) : null;
    }

    public static Object getFlagORIsValid(String tableName, String queryColumeCode, String condColumnCode, String condColumnValue) {
        List<Object> result = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder("SELECT ").append(queryColumeCode).append(" FROM ").append("column_config").append(" WHERE ")
                .append(condColumnCode).append(" = \"").append(condColumnValue).append("\"").append(" AND table_name = \"")
                .append(tableName).append("\"");
        //System.out.println(sql);
        result = query(sql.toString(), null, null);
        return result.size() > 0 ? result.get(0) : null;
    }

    public static List<Object> getObjectsByColumnCodeAndValue(String tableName, String columnCode, String columnValue) {
        List<Object> params = new ArrayList<Object>();
        params.add(columnValue);
        String columnName = String.valueOf(getColumnNameByColumnCode(tableName, columnCode));
        return query("SELECT * FROM " + tableName + " where " + columnName + " = ?", tableName, params);
    }

    public static List<Object> getObjectsByColumnCodeAndValue(String tableName, String columnCode1, String columnValue1, String columnCode2,
            String columnValue2) {
        List<Object> params = new ArrayList<Object>();
        params.add(columnValue1);
        params.add(columnValue2);
        String columnName1 = String.valueOf(getColumnNameByColumnCode(tableName, columnCode1));
        String columnName2 = String.valueOf(getColumnNameByColumnCode(tableName, columnCode2));
        return query("SELECT * FROM " + tableName + " where " + columnName1 + " = ? and " + columnName2 + " = ? ", tableName, params);
    }

    public static List<Object> getObjectsByParams(String tableName, Map<String, String> paramsCode) {
        if (paramsCode.size() == 0) {
            setErrorMsg("50012", "查询失败，参数值为空！");
            return null;
        }
        String segment = "";
        Map<String, String> paramsName = paramsCodeToName(tableName, paramsCode);
        Iterator<Map.Entry<String, String>> entries = paramsName.entrySet().iterator();
        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            sql.append(segment).append(entry.getKey()).append(" = \"").append(entry.getValue() + "\"");
            segment = " AND ";
        }
        //System.out.println(sql.toString());
        return query(sql.toString(), tableName, null);
    }

    public static List<Object> getObjects(String sql, List<Object> params) {
        return query(sql, null, params);
    }

    public static Integer saveObjects(String tableName, Map<String, String> paramsCode) {
        if (paramsCode.size() == 0) {
            setErrorMsg("50012", "保存失败，参数值为空！");
            return 0;
        }
        List<String> values = new ArrayList<String>();
        Map<String, String> paramsName = paramsCodeToName(tableName, paramsCode);
        Iterator<Map.Entry<String, String>> entries = paramsName.entrySet().iterator();
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " ( ");
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            sql.append(entry.getKey()).append(",");
            values.add(entry.getValue());
        }
        sql.deleteCharAt(sql.length() - 1).append(") VALUES ( ");
        for (int i = 0; i < values.size(); i++) {
            sql.append("\"" + values.get(i)).append("\",");
        }
        sql.deleteCharAt(sql.length() - 1).append(")");
        //System.out.println(sql.toString());
        return update(sql.toString(),"save");
    }

    public static Integer updateObjects(String tableName, String columnCode, String columnValue, Map<String, String> paramsCode) {
        if (paramsCode.size() == 0) {
            setErrorMsg("50012", "更新失败，参数值为空！");
            return 0;
        }
        Map<String, String> paramsName = paramsCodeToName(tableName, paramsCode);
        Iterator<Map.Entry<String, String>> entries = paramsName.entrySet().iterator();
        StringBuilder sql = new StringBuilder("update " + tableName + " set ");
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            sql.append(entry.getKey()).append("= \"").append(entry.getValue()).append("\",");
        }
        String columnName = String.valueOf(getColumnNameByColumnCode(tableName, columnCode));
        sql.deleteCharAt(sql.length() - 1).append(" where ").append(columnName).append("=\"").append(columnValue).append("\"");
        return update(sql.toString(),"update");
    }

    public static Integer deleteObject(String tableName, String columnCode, String columnValue) {
        String columnName = String.valueOf(getColumnNameByColumnCode(tableName, columnCode));
        StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ").append(columnName).append(" = \"")
                .append(columnValue).append("\"");
        //System.out.println(sql);
        return update(sql.toString(),"delete");
    }

    private static Map<String, String> paramsCodeToName(String tableName, Map<String, String> paramsCode) {
        Map<String, String> paramsName = new HashMap<>();
        Iterator<Map.Entry<String, String>> entries = paramsCode.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            String cloumDisName = String.valueOf(SqlUtils.getColumnNameByColumnCode(tableName, entry.getKey()));
            paramsName.put(cloumDisName, entry.getValue());
        }
        return paramsName;
    }

    private static Map<String, String> errorMsg = new HashMap<String, String>();

    public static Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public static void setErrorMsg(String code, String msg) {
        errorMsg.put(code, msg);
    }
}
