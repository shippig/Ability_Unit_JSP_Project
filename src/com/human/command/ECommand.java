package com.human.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface ECommand
{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public void setEvaluation(int sno, HttpSession session);
	public void setDomain(HttpSession session);
}
