package com.human.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.DomainDAO;
import com.human.dao.EvaluationDAO;
import com.human.dao.QuestionDAO;
import com.human.dao.StudentDAO;
import com.human.dto.DomainDTO;
import com.human.dto.EvaluationDTO;

public class SWACommand implements ECommand
{
	EvaluationDAO eDAO = EvaluationDAO.getInstance();
	StudentDAO sDAO = StudentDAO.getInstance();
	DomainDAO dDAO = DomainDAO.getInstance();
	QuestionDAO qDAO = QuestionDAO.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//해당 학생의 번호로 평가지를 하나 생성한다.
		request.setCharacterEncoding("UTF-8");
	
		HttpSession session = request.getSession();
		int sno = (int)session.getAttribute("sno");
		
		if(eDAO.isEvaluation(sno, "스마트 웹&앱 구현") == false)
		{
			this.setEvaluation(sno, session, request);
			this.setDomain(session);
			this.setQeustion(session);
		}
		
		//여기부터 수정
		EvaluationDTO evaluation = eDAO.selectFromAbilityUnit(sno, "스마트 웹&앱 구현");	
		request.setAttribute("evaluation", evaluation);
		
		//진단영역을 가져오려면 평가번호로 검색해서 해당 평가번호의 모든 진단영역을 ArrayList에 담는다.
		int eno = (int)session.getAttribute("eno");
		ArrayList<DomainDTO> domainList = dDAO.getDomainList(eno);
		request.setAttribute("domainList", domainList);
		
		//진단문항을 가져오려면 진단번호로 검색해서 해당 진단번호의 모든 진단문항을 ArrayList에 담는다.
		int dno = dDAO.getDomainDno(eno, "스마트 웹 개발");
		ArrayList<String> questionList1 = qDAO.getQuestions(dno);
		
		dno = dDAO.getDomainDno(eno, "스마트 앱 개발");
		ArrayList<String> questionList2 = qDAO.getQuestions(dno);
		
		request.setAttribute("questionList1", questionList1);
		request.setAttribute("questionList2", questionList2);
		
		String name = (String)session.getAttribute("name");
		request.setAttribute("name", name);
	}

	@Override
	public void setEvaluation(int sno, HttpSession session, HttpServletRequest request)
	{
		EvaluationDTO evaluation = new EvaluationDTO();
		evaluation.setInstitute("휴먼교육센터");
		evaluation.setTime("2018년 10월 19일 ~ 2018년 12월 07일");
		evaluation.setDate("2018-12-07 금요일");
		evaluation.setCourse("응용 S/W 개발자 양성과정");
		evaluation.setSubject("스마트 웹&앱 구현");
		evaluation.setAppraiser("박 수 민 (인)");
		evaluation.setAbilityUnit("스마트 웹&앱 구현");
		evaluation.setElement("스마트 웹 개발, 스마트 앱 개발");
		evaluation.setSno(sno);
		eDAO.addEvaluation(evaluation);
		
		//해당 평가지의 평가번호를 얻어와서 세션에 담는다.
		int eno = eDAO.getEvaluationEno(sno, "스마트 웹&앱 구현");
		session.setAttribute("eno", eno);
		
	}

	@Override
	public void setDomain(HttpSession session)
	{
DomainDTO domain = new DomainDTO();
		
		// 세션에서 평가번호를 가져와서 담는다.
		int eno = (int)session.getAttribute("eno");
		
		// 애플리케이션 배포 (2001020214_16v4) 능력단위에 진단영역들을 추가한다.
		domain.setEno(eno);
		domain.setDomain("스마트 웹 개발");
		this.addDomain(domain);
		
		domain.setDomain("스마트 앱 개발");
		this.addDomain(domain);
	}
	
	private void addDomain(DomainDTO domain)
	{
		//평가번호와, 능력단위명을 넘겨주고 해당 능력단위가 존재하는지 체크한다.
		if(dDAO.isDomain(domain.getEno(), domain.getDomain()) == false) 
		{
			//해당 능력단위가 존재하지 않는다면 해당 능력단위를 생성한다.
			DomainDTO addDomain = new DomainDTO();
			addDomain.setDomain(domain.getDomain());
			addDomain.setEno(domain.getEno());
			dDAO.appendDomain(addDomain);
		}
	}

	@Override
	public void setQeustion(HttpSession session)
	{
		//평가번호의 진단영역(애플리케이션 배포환경 구상하기)를 검색해서 
		//해당하는 진단영역에 진단문항을 추가한다.
		
		//진단영역을 검색해서 해당하는 진단영역의 진단번호를 가져온다.
		//진단영역을 검색하려면 평가 테이블의 평가번호를 가져와야한다.
		
		//(1)
		String domain = "스마트 웹 개발";
		int dno = getDno(domain, session);
		
		String question = "1.1 개발 하고자 하는 프로젝트를 분석하고 계획을 수입할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "1.2 프로젝트에 대한 분석을 바탕으로 설계를 할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "1.3 프로젝트 설계를 기반으로 하여 적절한 언어를 사용해서 UI 기능을 구현할 수 있다.";
		qDAO.appendQuestion(question, dno);
	
		//(2)
		domain = "스마트 앱 개발";
		dno = getDno(domain, session);
		
		question = "2.1 프로젝트 구현에서 실질적인 처리를 담당하는 로직을 구현할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "2.2 프로젝트의 구현된 기능을 테스트하며 디버깅을 할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "2.3 완성된 프로젝트를 기반으로 해서 포트플리오를 완성할 수 있다.";
		qDAO.appendQuestion(question, dno);
	}	
	
	private int getDno(String domain, HttpSession session)
	{
		int sno = (int)session.getAttribute("sno");
		System.out.println("["+domain+"]"+"진단영역의 SNO -> " + sno);
		int eno = eDAO.getEvaluationEno(sno, "스마트 웹&앱 구현");
		System.out.println("["+domain+"]"+"진단영역의 ENO -> " + eno);
		int dno = dDAO.getDomainDno(eno, domain);
		System.out.println("["+domain+"]"+"진단영역의 DNO -> " + dno);
		
		return dno;
	}
}
