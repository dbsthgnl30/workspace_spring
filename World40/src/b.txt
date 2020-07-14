package kr.co.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInter  extends HandlerInterceptorAdapter{

   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception {
      
      HttpSession session = request.getSession();
      
      Object login =session.getAttribute("login");
      
      if (login == null) {
         
      String uri=   request.getRequestURI(); // /member/update/m001
      String queryString=request.getQueryString();
      if (queryString == null ) {
         
         queryString ="";
         
      }else {
         queryString = "?"+queryString; // ?id=m001&pw=123 물음표오
         
      }
      
      if (request.getMethod().equals("GET")) {     //  /member/update/m001?id=m001&pw=123  가나옴... 우와
         String path =uri + queryString;            
            session.setAttribute("path", path);
      }
      
         
         response.sendRedirect("/member/login");
         
         return false;
      }
   
      return true;
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {
      
      super.postHandle(request, response, handler, modelAndView);
   }
   

}