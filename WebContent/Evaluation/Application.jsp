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
#wrap {width:75%; margin:0 auto;}
table td{text-align:center}
.evaluation_title, .evaluation_cont, .evaluation_domain{width:100%; border-spacing: 0px 0px;}
.evaluation_title tr #title{font-size:25px; background-color:#D5D5D5}
.evaluation_cont .culumn {background-color:#D5D5D5;}
.evaluation_domain .domain, .diag {width: 68px; border-bottom:solid 1px;}
.evaluation_domain .question, .lastQuestion{text-align:left; height:50px;}
.evaluation_domain .question{border-bottom:dashed 1px;}
.evaluation_domain .score{border-bottom:dashed 1px;}
.evaluation_domain .title{padding-top:30px; padding-bottom:30px;}
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
                <td style="width: 130px;">${session.getAttribute("name") } </td>
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
        <table border='1' class="evaluation_domain">
            <tr>
                <th class="domain">진단영역</th>
                <th class="diag" style="width:58%;">진 단 문 항</th>
                <th class="diag">매우<br>미흡</th>
                <th class="diag">미흡</th>
                <th class="diag">보통</th>
                <th class="diag">우수</th>
                <th class="diag">매우<br>우수</th>
            </tr>
            <tr>
                <th rowspan="4" class="title">애플리 케이션 배포환경 구성하기</th>
                <th class="question">1.1 애플리케이션 빌드와 배포를 위한 환경 구성 방안을 계획할 수 있다.</th>
                <th class="score"><input type="radio" name="1.1"></th>
                <th class="score"><input type="radio" name="1.1"></th>
                <th class="score"><input type="radio" name="1.1"></th>
                <th class="score"><input type="radio" name="1.1"></th>
                <th class="score"><input type="radio" name="1.1"></th>
            </tr>
            <tr>
                <th class="question">1.2 애플리케이션 배포를 위한 도구와 시스템을 결정할 수 있다.</th>
                <th class="score"><input type="radio" name="1.2"></th>
                <th class="score"><input type="radio" name="1.2"></th>
                <th class="score"><input type="radio" name="1.2"></th>
                <th class="score"><input type="radio" name="1.2"></th>
                <th class="score"><input type="radio" name="1.2"></th>
            </tr>
            <tr>
                <th class="question">1.3 결정한 애플리케이션 배포 한경을 위한 도구와 시스템을 설치할 수 있다.</th>
                <th class="score"><input type="radio" name="1.3"></th>
                <th class="score"><input type="radio" name="1.3"></th>
                <th class="score"><input type="radio" name="1.3"></th>
                <th class="score"><input type="radio" name="1.3"></th>
                <th class="score"><input type="radio" name="1.3"></th>
            </tr>
            <tr>
                <th class="lastQuestion">1.4 설치한 시스템과 도구 운영을 위해 상세 구성 및 설정을 할 수 있다.</th>
                <th class="score"><input type="radio" name="1.4"></th>
                <th class="score"><input type="radio" name="1.4"></th>
                <th class="score"><input type="radio" name="1.4"></th>
                <th class="score"><input type="radio" name="1.4"></th>
                <th class="score"><input type="radio" name="1.4"></th>
            </tr>
        </table>
    </div>
</body>
</html>