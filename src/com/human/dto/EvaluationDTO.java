		package com.human.dto;

public class EvaluationDTO
{
	// 평가번호, 교육기관, 교육기간, 평가일시, 과정명, 평과목, 평가자, 능력단위명, 능력단위 요소, 학생번호
	// eno, institute, time, date, course, subject, appraiser, abilityUnit, element, sno
	private int eno; 
	private String institute;
	private String time;
	private String date;
	private String course;
	private String subject;
	private String appraiser;
	private String abilityUnit;
	private String element;
	private int sno;
	
	public EvaluationDTO() {}
	public EvaluationDTO(int eno, String institute, String time,String date, 
			String course, String subject, String appraiser, String abilityUnit, String element, int sno)
	{
		this.eno = eno;
		this.institute = institute;
		this.time = time;
		this.date = date;
		this.course = course;
		this.subject = subject;
		this.appraiser = appraiser;
		this.abilityUnit = abilityUnit;
		this.element = element;
	}

	public int getEno()
	{
		return eno;
	}

	public void setEno(int eno)
	{
		this.eno = eno;
	}

	public String getInstitute()
	{
		return institute;
	}

	public void setInstitute(String institute)
	{
		this.institute = institute;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getCourse()
	{
		return course;
	}

	public void setCourse(String course)
	{
		this.course = course;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getAppraiser()
	{
		return appraiser;
	}

	public void setAppraiser(String appraiser)
	{
		this.appraiser = appraiser;
	}

	public String getAbilityUnit()
	{
		return abilityUnit;
	}

	public void setAbilityUnit(String abilityUnit)
	{
		this.abilityUnit = abilityUnit;
	}

	public String getElement()
	{
		return element;
	}

	public void setElement(String element)
	{
		this.element = element;
	}
	
	public int getSno()
	{
		return sno;
	}
	public void setSno(int sno)
	{
		this.sno = sno;
	}
	@Override
	public String toString()
	{
		return "평가번호: "+eno+"/"+"교육기관 : "+institute+"/"
					+"교육기간: "+time+"/"+"평가일시: "+date+"/"
						+"과정명: "+course+"/"+"평과목: "+subject+"/"+"평가자: "+appraiser+
							"/"+"능력단위명: "+abilityUnit+"/"+"능력단위요소: "+element+"/"+"학생번호: "+sno;
	}
}
