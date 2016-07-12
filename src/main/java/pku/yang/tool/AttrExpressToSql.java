package pku.yang.tool;

import java.util.ArrayList;
import java.util.List;

import pku.yang.model.DbTableName;

public class AttrExpressToSql {
	
	public AttrExpressToSql() {}
	
	private String kvTrans(String str,String tableName,String subTableName){
		
		String relative;
		String[] kvPair;
		
		if(str.split("=").length == 2){
			if(str.split(">").length == 2){
				relative = ">=";
				kvPair = str.split(">=");
			}else if(str.split("<").length == 2){
				relative = "<=";
				kvPair = str.split("<=");
			}else{
				relative = "=";
				kvPair = str.split("=");
			}
		}
		else if(str.split(">").length == 2){
			relative = ">";
			kvPair = str.split(">");
		}
		else if(str.split("<").length == 2){
			relative = "<";
			kvPair = str.split("<");
		}else if(str.split("<>").length == 2){
			relative = "!=";
			kvPair = str.split("<>");
		}else{
			return null;
		}
		
		kvPair[1] = kvPair[1].replace("'", "");
		
		Object columnName = SqlUtils.getColumnNameByColumnCode(tableName,kvPair[0]);
		
		if(!"".equals(columnName)){
			return  " (u."+ columnName + " " + relative +" '"+kvPair[1] +"')";
		}else{
			columnName = SqlUtils.getColumnNameByColumnCode(subTableName,kvPair[0]);
		}if(!"".equals(columnName)){
			return  " (t."+ columnName + " " + relative +" '"+kvPair[1] +"')";
		}else{
			String columnNameId = String.valueOf(SqlUtils.getColumnNameByColumnCode(tableName, "userID"));
			return " (u."+columnNameId+" = '-1')";
		}
	}
	
	public List<StringBuffer> getSqls(String columnCode,String columnValue){
		
		ArrayList<String> expressList = stringToList(columnValue);
		
		List<StringBuffer> sqls = new ArrayList<>();
		
		List<Object> dbTables = SqlUtils.getDBTable("1");
		if(dbTables.size() == 0 ){
			//setErrorMsg("50010", "获取访问控制表失败，请查看配置表是否配置正确！");
			return null;
		}
			
		DbTableName dbTableName = (DbTableName) dbTables.get(0);
		String dbName = String.valueOf(dbTableName.getDbName());
		String tableName = String.valueOf(dbTableName.getTableName());
		
		List<Object> subDbTables = SqlUtils.getDBTable("4");
		if(subDbTables.size() == 0 ){
			//setErrorMsg("50010", "获取访问控制表失败，请查看配置表是否配置正确！");
			return null;
		}

		for (Object subDbTable : subDbTables) {
			DbTableName subdbTableName = (DbTableName) subDbTable;
			String subDbName = String.valueOf(subdbTableName.getDbName());
			String subTableName = String.valueOf(subdbTableName.getTableName());

			StringBuffer sql = new StringBuffer("select u.pid From " + dbName + "." + tableName + " u," + subDbName + "." + subTableName + " t where ( ");
			String strTmp;
			for (String str : expressList) {
				switch (str) {
					case "#":
						break;
					case "$":
						sql.append(" or");
						break;
					case "&":
						sql.append(" and");
						break;
					case "!":
						sql.append(" not");
						break;
					case "(":
						sql.append(" (");
						break;
					case ")":
						sql.append(" )");
						break;
					default:

						strTmp = kvTrans(str, tableName, subTableName);
						if (strTmp == null) {
							System.out.println("属性表达式包含不支持的运算符，请核查！");
							return null;
						} else {
							sql.append(strTmp);
						}
				}
			}
			String columnName1 = String.valueOf(SqlUtils.getColumnNameByColumnCode(tableName, "userID"));
			String columnName2 = String.valueOf(SqlUtils.getColumnNameByColumnCode(subTableName, "id"));
			sqls.add(sql.append(" ) and u." + columnName1 + " = t." + columnName2));
		}
		return sqls;
	}
	
	private ArrayList<String> stringToList(String express){
		
		express = express.replaceAll("\\s", ""); 
		
		ArrayList<String> result = new ArrayList<>();
		String keyValue = "";
		for (int i = 0; i < express.length(); i++) {
			
			char ch = express.charAt(i);
			
			switch(ch){
				case '$': 
				case '&':
				case '!':
				case '(':
				case ')':
				case '#':
					if(!"".equals(keyValue)) result.add(keyValue);
					result.add(String.valueOf(ch));keyValue="";
					break;
				default : keyValue += express.charAt(i);
			}
		}
		return result;
	}
}
