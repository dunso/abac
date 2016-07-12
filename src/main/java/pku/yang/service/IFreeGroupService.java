package pku.yang.service;

import java.util.List;

import pku.yang.model.FreeGroup;

public interface IFreeGroupService {
	
	void save_fg(FreeGroup freegroup);
	void delete_fg(String fg_id);
	FreeGroup search_fg_info(String fg_id);
	String add_fg(String fg_name,String fg_manager,String fg_userlist,String storgeid);
	List<FreeGroup> fg_list();
	void add_user(String fg_id,String user_id);
}
