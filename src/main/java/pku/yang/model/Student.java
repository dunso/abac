package pku.yang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@Column(name="student_id")
	private String id;
	
	@Column(name="student_name")
	private String name;
	
	@Column(name="department")
	private String department;
	
	@Column(name="courses")
	private String courses;
	
	@Column(name="teacher_id")
	private String teacherID;
	
	@Column(name="study_group")
	private String studygroup;
	
	@Column(name="academy")
	private String academy;
	
	@Column(name="age")
	private int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public String getStudygroup() {
		return studygroup;
	}

	public void setStudygroup(String studygroup) {
		this.studygroup = studygroup;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
