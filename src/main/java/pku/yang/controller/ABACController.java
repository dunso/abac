package pku.yang.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pku.yang.model.ABACParams;
import pku.yang.service.IABACService;

/**
 * 访问控制Controller
 * 
 * @author chenbin
 * @Date 20160113
 * @Operate create
 * @address Peking University
 */

@Controller
public class ABACController {

    @ResponseBody
    @RequestMapping(value = "/queryaccess", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String queryAccess(ABACParams abacParams) {
        Map<String, String> paramsDisplay = getParamsDisplay1(abacParams);
        if (abacService.checkParams(paramsDisplay) == null) {
            return resultJson(null).toString();
        }
        String funcName = "${queryAccess}";
        String access = abacService.queryAccess(abacParams.getToken(), funcName, paramsDisplay, abacParams.getPrivilege());
        return String.valueOf(resultJson(access));
    }

    @ResponseBody
    @RequestMapping(value = "/queryattrexpress", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String queryAttrExpress(ABACParams abacParams) {
        Map<String, String> paramsDisplay = getParamsDisplay1(abacParams);
        if (abacService.checkParams(paramsDisplay) == null) {
            return resultJson(null).toString();
        }
        String funcName = "${queryAttrExpress}";
        String attrExpresses = abacService.queryPolicy(abacParams.getToken(), funcName, paramsDisplay);
        return String.valueOf(resultJson(attrExpresses));
    }


    @ResponseBody
    @RequestMapping(value = "/querypolicy", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String queryPolicy(ABACParams abacParams) {
        Map<String, String> paramsDisplay = getParamsDisplay1(abacParams);
        if (abacService.checkParams(paramsDisplay) == null) {
            return resultJson(null).toString();
        }
        String funcName = "${queryPolicy}";
        String policies = abacService.queryPolicy(abacParams.getToken(), funcName, paramsDisplay);
        return String.valueOf(resultJson(policies));
    }

    @ResponseBody
    @RequestMapping(value = "/insertpolicy", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String insertPolicy(ABACParams abacParams) {
        Map<String, String> paramsDisplay1 = getParamsDisplay1(abacParams);
        if (abacService.checkParams(paramsDisplay1) == null) {
            return resultJson(null).toString();
        }

        Map<String, String> paramsDisplay2 = getParamsDisplay2(abacParams);
        if (Strings.isNullOrEmpty(paramsDisplay2.get("attrExpress"))) {
            setErrorMsg("40001", "请求参数错误，参数attrExpress的值为空");
            return resultJson(null).toString();
        }

        String result = abacService.conflictdetection(abacParams.getToken(), "${conflictDetection}", paramsDisplay1, paramsDisplay2);
        if(!"{}".equals(result)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","40000");
            jsonObject.put("data",result);
            return jsonObject.toString();
        }

        String funcName = "${insertPolicy}";
        Integer num = abacService.insertPolicy(abacParams.getToken(), funcName, paramsDisplay1, paramsDisplay2);
        return String.valueOf(resultJson(num));
    }

    @ResponseBody
    @RequestMapping(value = "/modifypolicy", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String modifyPolicy(ABACParams abacParams) {
        Map<String, String> paramsDisplay1 = getParamsDisplay1(abacParams);
        if (abacService.checkParams(paramsDisplay1) == null) {
            return resultJson(null).toString();
        }
        Map<String, String> paramsDisplay2 = getParamsDisplay2(abacParams);

        String result = abacService.conflictdetection(abacParams.getToken(), "${conflictDetection}", paramsDisplay1, paramsDisplay2);
        if(!"{}".equals(result)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","40000");
            jsonObject.put("data",result);
            return jsonObject.toString();
        }

        String funcName = "${modifyPolicy}";
        Integer num = abacService.insertPolicy(abacParams.getToken(), funcName, paramsDisplay1, paramsDisplay2);
        return String.valueOf(resultJson(num));
    }


    @ResponseBody
    @RequestMapping(value = "/deletepolicy", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String deletePolicy(ABACParams abacParams) {
        Map<String, String> paramsDisplay1 = new HashMap<>();
        paramsDisplay1.put("fileFolderId", abacParams.getFileFolderId());

        if (Strings.isNullOrEmpty(paramsDisplay1.get("fileFolderId"))) {
            setErrorMsg("40001", "请求参数错误，该参数名fileFolderId书写错误");
            return resultJson(null).toString();
        }
        Map<String, String> paramsDisplay2 = new HashMap<>();
        paramsDisplay2.put("table2id", abacParams.getTable2id());
        String funcName = "${deletePolicy}";
        Integer num = abacService.deletePolicy(abacParams.getToken(), funcName, paramsDisplay1, paramsDisplay2);
        return String.valueOf(resultJson(num));
    }

    @ResponseBody
    @RequestMapping(value = "/conflictdetection", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String conflictdetection(ABACParams abacParams) {
        Map<String, String> paramsDisplay1 = getParamsDisplay1(abacParams);
        if (abacService.checkParams(paramsDisplay1) == null) {
            return resultJson(null).toString();
        }
        Map<String, String> paramsDisplay2 = getParamsDisplay2(abacParams);
        String funcName = "${conflictDetection}";
        String result = abacService.conflictdetection(abacParams.getToken(), funcName, paramsDisplay1, paramsDisplay2);
        return String.valueOf(resultJson(result));
    }

    private Map<String, String> getParamsDisplay1(ABACParams abacParams) {
        errorMsg.clear();
        Map<String, String> paramsDisplay = new HashMap<>();
        paramsDisplay.put("groupId", abacParams.getGroupId());
        paramsDisplay.put("fileFolderId", abacParams.getFileFolderId());
        return paramsDisplay;
    }

    private Map<String, String> getParamsDisplay2(ABACParams abacParams) {
        Map<String, String> paramsDisplay = new HashMap<>();
        paramsDisplay.put("table2id", abacParams.getTable2id());
        paramsDisplay.put("attrExpress", abacParams.getAttrExpress());
        paramsDisplay.put("createFloder", abacParams.getCreateFloder());
        paramsDisplay.put("deleteFloder", abacParams.getDeleteFloder());
        paramsDisplay.put("renameFloder", abacParams.getRenameFloder());
        paramsDisplay.put("moveFloder", abacParams.getMoveFloder());
        paramsDisplay.put("uploadFile", abacParams.getUploadFile());
        paramsDisplay.put("downloadFile", abacParams.getDownloadFile());
        paramsDisplay.put("deleteFile", abacParams.getDeleteFile());
        paramsDisplay.put("renameFile", abacParams.getRenameFile());
        paramsDisplay.put("moveFile", abacParams.getMoveFile());
        paramsDisplay.put("operateWays", abacParams.getOperateWays());
        paramsDisplay.put("integrity", abacParams.getIntegrity());
        return paramsDisplay;
    }

    private Object resultJson(Object result) {
        JSONArray jsonArray;
        JSONObject jsonObject = new JSONObject();

        errorMsg.putAll(abacService.getErrorMsg());
        if (errorMsg.size() != 0) {
            jsonArray = JSONArray.fromObject(errorMsg);
            jsonObject.put("code","50000");
            jsonObject.put("data",errorMsg);
            return jsonObject.toString();
        }
        if(result == null){
            jsonObject.put("code","40001");
            jsonObject.put("data","无匹配数据!");
            return jsonObject.toString();
        }else{
            jsonObject.put("code","20000");
            jsonObject.put("data",result);
            return jsonObject.toString();
        }
    }

    @Autowired
    private IABACService abacService;

    private Map<String, String> errorMsg = new HashMap<>();

    public void setErrorMsg(String code, String msg) {
        this.errorMsg.put(code, msg);
    }

}
