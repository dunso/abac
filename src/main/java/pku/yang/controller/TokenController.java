package pku.yang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;
import pku.yang.model.Token;
import pku.yang.service.ITokenService;
import pku.yang.tool.DESUtil;

@Controller
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private ITokenService tokenService;
	
	@ResponseBody 
	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	public String getToken(
			) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String ctime = df.format(new Date());
		
		
		
		
//		String md5 = "ABCDEFG";
		
//		Token token = new Token();
//		token.setTokenId(md5);
//		token.setDeadLine(ctime);
//		
//		tokenService.save(token);
		
		
		String tken  = DESUtil.encrypt(ctime);
		JSONArray jsonarray = new JSONArray();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("token", tken);
		
			jsonarray.add(jsonObject);
			JSONObject jsonData = new JSONObject();
			
			jsonData.put("code",0);
			jsonData.put("data",jsonarray);
					
		return jsonData.toString();
		
		
//		return "home";
	}
	
	@RequestMapping(value = "/setToken", method = RequestMethod.GET)
	public String setToken(
			) {
			
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String ctime = df.format(new Date());
		
		String md5 = "ABCDEFG";
		
		Token token = new Token();
		token.setTokenId(md5);
		token.setDeadLine(ctime);
		
		tokenService.save(token);
		
		return "home";
	}
	
	@ResponseBody 
	@RequestMapping(value = "/checkToken", method = RequestMethod.GET)
	public String checkToken(@RequestParam String token_id
			) {
		
		 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    String time;
			try {
				time = DESUtil.getUidBytoken(token_id);
				  Date date = format.parse(time);  
				    Long tokenTime = date.getTime();
				    
				    
				    
				    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					String newTime = df.format(new Date());
				    date = format.parse(newTime);  
				    Long nnewTime = date.getTime();
				  
				String code ="0";
				
				if(nnewTime > tokenTime + 5*60*1000){
					code = "1";
				}else{
					code ="0";
				}
				
				return code;
			} catch (Exception e) {
				return "1";
			}  
		  
	}
}
