package pku.yang.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.model.File;
import pku.yang.model.Folder;
import pku.yang.service.IFileService;
import pku.yang.tool.DESUtil;
import pku.yang.dao.imp.FileDao;

@Service
public class FileService implements IFileService{
	
	@Autowired
	private FileDao fileDao;
	
	@Override
	public void saveFile(File file){
		fileDao.save(file);
	}
	
	@Override
	public void addFile(String id,String name,String folder,
			String owner, String uploadtime,String expname,
			String cloudpath,String integrity_type,String share_type){
		System.out.println("Testlog: addFile of FileService");
		File file = new File();
		file.setFile_id(id);
		file.setFile_name(name);
		file.setFolderId(folder);
		file.setOwner(owner);
		file.setUpload_time(uploadtime);
		file.setExp_name(expname);
		file.setCloud_path(cloudpath);
		file.setIntegrityType(integrity_type);
		file.setShareType(share_type);
		fileDao.save(file);
	}
	
	@Override
	public void deleteFile(String file_id){
		fileDao.deleteFile(file_id);
	}
	
	@Override
	public List<File> FileList(){
		List<File> filelist = fileDao.getList("from File");
		return filelist;
	}
	@Override
	public List<File> getFilesByUserId(String user_id){
		String hql = "from File where owner="+user_id;
		List<File> filelist = fileDao.getList(hql);
		return filelist;
	}
	
	@Override
	public File findFileInfo(String fileID){
		return fileDao.getByID(fileID);
	}
	
	@Override
	public String shareFile(String token,String folderId,String fileId){
		//--get file info--//
		File oldFile = findFileInfo(fileId);
		//--generate upload time--//
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String uploadtime = format.format(date);
		//--generate file id--//
		String newFileId = "1";
		newFileId = newFileId + Long.toString(date.getTime());
		int randomNumber = (int)(Math.random()*1000);
		newFileId = newFileId + Integer.toString(randomNumber);
		addFile(newFileId, oldFile.getFile_name(), folderId, oldFile.getOwner(), uploadtime,oldFile.getExp_name(),oldFile.getCloud_path(),oldFile.getIntegrityType(),oldFile.getShareType());
		return newFileId;
	}
}
