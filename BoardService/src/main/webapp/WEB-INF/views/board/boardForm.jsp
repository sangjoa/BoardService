<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/default/header" />

<center>
	<c:choose>
		<c:when test="${boardList.isEmpty()== true}">
			<h2>게시글을 작성해주세요!!</h2>
		</c:when>
		<c:otherwise>
			<table style="width: 650px;">
				<thead>
					<tr>
						<th style="width: 330px; height: 20px;" align="center">제 목</th>
						<th style="width: 80px; height: 20px;" align="center">작성자</th>
						<th style="width: 120px; height: 20px;" align="center">작성일</th>
						<th style="width: 80px; height: 20px;" align="center">조회수</th>
					</tr>
				</thead>
					<tr>
						<td style="width: 330px; height: 20px;" align="center"><hr /></td>
						<td style="width: 80px; height: 20px;" align="center"><hr /></td>
						<td style="width: 120px; height: 20px;" align="center"><hr /></td>
						<td style="width: 80px; height: 20px;" align="center"><hr /></td>
					</tr>
				<tbody>
						
				<c:forEach var="boardList" items="${boardList }">
								<tr>
									<td style="width:330px; height:40px;" align="center"> <a href="viewForm?bI=${boardList.boardId }">${boardList.title } </a> </td>
									<td style="width: 80px; height: 40px;" align="center">${boardList.writer}</td>
									<td style="width: 120px; height: 40px;" align="center">${boardList.cDate}</td>
									<td style="width: 80px; height: 40px;" align="center">${boardList.vCount}</td>
								</tr>
					</c:forEach>
				</tbody>
			</c:otherwise>
		</c:choose>
	<tr>
		<td colspan=4><hr /></td>
	</tr>
	<tr>
	<c:choose>
		<c:when test="${not empty sessionScope.id }">		
		<td colspan=4 align="right">
			<input type="button" value='글쓰기'style="width: 100px;" onclick="location.href='${pageContext.request.contextPath}/board/writeForm'"/>
		 </td>
		</c:when>	
	</c:choose>
	</tr>
	<tr>
		<td colspan=5><hr /></td>
	</tr>
	</table>
	${page }
	<form action="/board/boardForm" method="post">	
		<table>
			<tr>
				<td><select name="select">
						<option>전체1</option>
						<option>제목</option>
						<option>작성자</option>
				</select>
				 <input type=text name='search' />
				 <input type=submit name='searchBtn' value='검색' style="width: 80px; "/>
				 </td>
			</tr>
		</table>
	</form>
</center>
<c:import url="/default/footer" />