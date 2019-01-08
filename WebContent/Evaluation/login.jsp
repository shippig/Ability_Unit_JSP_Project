<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%! String ses = ""; %>
<%
	// 로그인 세션이 남아있다면 로그아웃하거나 세션 유효기가진이 끝나기전까지 로그인을 유지한다.
	if((String)session.getAttribute("id") != null)
	{
		response.sendRedirect("index.do");
	}
	else ses = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style> th{text-align:left;}</style>
<script type="text/javascript" src="js/custom.js"></script>
</head>
<body>

<h1>로그인</h1>

<form action="login.do" method="post" name="frm">
	<table>
		<tr><th>아이디: </th><td><input type="text" name="id" /></td></tr>
		<tr>
			<th>비밀번호: </th>
			<td><input type="password" name="pwd"/></td>
		</tr>
	</table>
	
		<input type="submit" value="로그인" onclick="return loginConfirm()"/>
		<input type="button" value="회원가입" onclick="location.href='register.do'">
</form>


</body>
</html>