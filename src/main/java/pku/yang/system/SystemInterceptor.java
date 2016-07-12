package pku.yang.system;

import java.io.PrintWriter;  
import java.util.Iterator;  
import java.util.Map;  
  
import javax.annotation.Resource;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;  
  

  
/** 
 *  
 * @author geloin 
 * @date 2012-3-27 下午2:29:35 
 */  
@Repository  
public class SystemInterceptor extends HandlerInterceptorAdapter {  
  
 
    /* 
     * (non-Javadoc) 
     *  
     * @see 
     * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle 
     * (javax.servlet.http.HttpServletRequest, 
     * javax.servlet.http.HttpServletResponse, java.lang.Object) 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
  
        
        // 后台session控制  
        String[] noFilters = new String[] { };  
//        String[] noFilters = new String[] { "businessGroup","groupmanage"};  
        String uri = request.getRequestURI();  
  
            boolean beFilter = false;  
            
            for (String s : noFilters) {  
            	
//            	System.out.println(s);
//            	System.out.println(uri);
            	
                if (uri.indexOf(s) != -1) {
                	
                    beFilter = true;  
                    break;  
                }  
            }  
            
            if (beFilter) {  
                Object obj = request.getSession().getAttribute("sessionname");  
//                System.out.println(uri);
//                System.out.println((String) obj);
                if (null == obj) {  
  
                	//未登陆
                	  PrintWriter out = response.getWriter();  
                      StringBuilder builder = new StringBuilder();  
                      builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
                      builder.append("alert(\"页面过期，请重新登录\");");  
                      builder.append("window.top.location.href=\"");  
                      builder.append("/sshdemo/login.jsp\";</script>");  
                      out.print(builder.toString());  
                      out.close();  
                      return false; 
                } 
            }  
          
        return super.preHandle(request, response, handler);  
    }  
  
}  