package pku.yang.service;

import java.io.IOException;
import java.util.List;

/**
 * upload users by excel interface
 * @author summer
 */
public interface IUploadUserService {
	
	/**
	 * save data of uploaded excel
	 * @param fileName	filename of the excel
	 * @param type	user type
	 * @return	list of users uploaded
	 * @throws IOException
	 */
	List<?> saveExcelData(String fileName,String type) throws IOException;
}
