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

public class ApplicationCommand implements ECommand
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
		
		if(eDAO.isEvaluation(sno, "애플리케이션 배포 (2001020214_16v4)") == false)
		{
			this.setEvaluation(sno, session, request);
			this.setDomain(session);
			this.setQeustion(session);
		}
		
		//여기부터 수정
		EvaluationDTO evaluation = eDAO.selectFromAbilityUnit(sno, "애플리케이션 배포 (2001020214_16v4)");	
		request.setAttribute("evaluation", evaluation);
		
		//진단영역을 가져오려면 평가번호로 검색해서 해당 평가번호의 모든 진단영역을 ArrayList에 담는다.
		int eno = (int)session.getAttribute("eno");
		ArrayList<DomainDTO> domainList = dDAO.getDomainList(eno);
		request.setAttribute("domainList", domainList);
		
		//진단문항을 가져오려면 진단번호로 검색해서 해당 진단번호의 모든 진단문항을 ArrayList에 담는다.
		int dno = dDAO.getDomainDno(eno, "애플리케이션 배포환경 구성하기");
		ArrayList<String> questionList1 = qDAO.getQuestions(dno);
		
		dno = dDAO.getDomainDno(eno, "애플리케이션 소스 검증하기");
		ArrayList<String> questionList2 = qDAO.getQuestions(dno);
		
		dno = dDAO.getDomainDno(eno, "애플리케이션 빌드하기");
		ArrayList<String> questionList3 = qDAO.getQuestions(dno);
		
		dno = dDAO.getDomainDno(eno, "애플리케이션 배포하기");
		ArrayList<String> questionList4 = qDAO.getQuestions(dno);
		
		request.setAttribute("questionList1", questionList1);
		request.setAttribute("questionList2", questionList2);
		request.setAttribute("questionList3", questionList3);
		request.setAttribute("questionList4", questionList4);
	}

	@Override
	public void setEvaluation(int sno, HttpSession session, HttpServletRequest request)
	{
		EvaluationDTO evaluation = new EvaluationDTO();
		evaluation.setInstitute("휴먼교육센터");
		evaluation.setTime("2019년 01월 02일 ~ 2019년 01월 17일");
		evaluation.setDate("2019년-01-17 목요일");
		evaluation.setCourse("응용 S/W 개발자 양성과정");
		evaluation.setSubject("애플리케이션 배포");
		evaluation.setAppraiser("박 수 민 (인)");
		evaluation.setAbilityUnit("애플리케이션 배포 (2001020214_16v4)");
		evaluation.setElement("애플리케이션 배포 환경 구성하기, 애플리케이션 소스 검증하기, 애플리케이션 빌드하기, 애플리케이션 배포하기");
		evaluation.setSno(sno);
		eDAO.addEvaluation(evaluation);
		
		//해당 평가지의 평가번호를 얻어와서 세션에 담는다.
		int eno = eDAO.getEvaluationEno(sno, "애플리케이션 배포 (2001020214_16v4)");
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
		domain.setDomain("애플리케이션 배포환경 구성하기");
		this.addDomain(domain);
		
		domain.setDomain("애플리케이션 소스 검증하기");
		this.addDomain(domain);
		
		domain.setDomain("애플리케이션 빌드하기");
		this.addDomain(domain);
		
		domain.setDomain("애플리케이션 배포하기");
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
		String domain = "애플리케이션 배포환경 구성하기";
		int dno = getDno(domain, session);
		
		String question = "1.1 애플리케이션 빌드와 배포를 위한 환경 구성 방안을 계획할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "1.2 애플리케이션 배포를 위한 도구와 시스템을 결정할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "1.3 결정한 애플리케이션 배포 환경을 위한 도구와 시스템을 설치할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "1.4 설치한 시스템과 도구 운영을 위해 상세 구성 및 설정을 할 수 있다.";
		qDAO.appendQuestion(question, dno);
	
		//(2)
		domain = "애플리케이션 소스 검증하기";
		dno = getDno(domain, session);
		
		question = "2.1 정상적으로 작동하는 소프트웨어 빌드를 위해 형상관리 서버로부터 소스코드를 체크아웃 할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "2.2 소스코드 검증 도구를 활용하여 애플리케이션에서 사용한 라이브러리, 소스, 로직 등의 오류가 있는지 여부를 검증할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "2.3 소스코드의 환경 설정, 운영 환경 정보, 대상 시스템 정보 등에 오류가 있는지 확인 할 수 있다.";
		qDAO.appendQuestion(question, dno);
		
		//(3)
		domain = "애플리케이션 빌드하기";
		dno = getDno(domain, session);
		
		question = "3.1 애플리케이션 소스코드 검증 결과 문제가 없는 경우 해당 소스코드를 빌드 시스템으로 이관할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "3.2 애플리케이션 빌드 절차에 따른 빌드 스크립트를 작성할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "3.3 작성한 빌드 스크립트 또는 도구를 활용하여 애플리케이션 빌드를 실행 할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "3.4 애플리케이션 빌드 실행 결과를 확인하여 정상적으로 완료되었는지 여부를 확인할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "3.5 애플리케이션 빌드 실패 시 문제 내용과 원인을 파악하여 개발자에게 설명할 수 있다.";
		qDAO.appendQuestion(question, dno);
		
		//(4)
		domain = "애플리케이션 배포하기";
		dno = getDno(domain, session);
		
		question = "4.1 애플리케이션 실행 환경에 대한 정보를 확인할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "4.2 애플리케이션 배포 절차에 따라 운영환경에 적용할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "4.3 애플리케이션 배포 후 정상적으로 작동하는지 여부를 확인할 수 있다.";
		qDAO.appendQuestion(question, dno);
		question = "4.4 애플리케이션 배포 결과 문제가 발생했을 경우 적용 내용을 이전 상태로 복원할 수 있다.";
		qDAO.appendQuestion(question, dno);
	}
	
	private int getDno(String domain, HttpSession session)
	{
		int sno = (int)session.getAttribute("sno");
		System.out.println("["+domain+"]"+"진단영역의 SNO -> " + sno);
		int eno = eDAO.getEvaluationEno(sno, domain);
		System.out.println("["+domain+"]"+"진단영역의 ENO -> " + eno);
		int dno = dDAO.getDomainDno(eno, domain);
		System.out.println("["+domain+"]"+"진단영역의 DNO -> " + dno);
		
		return dno;
	}
	

}
