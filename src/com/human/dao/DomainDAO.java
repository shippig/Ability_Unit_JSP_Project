package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.human.dto.DomainDTO;

public class DomainDAO
{
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private static final boolean HAVE_A_DOMAIN = true;
	private static final boolean DOMAIN_NONE= false;
	
	private static DomainDAO instance = new DomainDAO();
	
	public static DomainDAO getInstance()
	{
		return instance;
	}
	
	// 진단영역 DTO를 넘겨받아서 데이터베이스에 INSERT 한다.
	public void appendDomain(DomainDTO domain)
	{
		final String QUERY = "INSERT INTO 진단영역(진단번호, 진단영역, 평가번호) VALUES (진단번호_SEQ.NEXTVAL, ?, ?)";
	
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setString(1, domain.getDomain());
			psmt.setInt(2, domain.getEno());
			
			int r = psmt.executeUpdate();
			if(r != -1) 
				System.out.println("["+domain.getDomain()+"] 진단영역 추가에 성공했습니다.");
			else
				System.out.println("["+domain.getDomain()+"] 진단영역 추가에 실패했습니다.");
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.CloseDatabaseResource();
		}
	}
	
	//평가번호로 검색해서 해당 평가번호의 모든 진단영역을 ArrayList에 담는다.
	public ArrayList<DomainDTO> getDomainList(int eno)
	{
		final String QUERY = "SELECT * FROM 진단영역 WHERE 평가번호 = ? ORDER BY 진단번호 ASC";
		ArrayList<DomainDTO> list = new ArrayList<DomainDTO>();
		
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setInt(1, eno);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				DomainDTO domain = new DomainDTO();
				domain.setDomain(rs.getString("진단영역"));
				domain.setDno(rs.getInt("진단번호"));
				domain.setEno(rs.getInt("평가번호"));
				
				list.add(domain);
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
		
		return list;
	}
	
	// 평가번호와 진단영역으로 검색해서 해당하는 진단영역이 있는지 체크한다.
	public boolean isDomain(int eno, String domain)
	{
		//해당 능력단위에 진단영역을 검색해서 존재한다면 추가하지 않는다.
		final String QUERY = "SELECT 진단영역 FROM 진단영역 WHERE 평가번호 = ? AND 진단영역 = ?";
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setInt(1, eno);
			psmt.setString(2, domain);
			
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				System.out.println("["+domain+"]진단영역이 존재합니다. (진단영역 생성 불필요)");
				return HAVE_A_DOMAIN;				
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
		
		System.out.println("["+domain+"]진단영역이 존재하지 않습니다. (진단영역 생성 필요)");
		return DOMAIN_NONE;
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
