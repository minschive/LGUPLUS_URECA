<%@ page import="java.util.List, com.mycom.myapp.dto.BookDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	List<BookDto> bookList = (List<BookDto>) request.getAttribute("bookList");
%>
<html>
<head>
	<title>Title</title>
</head>
<body>
<h1>도서 목록</h1>
<table>
	<thead>
	<tr>
		<th>bookId</th>
		<th>bookName</th>
		<th>publisher</th>
		<th>price</th>
	</tr>
	</thead>
	<tbody>
	<%
		for (BookDto bookDto : bookList) {
	%>
	<tr>
		<td> <a href="/books/detail/<%= bookDto.getBookId()%>"><%= bookDto.getBookId()%> </a> </td>
		<td><%= bookDto.getBookName()%></td>
		<td><%= bookDto.getPublisher()%></td>
		<td><%= bookDto.getPrice()%></td>
	</tr>
	<%
		}
	%>
	</tbody>
</table>
<hr>
<a href="/books/insertForm">등록</a>
<a href="/">홈</a>
</body>
</html>
