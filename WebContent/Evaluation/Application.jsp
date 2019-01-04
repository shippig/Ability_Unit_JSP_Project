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
</style>
</head>
<body>
    <div id="wrap" align="center">
    <form method="post" action="application.do">
        <table cellspacing=0 border=1>
            <tr>
                <th id="title">(사후) 학습자 자가 진단 평가</th>
            </tr>
        </table>
        <br>
        <table cellspacing=0 border=1>
            <tr>
            <%--
            // 평가번호, 교육기관, 교육기간, 평가일시, 과정명, 평과목, 평가자, 능력단위명, 능력단위 요소
			// no, institute, time, date, course, subject, appraiser, abilityUnit, element
             --%>
                <th id="cul">교육기관</th>
                <th style="width: 170px;">${evaluation.institute }</th>
                <th id="cul">교육기간</th>
                <th colspan="3">${evaluation.time }</th>
            </tr>
            <tr>
                <th id="cul">평가일시</th>
                <th>${evaluation.date }</th>
                <th id="cul">과정명</th>
                <th>${evaluation.course } </th>
                <th id="cul">학생명</th>
                <th style="width: 130px;"> </th>
            </tr>
            <tr>
                <th id="cul">교과목</th>
                <th colspan="3">${evaluation.subject }</th>
                <th id="cul">평가자</th>
                <th>${evaluation.appraiser }</th>
            </tr>
            <tr>
                <th id="cul">능력단위명</th>
                <th colspan="5">${evaluation.abilityUnit }</th>
            </tr>
            <tr>
                <th id="cul">능력단위요소</th>
                <th colspan="5">${evaluation.element }</th>
            </tr>
        </table>
        <br>
        <table cellspacing=0 border=1>
            <tr>
                <th id="cul2" style="width:100px;">진단영역</th>
                <th style="width:640px;">진 단 문 항</th>
                <th>매우<br>미흡</th>
                <th>미흡</th>
                <th>보통</th>
                <th>우수</th>
                <th>매우<br>우수</th>
            </tr>
            <tr>
                <th rowspan="4"></th>
                <th></th>
                <th><input type="radio" name="1"></th>
                <th><input type="radio" name="1"></th>
                <th><input type="radio" name="1"></th>
                <th><input type="radio" name="1"></th>
                <th><input type="radio" name="1"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th rowspan="3"></th>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th rowspan="5"></th>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th rowspan="4"></th>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
            <tr>
                <th></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
                <th><input type="radio"></th>
            </tr>
        </table>
        	<br>
        	<input type="submit" value="확인"/>
        	<input type="reset" value="다시작성"/>
       </form>
    </div>
</body>
</html>