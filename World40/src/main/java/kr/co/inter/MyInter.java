package kr.co.inter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInter extends HandlerInterceptorAdapter {
//alt+shift+s override에서 가져옴 @override
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		HandlerMethod hmethod=(HandlerMethod) handler;
		Method method =hmethod.getMethod();
		System.out.println(hmethod.getBean());
		System.out.println(method);
		return true;//true면 정상실행되고 (prehandle->controller->posthandle)
		//false면prehandle까지만 실행되고 끝난다 단.상황에따라 달라짐
		//super.pretHandle(request, response, handler, modelAndView);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	System.out.println("postHandle::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	Object test = modelAndView.getModel().get("test");
	modelAndView.getModel().put("show","show");
	System.out.println("test");
	
	
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
