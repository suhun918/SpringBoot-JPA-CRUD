<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/user/login" method="post">
		<table>

			<tr>
				<th>Email</th>
				<td><input type="email" name="email" /></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="password" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="로그인" /></td>
			</tr>
		</table>
	</form>
</body>
</html>