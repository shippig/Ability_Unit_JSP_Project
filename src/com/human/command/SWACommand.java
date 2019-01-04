package com.human.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.EvaluationDAO;
import com.human.dao.StudentDAO;
import com.human.dto.EvaluationDTO;

public class SWACommand implements ECommand
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//해당 학생의 아이디로 평가지를 하나 생성한다.
				request.setCharacterEncoding("UTF-8");
				
				EvaluationDAO eDAO = EvaluationDAO.getInstance();
				EvaluationDTO evaluation = new EvaluationDTO();
				StudentDAO sDAO = StudentDAO.getInstance();
				
				HttpSession session = request.getSession();
				String id = (String) session.getAttribute("id");
				int studentNo = sDAO.getStudentNo(id);
				System.out.println("학생의 아이디: "+ id);
				System.out.println("학생의 번호: "+ studentNo);
				
				// no, institute, time, date, course, subject, appraiser, abilityUnit, element
				// 평가번호, 교육기관, 교육기간, 평가일시, 과정명, 평과목, 평가자, 능력단위명, 능력단위 요소
				
				if(eDAO.isEvaluation(studentNo, "스마트 웹&앱 구현") == false)
				{
					evaluation.setEno(studentNo);
					evaluation.setInstitute("휴먼교육센터");
					evaluation.setTime("2018년 10월 19일 ~ 2018년 12월 07일");
					evaluation.setDate("2018년-12-07 금요일");
					evaluation.setCourse("응용 S/W 개발자 양성과정");
					evaluation.setSubject("스마트 웹&앱 구현");
					evaluation.setAppraiser("박 수 민 (인)");
					evaluation.setAbilityUnit("스마트 웹&앱 구현");
					evaluation.setElement("스마트 웹 개발, 스마트 앱 개발");			
					eDAO.addEvaluation(evaluation);
					
				}
				EvaluationDTO attribute = eDAO.selectFromAbilityUnit("스마트 웹&앱 구현");	
				request.setAttribute("attribute", attribute);	
	}

	@Override
	public void setEvaluation(int sno)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDomain()
	{
		// TODO Auto-generated method stub
		
	}
	
}
