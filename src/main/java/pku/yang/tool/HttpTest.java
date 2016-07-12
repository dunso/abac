package pku.yang.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class HttpTest {

    public static String SERVER = "http://139.129.61.112:8088/abac";

    /**
     * 通过GET方式发起http请求
     */
    @Test
    public void getAccess() {
        // 创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            // 用get方法发送http请求
            HttpGet get = new HttpGet(
                    SERVER + "/queryaccess?token=As6VkJPshb34jZ255nXEKw==&groupId=1&fileFolderId=014634826261380d&privilege=createFloder");
            System.out.println("执行get请求:...." + get.getURI());
            CloseableHttpResponse httpResponse;
            // 发送get请求
            httpResponse = httpClient.execute(get);
            try {
                // response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    System.out.println("响应状态码:" + httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    JSONObject jsonObject;
                    jsonObject = JSONObject.fromObject(EntityUtils.toString(entity));
                    if ("20000".equals(jsonObject.get("code"))) {
                        if ("1".equals(jsonObject.get("data"))) {
                            System.out.println("有权限");
                        } else if ("0".equals(jsonObject.get("data"))) {
                            System.out.println("无权限");
                        }
                    } else {
                        System.out.println("出错了");// 具体错误信息在data字段中
                    }
                    System.out.println("-------------------------------------------------");
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeHttpClient(httpClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * POST方式发起http请求
     */
    @Test
    public void insertPolicy() {
        String api = "/insertpolicy";
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost(SERVER + api);
            // 创建参数列表
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("token", "As6VkJPshb34jZ255nXEKw=="));
            list.add(new BasicNameValuePair("groupId", "1"));
            list.add(new BasicNameValuePair("fileFolderId", "014634826261380d"));
            list.add(new BasicNameValuePair("createFloder", "1"));
            list.add(new BasicNameValuePair("deleteFloder", "0"));
            list.add(new BasicNameValuePair("renameFloder", "0"));
            list.add(new BasicNameValuePair("moveFloder", "1"));
            list.add(new BasicNameValuePair("uploadFile", "0"));
            list.add(new BasicNameValuePair("downloadFile", "0"));
            list.add(new BasicNameValuePair("createFile", "0"));
            list.add(new BasicNameValuePair("deleteFile", "1"));
            list.add(new BasicNameValuePair("renameFile", "0"));
            list.add(new BasicNameValuePair("moveFile", "0"));
            list.add(new BasicNameValuePair("operateWays", "0"));
            list.add(new BasicNameValuePair("integrity", "0"));
            list.add(new BasicNameValuePair("attrExpress", "#projectId='1'&(age>'30' $ ty pe='0')$!userID = '1'#"));


            // url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            // 执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    System.out.println("-------------------------------------------------------");
                    JSONObject jsonObject;
                    jsonObject = JSONObject.fromObject(EntityUtils.toString(entity));
                    if ("20000".equals(jsonObject.get("code"))) {
                        if (jsonObject.get("data") instanceof Integer && Integer.parseInt(jsonObject.get("data").toString()) > 0) {
                            System.out.println("插入成功");
                        } else {
                            System.out.println("插入失败");
                        }
                    } else if ("40000".equals(jsonObject.get("code"))) {
                        System.out.println("权限冲突了,插入的权限与系统中的权限冲突");// 具体错误信息在data字段中
                    } else {
                        System.out.println("出错了!");// 具体错误信息在data字段中
                    }
                    System.out.println("-------------------------------------------------------");
                }
            } finally {
                httpResponse.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeHttpClient(httpClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    private void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null) {
            client.close();
        }
    }

}
