package com.human.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.human.dao.DomainDAO;
import com.human.dao.EvaluationDAO;
import com.human.dao.StudentDAO;
import com.human.dto.DomainDTO;
import com.human.dto.EvaluationDTO;

public class ApplicationCommand implements ECommand
{	
	EvaluationDAO eDAO = EvaluationDAO.getInstance();
	StudentDAO sDAO = StudentDAO.getInstance();
	DomainDAO dDAO = DomainDAO.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//해당 학생의 번호로 평가지를 하나 생성한다.
		request.setCharacterEncoding("UTF-8");
	
		HttpSession session = request.getSession();
		int sno = (int)session.getAttribute("sno");
		
		if(eDAO.isEvaluation(sno, "애플리케이션 배포 (2001020214_16v4)") == false)
		{
			this.setEvaluation(sno, session);
			this.setDomain(session);	
		}
		
		//여기부터 수정
		EvaluationDTO evaluation = eDAO.selectFromAbilityUnit(sno, "애플리케이션 배포 (2001020214_16v4)");	
		request.setAttribute("evaluation", evaluation);	
	}

	@Override
	public void setEvaluation(int sno, HttpSession session)
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
		
		//여기부터 수정 (진단 영역을 ArrayList에 담아서 세션에 담는 로직 추가해야함.)
		//진단영역을 가져오려면 평가번호와
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
}
