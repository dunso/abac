package pku.yang.tool;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	
	public static String listToString(List<?> list){
		JSONArray jsonArray = new JSONArray();
		for(Object item : list){
			JSONObject jsondata = new JSONObject();
			Field[] fields = item.getClass().getDeclaredFields();
			for(int i= 0; i < fields.length; i++){
				try {
					String fieldName = fields[i].getName();
					String firstLetter = fieldName.substring(0, 1).toUpperCase();    
					String getter = "get" + firstLetter + fieldName.substring(1);    
					Method method = item.getClass().getMethod(getter);
					Object value = method.invoke(item);
					jsondata.put(fieldName, value);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			}
			jsonArray.add(jsondata);
			
		}
		return jsonArray.toJSONString();
	}

}
