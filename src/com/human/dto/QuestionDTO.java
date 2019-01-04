package com.human.dto;

public class QuestionDTO
{
	//문항번호, 진단문항, 문항점수, 진단번호
	private int qno;
	private String question;
	private String questionScore;
	private int eno;
	
	public QuestionDTO(int qno, String question, String questionScore, int eno)
	{
		this.qno = qno;
		this.question = question;
		this.questionScore = questionScore;
		this.eno = eno;
	}

	public int getQno()
	{
		return qno;
	}

	public void setQno(int qno)
	{
		this.qno = qno;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getQuestionScore()
	{
		return questionScore;
	}

	public void setQuestionScore(String questionScore)
	{
		this.questionScore = questionScore;
	}

	public int getEno()
	{
		return eno;
	}

	public void setEno(int eno)
	{
		this.eno = eno;
	}
	
	@Override
	public String toString()
	{
		return "문항번호: "+this.qno+"/"+"진단문항: "+
				this.question+"/"+"진단점수: "+this.questionScore+"/"+"진단번호: "+this.eno;
	}
}
