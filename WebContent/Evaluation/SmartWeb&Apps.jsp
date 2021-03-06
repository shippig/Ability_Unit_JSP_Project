<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사후평가리스트</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

<style>
#wrap {width:76%; margin:0 auto;}
table td{text-align:center}
.evaluation_title, .evaluation_cont, .evaluation_domain{width:100%; border-spacing: 0px 0px;}
.evaluation_title tr #title{font-size:25px; background-color:#D5D5D5}
.evaluation_cont .culumn {background-color:#D5D5D5;}
.evaluation_domain .domain, .diag {width: 67px; border-bottom:solid 1px}
.evaluation_domain .question, .lastQuestion{text-align:left; height:50px;}
.evaluation_domain .question{border-bottom:dashed 1px;}
.evaluation_domain .score{border-bottom:dashed 1px;}
.evaluation_domain .title{padding-top:30px; padding-bottom:30px;}
#controlForm {margin:30px 0px auto auto;}
#controlForm .frmStyle {width:32.5%; padding:500px;}
</style>
</head>
<body>
    <div id="wrap" align="center">
    <!-- Evaluation Title(평가지 제목) -->
        <table border='1' class="evaluation_title">
            <tr>
                <th id="title">(사후) 학습자 자가 진단 평가</th>
            </tr>
        </table><br>
        <!-- Evaluation Contents(평가지 내용) -->
        <table border='1' class="evaluation_cont">
            <tr>
                <th class="culumn">교육기관</th>
                <td style="width: 170px;">${evaluation.institute } </td>
                <th class="culumn">교육기간</th>
                <td colspan="3">${evaluation.time } </td>
            </tr>
            <tr>
                <th class="culumn">평가일시</th>
                <td>${evaluation.date } </td>
                <th class="culumn">과정명</th>
                <td>${evaluation.course } </td>
                <th class="culumn">학생명</th>
                <td style="width: 130px;">${name } </td>
            </tr>
            <tr>
                <th class="culumn">교과목</th>
                <td colspan="3">${evaluation.subject } </td>
                <th class="culumn">평가자</th>
                <td>${evaluation.appraiser }</td>
            </tr>
            <tr>
                <th class="culumn">능력단위명</th>
                <td colspan="5">${evaluation.abilityUnit }</td>
            </tr>
            <tr>
                <th class="culumn">능력단위요소</th>
                <td colspan="5">${evaluation.element }</td>
            </tr>
        </table><br>
        
        <!-- Evaluation Domain(평가지 진단영역)-->
        <form method="post" action="SmartWeb&Apps.do" name="frm">
        <table border='1' class="evaluation_domain">
            <tr>
                <th class="domain" style="width:7.5%;">진단영역</th>
                <th class="diag" style="width:58%;">진 단 문 항</th>
                <th class="diag">매우<br>미흡</th>
                <th class="diag">미흡</th>
                <th class="diag">보통</th>
                <th class="diag">우수</th>
                <th class="diag">매우<br>우수</th>
            </tr>
            
            <%-- >>>> 시작  --%>
            <c:forEach var="domain" items="${domainList }">
            
            <%-- >>>> (1)  --%>
            	<c:if test="${domain.getDomain() eq '스마트 웹 개발' }">
            		<c:forEach var="i" begin="0" end="${questionList1.size()-1 }" step="1" varStatus="status">
            <%--4번 반복하는대 0일 때에는  --%>
            	<c:choose>
            		<c:when test="${status.index eq 0 }">
	            		<tr>
	            			<th rowspan="${questionList1.size() }" class="title">${domain.getDomain() }</th> 
	            			<th class="question">${questionList1.get(status.index) }</th>
	            			
	            			<%-- name 값 설정 이곳은 "1"임.--%>
	            			 <c:set var="index" value="1"></c:set>
	            			<c:forEach var="j" begin="1" end="5" step="1" varStatus="status">
	            				<th class="score"><input type="radio" value="${status.index }" name="${index }"></th>
	            			</c:forEach>
	            		</tr>
            		</c:when>
            		<%-- 1, 2, 3일 때에는 --%>
            		<c:otherwise>
            			<tr>
            				<th class="question">${questionList1.get(status.index) }</th>
            				<c:set var="index" value="${index + 1 }"></c:set>
            				<c:forEach var="j" begin="1" end="5" step="1" varStatus="status">
            				<%-- name 값 설정 이곳은 "2,3,4"임.--%>
            					<th class="score"><input type="radio" value="${status.index }" name="${index }"></th>
            				</c:forEach>
            				<c:set var="index" value="${index + 1 }"></c:set>
            			</tr>
            		</c:otherwise>
           		</c:choose>
            	</c:forEach>
            	</c:if>
            	
            	<%-- >>>> (2)  --%>
            	<c:if test="${domain.getDomain() eq '스마트 앱 개발' }">
            	<c:forEach var="i" begin="0" end="${questionList2.size()-1 }" step="1" varStatus="status">
            <%--4번 반복하는대 0일 때에는  --%>
            	<c:choose>
            		<c:when test="${status.index eq 0 }">
	            		<tr>
	            			<th rowspan="${questionList2.size() }" class="title">${domain.getDomain() }</th> 
	            			<th class="question">${questionList2.get(status.index) }</th>
	            			<c:set var="index" value="${index + 1 }"></c:set>
	            			<%-- name 값 설정 이곳은 "5"임.--%>
	            			<c:forEach var="j" begin="1" end="5" step="1" varStatus="status">
	            				<th class="score"><input type="radio" value="${status.index }" name="${index }"></th>
	            			</c:forEach>
	            		</tr>
	            		<c:set var="index" value="${index + 1 }"></c:set>
            		</c:when>
            		<%-- 1, 2, 3일 때에는 --%>
            		<c:otherwise>
            			<tr>
            				<th class="question">${questionList2.get(status.index) }</th>
            				<c:set var="index" value="${index + 1 }"></c:set>
            				<c:forEach var="j" begin="1" end="5" step="1" varStatus="status">
            				<%-- name 값 설정 이곳은 "6,7,8"임.--%>
            					<th class="score"><input type="radio" value="${status.index }" name="${index }"></th>
            				</c:forEach>
            			</tr>
            			<c:set var="index" value="${index + 1 }"></c:set>
            		</c:otherwise>
           		</c:choose>
            	</c:forEach>
            	</c:if>
           </c:forEach>	
        </table>
        <div id="controlForm">
        	<input class="frmStyle" type="submit" value="작성완료"/> 
        	<input class="frmStyle" type="reset" value="다시작성" /> 
        	<input class="frmStyle" type="button" value="뒤로가기" onclick="window.history.go(-1)"/>
        </div>
        </form>
    </div>
</body>
</html>