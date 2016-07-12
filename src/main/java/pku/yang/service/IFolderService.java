package pku.yang.service;

import java.util.List;

import pku.yang.model.File;
import pku.yang.model.Folder;

public interface IFolderService {
	Folder findFolderInfo(String folderID);
	
	void saveFolder(Folder folder);
	
	void addFolder(String folderID,String name,String fatherID,String storageID,String storageType
			,String whetherRoot,String creater,String createDate,String shareType,
			String integrityType);

	void deleteFolder(String folderID);
	
	String getRootId(String folderId);  //add by weishijia
	
	List<Folder> FolderList();
	//-----add by shengxiaoran-----//
	List<Folder> getFoldersByUserId(String user_id);
	//-----add by shengxiaoran-----//
	
	String addRootFolder(String uid,String name,String parentid,String time);
}
