package pku.yang.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IMessageDao;
import pku.yang.model.Message;


@Repository
public class MessageDao implements IMessageDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Message mess) {
		hibernateTemplate.saveOrUpdate(mess);
}
	
	@Override
	public void save_state(String mess_id,String state) {
		// TODO Auto-generated method stub
		Message mess=hibernateTemplate.get(Message.class,mess_id);
		if(mess != null){
			mess.setState(state);
			hibernateTemplate.saveOrUpdate(mess);
		}
	}

	@Override
	public void delete(String mess_id) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(hibernateTemplate.get(Message.class,mess_id));
		
	}

	@Override
	public Message getByID(String mess_id) {
		// TODO Auto-generated method stub
		Message mess=hibernateTemplate.get(Message.class,mess_id);
		return mess;
	}

	@Override
	public List<Message> getList(String hql) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
			List<Message>list=session.createQuery(hql).list();
			//System.out.println("FolderDao:"+list);
			return list;
			}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Message> userMessofNOrespond(String user_id) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
			String hql = "FROM Message where user_id="+user_id+" and state='0'";		
			//Query q = session.createQuery(hql);
			

			List<Message>list_usermess=session.createQuery(hql).list();
			//System.out.println("FolderDao:"+list);
			return list_usermess;
			}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Message> userMessResponded(String user_id) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
			String hql = "FROM Message where user_id="+user_id+" and state='1'";		
			//Query q = session.createQuery(hql);
			List<Message>list_usermess=session.createQuery(hql).list();
			//System.out.println("FolderDao:"+list);
			return list_usermess;
			}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Message> mess_of_fg(String fg_id) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
			String hql = "FROM Message where fg_id='"+fg_id+"'";		
			//Query q = session.createQuery(hql);

			List<Message>list_fgmess=session.createQuery(hql).list();
			return list_fgmess;
			}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
