package pku.yang.dao.imp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IABACDao;
import pku.yang.tool.SqlUtils;


/**
 * 访问控制Dao
 * @author  chenbin
 * @Date    20160113
 * @Operate create
 * @address Peking University
 */
@Repository
public class ABACDao implements IABACDao{
	

	private Map<String,String> errorMsg = new HashMap<String, String>();
	
	public Map<String,String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String code,String msg) {
		this.errorMsg.put(code, msg);
	}

	public void clear(){
		this.errorMsg.clear();
	}

	public Map<String,String> paramsDisplayToCode(String tableName,Map<String,String> paramsDisplay){
		Map<String,String> paramsCode = new HashMap<>();
		Iterator<Map.Entry<String, String>> entities = paramsDisplay.entrySet().iterator();
		while (entities.hasNext()) {
			Map.Entry<String, String> entity = entities.next();
			String cloumCode = String.valueOf(SqlUtils.getColumnCodeByColumnDisplay(tableName, entity.getKey()));
			if(cloumCode == null){
				setErrorMsg("50004","获取代码列失败!列名称为"+entity.getKey()+",请查看配置表是否配置正确");
			}
			paramsCode.put(cloumCode, entity.getValue());
		}
		return paramsCode;
	}


	public Map<String,String> paramsCodeToDisplay(String tableName,Map<String,String> paramsCode){
		Map<String,String> paramsDisplayl = new HashMap<String, String>();
		Iterator<Map.Entry<String, String>> entries = paramsCode.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = entries.next();
			String cloumDisplay = String.valueOf(SqlUtils.getColumnDisplayByColumnCode(tableName, entry.getKey()));
			paramsDisplayl.put(cloumDisplay, entry.getValue());
		}
		return paramsDisplayl;
	}

	public  List<Object> getDBTable(Integer dbTableType){
		return SqlUtils.getDBTable(String.valueOf(dbTableType));
	}

	public List<Object> getObjectsByColumnCodeAndValue(String tableName, String paramCode,String paramValue){
		return SqlUtils.getObjectsByColumnCodeAndValue(tableName,paramCode, paramValue);

	}

	public List<Object> getObjectsByColumnCodeAndValue(String tableName, String paramCode1, String paramValue1, String paramCode2,
														 String paramValue2){
		return SqlUtils.getObjectsByColumnCodeAndValue(tableName, paramCode1,paramValue1,paramCode2, paramValue2);
	}

	public Object getColumnDisplayByColumnCode(String tableName, String columnCode){
		return SqlUtils.getColumnDisplayByColumnCode(tableName,columnCode);
	}

	public Object getColumnCodeByColumnDisplay(String tableName, String columnDisplay){
		return SqlUtils.getColumnCodeByColumnDisplay(tableName,columnDisplay);
	}

	public Integer updateObjects(String tableName, String columnCode, String columnValue, Map<String, String> paramsCode){
		return SqlUtils.updateObjects(tableName, columnCode, columnValue, paramsCode);
	}

	public Integer saveObjects(String tableName, Map<String, String> paramsCode){
		return SqlUtils.saveObjects(tableName,paramsCode);
	}

	public Object getColumnValueBySingleCondition(String tableName, String queryColumeCode, String condColumnCode,
														 String condColumnValue){
		return SqlUtils.getColumnValueBySingleCondition(tableName, queryColumeCode, condColumnCode, condColumnValue);
	}

	public Integer deleteObject(String tableName, String columnCode, String columnValue){
		return SqlUtils.deleteObject(tableName, columnCode, columnValue);
	}






}
