package pku.yang.service.imp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import pku.yang.dao.IABACDao;
import pku.yang.model.DbTableName;
import pku.yang.model.Table1;
import pku.yang.model.Table2;
import pku.yang.model.User;
import pku.yang.service.IABACService;
import pku.yang.service.IBusinessGroupService;
import pku.yang.tool.AttrExpressToSql;
import pku.yang.tool.DESUtil;
import pku.yang.tool.PropertiesUtils;
import pku.yang.tool.SqlUtils;

/**
 * 访问控制服务层
 * 
 * @author chenbin
 * @Date 20160113
 * @Operate create
 * @address Peking University
 */

@Service
public class ABACService implements IABACService {

    @Override
    public String queryAccess(String token, String funcName, Map<String, String> paramsDisplay, String privilege) {
        if (beforeAction(funcName, paramsDisplay) == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }
        Map<String, String> privilegesMap = new HashMap<>();

        if ("1".equals(businessGroupService.checkIsAdmin(token, paramsCode.get("column2")))) {
            privilegesMap = objectToMap(new Table2(), relativeTableName, privilegesMap, "1");
            if (privilegesMap == null) {
                errorMsg.putAll(abacDao.getErrorMsg());
                return null;
            }
        } else {

            User user = getUserByToken(token);
            if (user == null) {
                errorMsg.putAll(abacDao.getErrorMsg());
                return null;
            }
            privilegesMap = getPrivileges(user, relativeTableName, paramsCode, privilege, "0");
            if (privilegesMap == null) {
                errorMsg.putAll(abacDao.getErrorMsg());
                return null;
            }
        }
        if (privilege != null) {
            privilegesMap = abacDao.paramsCodeToDisplay(relativeTableName, privilegesMap);
            return privilegesMap.get(privilege);
        } else {
            return JSONObject.fromObject(abacDao.paramsCodeToDisplay(relativeTableName, privilegesMap)).toString();
        }
    }

    public String queryPolicy(String token, String funcName, Map<String, String> paramsDisplay) {

        if (beforeAction(funcName, paramsDisplay) == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }

        List<Map<String, String>> policyResult = new ArrayList<>();
        List<String> attrExpressResult = new ArrayList<>();

        boolean isAdmin = false;
        if ("1".equals(businessGroupService.checkIsAdmin(token, paramsCode.get("column2")))) {
            isAdmin = true;
        }
        User user = getUserByToken(token);
        if (user == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }
        List<Map<String, String>> policys = getPolicys(user, relativeTableName, paramsCode, isAdmin);
        for (Map<String, String> policy : policys) {
            if ("${queryPolicy}".equals(funcName)) {
                policyResult.add(abacDao.paramsCodeToDisplay("table2", policy));
            } else if ("${queryAttrExpress}".equals(funcName)) {
                attrExpressResult.add(policy.get("column1"));
            }
        }
        if ("${queryPolicy}".equals(funcName)) {
            return JSONArray.fromObject(policyResult).toString();
        } else if ("${queryAttrExpress}".equals(funcName)) {
            return JSONArray.fromObject(attrExpressResult).toString();
        }
        return "";
    }



    @Override
    @Transactional
    public Integer insertPolicy(String token, String funcName, Map<String, String> paramsDisplay1, Map<String, String> paramsDisplay2) {

        if (beforeAction(funcName, paramsDisplay1) == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }

        Map<String, String> paramsCode2 = abacDao.paramsDisplayToCode(relativeTableName, paramsDisplay2);
        if (paramsCode2 == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }

        if ("0".equals(businessGroupService.checkIsAdmin(token, paramsCode.get("column2")))) {
            setErrorMsg("40006", "无权限执行该操作");
            return null;
        } else {
            String table2id = paramsCode2.get("table2id");
            Table1 table1;

            if (!Strings.isNullOrEmpty(table2id) && "${modifyPolicy}".equals(funcName)) {
                if (abacDao.updateObjects(relativeTableName, "table2id", table2id, paramsCode2) == 0) {
                    errorMsg.putAll(abacDao.getErrorMsg());
                    errorMsg.putAll(SqlUtils.getErrorMsg());
                    return 0;
                }
                List<Object> entities = abacDao.getObjectsByColumnCodeAndValue(tableName, "table2id", table2id);
                if (entities.size() > 0) {
                    table1 = (Table1) entities.get(0);
                    return abacDao.updateObjects(tableName, "table1id", String.valueOf(table1.getTable1id()), paramsCode);
                }

            } else {
                Table2 table2;
                paramsCode2.remove("table2id");
                Integer table2idTmp = abacDao.saveObjects(relativeTableName, paramsCode2);
                if (table2idTmp <= 0) {
                    errorMsg.putAll(abacDao.getErrorMsg());
                    errorMsg.putAll(SqlUtils.getErrorMsg());
                    return 0;
                }
                //List<Object> entities = SqlUtils.getObjectsByParams(relativeTableName, paramsCode2);

                //table2 = (Table2) (entities.size() == 0 ? null : entities.get(0));
                if (table2idTmp > 0) {
                    paramsCode.put("table2id", String.valueOf(table2idTmp));
                    return abacDao.saveObjects(tableName, paramsCode);
                }
            }
        }
        return 0;
    }


