<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/custom.js"></script>

</head>
<body>
<h1>능력단위명 평가지 작성</h1>
<table>

	<tr>
		<th style="text-align:left;">애플리케이션 배포 </th>
		<td><input type="button" value="입장하기" onclick="location.href='Application.do'"/></td>
	</tr>
	<tr>
		<th style="text-align:left;">스마트 웹&앱 구현 </th>
		<td><input type="button" value="입장하기" onclick="location.href='SmartWeb&Apps.do'"/></td>
	</tr>
	<tr>
		<th style="text-align:left;">애플리케이션 배포 결과 </th>
		<td><input type="button" value="입장하기" onclick="location.href='AResult.do'"/></td>
	</tr>
	<tr>
		<th style="text-align:left;">스마트 웹&앱 구현 결과 </th>
		<td><input type="button" value="입장하기" onclick="location.href='SResult.do'"/></td>
		<td><button onclick="location.href='logout.do'">로그아웃</button></td>
	</tr>
</table>


</body>
</html>