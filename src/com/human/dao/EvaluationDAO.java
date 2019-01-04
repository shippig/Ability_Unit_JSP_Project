package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.human.dto.EvaluationDTO;

public class EvaluationDAO
{
	
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private static final boolean HAVE_AN_ABILITY_UNIT = true;
	private static final boolean ABILITY_UNIT_NONE= false;
	
	private static EvaluationDAO instance = new EvaluationDAO();
	
	public static EvaluationDAO getInstance()
	{
		return instance;
	}
	
	
	//학생 번호와 능력단위명을 검색해서 해당 평가의 COLUMN을 DTO에 담아서 리턴한다.
	public EvaluationDTO selectFromAbilityUnit(int sno, String abilityUnit)
	{
		final String QUERY = "SELECT * FROM 평가 WHERE 학생번호 = ? AND 능력단위명 = ?";
		EvaluationDTO eDTO = new EvaluationDTO();
		
		try
		{
			System.out.println(sno + "학생의" + " 검색 키워드: " + abilityUnit);
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			
			psmt.setInt(1, sno);
			psmt.setString(2, abilityUnit);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{	
				eDTO.setEno(rs.getInt("평가번호"));
				eDTO.setInstitute(rs.getString("교육기관"));
				eDTO.setTime(rs.getString("교육기간"));
				eDTO.setDate(rs.getString("평가일시"));
				eDTO.setCourse(rs.getString("과정명"));
				eDTO.setSubject(rs.getString("교과목"));
				eDTO.setAppraiser(rs.getString("평가자"));
				eDTO.setAbilityUnit(rs.getString("능력단위명"));
				eDTO.setElement(rs.getString("능력단위요소"));
				eDTO.setSno(rs.getInt("학생번호"));
				
				System.out.println(abilityUnit + "는 존재하는 능력단위명 (검색 성공)");
			}
			else
			{
				System.out.println(abilityUnit + "는 존재하지 않는 능력단위명입니다. (검색 실패)");
			}
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.CloseDatabaseResource();
		}
		
		return eDTO;
	}
	
	//학생번호로 해당 학생의 능력단위를 얻어내서 능력단위가 존재하는가를 체크한다.
	public boolean isEvaluation(int studentNo, String abilityUnit)
	{
		
		final String QUERY = "SELECT 능력단위명 FROM 평가 WHERE 학생번호 = ? AND 능력단위명 = ?";
		
		// 학생번호와, 애플리케이션 배포 (2001020214_16v4)를 검색해서 있다면 true 없다면 false를 반환
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setInt(1, studentNo);
			psmt.setString(2, abilityUnit);
			
			rs = psmt.executeQuery();
			if(rs.next())
			{
				System.out.println(abilityUnit + " 능력단위명은 있습니다. (평가지 생성 불필요)");
				return HAVE_AN_ABILITY_UNIT;					
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.CloseDatabaseResource();
		}
		System.out.println(abilityUnit + " 능력단위명은 없습니다. (평가지 생성 필요)");
		return ABILITY_UNIT_NONE;
	}
	
	// 평가지 생성 메서드
	// EX) 애플리케이션 배포, 스마트 웹&앱 구현
	public void addEvaluation(EvaluationDTO evaluation)
	{
		final String QUERY = "INSERT INTO 평가 VALUES (평가_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			conn = this.getConnection();
			
			// eno, institute, time, date, course, subject, appraiser, abilityUnit, element, sno
			psmt = conn.prepareStatement(QUERY);
			psmt.setString(1, evaluation.getInstitute());
			psmt.setString(2, evaluation.getTime());
			psmt.setString(3, evaluation.getDate());
			psmt.setString(4, evaluation.getCourse());
			psmt.setString(5, evaluation.getSubject());
			psmt.setString(6, evaluation.getAppraiser());
			psmt.setString(7, evaluation.getAbilityUnit());
			psmt.setString(8, evaluation.getElement());
			psmt.setInt(9, evaluation.getSno());
			
			int r = psmt.executeUpdate();
			if(r != -1)
				System.out.println("["+evaluation.getAbilityUnit()+"] 평가지를 생성했습니다.");
			else
				System.out.println("["+evaluation.getAbilityUnit()+"] 평가지 생성이 실패했습니다.");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//sno랑 능력단위를 검색해서 해당 능력단위의 eno를 얻어와서 리턴한다.
	public int getEvaluationEno(int sno, String abilityUnit)
	{
		final String QUERY = "SELECT 평가번호 FROM 평가 WHERE 학생번호 = ? AND 능력단위명 = ?";
		
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setInt(1, sno);
			psmt.setString(2, abilityUnit);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				//평가번호를 가져온다.
				int eno = rs.getInt("평가번호");
				return eno;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.CloseDatabaseResource();
		}
		
		//가져오지 못했음.
		return -1;
	}
	
	
	
	private Connection getConnection()
	{
		Connection conn = null;
		DataSource ds = null;
		try
		{
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
			
			conn = ds.getConnection();
		} 
		catch (NamingException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	private void CloseDatabaseResource()
	{
		try
		{
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
