package pku.yang.service.imp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.dao.IUserDao;
import pku.yang.model.Student;
import pku.yang.model.Teacher;
import pku.yang.model.User;
import pku.yang.model.UserType;
import pku.yang.service.IUploadUserService;

@Service
public class UploadUserService implements IUploadUserService {
	@Autowired
	private IUserDao userdao;

	/*
	 * (non-Javadoc)
	 * @see pku.yang.service.IUploadUserService#saveExcelData(java.lang.String, java.lang.String)
	 */
	@Override
	public List<?> saveExcelData(String fileName, String type)
			throws IOException {
		InputStream ins = new FileInputStream(fileName);
		List<User> users = new ArrayList<>();
		try{
			Workbook workbook = WorkbookFactory.create(ins);
			//suppose only one sheet in the workbook, and it's the first
			Sheet sheet = workbook.getSheetAt(0);
			if (type.equals(UserType.Student.toString())) {
				List<Student> students= analysisStuSheet(sheet,users);
				userdao.saveUsers(users);
				userdao.saveStudents(students);
				return students;
			} else if (type.equals(UserType.Teacher.toString())) {
				List<Teacher> teachers = analysisTeaSheet(sheet,users);
				userdao.saveUsers(users);
				userdao.saveTeachers(teachers);
				return teachers;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			ins.close();
		}
		return null;
	}
	
	/**
	 * read teacher info from sheet 
	 * @param sheet	
	 * @param users	user list for save
	 * @return teacher list
	 */
	private List<Teacher> analysisTeaSheet(Sheet sheet, List<User> users){
		List<Teacher> teachers = new ArrayList<>();
		for(int rowNum = 1;rowNum <= sheet.getLastRowNum(); rowNum++){
			Row row = sheet.getRow(rowNum);
			Teacher teacher = new Teacher();
			teacher.setId(row.getCell(0).getStringCellValue());
			teacher.setName(row.getCell(1).getStringCellValue());
			teacher.setAge((int)row.getCell(2).getNumericCellValue());
			teacher.setTitle(row.getCell(3).getStringCellValue());
			teacher.setDuty(row.getCell(4).getStringCellValue());
			teacher.setDepartment(row.getCell(5).getStringCellValue());
			teacher.setStudyGroup(row.getCell(6).getStringCellValue());
			teacher.setCourses(row.getCell(7).getStringCellValue());
			teachers.add(teacher);
			users.add(new User(teacher));
		}
		return teachers;
	}

	/**
	 * read student info from sheet 
	 * @param sheet	
	 * @param users	user list for save
	 * @return student list
	 */
	private List<Student> analysisStuSheet(Sheet sheet,
			List<User> users) {
		List<Student> students = new ArrayList<>();
		for(int rowNum = 1;rowNum <= sheet.getLastRowNum(); rowNum++){
			Row row = sheet.getRow(rowNum);
			Student student = new Student();
			student.setId(row.getCell(0).getStringCellValue());
			student.setName(row.getCell(1).getStringCellValue());
			student.setAge((int)row.getCell(2).getNumericCellValue());
			student.setTeacherID(row.getCell(3).getStringCellValue());
			student.setDepartment(row.getCell(4).getStringCellValue());
			student.setAcademy(row.getCell(5).getStringCellValue());
			student.setStudygroup(row.getCell(6).getStringCellValue());
			student.setCourses(row.getCell(7).getStringCellValue());
			students.add(student);
			users.add(new User(student));
		}
		return students;
	}
}
