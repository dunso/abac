package pku.yang.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.ISpaceDao;
import pku.yang.model.Space;

@Repository
public class SpaceDao implements ISpaceDao{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void saveSpace(Space space) {
		hibernateTemplate.saveOrUpdate(space);
	}

	@Override
	public String addSpace(Space space) {
		hibernateTemplate.saveOrUpdate(space);
		return space.getID();
	}
	
	
	@Override
	public void deleteSpaceByID(String spaceId) {
		hibernateTemplate.delete(hibernateTemplate.get(Space.class, spaceId));
	}
	
	@Override
	public Space getSpaceByID(String spaceId) {
		Space space = (Space)hibernateTemplate.get(Space.class, spaceId);
		return space;
	}
	
	@Override
	public List<Space> getSpaceList(String hql){
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
		List<Space> list=session.createQuery(hql).list();
		return list;
		}catch(Exception e){
		e.printStackTrace();
		return null;
		}
	}

	@Override
	public Space getSpaceByRoot(String rootId) {
	
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "from Space as spa where spa.rootDirectory like :rootId ";		
		ArrayList<Space> space =(ArrayList<Space>) session.createQuery(hql).setString("rootId", rootId).list();
		return space.get(0);
	}
	
}
