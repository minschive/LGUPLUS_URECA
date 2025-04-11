<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<h1>도서 등록</h1>
<form action="/books/insert" method="post">
	<input type="text" name="bookId"></input> <br>
	<input type="text" name="bookName"></input> <br>
	<input type="text" name="publisher"></input> <br>
	<input type="text" name="price"></input> <br>
	<button type="submit">등록</button>
</form>
<hr>
<a href="/books/list">목록</a>
</body>
</html>
