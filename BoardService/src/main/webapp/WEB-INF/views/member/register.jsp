<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
<script src="../resources/check.js"></script>
<script src="../resources/infoChk.js"></script>
</head>
<body>
	<c:import url="/default/header" />
	<div align="center">
		<h1>회원 등록</h1>
		<p>${msg }
		<table>
			<tr>
				<td>
					<form action="register" method="post" id="f">
						<input type="text" name="id" placeholder="아이디" id="id" onchange="sendId()" > <br>
						<span id="idChk" style="font-size: 12px;" > 
							5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다
						</span> <br><br>
						<input type="password" name="pw" placeholder="비밀번호" id="pw"><br><br>
						<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm" onchange="pwCheck()"> <br>
						<label id="label" style="font-size: 12px;"> </label><br>
						<input type="text" name="userName" id="userName" placeholder="이름" onchange="sendName()" ><br>
						<span id="nameChk" style="font-size: 12px;"> 
							
						</span> <br>
						<input type="text"	name="nickName" id="nickName" placeholder="닉네임" onchange="sendNick()" ><br>
						<span id="nickChk" style="font-size: 12px;"> 
							닉네임은 한글,영어,숫자만 입력 가능합니다
						</span> <br>
						<input type="text" name="email" id="email" placeholder="이메일">
						<input type="button" name="mail-auth-btn" value="인증 번호 발송" style="width: 100px;" onclick="emailAuth()" ><br><br>

						<input type="text" name="authNumber" id="authNumber" placeholder="인증 번호">
						<input type="button" name="mail-chk-btn" value="인증 확인" style="width: 100px;" onclick="emailChk()" ><br>
						<span id="auth-success" style="font-size: 12px;"> 
							
						</span> <br><br>
						<input type="text" name="mobile" placeholder="전화번호"><br><br>
						<input type="button" value="회원가입" onclick="allCheck()" style="width: 100px;"><br>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<c:import url="/default/footer" />
</body>


<style>
	input {
	width: 300px;
	height: 22px;
	resize: none;
}
	td {
	resize: none;
}
	
</style>

</html>



