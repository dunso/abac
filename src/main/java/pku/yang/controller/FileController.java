package pku.yang.controller;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pku.yang.model.File;
import pku.yang.service.IFileService;

import pku.yang.model.Folder;
import pku.yang.service.IFolderService;
import pku.yang.tool.DESUtil;

@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private IFileService fileService;
	
	//-----这是临时代码-----//
	@Autowired
	private IFolderService folderService;
	//-----这是临时代码-----//
	
	//-----以下是正式的接口代码-----//
//	@ResponseBody
//	@RequestMapping(value = "/listAllFile", method = RequestMethod.GET)
//	public String GetAllFilebyId(@RequestParam String  user_id){
//			JSONObject result = new JSONObject();
//			JSONArray data = new JSONArray();
//			result.put("code",0);
//			//-----获取文件列表-----//
//			List<File> filelist = fileService.getFilesByUserId(user_id);
//			for(int i=0;i<filelist.size();i++){
//				JSONObject temp = new JSONObject();
//				temp.put("fileid", filelist.get(i).getFile_id());
//				temp.put("parentid", filelist.get(i).getFolderId());
//				temp.put("filename", filelist.get(i).getFile_name());
//				temp.put("type","file");
//				temp.put("date", filelist.get(i).getUpload_time());
//				temp.put("size",0);
//				temp.put("TPA",0);
//				temp.put("share",0);
//				data.add(temp);
//			}
//			//-----获取文件夹列表-----//
//			List<Folder> folderlist = folderService.getFoldersByUserId(user_id);
//			for(int i=0;i<folderlist.size();i++){
//				JSONObject temp = new JSONObject();
//				temp.put("fileid", folderlist.get(i).getFolderID());
//				temp.put("parentid", folderlist.get(i).getFatherID());
//				temp.put("filename", folderlist.get(i).getName());
//				temp.put("type","directory");
//				temp.put("date", folderlist.get(i).getCreateDate());
//				temp.put("size",0);
//				temp.put("TPA",0);
//				temp.put("share",0);
//				data.add(temp);
//			}
//			result.put("data", data);
//			return result.toJSONString();
//	}
	@ResponseBody
	@RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
	public String uploadFile(@RequestParam String token,
			@RequestParam String folderId,
			@RequestParam String fullname) {
		//--generate upload time--//
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String uploadtime = format.format(date);
		//--generate file id--//
		String fileId = "1";
		fileId = fileId + Long.toString(date.getTime());
		int randomNumber = (int)(Math.random()*1000);
		fileId = fileId + Integer.toString(randomNumber);
		//--get user id--//
		String userId = "";
		try{
			userId = DESUtil.getUidBytoken(token);
		}catch(Exception e){
			e.printStackTrace();
		}
		//--parse file name--//
		String[] nameStr = fullname.split("\\.");
		Folder folder = new Folder();
		folder = folderService.findFolderInfo(folderId);
		String integrity_type = folder.getIntegrityType();
		String share_type = folder.getShareType();
		fileService.addFile(fileId, nameStr[0], folderId, userId, uploadtime,nameStr[1], "",integrity_type,share_type);
		JSONObject result = new JSONObject();
		JSONArray data = new JSONArray();
		result.put("code",0);
		JSONObject subData = new JSONObject();
		subData.put("fileid", fileId);
		subData.put("filename", fullname);
		subData.put("uid", userId);
		data.add(subData);
		//-----获取文件列表-----//
//		List<File> filelist = fileService.getFilesByUserId(userId);
//		for(int i=0;i<filelist.size();i++){
//			JSONObject temp = new JSONObject();
//			temp.put("fileid", filelist.get(i).getFile_id());
//			temp.put("parentid", filelist.get(i).getFolderId());
//			temp.put("filename", filelist.get(i).getFile_name());
//			temp.put("type","file");
//			temp.put("date", filelist.get(i).getUpload_time());
//			temp.put("size",0);
//			temp.put("TPA",0);
//			temp.put("share",0);
//			data.add(temp);
//		}
//		//-----获取文件夹列表-----//
//		List<Folder> folderlist = folderService.getFoldersByUserId(userId);
//		for(int i=0;i<folderlist.size();i++){
//			JSONObject temp = new JSONObject();
//			temp.put("fileid", folderlist.get(i).getFolderID());
//			temp.put("parentid", folderlist.get(i).getFatherID());
//			temp.put("filename", folderlist.get(i).getName());
//			temp.put("type","directory");
//			temp.put("date", folderlist.get(i).getCreateDate());
//			temp.put("size",0);
//			temp.put("TPA",0);
//			temp.put("share",0);
//			data.add(temp);
//		}
		result.put("data", data);
		return result.toJSONString();
	}
	@ResponseBody
	@RequestMapping(value = "/deletefile", method = RequestMethod.GET)
	public String deleteFile(@RequestParam String token,
			@RequestParam String fileId) {
		JSONObject result = new JSONObject();
		//--get user id--//
		String userId = "";
		try{
			userId = DESUtil.getUidBytoken(token);
		}catch(Exception e){
			e.printStackTrace();
		}
		fileService.deleteFile(fileId);
		result.put("code", 0);
		result.put("data", "success");
		return result.toJSONString();
	}
	@ResponseBody
	@RequestMapping(value = "/downloadfile", method = RequestMethod.POST)
	public String downloadFile(@RequestParam String token,
			@RequestParam String fileId) {
		JSONObject result = new JSONObject();
		//--get user id--//
		String userId = "";
		try{
			userId = DESUtil.getUidBytoken(token);
		}catch(Exception e){
			e.printStackTrace();
		}
		result.put("code", 0);
		result.put("data", "success");
		return result.toJSONString();
	}
	//-----以下是测试代码-----//
	
	/**
	 * �����ļ�������Ϣ 
	 * 
	 * @param id
	 * @param name
	 * @param folder
	 * @param owner
	 * @param uploadtime
	 * @param expname
	 * @param cloudpath
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveFile(@RequestParam String id,
			@RequestParam String name,
			@RequestParam String folder,
			@RequestParam String owner,
			@RequestParam String uploadtime,
			@RequestParam String expname,
			@RequestParam String cloudpath) {
		System.out.println("Testlog: 111---addFile of FileController");
		String integrity_type = "0";String share_type = "0";
		fileService.addFile(id, name, folder, owner, uploadtime,expname, cloudpath,integrity_type,share_type);
		System.out.println("Testlog: 222---addFile of FileController");
		return "filemanage/add_file";
	}
	
	/**
	 * ��ѯ�ļ��б� TODO
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listFile(Model model) {
		int code=200;
		List<File> list = fileService.FileList();
		model.addAttribute("filelist",list);
		JSONObject resultjson = new JSONObject();
		JSONArray dorjsonarray = new JSONArray();
		for(int i=0;i<list.size();i++){
			JSONObject json1 = new JSONObject();
			json1.put("id",list.get(i).getFile_id());
			json1.put("name", list.get(i).getFile_name());
			json1.put("folder", list.get(i).getFolderId());
			json1.put("owner", list.get(i).getOwner());
			json1.put("uploadtime", list.get(i).getUpload_time());
			json1.put("expname", list.get(i).getExp_name());
			json1.put("cloudpath", list.get(i).getCloud_path());
			dorjsonarray.add(json1);
		}
		resultjson.put("code",code);
		resultjson.put("content", dorjsonarray);
		model.addAttribute("jsonArray",resultjson);
		return "filemanage/list_file";
	}
	
	/**
	 * ɾ���ļ� TODO
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteFolder(@RequestParam String id) {
		fileService.deleteFile(id);
		return "filemanage/add_file";
	}
}
