package pku.yang.dao;

import java.util.List;

import pku.yang.model.FreeGroup;

public interface IFreeGroupDao {
	
	void save(FreeGroup freegroup);
	void delete(String fg_id);
	FreeGroup getByID(String fg_id);
	List<FreeGroup> getList(String hql);
	void add_user(String fg_id, String user_id);
	
	//void savemsg();
}
