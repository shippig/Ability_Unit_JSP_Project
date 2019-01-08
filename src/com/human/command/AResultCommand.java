package com.human.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AResultCommand implements Command
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		Enumeration<String> enumeration = request.getParameterNames();
		
		ArrayList<String> score = new ArrayList<String>();
		int app1, app2, app3, app4;
		app1 = app2 = app3 = app4 = 0;
		while(enumeration.hasMoreElements())
		{
			String data = request.getParameter(enumeration.nextElement());
			score.add(data);
		}
		
		//=============== 애플리테이션 배포환경 구성하기 =============
		for(int i = 0; i < 4; i++) 
		{ 
			int temp = Integer.parseInt(score.get(i));
			app1 += temp;
		}
		
		int smartApp = 0;
		//===============애플리케이션 소스 검증하기 =============
		for(int i = 4; i < 7; i++) 
		{ 
			int temp = Integer.parseInt(score.get(i));
			app2 += temp;
		}
		
		//===============애플리케이션 빌드하기 =============
		for(int i = 7; i < 12; i++)
		{
			int temp = Integer.parseInt(score.get(i));
			app3 += temp;
		}
		
		//===============애플리케이션 배포하기 =============
		for(int i = 12; i < 16; i++)
		{
			int temp = Integer.parseInt(score.get(i));
			app4 += temp;
		}
		
		// 점수를 세션에 저장
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(-1);
		session.setAttribute("app1", app1);
		session.setAttribute("app1Sum", app1 / 4);
		
		session.setAttribute("app2", app2);
		session.setAttribute("app2Sum", app2 / 3);
		
		session.setAttribute("app3", app3);
		session.setAttribute("app3Sum", app3 / 5);
		
		session.setAttribute("app4", app4);
		session.setAttribute("app4Sum", app1 / 4);
		
		int resultSum = app1 + app2 + app3 + app4;
		session.setAttribute("AresultSum", resultSum / 16);
		
		// 평가지를 작성했는지에 관한 플래그를 세션에 담음.(제출했으면 평가지를 다시 작성하지 못하게 하기 위함.)
		boolean appFlag = true;
		session.setAttribute("appFlag", appFlag);	
	}
}
