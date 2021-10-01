<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>

    <!-- 구글 폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
        rel="stylesheet">

    <!-- 부트스트랩 사용을 위한 css 추가 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
        integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <!-- 부트스트랩 사용을 위한 라이브러리 추가 (반드시 jQuery가 항상 먼저여야한다. 순서 중요!) -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
   
</head>

<body>

	
    <div class="container">
        <div class="row">
            <div class="col-md-4">
            </div>

            <div class="col-md-4">

                <div class="text-center">

                    <!-- <h3 style="margin-bottom:50px;">로고,,ㄱ-</h3> -->

                    <h3>회원가입</h3>

                    <!-- <div class="text-center" id="sns_signUp">
                        <div class="sns_text">
                            SNS계정 간편 로그인/회원가입
                        </div>
                        <a href="#"><img class="sns_logo" src="img/sns_naver.jpg"></a>
                        <a href="#"><img class="sns_logo" src="img/sns_kakao.jpg"></a>
                    </div> -->

                    <hr>
                </div>



                <form action="signUpAction" method="post" name="signUpForm" onsubmit='return memberJoinvalidate();'>
                    <!-- required : 필수 입력 속성 -->
                    <div class="form-group">
                        <label for="userId" class="textBold500"><span class="requiredInput">*</span> 아이디</label>
                        <div>
                            <small>첫 글자는 영어 소문자, 나머지 글자는 영어 대, 소문자 + 숫자 (총 6~12글자)</small>
                        </div>
                        <input type="text" class="form-control" id="userId" name="memberId" autocomplete="off" placeholder="아이디를 입력하세요." required>
                        <input type="hidden" name="idDup" id="idDup" value="false">
                        <div><!-- 아이디 유효성 검사 -->
                            <small id="checkId">&nbsp;</small>
                        </div> 
                    </div>

                    <div class="form-group">
                        <label for="pwd1" class="textBold500"><span class="requiredInput">*</span> 비밀번호</label>
                        <div>
                            <small>영어 대, 소문자 + 숫자 (총 6~12글자)</small>
                        </div>
                        <input type="password" class="form-control" id="pwd1" name="memberPwd" placeholder="비밀번호를 입력하세요." required>
                        <div><!-- 비밀번호 유효성 검사 -->
                            <small id="checkPwd1" >&nbsp;</small>
                        </div> 
                    </div>

                    <div class="form-group">
                        <label for="pwd2" class="textBold500"><span class="requiredInput">*</span> 비밀번호 확인</label>
                        <input type="password" class="form-control" id="pwd2" name="pwd2" placeholder="비밀번호를 한번 더 입력하세요." required>
                        <div> <!-- 비밀번호 확인 유효성 검사 -->
                            <small id="checkPwd2" >&nbsp;</small>
                        </div>
                    </div>
					
					<!-- 이름 -->
                    <div class="form-group">
                        <label for="name" class="textBold500"><span class="requiredInput">*</span> 이름</label>
                        <div>
                            <small>한글 입력 (2자 이상)</small>
                        </div>
                        <input type="text" class="form-control" id="name" name="memberName" placeholder="이름을 입력하세요." required>
                        <div> <!-- 이름 유효성 검사 -->
                            <span id="checkName" >&nbsp;</span>
                        </div> 
                    </div>
					
					<!-- 닉네임 -->
                    <div class="form-group">
                        <label for="nickname" class="textBold500"><span class="requiredInput">*</span> 닉네임</label>
                        <div>
                            <small>한글 또는 영문 입력 (총 2~8글자)</small>
                        </div>
                        <input type="text" class="form-control" id="nickname" name="memberNickname" placeholder="닉네임을 입력하세요." required>
                        <input type="hidden" name="nnDup" id="nnDup" value="false">
                        <div>
                            <!-- userName을 Nickname으로 바꾸기 -->
                            <span id="checkNickname" >&nbsp;</span>
                        </div> 
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
                        <div> <!-- 전화번호 유효성 검사 -->
                            <span id="checkPhone" >&nbsp;</span>
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
                            <div>
                            <!-- 이메일 유효성 검사 -->
                               <span id="checkEmail" ></span>
                            </div>

                        </div>

                        <br>

                        
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

                    <hr>

                    <div class="submit">
                    	<button id="nextBtn" type="submit" class="btn btn-primary btn-block maincolor">회원가입 완료</button>
                    </div>
		

                    <br>
                    <br><br><br><br><br><br>
                </form>
            </div>
        </div>

        <div class="col-md-4">

        </div>
    </div>
    <script>
    
        function memberJoinvalidate(){
        
         	// 입력된 전화번호, 주소 조합하여 form태그에 hidden으로 추가 하기
			// 왜? -> 커맨드 객체를 이용하여 파라미터를 한번에 받기 쉽게 하기 위하여
			$memberPhone = $("<input>", {type : "hidden", name : "memberPhone",
					value : $("#phone1").val() + "-" + $("#phone2").val() + "-" + $("#phone3").val()
			});
			
			$memberEmail = $("<input>", {type : "hidden", name : "memberEmail",
					value : $("#email1").val() + "@" + $("#email2").val()
			});


			$("form[name='signUpForm']").append($memberPhone).append($memberEmail);
			
        }

    </script>
</body>

</html>