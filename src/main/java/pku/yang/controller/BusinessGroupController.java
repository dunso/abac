package pku.yang.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pku.yang.dao.IBusinessGroupDao;
import pku.yang.dao.IUserDao;
import pku.yang.model.BusinessGroup;
import pku.yang.model.FreeGroup;
import pku.yang.model.Message;
import pku.yang.model.Space;
import pku.yang.model.Student;
import pku.yang.model.Teacher;
import pku.yang.model.User;
import pku.yang.service.IBusinessGroupService;
import pku.yang.service.IFolderService;
import pku.yang.service.IFreeGroupService;
import pku.yang.service.IMessageService;
import pku.yang.service.ISpaceService;
import pku.yang.service.ITokenService;
import pku.yang.service.IUserService;
import pku.yang.tool.*;


@Controller
@RequestMapping("/businessGroup")
public class BusinessGroupController {

	@Autowired
	private IBusinessGroupService businessGroup;
	@Autowired
	private ISpaceService spaceService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IFolderService folderService;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IMessageService messageservice;
	@Autowired
	private IFreeGroupService freeGroupService;


	
	@ResponseBody 
	@RequestMapping(value = "/ttt", method = RequestMethod.GET)
	public String ttt(
			@RequestParam String token
//			@RequestParam String uAttrs,
//			@RequestParam int storageId, 
//			@RequestParam String adminAttrs
			) {
	
		String result = businessGroup.checkIsAdmin("As6VkJPshb34jZ255nXEKw==", "014634824656000d");
	System.out.println(result);	
//		AttributeToSql adminAttributeToSql = new AttributeToSql();
//		adminAttributeToSql.getAdminIds("" , sessionFactory.getCurrentSession());
//		List<Student> list = sessionFactory.getCurrentSession().createSQLQuery("select * from student where courses='java' ").addEntity(Student.class).list();
//		userDao.excute("select * from user");
//		System.out.println(list.get(0).getCourses());
//		String groupId ="";
//		
//		String uid = DESUtil.getUidBytoken("As6VkJPshb2AfJediKXVbQ==");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = new Date();
//		String createDate = format.format(date);
//		
//		uid =  folderService.addRootFolder("123", "java", "1", createDate);
//		
//		userService.addUserGroup("1501211004", "3,4,5");
		
//		
//		List<String> list = new ArrayList<String>();
//			
//		String a = "";
//		String userGroups = userService.getUserGroupString("1501211004");
//
//		return userGroups + ",12";
		
//		return "";
//		
//		for(int i= 0; i< list.size();i++){
//			groupId += "," + list.get(i);
		
//		}
//		return groupId.substring(1);
		
		
//		return "123";
//		return spaceService.findById("402881fb54433ecd0154433ed4d00000").getRoot();
		
		
//		return	spaceService.addSpace("ttt", 23, "12");

		
		
		//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String ctime = df.format(new Date());
//		
//		businessGroup.addGroup(name,"3",22,adminAttrs,uAttrs,ctime);
		
		return "home";
	}
	
	
	
	
	
