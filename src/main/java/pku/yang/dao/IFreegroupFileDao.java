package pku.yang.dao;

import java.util.List;
import pku.yang.model.FreegroupFile;

public interface IFreegroupFileDao {
	
	void save(FreegroupFile fgfile);
	void delete(String fgfile_id);
	FreegroupFile getByID(String fgfile_id);
	List<FreegroupFile> getList(String folder_id);
	//void add_user(String fg_id, String user_id);

}
