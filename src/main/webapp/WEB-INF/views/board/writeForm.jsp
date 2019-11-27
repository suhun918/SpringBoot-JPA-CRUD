<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/write" method="post">
		<table>

			<tr>
				<th>Title</th>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<th>Content</th>
				<td><textarea row="5" cols="40" name="content"></textarea></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="글쓰기 완료" /></td>
			</tr>
		</table>
	</form>
</body>
</html>