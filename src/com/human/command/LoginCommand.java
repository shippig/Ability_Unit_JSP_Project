package com.human.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.StudentDAO;

public class LoginCommand implements Command
{
	
	private boolean flag = false;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		StudentDAO sDAO = StudentDAO.getInstance();
		
		//입력한 아이디와 패스워드를 가져와서 해당 회원이 맞는지 체크하고 로그인
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		flag = sDAO.login(id, pwd);
	}
	
	public boolean getLoginFlag()
	{
		return this.flag;
	}
}
