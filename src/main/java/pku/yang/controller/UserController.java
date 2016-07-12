package pku.yang.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

import pku.yang.model.Student;
import pku.yang.model.Teacher;
import pku.yang.model.User;
import pku.yang.model.Token;
import pku.yang.service.IFolderService;
import pku.yang.service.ISpaceService;
import pku.yang.service.ITokenService;
import pku.yang.service.IUserService;
import pku.yang.tool.Pagination;
import pku.yang.tool.DESUtil;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ISpaceService spaceService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IFolderService folderService;
	@Autowired
	private ITokenService tokenService;

	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLogin(Model model, String id, String password,
			HttpServletRequest request) {
		User user;
		try {
			
			if(id.equals("admin")&&password.equals("123456")){
				request.getSession().setAttribute("sessionname", id);
				return "home";
			}else{
				user = userService.login(id, password);
				
				if(user.getRoleNum()==1){
					return "loginError";
				}
				model.addAttribute(user);
				request.getSession().setAttribute("sessionname", id);
							
				return "home";	
			}
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
	
	@ResponseBody 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login( String id, String password,
			HttpServletRequest request) {
		User user;
		try {
			user = userService.login(id, password);
			if(user.getRole().isCommonUser||user.getRole().isGroupMng){
				request.getSession().setAttribute("sessionname", id);
				
				JSONArray jsonarray = new JSONArray();
				
					String tken  = DESUtil.encrypt(id);
					
					jsonarray.add(tken);
					JSONObject jsonData = new JSONObject();
					
					jsonData.put("code",0);
					jsonData.put("data",jsonarray);
					
				return jsonData.toString();
			}else{
				
				JSONObject jsonData = new JSONObject();
				
				jsonData.put("code",1);
				jsonData.put("data","密码错误!");
				
				return  jsonData.toString();
			}

		} catch (Exception e) {
		
			return "error";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, String id, String password,
			HttpServletRequest request) {
	
			request.getSession().removeAttribute("sessionname");
			
			return "../login";
		
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("readonly", false);
		return "usermanage/add_user";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		return this.listUser(model, "", "", "", 1, 10);
	}

	/**
	 * find users by query
	 * @param type
	 * @param name
	 * @param userID
	 * @param page
	 * @param size
	 * @return user list
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listUser(Model model, @RequestParam(defaultValue = "") String type,
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String userID,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int pagesize) {
		Pagination<User> users;
		try {
			users = userService.findUsers(type, userID, name, page, pagesize);
			model.addAttribute("userList", users.getList());
			model.addAttribute("pagenum", users.getPagenum());
			model.addAttribute("pagesize", users.getPagesize());
			model.addAttribute("page", users.getPage());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("type", type);
		model.addAttribute("name", name);
		model.addAttribute("userID",userID);
		return "usermanage/list_user";
	}
	
	@RequestMapping(value = "/edit/{userID}/{type}", method = RequestMethod.GET)
	public String editUser(Model model, @PathVariable String userID,
			@PathVariable String type){
		if (type.equals("0")) {
			Student student = userService.findStudentInfo(userID);
			model.addAttribute("user", student);
		} else if (type.equals("1")) {
			Teacher teacher = userService.findTeaherInfo(userID);
			model.addAttribute("user", teacher);
		}
		model.addAttribute("type", type);
		return "usermanage/edit_user";
	}

	/**
	 * get user info by userID and user's type
	 * 
	 * @param userID
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/info/{userID}/{type}", method = RequestMethod.GET)
	public String UserInfo(Model model, @PathVariable String userID,
			@PathVariable String type) {
		if (type.equals("0")) {
			Student student = userService.findStudentInfo(userID);
			model.addAttribute("user", student);
		} else if (type.equals("1")) {
			Teacher teacher = userService.findTeaherInfo(userID);
			model.addAttribute("user", teacher);
		}
		model.addAttribute("type", type);
		return "usermanage/info_user";
	}

	/**
	 * save student info
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param teacherID
	 * @param department
	 * @param academy
	 * @param studyGroup
	 * @param courses
	 * @return
	 */
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String saveStudent(Model model,@RequestParam String id,
			HttpServletRequest request, 
			@RequestParam String name,
			@RequestParam(defaultValue = "0") int age,
			@RequestParam String teacherID, @RequestParam String department,
			@RequestParam String academy, @RequestParam String studyGroup,
			@RequestParam String courses) {
	

		if (userService.isExistUser(id)) {
			userService.savestudent(id, name, age, teacherID, department,
					academy, studyGroup, courses);
		} else {
			
			String  uid = 	(String) request.getSession().getAttribute("sessionname");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ctime = df.format(new Date());
			String FolderId =  folderService.addRootFolder(uid, name, "3", ctime);
			String storage_id  = spaceService.addSpace(name, 23, FolderId);
			
			userService.addStudent(id, name, age, teacherID, department,
					academy, studyGroup, courses , storage_id);
		}

		return listUser(model, "", "", "", 1, 10);
	}

	/**
	 * save teacher info
	 * 
	 * @param id
	 * @param name
	 * @param age
	 * @param title
	 * @param duty
	 * @param deparment
	 * @param studyGroup
	 * @param courses
	 * @return
	 */
	@RequestMapping(value = "/teacher", method = RequestMethod.POST)
	public String saveTeacher(Model model,@RequestParam String id,
			HttpServletRequest request, 
			@RequestParam String name,
			@RequestParam(defaultValue = "0") int age,
			@RequestParam String title, @RequestParam String duty,
			@RequestParam String department, @RequestParam String studyGroup,
			@RequestParam String courses) {
		
		
		
		if (userService.isExistUser(id)) {
			userService.saveTeacher(id, name, age, title, duty, department,
					studyGroup, courses);
		} else {
			
			String  uid = 	(String) request.getSession().getAttribute("sessionname");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ctime = df.format(new Date());
			String FolderId =  folderService.addRootFolder(uid, name, "3", ctime);
			String storage_id  = spaceService.addSpace(name, 23, FolderId);
			userService.addTeacher(id, name, age, title, duty, department,
					studyGroup, courses ,storage_id);
		}
		return listUser(model, "", "", "", 1, 10);
	}

	/**
	 * delete user
	 * 
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/{userID}/{type}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String userID,@PathVariable String type) {
		userService.deleteUser(userID,type);
		JSONObject jsonData = new JSONObject();
		jsonData.put("deleted", true);
		System.out.println(jsonData.toString());
		return jsonData.toString();
	}

	@ResponseBody
	@RequestMapping(value="/isExist/{userID}", method = RequestMethod.GET)
	public String isExist(@PathVariable String userID){
		JSONObject jsonData = new JSONObject();
		if(userService.isExistUser(userID)){
			jsonData.put("isExist", true);
		}
		else{
			jsonData.put("isExist", false);
		}
		return jsonData.toString();
	}


}
