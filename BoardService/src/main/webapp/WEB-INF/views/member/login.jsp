<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="../resources/infoChk.js"></script>
</head>
<body>
	<c:import url="/default/header" />
	<div align="center">
		<h1>로그인</h1>
		<p>${msg }
		<table>
		<tr><td>
			<form action="/member/login" id="f">
				<input type="text" name="id" placeholder="아이디" id="id"> <br>
				<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
				<input type="button" value="로그인" onclick="loginCheck()"/><br>
			</form>
		</td></tr>
		</table>
	</div>
	<c:import url="/default/footer" />
</body>
</html>