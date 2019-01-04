<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사후평가리스트</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="wrap" align="center">
		<form action="SmartWeb&Apps.do" method="post"></form>
        <table cellspacing=0 border=1>
            <tr>
                <th id="title">(사후) 학습자 자가 진단 평가</th>
            </tr>
        </table>
        <br>
        <table cellspacing=0 border=1>
            <tr>
                <th id="cul">교육기관</th>
                <th style="width: 170px;"> </th>
                <th id="cul">교육기간</th>
                <th colspan="3"> </th>
            </tr>
            <tr>
                <th id="cul">평가일시</th>
                <th> </th>
                <th id="cul">과정명</th>
                <th> </th>
                <th id="cul">학생명</th>
                <th style="width: 130px;"> </th>
            </tr>
            <tr>
                <th id="cul">교과목</th>
                <th colspan="3"> </th>
                <th id="cul">평가자</th>
                <th>박 수 민 (인)</th>
            </tr>
            <tr>
                <th id="cul">능력단위명</th>
                <th colspan="5"> </th>
            </tr>
            <tr>
                <th id="cul">능력단위요소</th>
                <th colspan="5"> </th>
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
                <th rowspan="3"></th>
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
        </table>
        <br>
        <br>
        <h4>
            <center>[진단결과]</center>
        </h4>
        <table cellspacing=0 border=1>
            <tr>
                <th style="width:240px">진단영역</th>
                <th style="width:200px">문항수</th>
                <th style="width:230px">점수</th>
                <th>점수%문항 수</th>
            </tr>
            <tr>
                <th> &nbsp;</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th> &nbsp; </th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th>합계</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </table>
    </div>
</body>
</body>
</html>