package pku.yang.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import pku.yang.dao.IBusinessGroupDao;
import pku.yang.dao.imp.BusinessGroupDao;
import pku.yang.dao.imp.FileDao;
import pku.yang.model.BusinessGroup;
import pku.yang.model.File;
import pku.yang.model.Folder;
import pku.yang.model.Space;
import pku.yang.service.IBusinessGroupService;
import pku.yang.service.IFileService;
import pku.yang.service.IFolderService;
import pku.yang.service.ISpaceService;
import pku.yang.tool.DESUtil;


@Service
public class BusinessGroupService implements IBusinessGroupService{

	@Autowired
	private IFolderService folderService;
	
	@Autowired
	private IFileService fileService;
	
	
	@Autowired
	private ISpaceService spaceService;
	
	@Autowired
	private IBusinessGroupDao businessGroupDao;
	
	@Autowired
	private FileDao fileDao;

	@Override
	public	String addGroup(String name, String adminId, String storageId,
			String adminAttrs, String uAttrs , String uids,String ctime){
			
			BusinessGroup businessGroup = new BusinessGroup();
			businessGroup.setAdminAttrs(adminAttrs);
//			businessGroup.setID(id);
			businessGroup.setAdminId(adminId);
			businessGroup.setUAttrs(uAttrs);
			businessGroup.setName(name);
			businessGroup.setUids(uids);
			businessGroup.setStorageId(storageId);
			businessGroup.setCtime(ctime);
			businessGroupDao.addGroup(businessGroup);
			return businessGroup.getID();
	}

	

	@Override
	public void deleteGroup(String groupID) {
		// TODO Auto-generated method stub
		businessGroupDao.deleteBusinessGroup(groupID);
	}

	@Override
	public List<BusinessGroup> getBussinessGroupList() {
		// TODO Auto-generated method stub
		List<BusinessGroup> grouplist = businessGroupDao.getList("from BusinessGroup");
		return grouplist;
	}

	@Override
	public BusinessGroup findGroupInfo(String GroupID) {
		// TODO Auto-generated method stub
		BusinessGroup group = businessGroupDao.getByID(GroupID);
		return group;
	}



	@Override
	public void saveGroup(BusinessGroup businessGroup) {
		// TODO Auto-generated method stub
		businessGroupDao.addGroup(businessGroup);
	}



	@Override
	public BusinessGroup findGroupInfoBySid(String sid) {
		// TODO Auto-generated method stub
		return businessGroupDao.getBySID(sid);
	}



	@Override
	public String checkIsAdmin(String token, String fileId) {
		// TODO Auto-generated method stub
		try{
			String uid = DESUtil.getUidBytoken(token);
			String flag = fileId.substring(fileId.length()-1);
			String id = fileId.substring(0, fileId.length()-1);
			if(flag.equals("f")){
			File file= 	fileDao.getByID(id);
			id = file.getFolderId();
			}
			System.out.println("file"+ id);
			String rootId  = folderService.getRootId(id);
			if(rootId.equals("0")) return "0";
			System.out.println("rootId" + rootId);
			Space space = spaceService.findByRootId(rootId);
			String spaceId = space.getID();
			System.out.println("spaceId" + spaceId);
			BusinessGroup bGroup = findGroupInfoBySid(spaceId);
			String adminIds = bGroup.getAdminId();
			String[] adminId = adminIds.split(",");
			for(String uuid: adminId){
				if(uid.equals(uuid)){ //是该群组的一个管理员
					return "1";
				}
			}
			return "0";
		}catch(Exception e){
			return "0";
			
		}
	}

	
}
