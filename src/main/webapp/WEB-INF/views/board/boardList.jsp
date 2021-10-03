<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp" />

<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>카테고리</th>
			<th>제목</th>
			<th>닉네임</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${bList }" varStatus="vs">
			<tr>
				<td>${board.boardNo }</td>
				<td>${board.categoryName }</td>
				<td>${board.boardTitle }</td>
				<td>${board.nickname }</td>
				<td>${board.readCount }</td>
				<td>
					<fmt:formatDate var="createDate" value="${board.createDate }" pattern="yyyy-MM-dd"/>
					<fmt:formatDate var="now" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/> 
					<c:choose>
						<c:when test="${createDate != now}">
							${createDate}
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${board.createDate}" pattern="HH:mm"/>
						</c:otherwise>
					</c:choose>
				</td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>


	
	
<jsp:include page="../common/footer.jsp" />
</body>
</html>