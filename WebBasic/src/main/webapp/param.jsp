<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 실행되는 자바코드 
	String searchWord = request.getParameter("searchWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Your searchWord : <%= searchWord%></h1>
</body>
</html>

<%--
1. jsp 는 compiler 에 의해 servlet 변환
2. Html like 코드 <= Html 안에 java 코드를 위치
3. <%__%>
--%>