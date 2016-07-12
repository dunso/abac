package pku.yang.dao;

import java.util.List;

import pku.yang.model.File;
import pku.yang.model.Folder;

public interface IFileDao {
	void save (File file);
	void deleteFile(String file_id);
	List<File> getList(String hql);
	File getByID(String id);
}
