package pku.yang.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IFreeGroupDao;
import pku.yang.model.FreeGroup;

@Repository
public class FreeGroupDao implements IFreeGroupDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(FreeGroup freegroup) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(freegroup);
		
	}

	@Override
	public void delete(String fg_id) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(hibernateTemplate.get(FreeGroup.class, fg_id));
	}
	
	@Override
	public FreeGroup getByID(String fg_id) {
		// TODO Auto-generated method stub
		FreeGroup freegroup = (FreeGroup)hibernateTemplate.get(FreeGroup.class, fg_id);
		return freegroup;
	}
	

	@Override
	public List<FreeGroup> getList(String hql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
		List<FreeGroup> list=session.createQuery(hql).list();
		System.out.println("FolderDao:"+list);
		return list;
		}catch(Exception e){
		e.printStackTrace();
		return null;
		}
	}

	@Override
	public void add_user(String fg_id, String user_id) {
		// TODO Auto-generated method stub
		FreeGroup freegroup = (FreeGroup)hibernateTemplate.get(FreeGroup.class, fg_id);
		//freegroup.getFg_userlist().add(user_id);
	}

	

}
