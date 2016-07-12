package pku.yang.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.dao.imp.FreeGroupDao;
import pku.yang.model.FreeGroup;
import pku.yang.service.IFreeGroupService;

@Service
public class FreeGroupService implements IFreeGroupService {
	
	@Autowired
	private FreeGroupDao freegroupdao;
	@Override
	public void save_fg(FreeGroup freegroup) {
		// TODO Auto-generated method stub
		freegroupdao.save(freegroup);
	}

	@Override
	public void delete_fg(String fg_id) {
		// TODO Auto-generated method stub
		freegroupdao.delete(fg_id);
		
	}

	@Override
	public FreeGroup search_fg_info(String fg_id) {
		// TODO Auto-generated method stub
		FreeGroup fg=freegroupdao.getByID(fg_id);
		return fg;
	}

	@Override
	public String add_fg( String fg_name, String fg_manager, String fg_userlist, String storgeid) {
		// TODO Auto-generated method stub
		FreeGroup freegroup=new FreeGroup();
//  	freegroup.setFg_id(fg_id);
		freegroup.setFg_name(fg_name);
		freegroup.setFg_manager(fg_manager);
		freegroup.setFg_userlist(fg_userlist);
		freegroup.setStorgeid(storgeid);
		this.save_fg(freegroup);
		String fg_id=freegroup.getFg_id();
		return fg_id;
	}

	@Override
	public List<FreeGroup> fg_list() {
		// TODO Auto-generated method stub
		List<FreeGroup> fg_list=freegroupdao.getList("from FreeGroup");
		return fg_list;
	}

	@Override
	public void add_user(String fg_id, String user_id) {
		// TODO Auto-generated method stub
		freegroupdao.add_user(fg_id, user_id);
		
	}

}
