package pku.yang.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IBusinessGroupDao;
import pku.yang.model.BusinessGroup;



@Repository
public class BusinessGroupDao implements IBusinessGroupDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void addGroup(BusinessGroup businessGroup) {
		hibernateTemplate.saveOrUpdate(businessGroup);
	}
	
	@Override
	public List<BusinessGroup> getList(String hql){
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
	
			List<BusinessGroup> list=session.createQuery(hql).list();
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteBusinessGroup(String group_id) {
		
		hibernateTemplate.delete(hibernateTemplate.get(BusinessGroup.class, group_id));
		
	}
	
	@Override
	public BusinessGroup getByID(String id) {
		BusinessGroup group = (BusinessGroup)hibernateTemplate.get(BusinessGroup.class, id);
		return group;
	}

	@Override
	public BusinessGroup getBySID(String sid) {
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "from BusinessGroup as bg where bg.storageId like :sid ";		
		ArrayList<BusinessGroup> bg =(ArrayList<BusinessGroup>) session.createQuery(hql).setString("sid", sid).list();
		return bg.get(0);
	}

	
}
