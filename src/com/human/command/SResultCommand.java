package com.human.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SResultCommand implements Command
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		//1~4, 5~8
		
		Enumeration<String> enumeration = request.getParameterNames();
		
		String score[];
		while(enumeration.hasMoreElements())
		{
			
		}
	}

}
