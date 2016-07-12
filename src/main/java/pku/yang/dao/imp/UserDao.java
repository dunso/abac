package pku.yang.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IUserDao;
import pku.yang.model.User;
import pku.yang.model.Student;
import pku.yang.model.Teacher;
import pku.yang.tool.Pagination;

@Repository
public class UserDao implements IUserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public User getByID(String id) {

		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("userID", id));
		List<User> user = (List<User>) hibernateTemplate.findByCriteria(criteria);
		if(user != null && user.size()>0){
			return user.get(0);
		}
		return null;

	}

	@Override
	public void save(User user) {
		hibernateTemplate.saveOrUpdate(user);
	}

	@Override
	public void deleteUser(String userID) {
		User user = this.getByID(userID);
		hibernateTemplate.delete(user);
	}

	@Override
	public void saveStudent(Student student) {
		hibernateTemplate.saveOrUpdate(student);
		hibernateTemplate.flush();
		
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		hibernateTemplate.saveOrUpdate(teacher);
		
	}

	@Override
	public void deletestudent(String id) {
		Student student = this.getStudentByID(id);
		hibernateTemplate.delete(student);
		
	}

	@Override
	public void deleteTeacher(String id) {
		Teacher teacher = this.getTeacherByID(id);
		hibernateTemplate.delete(teacher);
	}

	@Override
	public Student getStudentByID(String id) {
		Student student = (Student)hibernateTemplate.get(Student.class, id);
		return student;
	}

	@Override
	public Teacher getTeacherByID(String id) {
		Teacher teacher = (Teacher)hibernateTemplate.get(Teacher.class, id);
		return teacher;
	}

	@Override
	public Pagination<User> findUsers(String type, String id, String name,int page,int pagesize) {
		Pagination<User> pagination = new Pagination<>();
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class); 
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from User as user where 1=1");
		if(type != null && !type.equals("")){
			criteria.add(Restrictions.eq("type", type));
			query.append("and user.type ="+type);
		}
		if(id != null && !id.equals("")){
			criteria.add(Restrictions.like("userID", id, MatchMode.ANYWHERE));
			query.append("and user.userID like '%"+id+"%'");
		}
		if(name != null && !name.equals("")){
			criteria.add(Restrictions.like("username", name, MatchMode.ANYWHERE));
			query.append("and user.username like '%"+name+"%'");
		}
		int count =((Long) hibernateTemplate.iterate(query.toString()).next()).intValue();
		pagination.setPagenum(count/pagesize+(count%pagesize == 0 ? 0:1));
		pagination.setPage(page);
		pagination.setPagesize(pagesize);
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) hibernateTemplate.findByCriteria(criteria, (page-1)*pagesize, pagesize);
		pagination.setList(users);
		return pagination;
	}

	@Override
	public void saveStudents(final List<Student> students) {
		hibernateTemplate.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				for(int i = 0 ; i < students.size(); i++){
					session.save(students.get(i));
					if(i % 50 == 0){
						session.flush();
						session.clear();
					}
				}
				return null;
			}	
		});
	}

	@Override
	public void saveTeachers(final List<Teacher> teachers) {
		hibernateTemplate.execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				for(int i = 0 ; i < teachers.size(); i++){
					session.save(teachers.get(i));
					if(i % 50 == 0){
						session.flush();
						session.clear();
					}
				}
				return null;
			}	
		});
	}

	@Override
	public void saveUsers(final List<User> users) {
		//TODO FIX bug
		hibernateTemplate.execute(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException {
				for(int i = 0 ; i < users.size(); i++){
					session.save(users.get(i));
					if(i % 50 == 0){
						session.flush();
						session.clear();
					}
				}
				return null;
			}	
		});
	}

	@Override
	public String getUserGroup(String id) {
		User user = this.getByID(id);
		return user.getGourps();
	}

	@Override
	public String setUserGroup(String id, String userGroup) {
		User user = this.getByID(id);
		user.setGourps(userGroup);
		hibernateTemplate.saveOrUpdate(user);
		return user.getGourps();
	}

	@Override
	public String getStorageId(String id) {
		User user = this.getByID(id);
		return user.getStorageID();
	}

	@Override
	public String getUserPid(String id) {
		// TODO Auto-generated method stub
		User user = this.getByID(id);
		return user.getUser_pid();
	}	
	
}
