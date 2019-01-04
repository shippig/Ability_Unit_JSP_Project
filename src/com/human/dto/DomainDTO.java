package com.human.dto;

public class DomainDTO
{
	private String domain; // 진단 영역
	private int eno;
	
	public DomainDTO() {}
	public DomainDTO(String domain)
	{
		this.domain = domain;
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
	
	@Override
	public String toString()
	{
		return "진단영역: "+this.domain+"평가번호: "+this.eno;
	}
	
	
	
}
