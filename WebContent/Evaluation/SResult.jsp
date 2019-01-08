<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! boolean flag = false; %>
<%
	//평가지를 이미 작성한 이력이 있다면 결과를 보여준다.
	if(session.getAttribute("smartWebAndAppFlag") != null)
	{
		flag = (boolean)session.getAttribute("smartWebAndAppFlag");
		if(flag != true) {response.sendRedirect("index.do");}
	}
	else response.sendRedirect("index.do");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스마트 웹&앱 구현 결과</title>
</head>
<body>
	<div style="width:80%; height:auto; margin:0 auto;">
        <table border="1" style="border-spacing: 0px 0px; width:100%;">
        <tr>
        	<th colspan="4" style="border-bottom:1px solid black; background-color:gray; color:white;">
        	스마트 웹&앱 구현 능력단위 진단결과</th>
        </tr>	
            <tr>
                <th style="width:240px; background-color:#D5D5D5;">진단영역</th>
                <th style="width:200px; background-color:#D5D5D5;">문항수</th>
                <th style="width:230px; background-color:#D5D5D5;">점수</th>
                <th style="background-color:#D5D5D5;">점수÷문항 수</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">스마트 웹 개발</th>
                <th>3</th>
                <th>${smartWeb }</th>
                <th>${smartWebSum }</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">스마트 앱 개발</th>
                <th>3</th>
                <th>${smartApp }</th>
                <th>${smartAppSum}</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">합계</th>
                <th>6</th>
                <th>${smartWeb + smartApp }</th>
                <th>${resultSum }</th>
            </tr>
        </table>  
	     <button style="width:100%;"onclick="location.href='index.do'">메인 페이지로</button>
     </div>
</body>
</html>