	@ResponseBody 
	@RequestMapping(value="getServiceGroupFile",method=RequestMethod.GET)
	public String getServiceGroupFile(@RequestParam String token){
		String uid = new String();
		try{
			
			uid = DESUtil.getUidBytoken(token);
			System.out.println(uid);
//			uid = "1501211004";
			
			String userGroups = userService.getUserGroupString(uid);
			
		
			JSONArray dorjsonarray = new JSONArray();
			
			JSONObject jsonData = new JSONObject();
			System.out.println(1);
			if(userGroups.isEmpty()){
				jsonData.put("code", 1);
				jsonData.put("data", dorjsonarray);	
				return jsonData.toJSONString();
			}else{
				String groups[] = userGroups.split(",");	
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String time = df.format(new Date());
				
				for(String group : groups){
					BusinessGroup bg = businessGroup.findGroupInfo(group);
					if(bg != null){
						JSONObject obj = new JSONObject();
						String sid = bg.getStorageId();
						obj.put("parentid", "1");
						obj.put("filename", bg.getName());
						obj.put("fileid", spaceService.findById(sid).getRoot());
						obj.put("date", time);
						obj.put("size", 0);
						obj.put("type", "folder");
						
						dorjsonarray.add(obj);			
					}
				}
				System.out.println(2);
				String storageId = userService.getStorageId(uid);
				JSONObject json = new JSONObject();
				json.put("parentid", "3");
				json.put("filename", "person space");
				json.put("fileid", spaceService.findById(storageId).getRoot());
				json.put("date", time);
				json.put("size", 0);
				json.put("type", "folder");
				dorjsonarray.add(json);
								
				List<Message> list=messageservice.search_user_fg(uid);
				
				JSONObject resultjson = new JSONObject();
				for (int i = 0; i < list.size(); i++) {
					JSONObject json1 = new JSONObject();
					String fg_id=list.get(i).getFg_id();
					json1.put("parentid", "2");
					FreeGroup fg= freeGroupService.search_fg_info(fg_id);
					json1.put("filename",fg.getFg_name());
					Space space=spaceService.findById(fg.getStorgeid());     //fg.getStorgeid()
					String rootid=space.getRoot();
					json1.put("fileid",rootid);
					json1.put("date", time);
					json1.put("size", 0);
					json.put("type", "folder");	
					dorjsonarray.add(json1);
				}				
				
				jsonData.put("code", 0);
				jsonData.put("data", dorjsonarray);
			
				return jsonData.toJSONString();	
			}
		}catch(Exception e){
			JSONObject jsonData = new JSONObject();
			jsonData.put("code", 1);
			jsonData.put("data", "user don't exit");
			
			return jsonData.toJSONString();	
			
		}
		
		
	}
	
	
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public String addGroup(
			Model model,
			HttpServletRequest request, 
			@RequestParam String name,
			@RequestParam String uAttrs,
			@RequestParam int storageSize, 
			@RequestParam String adminAttrs
			) {
	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = df.format(new Date());
		
		HashSet<String> set = new HashSet();
		String  uid = 	(String) request.getSession().getAttribute("sessionname");
		String  adminIds ="";
		String  uids ="";
					
		AttributeToSql adminAttributeToSql = new AttributeToSql();
		AttributeToSql userAttributeToSql = new AttributeToSql();
		
		String adminAttrss[] = adminAttrs.split("\\|\\|");
		for(int i = 0; i< adminAttrss.length;i++){
			if(adminAttrss[i] != null){
				if(adminAttributeToSql.check(adminAttrss[i])){
					String	sql = adminAttributeToSql.getSql();
					adminIds += "," + adminAttributeToSql.getAdminIds(sql,sessionFactory.getCurrentSession());
				}else{
					// 输入错误
					model.addAttribute("errorMessage","admin attribute input error");
					return "error";
				}
			}
		}
		adminIds = adminIds.substring(1);
		
		String uAttrss[] = uAttrs.split("\\|\\|");
		for(int i = 0 ; i< uAttrss.length;i++){
			if(uAttrss[i]!=null){
				if(userAttributeToSql.check(uAttrss[i])){
					String 	sql = userAttributeToSql.getSql();
					uids += "," + adminAttributeToSql.getUids(sql,sessionFactory.getCurrentSession());
				}else{
					// 输入错误
					model.addAttribute("errorMessage","normal user attribute input error");
					return "error";
				}	
			}
		}
		uids = uids.substring(1);
	
		String FolderId =  folderService.addRootFolder(uid, name, "1", ctime);
		String storageId = spaceService.addSpace(name, storageSize, FolderId);
		String groupId =  businessGroup.addGroup(name,adminIds,storageId,adminAttrs,uAttrs,uids,ctime);
		
		for(String u : adminIds.split(",")){
			if(!set.contains(u)){
				set.add(u);
			}
		}
		
		for(String u: uids.split(",")){
			if(!set.contains(u)){
				set.add(u);
			}
		}
		Iterator<String> iterator=set.iterator();
		while(iterator.hasNext()){
			String u = iterator.next();
			System.out.println(u);
			String userGroups = userService.getUserGroupString(u);
			System.out.println(userGroups);
			if( userGroups == null ){
				userGroups = groupId;
			}else{
				userGroups += "," + groupId;
			}
			userService.addUserGroup(u, userGroups);
		}

		return "home";
	}
	
	
	
	

