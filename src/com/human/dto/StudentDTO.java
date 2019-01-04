package com.human.dto;

public class StudentDTO
{
	// 학생번호, 이름, 아이디, 비밀번호
	private Integer no; 
	private String name;
	private String id; 
	private String password;

	public StudentDTO() {}
	public StudentDTO(String name, String id, String password)
	{
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public Integer getNo()
	{
		return no;
	}
	public void setNo(Integer no)
	{
		this.no = no;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return "학생번호: "+this.no+"/"+"학생이름: "
				+this.name+"아이디: "+this.id+"비밀번호: "+this.password;
	}

}
