package com.human.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.EvaluationDAO;
import com.human.dto.EvaluationDTO;

public class forwardCommand implements Command
{
	String abilityUnit = "";
	public forwardCommand(String abilityUnit)
	{
		this.abilityUnit = abilityUnit;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		EvaluationDAO eDAO = EvaluationDAO.getInstance();
		EvaluationDTO attribute = eDAO.selectFromAbilityUnit(this.abilityUnit);	
		request.setAttribute("attribute", attribute);	
	}
	
}
