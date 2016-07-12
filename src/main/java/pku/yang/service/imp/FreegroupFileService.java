package pku.yang.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.dao.imp.FreeGroupDao;
import pku.yang.dao.imp.FreegroupFileDao;
import pku.yang.model.FreeGroup;
import pku.yang.model.FreegroupFile;
import pku.yang.service.IFreeGroupService;
import pku.yang.service.IFreegroupFileService;


@Service
public class FreegroupFileService implements IFreegroupFileService{

	@Autowired
	private FreegroupFileDao fgfilepdao;
	
	@Override
	public void save_fgfile(FreegroupFile fgfile) {
		// TODO Auto-generated method stub
		fgfilepdao.save(fgfile);
	}

	@Override
	public void delete_fgfile(String fgfile_id) {
		// TODO Auto-generated method stub
		fgfilepdao.delete(fgfile_id);
		
	}

	@Override
	public FreegroupFile search_fgfile_info(String fgfile_id) {
		// TODO Auto-generated method stub
		FreegroupFile fgfile=fgfilepdao.getByID(fgfile_id);
		return fgfile;
	}

	@Override
	public void add_fgfile(String fgfile_id,String fileid, String folderid) {
		// TODO Auto-generated method stub
		FreegroupFile fgfile=new FreegroupFile();
		fgfile.setFgfile_id(fgfile_id);
		fgfile.setFile_id(fileid);
		fgfile.setFolder_id(folderid);
		this.save_fgfile(fgfile);
	}

	@Override
	public List<FreegroupFile> search_by_folder(String folder_id) {
		// TODO Auto-generated method stub
		List<FreegroupFile> list=fgfilepdao.getList(folder_id);
		return list;
	}

}