    @Override
    @Transactional
    public Integer deletePolicy(String token, String funcName, Map<String, String> paramsDisplay1, Map<String, String> paramsDisplay2) {

        if (beforeAction(funcName, paramsDisplay1) == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }

        Map<String, String> paramsCode2 = abacDao.paramsDisplayToCode(relativeTableName, paramsDisplay2);
        if (paramsCode2 == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }

        if ("0".equals(businessGroupService.checkIsAdmin(token, paramsCode.get("column2")))) {
            setErrorMsg("40006", "无权限执行该操作");
            return null;
        } else {
            Object o = abacDao.getColumnValueBySingleCondition(tableName, "table1id", "table2id", paramsCode2.get("table2id"));
            if (o == null) {
                setErrorMsg("50007", "数据库中无相应数据");
                return null;
            }
            String table1id = (String) o;
            if (abacDao.deleteObject(tableName, "table1id", table1id) > 0) {
                return SqlUtils.deleteObject(relativeTableName, "table2id", paramsCode2.get("table2id"));
            }
        }
        return 0;
    }

    @Override
    public String conflictdetection(String token, String funcName, Map<String, String> paramsDisplay1, Map<String, String> paramsDisplay2) {

        if (beforeAction(funcName, paramsDisplay1) == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }

        Map<String, String> paramsCode2 = abacDao.paramsDisplayToCode(relativeTableName, paramsDisplay2);
        if (paramsCode2 == null) {
            errorMsg.putAll(abacDao.getErrorMsg());
            return null;
        }

        Map<String, List<String>> conflictMap = new HashMap<>();

        if ("0".equals(businessGroupService.checkIsAdmin(token, paramsCode.get("column2")))) {
            setErrorMsg("40006", "无权限执行该操作");
            return null;
        } else {
            String attrExpress = paramsCode2.get("column1");

            List<Object> o1List = getObjectsByColumnCodeAndValue("column1", paramsCode2.get("column1"));

            List<Object> entities = abacDao.getObjectsByColumnCodeAndValue(tableName, "column1", paramsCode.get("column1"), "column2",
                    paramsCode.get("column2"));

            for (Object entity : entities) {

                Table1 table1 = (Table1) entity;
                Table2 table2 = table1.getTable2();

                if(String.valueOf(table2.getTable2id()).equals(paramsCode2.get("table2id"))){
                    continue;
                }

                List<Object> o2List = getObjectsByColumnCodeAndValue("column1", table2.getColumn1());
                for (Object o2 : o2List) {

                    List<String> privilegeConflictList;

                    String oldUser_pid = (String) o2;

                    for (Object o1 : o1List) {
                        String user_pid = (String) o1;

                        if (!user_pid.equals(oldUser_pid)) {
                            continue;
                        }

                        privilegeConflictList = privilegeConflict(paramsCode2, table2);

                        if (privilegeConflictList.size() > 0) {
                            conflictMap.put(table2.getTable2id() + "", privilegeConflictList);
                        }


                    }
                }
            }

        }

        return JSONObject.fromObject(conflictMap).toString();
    }


