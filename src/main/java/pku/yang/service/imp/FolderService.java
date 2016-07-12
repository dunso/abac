package pku.yang.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pku.yang.dao.imp.FolderDao;
import pku.yang.model.File;
import pku.yang.model.Folder;
import pku.yang.service.IFolderService;

@Service
public class FolderService implements IFolderService{
	@Autowired
	private FolderDao folderdao;
	
	@Override
	public Folder findFolderInfo(String folderID){
		return folderdao.getByID(folderID);
	}

	@Override
	public void saveFolder(Folder folder){
		folderdao.save(folder);
	}
	
	@Override
	public void addFolder(String folderID,String name,String fatherID,String storageID,
			String storageType,String whetherRoot,String creater,
			String createDate,String shareType,String integrityType){
		Folder folder= new Folder();
		folder.setFolderID(folderID);
		folder.setName(name);
		folder.setFatherID(fatherID);
		folder.setStorageID(storageID);
		folder.setStorageType(storageType);
		folder.setWhetherRoot(whetherRoot);
		folder.setCreater(creater);
		folder.setCreateDate(createDate);
		folder.setShareType(shareType);
		folder.setIntegrityType(integrityType);
		this.saveFolder(folder);
	}
	
	@Override
	public String addRootFolder(String uid,String name,String parentid,String time){
		Folder folder= new Folder();
		String id = "0";
	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String unixtime = Long.toString(date.getTime());
		id = id + unixtime;
		int i = (int)Math.random()*1000;
		String randomNumber = Integer.toString(i);
		id = id + randomNumber;
		System.out.println(id);
		folder.setFolderID(id);
		folder.setName(name);
		folder.setFatherID(parentid);
		folder.setCreater(uid);
		folder.setCreateDate(time);
		
		folder.setStorageID("0");
		folder.setStorageType("0");
		folder.setWhetherRoot("0");

		folder.setShareType("0");
		folder.setIntegrityType("0");
		folderdao.save(folder);
		return id;
	}
	
	@Override
	public void deleteFolder(String folderID){
		folderdao.deleteFolder(folderID);
	}
	
	@Override
	public List<Folder> FolderList(){
		List<Folder> folderlist = folderdao.getList("from Folder");
		System.out.println("FolderService:"+folderlist);
		return folderlist;
	}
	//-----add by shengxiaoran-----//
	@Override
	public List<Folder> getFoldersByUserId(String user_id){
		String hql = "from Folder where creater="+user_id;
		List<Folder> filelist = folderdao.getList(hql);
		return filelist;
	}
	//-----add by shengxiaoran-----//

	@Override
	public String getRootId(String folderId) {
		// TODO Auto-generated method stub
		Folder folder = folderdao.getByID(folderId);
		String fatherId = folder.getFatherID();
		while(!fatherId.equals("0") && !fatherId.equals("1") &&!fatherId.equals("2") ){
			folder = folderdao.getByID(fatherId);
			fatherId = folder.getFatherID();
		}
		
		if(fatherId.equals("1"))
		return folder.getFolderID();
		else{
			return "0";
		}
	}
}
