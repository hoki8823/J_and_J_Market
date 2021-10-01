<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    
<title>navbar</title>
<script src="https://kit.fontawesome.com/83424fe51a.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css">
    
</head>
<body>
    
<nav class="navbar">
<div class="navbar_logo">
    <i class="fas fa-gift"></i>
    <a href="#">J&J Market</a>
</div>
<ul class="navbar_menu">
    <li><a href="#">마이페이지</a></li>
    <li><a href="#">중고거래</a></li>
    <li><a href="#">커뮤니티</a></li>
    <li><a href="#">공지사항</a></li>
</ul>
<ul class="navbar_icons">
    <li><a href="#"><i class="fas fa-user-circle"></i></a></li>
    <li><a href="#">닉네임</a></li>
    <li><a href="#">로그아웃</a></li>
</ul>
<a href="#" class="navbar_toogleBtn">
    <i class="fas fa-bars"></i>
</a>
</nav>
    <script src="script.js"></script>

<jsp:include page="header.jsp" />


<jsp:include page="footer.jsp" />

</body>
</html>
