package pku.yang.dao;

import java.util.List;
import java.util.Map;

import pku.yang.model.AccessControl;
import pku.yang.model.Strategy;
import pku.yang.model.User;

/**
 * 访问控制Dao接口
 * 
 * @author chenbin
 * @Date 20160113
 * @Operate create
 * @address Peking University
 */

public interface IABACDao {

    Map<String, String> getErrorMsg();

    Map<String, String> paramsDisplayToCode(String tableName, Map<String, String> paramsDisplay);

    Map<String, String> paramsCodeToDisplay(String tableName, Map<String, String> paramsCode);

    List<Object> getDBTable(Integer funcType);

    List<Object> getObjectsByColumnCodeAndValue(String tableName, String paramCode, String paramValue);

    Object getColumnCodeByColumnDisplay(String tableName, String columnDisplay);

    List<Object> getObjectsByColumnCodeAndValue(String tableName, String paramCode1, String paramValue1, String paramCode2,
            String paramValue2);

    Integer updateObjects(String tableName, String columnCode, String columnValue, Map<String, String> paramsCode);

    Integer saveObjects(String tableName, Map<String, String> paramsCode);

    Object getColumnValueBySingleCondition(String tableName, String queryColumeCode, String condColumnCode, String condColumnValue);

    Integer deleteObject(String tableName, String columnCode, String columnValue);


}
