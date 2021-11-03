<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>J&J Market</title>
    <script src="https://kit.fontawesome.com/83424fe51a.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../../resources/css/style.css">
    <script src="../../../resources/js/script.js" defer></script>
</head>
<body>
<jsp:include page="header.jsp" />
<!-- 페이지헤더 -->
<header class="headerbar">
    <h1 class="headerbar__title">J&J Market</h1>
    <h2 class="headerbar__description">회원제 사이트 'J&J market'에 오신것을 환영합니다.</h2>
</header>
<!-- 거래게시판 -->
<div class="tContainer">
<h2 class="tboard__title">거래게시판</h2>
<ul class="tboard">
    <li class="tborard__item">
        <a href="#">
        <div class="sale">팝니다</div>
        <div class="item__imgAndData">
            <img src="imgs/item1.png" alt="거래품목사진1" class="item__img">
            <span class="item__data">나이키 신발 사이즈 260 팝니다.</span>
        </div>
    </a>
    </li>
    <li class="tborard__item">
        <a href="#">
        <div class="buy">삽니다</div>
        <div class="item__imgAndData">
            <img src="imgs/item1.png" alt="거래품목사진1" class="item__img">
            <span class="item__data">나이키 신발 사이즈 260 팝니다.</span>
        </div>
    </a>
    </li>
    <li class="tborard__item">
        <a href="#">
        <div class="sale">팝니다</div>
        <div class="item__imgAndData">
            <img src="imgs/item1.png" alt="거래품목사진1" class="item__img">
            <span class="item__data">나이키 신발 사이즈 260 팝니다.</span>
        </div>
    </a>
    </li>
    <li class="tborard__item">
        <a href="#">
        <div class="buy">삽니다</div>
        <div class="item__imgAndData">
            <img src="imgs/item1.png" alt="거래품목사진1" class="item__img">
            <span class="item__data">나이키 신발 사이즈 260 팝니다.</span>
        </div>
    </a>
    </li>
</ul>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>