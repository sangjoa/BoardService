<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<script src="../resources/check.js"></script>
<script src="../resources/infoChk.js"></script>
</head>
<body>
	<c:import url="/default/header"/>
	<div align="center">
		<h1>회원 수정</h1>
		<p> ${msg }
		<table>
		<tr><td>
			<form action="userUpdate" method="post" id="f">
				<input type="text" id="id" value="${sessionScope.id }" readonly="readonly"><br><br>
				<input type="password" name="pw" placeholder="비밀번호" id="pw"><br><br>
				<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm" onchange="pwCheck()">
				<br> <label id="label" style="font-size: 12px;"></label><br><br>
				<input type="text" name="userName" id="userName" value="${member.userName }" readonly="readonly" ><br><br>
				<input type="text" name="nickName" id="nickName" value="${member.nickName }" onchange="sendNick()" ><br>
				<span id="nickChk" style="font-size: 12px;"> 
							
				</span> <br> 
				<input type="text" name="email" id = "email" value="${member.email }" >
				<input type="button" name="mail-auth-btn" value="인증 번호 발송" style="width: 100px;" onclick="emailAuth()" ><br><br>
				<input type="text" name="authNumber" id="authNumber" placeholder="인증 번호">
				<input type="button" name="mail-chk-btn" id = "mail-chk-btn" value="인증 확인" style="width: 100px;" onclick="emailChk()" ><br>
				<span id="auth-success" style="font-size: 12px;"> 
					
				</span> <br>
				<input type="text" name="mobile" value="${member.mobile }" ><br><br>
				<input type="button" value="회원수정" onclick="allCheck()" style="width: 100px;"><br>
			</form>
		</td></tr>
		</table>
	</div>
	<c:import url="/default/footer"/>
</body>

</html>

<style>
	input {
	width: 300px;
	height: 22px;
	resize: none;
}
	td {
	resize: none;
}

