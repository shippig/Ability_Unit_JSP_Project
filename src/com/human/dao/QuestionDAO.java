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

import com.human.dto.QuestionDTO;

public class QuestionDAO
{
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	private static QuestionDAO instance = new QuestionDAO();
	
	public static QuestionDAO getInstance()
	{
		return instance;
	}
	
	//DNO를 넘겨받아 해당 DNO의 진단문항을 String 배열로 넘겨줌
	public ArrayList<String> getQuestions(int dno)
	{
		final String QUERY = "SELECT 진단문항 FROM 진단문항 WHERE 진단번호 = ?";
		ArrayList<String> questions = new ArrayList<>();
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setInt(1, dno);
			rs = psmt.executeQuery();
			
			while(rs.next())
			{
				questions.add(rs.getString("진단문항"));
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
		
		return questions;	
	}
	
	//해당 진단영역의 진단 문항을 추가하는 메서드
	public void appendQuestion(String question, int dno)
	{
		final String QUERY = 
				"INSERT INTO 진단문항(문항번호, 진단문항, 진단번호) VALUES (문항번호_SEQ.NEXTVAL, ?, ?)";
		try
		{
			conn = this.getConnection();
			psmt = conn.prepareStatement(QUERY);
			psmt.setString(1, question);
			psmt.setInt(2, dno);
			
			int r = psmt.executeUpdate();
			if(r != -1)
				System.out.println("진단 문항 추가에 성공했습니다.");
			else
				System.out.println("진단 문항 추가에 실패했습니다.");
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
