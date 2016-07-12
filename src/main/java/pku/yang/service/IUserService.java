package pku.yang.service;

import java.util.ArrayList;
import java.util.List;

import pku.yang.model.Student;
import pku.yang.model.Teacher;
import pku.yang.model.User;
import pku.yang.tool.Pagination;

public interface IUserService {
	/**
	 * user login 
	 * @param id user id
	 * @param password user password
	 * @return  if the user can find in database, and the password matched, return userInfo
	 * @throws Exception if no user found, throw exception
	 */
	User login(String id, String password) throws Exception;
	
	/**
	 * find users
	 * @param type user type, 0 for student, 1 for teacher
	 * @param userID user's id
	 * @param name user's name
	 * @param page current page
	 * @param pagesize the size of page
	 * @return
	 * @throws Exception
	 */
	Pagination<User> findUsers(String type, String userID, String name, int page,
			int pagesize) throws Exception;

	/**
	 * find user's info by user's id
	 * @param userID user's id
	 * @return Student
	 */
	Student findStudentInfo(String userID);
	
	/**
	 * find user's info by teacher's id
	 * @param teacherID
	 * @return Teacher
	 */
	Teacher findTeaherInfo(String teacherID);

	/**
	 * save student info
	 * @param id  id
	 * @param name name
	 * @param age age
	 * @param teacherID tutor's id
	 * @param department belong to which department
	 * @param academy belong to what academy
	 * @param studyGroup belong to which studygroup
	 * @param courses study what courses
	 */
	void savestudent(String id, String name, int age, String teacherID,
			String department, String academy, String studyGroup,
			String courses);
	/**
	 * add student info
	 * @param id  id
	 * @param name name
	 * @param age age
	 * @param teacherID tutor's id
	 * @param department belong to which department
	 * @param academy belong to what academy
	 * @param studyGroup belong to which studygroup
	 * @param courses study what courses
	 */
	void addStudent(String id, String name, int age, String teacherID,
			String department, String academy, String studyGroup,
			String courses,String storage_id);

	/**
	 * save teacher info
	 * @param id id
	 * @param name name
	 * @param age age
	 * @param title title
	 * @param duty duty
	 * @param department belong to what department
	 * @param studyGroup belong to which department
	 * @param courses teaches what courses
	 */
	void saveTeacher(String id, String name, int age, String title,
			String duty, String department, String studyGroup,
			String courses);

	/**
	 * add teacher info
	 * @param id id
	 * @param name name
	 * @param age age
	 * @param title title
	 * @param duty duty
	 * @param department belong to what department
	 * @param studyGroup belong to which department
	 * @param courses teaches what courses
	 */
	void addTeacher(String id, String name, int age, String title,
			String duty, String department, String studyGroup,
			String courses , String storage_id);

	/**
	 * delete userInfo
	 * @param userID  user id
	 * @param type user type, 0 for student, 1 for teacher
	 */
	void deleteUser(String userID, String type);
	
	/**
	 * find whether the user is existed in the database
	 * @param id user id
	 * @return true for existed, false for no
	 */
	boolean isExistUser(String id);
	
	ArrayList<String> getUserGroup(String id);
	
	String getUserGroupString(String id);
	
	String getStorageId(String id);
	
	String getUserPid(String id);
	
	boolean deleteUserGroup(String id,String group);
	
	boolean addUserGroup(String id, String group);
}
