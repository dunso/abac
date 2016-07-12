package pku.yang.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IFileDao;
import pku.yang.model.File;
import pku.yang.model.Folder;

@Repository
public class FileDao implements IFileDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(File file){
		System.out.println("Testlog: save of FileDao");
		hibernateTemplate.saveOrUpdate(file);
		hibernateTemplate.flush();
	}
	
	@Override
	public void deleteFile(String file_id) {
		hibernateTemplate.delete(hibernateTemplate.get(File.class, file_id));
	}
	
	@Override
	public File getByID(String id) {
		File file = (File)hibernateTemplate.get(File.class, id);
		return file;
	}
	
	@Override
	public List<File> getList(String hql){
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
			List<File> list=session.createQuery(hql).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
