<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/default/header" />
<center>
	<form action="updateForm" method="post">
		<input type="hidden" name='bI' value="${bI }" />
		<p> ${msg }
		<table style="width: 650px; ">
			<tr>
				<td style="width: 80px; height:40px;" align="right">작성자</td>
				<td style="width: 570px; height:40px;">
					<input type=text name='id' value="${nickName }" disabled="disabled"/> 
				</td>
			</tr>
			<tr>
				<td  style="width: 80px; height:40px;" align="right">제 목</td>
				<td style="width: 570px; height:40px;">
					<input type=text name='title' style="width: 500px; " value="${board.title }"/> 
				</td>
			</tr>
			<tr>
				<td colspan=2 align="right">
				<textarea name="content" style="width: 650px; height: 300px">${board.content }</textarea></td>
			</tr>
			<tr>
				<td align='center' height=40 colspan=2>
					<input type=submit value='수정' style="width: 120px; "/>
					<input type=reset value='취소' style="width: 120px; " onclick="location.href='${pageContext.request.contextPath}/board/viewForm?bI=${board.boardId}'"/>	 
				</td>
			</tr>
		</table>
	</form> 
</center>
<c:import url="/default/footer" />