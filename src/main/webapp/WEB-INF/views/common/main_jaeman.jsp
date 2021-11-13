<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>J&J Market</title>
    <!-- 부트스트랩 사용을 위한 css 추가 -->
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
     integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/83424fe51a.js" crossorigin="anonymous"></script>
    <style>
    .images{
      width:40px;
      height: 40px;
   }
    </style>
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

<table class="table-hover">
	<c:if test="${empty mList }">
		<tr>
			<td style="text-align: center;">존재하는 게시글이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${!empty mList }">
		<c:forEach var="market" items="${mList}" varStatus="vs">
			<tr>
				<td>
               	
               	<div class='badge badge-danger px-3 rounded-pill font-weight-normal' style="background-color : #ffaf18;">
               	<c:if test="${market.transactionCategory == 1}">삽니다</c:if>
						<c:if test="${market.transactionCategory == 2}">팝니다</c:if></div>
               
				</td>
				<tr style="height:60px;">
				<td>
				<span style='display : none;'>${market.marketNo}</span>
				<div class="embed-responsive embed-responsive-4by3">
			
					<c:forEach items="${thumbnailList2}" var="th">
					<c:if test="${th.parentMarketNo == market.marketNo}">
					<a href="market/${market.marketNo}"><img src="${contextPath}${th.filePath}/${th.fileName}" 
						class="img-fluid card-img-top embed-responsive-item marketNo"
						<c:if test="${market.transactionStatus != 1}"> style="opacity: 0.5;" </c:if>></a>
					</c:if>
					</c:forEach>
				</div>
				</td>
				<td style="padding-left: 10px;">
				<h7> <a href="market/${market.marketNo}" class="text-dark">${market.marketTitle}</a></h7></td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<jsp:include page="footer.jsp" />
</body>
</html>