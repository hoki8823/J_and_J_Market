<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="loginAction" method="post" >
		<h3>로그인</h3>
		<input type="text" id="memberId" name="memberId" placeholder="아이디" value="${cookie.saveId.value }" required autofocus />
		<input type="password" id="memberPwd" name="memberPwd" placeholder="비밀번호" required />
		<label class="textBold500"> 
		<input type="checkbox" name="saveId"			
	 	<c:if test="${!empty cookie.saveId.value}"> checked </c:if>
			<%-- 아이디저장 체크박스에 체크를 하면 저장을 하겠다... --%>
		> 아이디 저장
		</label>
		<button type="submit">login</button>
	</form>
</body>
</html>