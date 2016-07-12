package pku.yang.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import pku.yang.model.File;
import pku.yang.model.Folder;

public interface IFileService {
	void addFile(String id,String name,String folder,
			String owner, String uploadtime,String expname,
			String cloudpath,String integrity_type,String share_type);
	void deleteFile(String file_id);
	List<File> FileList();
	List<File> getFilesByUserId(String user_id);
	void saveFile(File file);
	File findFileInfo(String fileID);
	String shareFile(String token,String folderId,String fullname);
}