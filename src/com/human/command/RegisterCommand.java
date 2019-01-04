package com.human.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.StudentDAO;
import com.human.dto.StudentDTO;

public class RegisterCommand implements Command
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 아이디가 이미 있는지 체크한다.
		// 아이디가 없다면 데이터베이스에 데이터를 추가한다.
		
		request.setCharacterEncoding("UTF-8");
		
		// 이름, 아이디, 비밀번호
		String name = request.getParameter("name");
		String id = request.getParameter("id"); 
		String pwd = request.getParameter("pwd");
		
		StudentDAO sDAO = StudentDAO.getInstance();
		
		if(sDAO.idDoubleCheck(id))
		{
			response.sendRedirect("go.jsp");
		}
		else
		{
			StudentDTO student = new StudentDTO(name, id, pwd);
			sDAO.addStudent(student);
		}
	}
}
