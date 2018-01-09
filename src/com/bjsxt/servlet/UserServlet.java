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
ע���ʽ:@WebServlet(name="FirstServlet",urlPatterns={"/firstServlet","/MyServlet"},
initParams={@WebInitParam(name="hello",value="world init....")})  
 @author ͷ���ţ�IT�߾���ԭ��
 ģ���½
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
		if("leave".equals(action)){ //�뿪
			request.getSession().removeAttribute("uname");
			out.append("leave");
			out.flush();
			
		}else{		//��¼
			System.out.println("---------------------");
			String uname =request.getParameter("uname");
			boolean success = !uname.equals("�ϸ�");			
			if(success){
				request.getSession().setAttribute("uname", uname);
				response.sendRedirect("chat.jsp");
			}
		}		
	}
}
