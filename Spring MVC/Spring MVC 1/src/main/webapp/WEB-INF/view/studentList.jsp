<%--
  Created by IntelliJ IDEA.
  User: choijaehun
  Date: 1/9/24
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>학생 목록</title>
</head>
<body>
<h1>학생 목록</h1>
<table>
  <thead>
  <th>아이디</th>
  <th>이름</th>
  <th>이메일</th>
  <th>성적</th>
  <th>평가</th>
  <th>조회</th>
  </thead>
  <tbody>
  <c:forEach var="student" items="${students}">
    <tr>
      <td>${student.id}</td>
      <td>${student.name}</td>
      <td>${student.email}</td>
      <td>${student.score}</td>
      <td>${student.comment}</td>
      <td><a href="/student/${student.id}">조회</a></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<a href="/student/register">학생 등록</a>
</body>
</html>
