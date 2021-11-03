<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>navbar</title>
    <script src="https://kit.fontawesome.com/83424fe51a.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../../resources/css/style.css">
</head>
<body>


<nav class="navbar">
<div class="navbar_logo">
    <i class="fas fa-gift"></i>
    <a href="/">J&J Market</a>
</div>
<ul class="navbar_menu">
    <li><a href="#">마이페이지</a></li>
    <li><a href="${contextPath }/market/list">중고거래</a></li>
    <li><a href="${contextPath }/board/list">커뮤니티</a></li>
    <li><a href="#">공지사항</a></li>
</ul>
<ul class="navbar_icons">
	<c:choose>
		<%-- 비로그인 --%>
		<c:when test="${empty sessionScope.loginMember }">
			<li><a href="${contextPath }/member/login">로그인</a></li>
			<li><a href="${contextPath }/member/signUp">회원가입</a></li>
		</c:when>
		<%-- 회원로그인 --%>
		<c:otherwise>
			<c:if test="${loginMember.memberGrade == 'F'}">
			1등급
			</c:if>
			<c:if test="${loginMember.memberGrade == 'S'}">
			2등급
			</c:if>
			<c:if test="${loginMember.memberGrade == 'T'}">
			3등급
			</c:if>
	   		<li><a href="#"><i class="fas fa-user-circle"></i></a></li>
	    	<li><a href="#">${loginMember.memberNickname}</a></li>
	    	<li><a href="${contextPath }/member/logout">로그아웃</a></li>
		</c:otherwise>
	</c:choose>
</ul>
<a href="#" class="navbar_toogleBtn">
    <i class="fas fa-bars"></i>
</a>
</nav>

    <script src="../../../resources/js/script.js"></script>
</body>
</html>