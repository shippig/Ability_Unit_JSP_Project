package com.human.dto;

public class DomainDTO
{
	// 진단영역, 진단번호, 평가번호
	private String domain;
	private int dno;
	private int eno; 
	
	public DomainDTO() {}
	public DomainDTO(String domain, int dno, int eno)
	{
		this.domain = domain;
		this.dno = dno;
		this.eno = eno;
	}
	
	public String getDomain()
	{
		return domain;
	}
	public void setDomain(String domain)
	{
		this.domain = domain;
	}
	
	public int getEno()
	{
		return eno;
	}
	public void setEno(int eno)
	{
		this.eno = eno;
	}
	public int getDno()
	{
		return dno;
	}
	public void setDno(int dno)
	{
		this.dno = dno;
	}
	@Override
	public String toString()
	{
		return "진단영역: "+this.domain+"평가번호: "+this.eno;
	}
}
