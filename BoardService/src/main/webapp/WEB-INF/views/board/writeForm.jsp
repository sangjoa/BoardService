<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/default/header" />
<center>
	<form method="post" enctype="multipart/form-data">
		<table style="width: 650px;">
			<tr>
				<td style="width: 80px; height: 40px;" align="right">작성자</td>
				<td style="width: 570px; height: 40px;"><input type=text name='writer'  readonly="readonly"  value="${sessionScope.id }"></td>
			</tr>
			<tr>
				<td style="width: 80px; height: 40px;" align="right">제 목</td>
				<td style="width: 570px; height: 40px;"><input type=text name='title' style="width: 500px;" placeholder="제목을 입력하세요." /></td>
			</tr>
			<tr>
				<td colspan=2 align="right">
				<textarea style="width: 650px; height: 300px" name="content" placeholder="내용을 입력하세요."></textarea></td>
			</tr>
			<tr>
				<td align='right' height=40 colspan=2> <input type=file style="width: 300px;" name="fName" /> </td>
			</tr>
			<tr>
				<td align='center' height=40 colspan=2>
					<input type=submit value='글쓰기' style="width: 120px;" />
					<input type=reset value='취소' style="width: 120px;" onclick="location.href='${pageContext.request.contextPath}/board/boardForm'" />
				</td>
			</tr>
		</table>
	</form>
</center>

<c:import url="/default/footer" />