    private List<Object> getObjectsByColumnCodeAndValue(String columnCode, String columnValue) {
        List<StringBuffer> sqls = attrExpressToSql.getSqls(columnCode, columnValue);
        List<Object> resultList = new ArrayList<Object>();
        for (int i = 0; i < sqls.size(); i++) {
            String sql = sqls.get(i).toString();
            List<Object> o = SqlUtils.getObjects(sql, null);
            if (o != null && o.size() > 0) {
                resultList.addAll(o);
            }
        }
        return resultList;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<String> privilegeConflict(Map<String, String> paramsCode2, Object o) {

        List<String> privilegeConflictList = new ArrayList<String>();
        Class clazz = (Class<Table2>) o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String propertyName = "";
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            propertyName = String.valueOf(fields[i]);
            propertyName = propertyName.substring(propertyName.lastIndexOf(".") + 1);
            try {
                Object value = fields[i].get(o);
                if (value != null) {
                    if ("1".equals(SqlUtils.getFlagORIsValid("table2", "flag", "column_code", propertyName))) {
                        if (!value.equals(paramsCode2.get(propertyName))) {
                            privilegeConflictList.add(SqlUtils.getColumnDisplayByColumnCode(relativeTableName, propertyName).toString());
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return privilegeConflictList;
    }

    private Map<String, String> getPrivileges(User user, String relativeTableName, Map<String, String> paramsCode, String privilege,
            String isAdmin) {

        Map<String, String> privilegesMap = new HashMap<>();
        List<Object> entities = abacDao.getObjectsByColumnCodeAndValue(tableName, "column1", paramsCode.get("column1"), "column2",
                paramsCode.get("column2"));

        for (Object entity : entities) {

            Table1 table1 = (Table1) entity;
            Table2 table2 = table1.getTable2();

            List<Object> oList = getObjectsByColumnCodeAndValue("column1", table2.getColumn1());
            for (Object o : oList) {
                String user_pid = (String) o;

                if (!user_pid.equals(user.getUser_pid())) {
                    continue;
                }
                privilegesMap = objectToMap(table2, relativeTableName, privilegesMap, isAdmin);
                if (privilege != null) {
                    String columnName = String.valueOf(abacDao.getColumnCodeByColumnDisplay(relativeTableName, privilege));
                    if ("1".equals(privilegesMap.get(columnName))) {
                        return privilegesMap;
                    }
                }
            }
        }
        return privilegesMap;
    }

    private List<Map<String, String>> getPolicys(User user, String relativeTableName, Map<String, String> paramsCode, Boolean isAdmin) {
        List<Map<String, String>> policys = new ArrayList<>();
        List<Object> entities = abacDao.getObjectsByColumnCodeAndValue(tableName, "column1", paramsCode.get("column1"), "column2",
                paramsCode.get("column2"));

        for (Object entity : entities) {
            Table1 table1 = (Table1) entity;
            Table2 table2 = table1.getTable2();
            List<Object> oList = getObjectsByColumnCodeAndValue("column1", table2.getColumn1());

            for (Object o : oList) {
                String user_pid = (String) o;

                if (!user_pid.equals(user.getUser_pid()) && !isAdmin) {
                    continue;
                }
                Map<String, String> policy = new HashMap<String, String>();
                if ("${queryAttrExpress}".equals(funcName)) {
                    policy.put("column1", table2.getColumn1() + "");
                } else if ("${queryPolicy}".equals(funcName)) {
                    policy = objectToMap(table2, relativeTableName, policy, null);
                }
                if (!policys.contains(policy)) {
                    policys.add(policy);
                }
                break;
            }
        }
        return policys;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Map<String, String> objectToMap(Object object, String relativeTableName, Map<String, String> objectMap, String isAdmin) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String propertyName;

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            propertyName = String.valueOf(fields[i]);
            propertyName = propertyName.substring(propertyName.lastIndexOf(".") + 1);
            try {
                Object value = fields[i].get(object);
                if ("${queryAccess}".equals(funcName)) {
                    if (flagIs1Map.get(propertyName) == null
                            && "1".equals(SqlUtils.getFlagORIsValid(relativeTableName, "flag", "column_code", propertyName))) {
                        flagIs1Map.put(propertyName, propertyName);
                    }
                    if (flagIs1Map.get(propertyName) == null) {
                        continue;
                    }
                    if ("1".equals(fields[i].get(object))) {
                        objectMap.put(propertyName, "1");
                    } else if (!"1".equals(objectMap.get(propertyName))) {
                        objectMap.put(propertyName, "0");
                        if ("1".equals(isAdmin)) {
                            objectMap.put(propertyName, "1");
                        }
                    }
                } else if ("${queryPolicy}".equals(funcName) || "${queryAttrExpress}".equals(funcName)) {
                    objectMap.put(propertyName, value == null ? "" : String.valueOf(value));
                }

            } catch (Exception e) {
                setErrorMsg("40004", "反射失败");
                return null;
            }
        }
        return objectMap;
    }

    /**
     * 可变部分（需要抽象）
     * 
     * @param token
     * @return
     */
    private User getUserByToken(String token) {
        String userID;
        try {
            userID = DESUtil.getUidBytoken(token);
            List<Object> entity = abacDao.getObjectsByColumnCodeAndValue("user", "userID", userID);
            if (CollectionUtils.isEmpty(entity)) {
                setErrorMsg("40005", "无效的Token");
                return null;
            } else {
                return (User) entity.get(0);
            }
        } catch (Exception e) {
            setErrorMsg("40005", "Token参数错误，无法解析");
        }
        return null;
    }

    @Autowired
    private IBusinessGroupService businessGroupService;
    @Autowired
    private IABACDao abacDao;
    @Autowired
    private PropertiesUtils propertiesUtils;

    private AttrExpressToSql attrExpressToSql = new AttrExpressToSql();

    public ABACService() {}

    private Map<String, String> errorMsg = new HashMap<String, String>();
    private Integer dbTableType = null;
    private String dbName = null;
    private String tableName = null;
    private String relativeDBName = null;
    private String relativeTableName = null;
    private String funcName = null;
    private Map<String, String> paramsCode = null;
    private static Map<String, String> flagIs1Map = new HashMap<>();

    private Integer beforeAction(String funcName, Map<String, String> paramsDisplay) {
        errorMsg.clear();
        abacDao.getErrorMsg().clear();
        Integer dbTableType = initDBTable(funcName);
        if (dbTableType == null) {
            return null;
        }
        paramsCode = abacDao.paramsDisplayToCode(tableName, paramsDisplay);
        if (paramsCode == null) {
            return null;
        }
        return dbTableType;
    }

    private Integer initDBTable(String funcName) {
        this.funcName = funcName;
        String dbTableTypeStr = propertiesUtils.getPropertiesValue(funcName);
        if (dbTableTypeStr == null) {
            setErrorMsg("50003", "获取表类型" + (funcName.substring(2, funcName.length() - 1)) + "失败，请查看配置文件是否配置正确！");
            return null;
        }
        dbTableType = Integer.parseInt(dbTableTypeStr);
        List<Object> dbTables = abacDao.getDBTable(dbTableType);
        if (dbTables.size() == 0) {
            setErrorMsg("50002", "获取数据库名称或表名称失败，请查看配置表是否配置正确！");
            return null;
        }
        DbTableName dbTableName = (DbTableName) dbTables.get(0);
        this.dbName = String.valueOf(dbTableName.getDbName());
        this.tableName = String.valueOf(dbTableName.getTableName());

        List<Object> relativeDBTables = abacDao.getDBTable(dbTableType + 1);
        if (relativeDBTables.size() == 0) {
            setErrorMsg("50002", "获取数据库名称或表名称失败，表类型为" + dbTableType + 1 + ",请查看配置文件和配置表是否配置正确！");
            return null;
        }
        DbTableName localDBTableName = (DbTableName) relativeDBTables.get(0);
        relativeDBName = String.valueOf(localDBTableName.getDbName());
        relativeTableName = String.valueOf(localDBTableName.getTableName());
        return dbTableType;
    }


    public String checkParams(Map<String, String> paramsDisplay) {
        errorMsg.clear();
        Iterator<Map.Entry<String, String>> entities = paramsDisplay.entrySet().iterator();
        while (entities.hasNext()) {
            Map.Entry<String, String> entity = entities.next();
            if ("".equals(entity.getKey())) {
                setErrorMsg("40001", "请求参数错误，该参数名" + entity.getKey() + "书写错误");
                return null;
            } else if (Strings.isNullOrEmpty(entity.getValue())
                    && ("groupId".equals(entity.getKey()) || "fileFloderId".equals(entity.getKey()) || "token".equals(entity.getKey()))) {
                setErrorMsg("40001", "请求参数错误，参数" + entity.getKey() + "值为空");
                return null;
            }
        }
        return "";
    }

    public Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String code, String msg) {
        this.errorMsg.put(code, msg);
    }
}
