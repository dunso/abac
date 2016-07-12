package pku.yang.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import pku.yang.dao.IFreeGroupDao;
import pku.yang.dao.IFreegroupFileDao;
import pku.yang.model.FreeGroup;
import pku.yang.model.FreegroupFile;
import pku.yang.model.Message;


@Repository
public class FreegroupFileDao implements IFreegroupFileDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Override
	public void save(FreegroupFile fgfile) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(fgfile);
		
	}

	@Override
	public void delete(String fgfile_id) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(hibernateTemplate.get(FreegroupFile.class, fgfile_id));
		
	}

	@Override
	public FreegroupFile getByID(String fgfile_id) {
		// TODO Auto-generated method stub
		FreegroupFile fgfile = (FreegroupFile)hibernateTemplate.get(FreegroupFile.class, fgfile_id);
		return fgfile;
	}

	@Override
	public List<FreegroupFile> getList(String folder_id) {
		// TODO Auto-generated method stub
		Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
		try{
			String hql = "FROM FreegroupFile where folder_id='"+folder_id+"'";		
			//Query q = session.createQuery(hql);

			List<FreegroupFile>list_fgfile=session.createQuery(hql).list();
			return list_fgfile;
			}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
