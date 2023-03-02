<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/default/header" />
<center>
	${msg }
	<table style="width: 1000px;">
		<tr>
			<td style="width: 300px; height: 40px;" valign="middle"><h2>${board.title }</h2></td>
		</tr>
		<tr>
			<td colspan=3><hr /></td>
		</tr>
		<tr>
			<td style="width: 300px; height: 40px;" valign="top">${nickName }</td>
			<td style="width: 300px; height: 40px;" align="center" valign="top">${board.mDate }</td>
			<td style="width: 300px; height: 40px;" align="right" valign="top">${board.fName }</td>
		</tr>
		<tr>
			<td colspan=3 style="width: 650px; height: 300px" valign="top"><hr />
				<pre>${board.content }	</pre></td>
		</tr>
		<tr>
			<td colspan=3><hr /></td>
		</tr>
	</table>
<div align="center" id="boardSelect">
	<form action="/board/viewForm" method="post"
		style="margin-bottom: 20px">
		<input type="hidden" name="bI" value="${board.boardId }">
		<textarea name="content" rows="7" cols="90" style="resize: none;"
			placeholder="내용을 입력하세요."></textarea>
		<input type="submit" value="등록"
			style="width: 120px; height: 35px; margin-bottom: 20px">
	</form>
	<table align="right" style="margin-bottom: 20px; margin-right: 29%">
		<tr>
			<td colspan=3 align="right" valign="top"><c:choose>
					<c:when test="${not empty sessionScope.id }">
						<input type="button" style="width: 60px;" value='글쓰기'
							onclick="location.href='${pageContext.request.contextPath}/board/writeForm'" />
					</c:when>
				</c:choose>
				 <c:choose>
					<c:when test="${board.writer eq sessionScope.id}">
						<input type=button style="width: 60px;" value='수정'
							onclick="location.href='${pageContext.request.contextPath}/board/updateForm?bI=${board.boardId}'" />
						<input type=button style="width: 60px;" value='삭제'
							onclick="location.href='${pageContext.request.contextPath}/board/deleteForm?bI=${board.boardId }&fName=${board.fName }'" />
					</c:when>
				</c:choose> <input type=button style="width: 60px;" value='목록'
				onclick="location.href='${pageContext.request.contextPath}/board/boardForm'" /></td>
		</tr>
	</table>
	<br>
	<br>
</div>
<div align="center" id="commentPart">
	<c:if test="${not empty commentList}">
		<hr>
		<c:forEach var="commentList" items="${commentList }">
		 <div id="commentView">
				<table style="align-content: center; margin-right: 10%">
					<tr>
						<td>${commentList.writer }&nbsp; ${commentList.cTime } &nbsp;
						<c:if test="${sessionScope.id eq commentList.writer }">
							<input type="hidden" id="commentId" value="${commentList.commentId}">
							<a href="javascript:commentDelete(${commentList.commentId })">
							 <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTrpS0aWOR90DhYrNqtOxQLmoTxRU1EVOpww&usqp=CAU">
							</a>
						</c:if>
						</td><br>
					</tr>
					<tr>
						<td><textarea rows="3" cols="80" style="resize: none;" readonly="readonly">${commentList.content }</textarea></td>
					</tr>
				</table>
		 </div>
		</c:forEach>
	</c:if>
</div>
</center>

<script type="text/javascript">
	var req
	
	function commentDelete(commentId){
		console.log(commentId)
		
		var ans = confirm("댓글을 삭제하시겠습니까?")
		if(ans){
		req = new XMLHttpRequest();
		req.open('post','${pageContext.request.contextPath}/ajax/deleteComment')
		req.send(commentId)
		}
		location.reload();
	}	

</script>

<style>
	img{
		width: 14px;
		height: 14px;
	}
</style>


<c:import url="/default/footer" />



