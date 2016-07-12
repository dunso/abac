package pku.yang.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IFolderDao;
import pku.yang.model.Folder;

@Repository
public class FolderDao implements IFolderDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Folder folder) {
		hibernateTemplate.saveOrUpdate(folder);
	}
	
	@Override
	public void deleteFolder(String id) {
		hibernateTemplate.delete(hibernateTemplate.get(Folder.class, id));
	}
	
	@Override
	public Folder getByID(String id) {
		Folder folder = (Folder)hibernateTemplate.get(Folder.class, id);
		return folder;
	}
	
	@Override
	public List<Folder> getList(String hql){
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
		List<Folder> list=session.createQuery(hql).list();
		System.out.println("FolderDao:"+list);
		return list;
		}catch(Exception e){
		e.printStackTrace();
		return null;
		}
		}

	@Override
	public String getFatherId(String id) {
		// TODO Auto-generated method stub
		Folder folder = (Folder)hibernateTemplate.get(Folder.class, id);
		return folder.getFatherID();
	}
	
}
