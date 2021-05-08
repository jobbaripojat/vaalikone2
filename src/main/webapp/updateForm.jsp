<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("username")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("login.jsp");
%>
<form action='../updatequestion' method='post'>
<input type='text' name='id' value='${requestScope.question.QUESTION_ID }' readonly>
<input type='text' name='question' value='${requestScope.question.QUESTION }'>
<input type='submit' name='ok' value='OK'>
</form>
</body>
</html>