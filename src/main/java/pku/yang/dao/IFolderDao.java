package pku.yang.dao;

import java.util.List;

import pku.yang.model.Folder;

public interface IFolderDao {
	void save(Folder folder);
	void deleteFolder(String Fid);
	Folder getByID(String id);
	String getFatherId(String id);  //add  by weishijia
	List<Folder> getList(String hql);
}
