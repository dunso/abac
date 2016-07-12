package pku.yang.dao;

import java.util.List;

import pku.yang.model.BusinessGroup;


public interface IBusinessGroupDao {

	void addGroup(BusinessGroup businessGroup);
	void deleteBusinessGroup(String file_id);
	List<BusinessGroup> getList(String hql);
	BusinessGroup  getByID(String id);
	BusinessGroup  getBySID(String sid);

}
