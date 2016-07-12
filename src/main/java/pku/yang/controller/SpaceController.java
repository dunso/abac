package pku.yang.controller;


import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import pku.yang.model.Space;
import pku.yang.service.ISpaceService;


@Controller
@RequestMapping("/space")
public class SpaceController {

	@Autowired
	private ISpaceService spaceService;
	
	@RequestMapping(value = "/addSpace", method = RequestMethod.POST)
	public String addSpace(
			@RequestParam String name,
			@RequestParam String id,
			@RequestParam int size, 
			@RequestParam String root
			) {
		spaceService.addSpace(id,name,size,root);
		
		return "spacemanage/space_list";
	}
	

	@ResponseBody 
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String getData( ) {
	
		List<Space> list = spaceService.getSpaceList();
		
		JSONArray dorjsonarray = new JSONArray();
		for(int i=0;i<list.size();i++){
			JSONObject jsonData = new JSONObject();
			jsonData.put("id",list.get(i).getID());
			jsonData.put("name", list.get(i).getName());
			jsonData.put("size", list.get(i).getSize());
			jsonData.put("root", list.get(i).getRoot());
			
			String operation = "<a href = 'delete?id="+list.get(i).getID() + "'>delete</a>";
			jsonData.put("operation", operation);
			dorjsonarray.add(jsonData);
		}
		
		return dorjsonarray.toJSONString();

	}

	
	@RequestMapping(value ="/getList" , method = RequestMethod.GET)
	public String getList(){
		
		return "spacemanage/space_list";
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteSpace(@RequestParam String id) {
		spaceService.deleteSpace(id);
		return "spacemanage/space_list";
	}

	@RequestMapping(value = "/deleteSpaceByPost", method = RequestMethod.POST)
	public String deleteSpaceByPost(@RequestParam String id) {
		spaceService.deleteSpace(id);
		return "spacemanage/space_list";
	}


}
