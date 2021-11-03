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
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- Bootstrap core JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

</head>
<style>
/* 버튼 색상 */
.maincolor1{
    color: #ffffff !important; 
    background-color:#4ab34a !important;
    border: 1px solid #4ab34a !important;
}
.maincolor1:hover{
    color: #ffffff !important; 
    background-color:#4ca975 !important;
    border: 1px solid #4ca975 !important;
}

.maincolor-re1{
        color: #4ab34a !important;
        background-color: #ffffff !important;
        border: 1px solid #4ab34a !important;
}
.maincolor-re1:hover{
    color: #ffffff !important; 
    background-color:#4ca975 !important;
    border: 1px solid #4ca975 !important;
}

.boardName { margin-right: 40px; }

.category {
	color: black;
	line-height : 54px;
}

.category:hover {
	text-decoration: none;
}

#list-table > tbody > tr:hover {
	cursor: pointer;
}

/* 좋아요 이미지 */
#image {
	width: 16px;
	height: 16px;
	margin-top : -5px;
	margin-left : -3px;
}
   
/* 검색창 */
.search { 
	text-align: center; 
	margin-top : 50px;
	margin-bottom: 100px;
}

/* 페이징바 */
.col-md-4 {
  flex: none !important;
  max-width: none !important;
}

.flex {
	-webkit-box-flex: 1;
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
}

@media (max-width:991.98px) {
	.padding {
	    padding: 1.5rem;
	}
}

@media (max-width:767.98px) {
	.padding {
	    padding: 1rem;
	}
}

.container { margin-top: 100px; }

.pagination, .jsgrid .jsgrid-pager {
	display: flex;
	padding-left: 0;
	list-style: none;
	border-radius: 0.25rem;
}

.page-item > a, .page-item > a:hover { color: black; }

.pagination.pagination-rounded-flat .page-item { margin: 0 .25rem; }

.pagination-success .page-item.active .page-link {
	background: #4ab34a;
	border-color: #00c689;
}

.pagination.pagination-rounded-flat .page-item .page-link {
	border: none;
	border-radius: 50px;
}
</style>
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
<div class="padding">								
	<c:set var="firstPage" value="${pageUrl}cp=1" />
	<c:set var="lastPage" value="${pageUrl}cp=${pageMaker.endPage }" />

	<fmt:parseNumber var="c1" value="${(bpInfo.currentPage - 1) / 10 }" integerOnly="true" />
	<fmt:parseNumber var="prev" value="${ c1 * 10 }" integerOnly="true" />
	<c:set var="prevPage" value="${pageUrl}cp=${prev}" />


	<fmt:parseNumber var="c2" value="${(bpInfo.currentPage + 9) / 10 }" integerOnly="true" />
	<fmt:parseNumber var="next" value="${ c2 * 10 + 1 }" integerOnly="true" />
	<c:set var="nextPage" value="${pageUrl}cp=${next}" />

	<div class="container d-flex justify-content-center">
		<div class="col-md-4 col-sm-6 grid-margin stretch-card">
			<nav>
				<ul class="pagination d-flex justify-content-center flex-wrap pagination-rounded-flat pagination-success">
					<c:if test="${bpInfo.currentPage > bpInfo.pageSize}">
						<!-- 첫 페이지로 이동(<<) -->
						<li class="page-item"><a class="page-link" href="${firstPage }" data-abc="true"><i class="fas fa-angle-double-left"></i></a></li>
						<!-- 이전 페이지로 이동 (<) -->
						<li class="page-item"><a class="page-link" href="${prevPage }" data-abc="true"><i class="fa fa-angle-left"></i></a></li>
					</c:if>
					
					<!-- 페이지 목록 -->
					<c:forEach var="page" begin="${bpInfo.startPage}" end="${bpInfo.endPage}">
						<c:choose>
							<c:when test="${bpInfo.currentPage == page }">
								<li class="page-item active"><a class="page-link" data-abc="true">${page}</a></li>
							</c:when>

							<c:otherwise>
								<li class="page-item"><a class="page-link" href="${pageUrl}cp=${page}" data-abc="true">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<%-- 다음 페이지가 마지막 페이지 이하인 경우 --%>
					<c:if test="${next <= bpInfo.maxPage}">
						<!-- 다음 페이지로 이동 (>) -->
						<li class="page-item"><a class="page-link" href="${nextPage }" data-abc="true"><i class="fa fa-angle-right"></i></a></li>
						<!-- 마지막 페이지로 이동(>>) -->
						<li class="page-item"><a class="page-link" href="${lastPage }" data-abc="true"><i class="fas fa-angle-double-right"></i></a></li>
					</c:if>
				</ul>
			</nav>

		</div>
	</div>
</div>
	
	
<jsp:include page="../common/footer.jsp" />
</body>
</html>