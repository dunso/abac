package pku.yang.dao;

import java.util.List;

import pku.yang.model.Space;

public interface ISpaceDao {
	void saveSpace(Space space);
	String addSpace(Space space);
	void deleteSpaceByID(String spaceId);
	Space getSpaceByID(String spaceId);
	Space getSpaceByRoot(String rootId);
	List<Space> getSpaceList(String hql);
}
