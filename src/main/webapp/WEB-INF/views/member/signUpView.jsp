<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

회원가입페이지

<form action="signUpAction" method="post" name="signUpMember" onsubmit="return memberJoinvalidate();">
<div>
	<input type="text" class="form-control" id="userId" name="memberId" autocomplete="off" placeholder="아이디를 입력하세요." required>
</div>
<div>
	<input type="password" class="form-control" id="pwd1" name="memberPwd" placeholder="비밀번호를 입력하세요." required>
</div>
<div>
	<input type="text" class="form-control" id="name" name="memberName" placeholder="이름을 입력하세요." required>
</div>
<div>
	 <input type="text" class="form-control" id="nickname" name="memberNickname" placeholder="닉네임을 입력하세요." required>
</div>

<div class="form-group">
  <label for="phone" class="textBold500"><span class="requiredInput">*</span> 전화번호</label>
  <div class="input-group"><!-- ph-area -->
     <div class="ph">
         <select class="form-control phone" id="phone1" name="phone1">
             <option>010</option>
             <option>011</option>
             <option>016</option>
             <option>017</option>
             <option>019</option>
         </select>
     </div>
     <div class="ph">
         <input type="text" class="form-control phone" id="phone2" name="phone2" maxlength="4" size="4" required>
     </div>
     <div class="ph ph-end">
         <input type="text" class="form-control phone" id="phone3" name="phone3" maxlength="4" size="4" required>
     </div>
 	</div>
 </div>
 <div class="form-group">
<label for="email" class="textBold500"><span class="requiredInput">*</span> 이메일</label>
<div class="input-group">
    <div class="em email-1">
        <input type="text" class="form-control email" id="email1" name="email1" placeholder="이메일" required>
    </div>

    <div class="emg email-2">@</div>
    <div class="em email-3 em-end">
        <select class="form-control email" id="email2" name="email2">
            <option>이메일 선택</option>
            <option>naver.com</option>
            <option>daum.net</option>
            <option>hanmail.net</option>
            <option>gmail.com</option>
            <option>nate.com</option>
        </select>
    </div>
</div>
<div class="form-group">
    <label for="gender" class="textBold500"><span class="requiredInput">*</span> 성별</label>
    <br>

    <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" id="m" name="memberGender" value="M" class="custom-control-input" required>
        <label class="custom-control-label textBold400" for="m">남자</label>
      </div>
      <div class="custom-control custom-radio custom-control-inline">
        <input type="radio" id="w" name="memberGender" value="W" class="custom-control-input">
        <label class="custom-control-label textBold400" for="w">여자</label>
      </div>
</div>

 
	<button id="nextBtn" type="submit" class="btn btn-primary btn-block maincolor">회원가입 완료</button>
</form>


<jsp:include page="../common/footer.jsp" />

<script>
	function memberJoinvalidate(){
		$memberPhone = $("<input>", {type : "hidden", name : "memberPhone",
			value : $("#phone1").val() + "-" + $("#phone2").val() + "-" + $("#phone3").val()
		});
		
		$memberEmail = $("<input>", {type : "hidden", name : "memberEmail",
				value : $("#email1").val() + "@" + $("#email2").val()
		});


		$("form[name='signUpMember']").append($memberPhone).append($memberEmail);
		window.alert("회원가입 완료");
  	}	
</script>
</body>
</html>