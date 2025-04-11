<%@ page import="java.util.List, com.mycom.myapp.dto.BookDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	BookDto bookDto = (BookDto) request.getAttribute("bookDto");
%>
<html>
<head>
	<title>Title</title>
</head>
<body>
<h1>도서 상세 및 수정</h1>
<form action="/books/update" method="post">
	<input type="text" name="bookId" value="<%=bookDto.getBookId()%>"></input> <br>
	<input type="text" name="bookName" value="<%=bookDto.getBookName()%>"></input> <br>
	<input type="text" name="publisher" value="<%=bookDto.getPublisher()%>"></input> <br>
	<input type="text" name="price" value="<%=bookDto.getPrice()%>"></input> <br>
	<button type="submit">수정</button>
</form>
<a href="/books/list">목록</a>
<a href="/books/delete/=<%=bookDto.getBookId()%>">삭제</a>
<hr>
</body>
</html>
