package com.bjsxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.util.StringUtil;
/**
注解格式:@WebServlet(name="FirstServlet",urlPatterns={"/firstServlet","/MyServlet"},
initParams={@WebInitParam(name="hello",value="world init....")})  
 @author 头条号：IT蛇精病原创
 模拟登陆
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");		
		PrintWriter out=response.getWriter();
		String action =StringUtil.nullBlank(request.getParameter("action"));
		if("leave".equals(action)){ //离开
			request.getSession().removeAttribute("uname");
			out.append("leave");
			out.flush();
			
		}else{		//登录
			System.out.println("---------------------");
			String uname =request.getParameter("uname");
			boolean success = !uname.equals("老高");			
			if(success){
				request.getSession().setAttribute("uname", uname);
				response.sendRedirect("chat.jsp");
			}
		}		
	}
}
