package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.human.dto.StudentDTO;

public class StudentDAO
{
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	
	private static final boolean STUDENT_SEARCH_FAIL = false;
	private static final boolean STUDENT_SEARCH_SUCCESS = true;
	private static StudentDAO instance = new StudentDAO();
	
	public static StudentDAO getInstance()	
	{
		return instance;
	}
	
	public void isStudent(String id)
	{
		
	}
	
	public int getStduentNo(String id)
	{
		final String QUERY = "SELECT 학생번호 FROM 학생 WHERE 학생ID = ?";
		
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			if(rs.next())
			{
				int sno = rs.getInt("학생번호");
				return sno;
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
		
		return -1;
	}

	public void addStudent(StudentDTO student)
	{
		final String QUERY = 
				"INSERT INTO 학생(학생번호, 학생이름, 학생ID, 학생PWD) VALUES (학생_SEQ.NEXTVAL, ?, ?, ?)";
		
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			
			psmt.setString(1, student.getName());
			psmt.setString(2, student.getId());
			psmt.setString(3, student.getPassword());
			int r = psmt.executeUpdate();  
			
			if(r != 0) System.out.println(student.getName() + "학생 추가 완료");
			else System.out.println(student.getName() + "학생 추가 실패");
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
	
	// id 컬럼에 unique 줘야 함.
	public int getStudentNo(String id)
	{
		final String QUERY = "SELECT 학생번호 FROM 학생 WHERE 학생ID = ?";
		
		int no = -1;
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				no = rs.getInt("학생번호");
				return no;
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
		
		return no;
	}
	
	public boolean login(String id, String pwd)
	{
		final String QUERY = "SELECT 학생PWD FROM 학생 WHERE 학생ID = ?";
		
		conn = this.getConnection();
		try
		{
			psmt = conn.prepareStatement(QUERY);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next())
			{
				String dbPwd = rs.getString("학생PWD");
				if(pwd.equals(dbPwd))
				{
					System.out.println(id + "님이 로그인에 성공했습니다.");
					return STUDENT_SEARCH_SUCCESS;
				}
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
		
		System.out.println(id + "님이 로그인에 실패했습니다.");
		return STUDENT_SEARCH_FAIL;
	}
	
	public boolean idDoubleCheck(String id)
	{
		final String QUERY = "SELECT * FROM 학생 WHERE 학생ID = ?";
		
		conn = this.getConnection();
		try
		{
			psmt = conn.prepareStatement(QUERY);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			if(rs.next())
			{
				return STUDENT_SEARCH_SUCCESS;
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
		
		return STUDENT_SEARCH_FAIL;
		
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
