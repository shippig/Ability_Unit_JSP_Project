package com.human.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.command.AResultCommand;
import com.human.command.ApplicationCommand;
import com.human.command.Command;
import com.human.command.ECommand;
import com.human.command.LoginCommand;
import com.human.command.SResultCommand;
import com.human.command.SWACommand;
import com.human.command.SignInCommand;
import com.human.dao.EvaluationDAO;
import com.human.dao.StudentDAO;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EvaluationDAO eDAO = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		actionDo(request, response);
		
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		eDAO = EvaluationDAO.getInstance();
		
		String method = request.getMethod();
		String command = requestCommand(request, method);
		
		Command BCommand;
		ECommand ECommand;
		String path = "";
	
		switch(method)
		{
			case "GET":
				if(command.equals("/Application.do"))
				{
					ECommand = new ApplicationCommand();
					ECommand.execute(request, response);
					path = "Evaluation/Application.jsp";	
				}
				else if(command.equals("/SmartWeb&Apps.do"))
				{	
					ECommand = new SWACommand();
					ECommand.execute(request, response);
					path = "Evaluation/SmartWeb&Apps.jsp";
				}
				else if(command.equals("/login.do"))
				{
					path = "Evaluation/login.jsp";
				}
				else if(command.equals("/register.do"))
				{
					path = "Evaluation/register.jsp";
				}
				break;
			
			case "POST":
				if(command.equals("/Application.do"))
				{
					BCommand = new AResultCommand();
					BCommand.execute(request, response);
					path = "Evaluation/index.jsp";
				}
				else if(command.equals("/SmartWeb&Apps.do"))
				{
					BCommand = new SResultCommand();
					BCommand.execute(request, response);
					path = "Evaluation/index.jsp";
					
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(-1);
				}
				else if(command.equals("/login.do"))
				{
					LoginCommand LCommand = new LoginCommand();
					LCommand.execute(request, response);
					
					if(LCommand.getLoginFlag())
					{
						//아이디를 가져와서 학생번호와 이름을 얻어내고 세션에 담음
						HttpSession session = request.getSession();
						String id = (String) session.getAttribute("id");
						StudentDAO sDAO = StudentDAO.getInstance();
						int sno = sDAO.getStduentNo(id);
						session.setAttribute("sno", sno);
						
						System.out.println("학생 아이디: "+"["+id+"]");
						String name = sDAO.getStudentName(id);
						session.setAttribute("name", name);
						System.out.println("["+name+"]을 request 객체에 담았습니다.");
						
						path = "Evaluation/index.jsp";
					}
					else
						path = "Evaluation/login.jsp";
					
					
				}
				else if(command.equals("/register.do"))
				{
					BCommand = new SignInCommand();
					BCommand.execute(request, response);
					path = "Evaluation/login.jsp";
				}
				break;
		}
		System.out.println("포워드할 경로: " + path);
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	private String requestCommand(HttpServletRequest request, String method)
	{
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		
		String command = uri.substring(context.length());
		
		String jspPath = "/Evaluation";
		if(command.contains(jspPath))
		{
			command = command.substring(jspPath.length());
		}
		
		System.out.println("클라이언트 요청 확인: " + command + "("+ method + "방식"+ ")");
		return command;
	}

}
