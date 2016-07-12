package pku.yang.service;

import java.util.List;

import pku.yang.model.FreeGroup;
import pku.yang.model.FreegroupFile;
import pku.yang.model.Message;

public interface IFreegroupFileService {
	void save_fgfile(FreegroupFile fgfile);
	void delete_fgfile(String fgfile_id);
	FreegroupFile search_fgfile_info(String fgfile_id);
	void add_fgfile(String fgfile_id,String fileid,String folderid);
	List<FreegroupFile> search_by_folder(String folder_id);

}
