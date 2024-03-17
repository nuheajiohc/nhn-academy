<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>학생 정보 조회</title>
</head>
<body>
    이름: ${student.id}<br />
    이메일: ${student.email}<br />
    <c:if test="${hideInfo != true}">
        성적: ${student.score}<br />
        평가: ${student.comment}<br />
    </c:if>
    <br />
    <a href="/student/${student.id}/modify">정보 수정</a><br />
    <a href="/student/studentlist">학생 목록</a>
</body>
</html>
