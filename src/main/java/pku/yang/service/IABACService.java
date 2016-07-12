package pku.yang.service;

import java.util.List;
import java.util.Map;

import pku.yang.model.ABACParams;

/**
 * 访问控制服务层接口
 * @author  chenbin
 * @Date    20160113
 * @Operate create
 * @address Peking University
 */

public interface IABACService {

	String queryAccess(String token,String funcName,Map<String,String> paramsDisplay,String privilege);
	
	String queryPolicy(String token,String funcType,Map<String,String> paramsDisplay);

	Integer insertPolicy(String token,String funcName,Map<String,String> paramsDisplay1, Map<String,String> paramsDisplay2);

	Integer deletePolicy(String token,String funcName,Map<String,String> paramsDisplay1, Map<String,String> paramsDisplay2);

	String conflictdetection(String token,String funcName,Map<String,String> paramsDisplay1, Map<String,String> paramsDisplay2);

	Map<String,String> getErrorMsg();

	String checkParams(Map<String, String> paramsDisplay);

}
