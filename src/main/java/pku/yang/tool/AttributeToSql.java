package pku.yang.tool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pku.yang.model.Student;
import pku.yang.model.Teacher;


public class AttributeToSql {

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	private  static HashSet<String> studentSet = new HashSet(Arrays.asList("age", "number","name","courses","department","teacher","group","academy")) ;
	
	private  static HashSet<String> teacherSet = new HashSet(Arrays.asList("age", "number","name","courses","department","duty","group","title")) ;

	private  static HashMap<String,String> studentMap = new HashMap<String, String>(){{
        put("number", "student_id");  
        put("age", "age");  
        put("name", "student_name");  
        put("courses", "courses");  
        put("group", "study_group");  
        put("teacher", "teacher_id");  
        put("academy", "academy");  
        put("department", "department");  
        
	}};
	
	private  static HashMap<String,String> teacherMap = new HashMap<String, String>(){{
        put("number", "teacher_id");  
        put("age", "age");  
        put("name", "teacher_name");  
        put("courses", "courses");  
        put("group", "study_group");  
        put("title", "title");  
        put("academy", "academy");  
        put("department", "department");  
        
	}};
	public   HashMap<String,String> map = new HashMap();
	
	public   boolean check(String attribute){
		
//	String studentAttribute =  "type=student;age=30;number=14000;name=zhangsan;courses=java;department=kewu;academy=kewu;group=kewu;teacher=yangyahui";
//	String teacherAttribute =  "type=teacher;age=30;number=14000;name=zhangsan;department=kewu;courses=java;duty=123;group=234;title=jiaoshou";	
		
	String	studentAttribute = attribute;
	String[] attributes= studentAttribute.split(";");
	
	for(String s : attributes){
		String[] ss = s.split("=");
		if(ss.length<2){
			System.out.println("error");
			return false;
		}else{
			this.map.put(ss[0],ss[1]);	
		}
	}
	
	if(this.map.get("type").equals("student")){
		for(String key :this.map.keySet()){
			if(key.equals("type")){
				continue;
			}
			
		if(!studentSet.contains(key)){
			System.out.println(key);
			return false;
		}
		}
	}else if(this.map.get("type").equals("teacher")){
		for(String key :this.map.keySet()){
			if(key.equals("type")){
				continue;
			}
			
		if(!teacherSet.contains(key)){
			System.out.println(key);
			return false;
		}
		}
	}else{
		System.out.println(this.map.get("type"));
		return false;
	}
	
	return true;
	}
	
	
	public String getSql(){
		
		StringBuffer sql = new StringBuffer();
		
		String type = this.map.get("type");
		if(type.equals("student")){
			sql.append("select * from student where ");	
		}else{
			sql.append("select * from teacher where ");
		}
		for(String key : this.map.keySet()){
			
			if(key.equals("type")){
				continue;
				
			}else{
				if(type.equals("student")){
					sql.append(studentMap.get(key) + "='" + this.map.get(key) + "'" + " AND ");	
				}else{
					sql.append(teacherMap.get(key) + "= '" + this.map.get(key) +"'"+ " AND ");
				}	
			}
		}
	
		return sql.toString().substring(0,sql.toString().length()-4);
	}
	
	public String getUids(String sql , Session session ){
		String uids = "";
		String[] tmp = sql.split(" ");
		String table = tmp[3];
		if(table.equals("student")){
			List<Student> list = session.createSQLQuery(sql).addEntity(Student.class).list();
			for(int i=0; i< list.size();i++){
				uids += list.get(i).getId() +",";
			}
		}else if(table.equals("teacher")){
			List<Teacher> list = session.createSQLQuery(sql).addEntity(Teacher.class).list();
			for(int i=0; i< list.size();i++){
				uids += list.get(i).getId() +",";
			}
		}else{	
			//类型输入错误
		}
		if(uids.length()>1)
			uids = uids.substring(0, uids.length()-1);
		
		return uids;
	}
	
	public String getAdminIds(String sql , Session session){
		
		String adminIds = "";
		String[] tmp = sql.split(" ");
		String table = tmp[3];
		if(table.equals("student")){
			System.out.println(sql);
			List<Student> list = session.createSQLQuery("select * from student where courses='java'").addEntity(Student.class).list();
			for(int i=0; i< list.size();i++){
				adminIds += list.get(i).getId() +",";
			}
		}else if(table.equals("teacher")){
			List<Teacher> list = session.createSQLQuery(sql).addEntity(Teacher.class).list();
			for(int i=0; i< list.size();i++){
				adminIds += list.get(i).getId() +",";
			}
		}else{	
			//类型输入错误
		}
		
		if(adminIds.length()>1)
		adminIds = adminIds.substring(0, adminIds.length()-1);
		
		return adminIds;
	}
	
	
	
	
	
	
	public static void main(String[] args){
		
		AttributeToSql attributeToSql = new AttributeToSql();
		
		if(attributeToSql.check(null)){
		String sql = attributeToSql.getSql();
		System.out.println(123);
		System.out.println(sql);
		}else{
			System.out.println(222);
		}
		
		
	}
}