	@RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
	public String updateGroup(
			Model model,
			@RequestParam String id,
			@RequestParam String name,
			@RequestParam String uAttrs,
			@RequestParam String storageId, 
			@RequestParam String adminAttrs
			) {
		
		BusinessGroup groupModel = businessGroup.findGroupInfo(id);
			
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String utime = df.format(new Date());
	
		HashSet<String> set = new HashSet();
		String  adminIds ="";
		String  uids ="";
					
		AttributeToSql adminAttributeToSql = new AttributeToSql();
		AttributeToSql userAttributeToSql = new AttributeToSql();
		
		String adminAttrss[] = adminAttrs.split("\\|\\|");
		for(int i = 0; i< adminAttrss.length;i++){
			if(adminAttrss[i] != null){
				if(adminAttributeToSql.check(adminAttrss[i])){
					String	sql = adminAttributeToSql.getSql();
					adminIds += "," + adminAttributeToSql.getAdminIds(sql,sessionFactory.getCurrentSession());
				}else{
					// 输入错误
					model.addAttribute("errorMessage","admin attribute input error");
					return "error";
				}
			}
		}
		adminIds = adminIds.substring(1);
		
		String uAttrss[] = uAttrs.split("\\|\\|");
		for(int i = 0 ; i< uAttrss.length;i++){
			if(uAttrss[i]!=null){
				if(userAttributeToSql.check(uAttrss[i])){
					String 	sql = userAttributeToSql.getSql();
					uids += "," + adminAttributeToSql.getUids(sql,sessionFactory.getCurrentSession());
				}else{
					// 输入错误
					model.addAttribute("errorMessage","normal user attribute input error");
					return "error";
				}	
			}
		}
		uids = uids.substring(1);
		
		
		String oldAdminId = groupModel.getAdminId();
		String oldUid = groupModel.getUids();

		groupModel.setName(name);
		groupModel.setUAttrs(uAttrs);
		groupModel.setAdminAttrs(adminAttrs);
		groupModel.setUptime(utime);
		groupModel.setAdminId(adminIds);
		groupModel.setUids(uids);
		businessGroup.saveGroup(groupModel);
		
		//在旧用户中删除groupId
		String[] oldAdminIds = oldAdminId.split(",");
		for(String uid : oldAdminIds){
			String oldGroupId = userService.getUserGroupString(uid);
			if(oldGroupId != null || oldGroupId !="" || oldGroupId != "NULL"){
				String tem = oldGroupId.replaceAll(","+id, "");	
				if(tem == oldGroupId){
					oldGroupId = oldGroupId.replaceAll(id, "");	
				}
			}
			userService.addUserGroup(uid, oldGroupId);
		}
		String[] oldUids = oldUid.split(",");
		for(String uid : oldUids){
			String oldGroupId = userService.getUserGroupString(uid);
			if(oldGroupId !="" && oldGroupId !=null && oldGroupId != "NULL"){
				System.out.println(oldGroupId + "ADF");
				String tem = oldGroupId.replaceAll(","+id, "");	
				if(tem == oldGroupId){
					oldGroupId = oldGroupId.replaceAll(id, "");	
				}
			}
			
			userService.addUserGroup(uid, oldGroupId);
		}
		
		//在新用户中添加groupId
		for(String u : adminIds.split(",")){
			if(!set.contains(u)){
				set.add(u);
			}
		}
		
		for(String u: uids.split(",")){
			if(!set.contains(u)){
				set.add(u);
			}
		}
		Iterator<String> iterator=set.iterator();
		while(iterator.hasNext()){
			String u = iterator.next();
			System.out.println(u);
			String userGroups = userService.getUserGroupString(u);
			System.out.println(userGroups);
			if(userGroups =="" || userGroups == null ||userGroups=="NULL"){
				userGroups = id;
			}else{
				userGroups += "," + id;
			}
			userService.addUserGroup(u, userGroups);
		}
		
		return "home";
	}

	@ResponseBody 
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String getData( ) {
	
		List<BusinessGroup> list = businessGroup.getBussinessGroupList();
		
		JSONArray dorjsonarray = new JSONArray();
		for(int i=0;i<list.size();i++){
			JSONObject jsonData = new JSONObject();
//			jsonData.put("id",list.get(i).getID());
			jsonData.put("name", list.get(i).getName());
			jsonData.put("adminAttr", list.get(i).getAdminAttrs());
			jsonData.put("uAttr", list.get(i).getUAttrs());
//			jsonData.put("storageId", list.get(i).getStorageId());
			jsonData.put("ctime", list.get(i).getCtime());
			jsonData.put("utime", list.get(i).getUtime());
			
			String operation = "<a href = 'update?id="+list.get(i).getID() + "'>edit</a>   <a href = 'delete?id="+list.get(i).getID() + "'>delete</a>";
			jsonData.put("operation", operation);
			dorjsonarray.add(jsonData);
		}
		
		return dorjsonarray.toJSONString();

	}

	
	@RequestMapping(value ="/getList" , method = RequestMethod.GET)
	public String getList(){
		
		return "groupmanage/list_group";
	}
	
	
	@RequestMapping(value ="/update" , method = RequestMethod.GET)
	public String update(Model model,@RequestParam String id){
		
		BusinessGroup groupModel = businessGroup.findGroupInfo(id);
		model.addAttribute("businessGroup",groupModel);
		
		return "groupmanage/update_group";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteFolder(@RequestParam String id) {
		businessGroup.deleteGroup(id);
		return "groupmanage/list_group";
	}


}
