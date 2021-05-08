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
<form action='../addquestion' method='post'>
<input id='id' type='hidden' name='id' value='0' placeholder='new id'>
<input id='question' type='text' name='question' value='' placeholder='New question'>
<input type='submit' name='ok' value='OK'>
</form>
</body>
</html>