package pku.yang.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pku.yang.model.UserType;
import pku.yang.service.IUploadUserService;
import pku.yang.tool.JsonUtil;

@RequestMapping(value = "/user")
public class UploadController {

	private String filePath;
	
	@Autowired
	private IUploadUserService uploadService;

	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String getImport() {
		return "/usermanage/import_user";
	}

	@RequestMapping(value = "/import/{type}",produces="text/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String postImport(MultipartFile file, @PathVariable String type,
			Model model, HttpServletRequest request,HttpServletResponse response) throws ParseException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		List<?> list = null;
		if (!file.isEmpty()) {
			String originalName = file.getOriginalFilename();
			StringBuilder path = new StringBuilder(filePath);
			if (type.equals(UserType.Student.toString())) {
				path.append("S_");
			}
			if (type.equals(UserType.Teacher.toString())) {
				path.append("T_");
			}
			path.append(generateFileName());
			path.append(originalName.substring(originalName.lastIndexOf(".")));
			File tempFile = new File(path.toString());
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdir();
			}
			while (tempFile.exists()) {
				path = new StringBuilder(filePath);
				path.append(generateFileName());
				path.append(originalName.substring(originalName
						.lastIndexOf(".")));
				tempFile = new File(path.toString());
			}
			try {
				tempFile.createNewFile();
				file.transferTo(tempFile);
				list  = uploadService.saveExcelData(path.toString(), type);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JsonUtil.listToString(list);
	}
	/**
	 * get timestamp and a random timestamp*1000+Random==>hex for new file name
	 * 
	 * @return
	 */
	private String generateFileName() {

		Long timestamp = System.currentTimeMillis();
		int random = new Random().nextInt(1000);
		String fileName = Long.toHexString(timestamp * 1000 + random);
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
