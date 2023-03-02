<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userInfo</title>
</head>
<body>
	<c:import url="/default/header" />
	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<c:redirect url="login" />
		</c:when>
		<c:otherwise>
			
			<div align="center">
				<h1>개인 정보</h1>
				아이디 : ${member.id } <br> 
				비밀번호 : ${member.pw }<br>
				이름 : ${member.userName } <br>
				닉네임 : ${member.nickName } <br>
				이메일 : ${member.email } <br>
				전화번호 : ${member.mobile } <br><br>
				<button type="button" onclick="location.href='userUpdate?id=${member.id }'">회원 수정</button>
				<button type="button" onclick="location.href='userDelete?id=${member.id }'">회원 삭제</button>
			</div>	
		</c:otherwise>
	</c:choose>
	<c:import url="/default/footer" />
</body>
</html>



















