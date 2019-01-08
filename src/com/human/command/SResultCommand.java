package com.human.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SResultCommand implements Command
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		Enumeration<String> enumeration = request.getParameterNames();
		
		ArrayList<String> score = new ArrayList<String>();
		while(enumeration.hasMoreElements())
		{
			String data = request.getParameter(enumeration.nextElement());
			score.add(data);
		}
		
		//=============== 스마트 웹 개발 =============
		int smartWeb = 0;
		for(int i = 0; i < 3; i++) 
		{ 
			//System.out.println(score.get(i)); 
			int temp = Integer.parseInt(score.get(i));
			smartWeb += temp;
		}
		
		int smartApp = 0;
		//=============== 스마트 앱 개발 =============
		for(int i = 3; i < 6; i++) 
		{ 
			//System.out.println(score.get(i));
			int temp = Integer.parseInt(score.get(i));
			smartApp += temp;
		}
		
		// 점수를 세션에 저장
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(-1);
		session.setAttribute("smartWeb", smartWeb);
		session.setAttribute("smartWebSum", smartWeb / 3);
		session.setAttribute("smartApp", smartApp);
		session.setAttribute("smartAppSum", smartApp / 3);
		
		session.setAttribute("resultSum", (smartWeb + smartApp) / 6);
		
		// 평가지를 작성했는지에 관한 플래그를 세션에 담음.(제출했으면 평가지를 다시 작성하지 못하게 하기 위함.)
		boolean smartWebAndAppFlag = true;
		session.setAttribute("smartWebAndAppFlag", smartWebAndAppFlag);
	}
}
