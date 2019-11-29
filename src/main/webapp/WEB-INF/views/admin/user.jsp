<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/admin/update" method="post">
	<input type = "hidden" value="${user.id}" name="id"/><br/>
	<input type = "hidden" value="${user.password}" name="password"/><br/>
	<input type = "hidden" value="${user.phone}" name="phone"/><br/>
	<input type = "hidden" value="${user.email}" name="email"/><br/>
	
	<input type = "text" name = "username" value="${user.username}" readonly="readonly"/><br/>
	  <select name ="role" class="role">
      <option value ="user" <c:if test="${user.role == 'user'}">selected</c:if>>user</option>
      <option value ="admin" <c:if test="${user.role == 'admin'}">selected</c:if>>admin</option>
   </select>
	<input type="submit" value="변경"/>
</form>
</body>
</html>