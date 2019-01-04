<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style> th{text-align:left;}</style>
<script type="text/javascript" src="js/custom.js"></script>
</head>

<body>

<h1>회원가입</h1>

<form action="register.do" method="post" name="frm">
	<table>
		<tr><th>이름: </th><td><input type="text" name="name"/></td></tr>
		<tr><th>아이디: </th><td><input type="text" name="id"/></td></tr>
		<tr><th>비밀번호: </th><td><input type="password" name="pwd"/></td></tr>
		<tr><th>비밀번호 확인: </th><td><input type="password" name="pwdCheck"/></td></tr>
	</table>
	
	<input type="submit" value="회원가입" onclick="return registerConfirm()"/>
	<input type="reset" value="재입력"/>
	<input type="button" value="뒤로가기" onclick="history.back()"/>
</form>


</body>
</html>