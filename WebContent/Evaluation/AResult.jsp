<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! boolean flag = false; %>
<%
	//평가지를 이미 작성한 이력이 있다면 결과를 보여준다.
	if(session.getAttribute("appFlag") != null)
	{
		flag = (boolean)session.getAttribute("appFlag");
		if(flag != true) {response.sendRedirect("index.do");}
	}
	else response.sendRedirect("index.do");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>애플리케이션 배포 결과</title>
</head>
<body>
	<div style="width:80%; height:auto; margin:0 auto;">
        <table border="1" style="border-spacing: 0px 0px; width:100%;">
        <tr>
        	<th colspan="4" style="border-bottom:1px solid black; background-color:gray; color:white;">
        	애플리케이션 배포 능력단위 진단결과</th>
        </tr>	
            <tr>
                <th style="width:240px; background-color:#D5D5D5;">진단영역</th>
                <th style="width:200px; background-color:#D5D5D5;">문항수</th>
                <th style="width:230px; background-color:#D5D5D5;">점수</th>
                <th style="background-color:#D5D5D5;">점수÷문항 수</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">애플리케이션 배포환경 구성하기</th>
                <th>4</th>
                <th>${app1 }</th>
                <th>${app1Sum }</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">애플리케이션 소스 검증하기</th>
                <th>3</th>
                <th>${app2 }</th>
                <th>${app2Sum }</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">애플리케이션 빌드하기</th>
                <th>5</th>
                <th>${app3 }</th>
                <th>${app3Sum }</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">애플리케이션 배포하기</th>
                <th>4</th>
                <th>${app4 }</th>
                <th>${app4Sum }</th>
            </tr>
            <tr>
                <th style="background-color:#D5D5D5;">합계</th>
                <th>16</th>
                <th>${app1 + app2 + app3 + app4 }</th>
                <th>${AresultSum }</th>
            </tr>
        </table>  
	     <button style="width:100%;"onclick="location.href='index.do'">메인 페이지로</button>
     </div>
</body>
</html>