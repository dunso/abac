package pku.yang.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="user")
public class User{

	@Id
	@Column(name="pid")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	private String user_pid;
	
	@Column(name="user_id")
	private String userID;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="storage_id",length=128)
	private String storageID;
	
	@Column(name="type")
	private String type;
	
	@Column(name = "role")
	private int roleNum;
	
	@Column(name = "groups",length=512)
	private String gourps;
	
	public User(){
		
	}
	
	public User(Student student){
		this.setUserID(student.getId());
		this.setUsername(student.getName());
		this.setType(UserType.Student.toString());
		this.setPassword("666666");
	}
	
	public User(Teacher teacher){
		this.setUserID(teacher.getId());
		this.setUsername(teacher.getName());
		this.setType(UserType.Teacher.toString());
		this.setPassword("666666");
	}
	
	public String getStorageID() {
		return storageID;
	}
	public void setStorageID(String storageID) {
		this.storageID = storageID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_pid() {
		return user_pid;
	}
	public void setUser_pid(String user_pid) {
		this.user_pid = user_pid;
	}
	public int getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}
	public Role getRole(){
		return new Role(this.roleNum);
	}

	public String getGourps() {
		return gourps;
	}

	public void setGourps(String gourps) {
		this.gourps = gourps;
	}
	
}
