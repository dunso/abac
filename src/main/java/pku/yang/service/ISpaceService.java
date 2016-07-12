package pku.yang.service;


import java.util.List;
import pku.yang.model.Space;


public interface ISpaceService {

	void addSpace(String id, String name, int size, String root);
	String addSpace(String name, int size, String root);
	void deleteSpace(String spaceID);
	Space findById(String id);
	Space findByRootId(String id);
	
	List<Space> getSpaceList();
	

	
}
