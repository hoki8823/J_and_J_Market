<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>벙글장터 - 게시글 수정</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


</head>
<body>
	<jsp:include page="../common/header.jsp"/>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="px-lg-5">
				


					<!-- 게시판 이름/카테고리 -->
					<div class="row py-5 no-gutters">
						<div class="col-lg-12 mx-auto">
							<div class="text-black banner">
								<a class="ListGo" href="../list"><h1 class="boardName">벙글장터</h1></a>
								<hr>
							</div>
						</div>
							
					</div>
					<!-- End -->
					
					
					<h2>기본정보  <span id="requiredText">*필수정보</span> </h2> 
				
					
					<form action="../updateAction/${market.marketNo}" name="updateForm" method="POST" enctype="multipart/form-data" role="form" onsubmit= "return validate();">
					<ul class="insertForm">
					<!-- 이미지 -->
						<li class="formRow row borderTop">
							<div class="formList">
								<span>상품이미지<span class="star">*</span> (<span id="imgCnt">${fn:length(at) }

								</span>/10) </span>
							</div>
							
							<div class="formContent">
								<ul class="itemImages"> 
									<li class="itemImageInsert" id="imgInput">
										<label for="images0" class="imgLabel"   <c:if test="${!vs.last}"> style="display:none;"  </c:if>     >
											<span>이미지 등록</span>
										</label>
										<input type="file" id="images0" name="images"  style="display: none;" onchange="LoadImg(this);">
	
										<c:forEach items="${at}" var="image" varStatus="vs">
											<label for="images${vs.count}" class="imgLabel"   <c:if test="${!vs.last}"> style="display:none;"  </c:if>     >
												<span>이미지 등록</span>
											</label>
											<input type="file" id="images${vs.count}" name="images"  style="display: none;" onchange="LoadImg(this);">
										</c:forEach>								
									</li>
									
									<c:forEach items="${at}" var="image" varStatus="vs">
										<li class="itemImage">
											<img id="images${vs.count}" name="test1" src="${contextPath}${image.filePath}/${image.fileName}">
											<button type="button" class="deleteBtn" onclick="deleteImg(this, ${vs.count}, ${image.fileNo});"></button>
										</li>
									</c:forEach>
									
								</ul>
							</div>
						</li>
						
						<!-- 상태 -->
						<li class="formRow row">
							<div class="formList">
								<span>분류<span class="star">*</span></span>
							</div>
							<div class="formContent radioArea">
								<div class="itemStatusArea form-check">
									<input  type="radio" name="transactionCategory" value="1" class="itemRadio form-check-input" id="buy"> 
										<label for="buy" class="radioM">삽니다</label>
									<input type="radio" name="transactionCategory" value="2" class="itemRadio form-check-input" id="sell"> <label for="sell" class="radioM">팝니다</label>
								</div>
							</div>
						</li>
						
						<!-- 제목 -->
						<li class="formRow row">
							<div class="formList">
								<span>제목<span class="star">*</span></span>
							</div>
							
							<div class="formContent titleFlex">
								<div class="titleArea ">
									<input type="text" placeholder="상품 제목을 입력해주세요." class="titleInput" id="title" name="marketTitle" required maxlength="40" minlength="2" value="${market.marketTitle }"required>
									<button id="cancelBtn" type="button"></button>
									<div class="titleCnt">
									<span id="currCnt">0</span>
									<span id="maxCnt">/ 40</span>
								</div>
								</div>
							</div>
						</li>
						
						
						<!-- 카테고리 -->
						<li class="formRow row">
							<div class="formList">
								<span>카테고리<span class="star">*</span></span>
							</div>			
							
							<div class="formContent">
								<select id="SelectCategory" name="categoryCd" class="form-control div large" style="height: 40px">
									<option value="1">디지털/가전</option>
									<option value="2">가구/인테리어</option>
									<option value="3">유아동/유아도서</option>
									<option value="4">생활/가공식품</option>
									<option value="5">스포츠/레저</option>
									<option value="6">여성패션/잡화</option>
									<option value="7">남성패션/잡화</option>
									<option value="8">게임/취미</option>
									<option value="9">뷰티/미용</option>
									<option value="10">반려동물용품</option>
									<option value="11">도서/티켓/음반</option>
									<option value="12">식물</option>
									<option value="13">기타 중고물품</option>
								</select>
							</div>
						</li>
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						<!-- 상태 -->
						<li class="formRow row">
							<div class="formList">
								<span>상태<span class="star">*</span></span>
							</div>

							<div class="formContent radioArea">
								<div class="itemStatusArea form-check">
									<input type="radio" name="status" value="U" class="itemRadio form-check-input" id="usedStatus" > <label for="usedStatus" class="radioM">중고</label>
									
									
									<input type="radio" name="status" value="N" class="itemRadio form-check-input" id="newStatus"> <label for="newStatus" class="radioM">새상품</label>
								</div>
							</div>
						</li>
						
						<!-- 가격 -->
						<li class="formRow row">
							<div class="formList">
								<span>가격<span class="star">*</span></span>
							</div>

							<div class="formContent">
								<div class="priceArea">
									<input type="text" name="price" id="itemPrice" class="priceInput itemInputs" placeholder="숫자만 입력해주세요." value="${market.price}" required> 원
									
										<span class="errorMsg" id="priceMsg"></span>
									<input type="radio" name="deliveryCharge" value="F" class="itemRadio" id="including" checked> <label for="including" class="radioM" >택배비 포함</label>
									<input type="radio" name="deliveryCharge" value="N" class="itemRadio" id="noincluding"> <label for="noincluding" class="radioM">택배비 미포함</label>
								</div>
							</div>
						</li>
						
						
						<!-- 설명 -->
						<li class="formRow row">
							<div class="formList">
								<span>설명<span class="star">*</span></span>
							</div>
							
							<div class="formContent titleFlex">
								<textarea rows="6" name="marketContent" placeholder="상품 설명을 입력해주세요.(최소 5글자)" class="itemInfoText" maxlength="2000" minlength="5" required >${market.marketContent }</textarea>
									<div class="titleCnt float-right">
									<span id="ContentCurrCnt">0</span>
									<span id="ContentMaxCnt">/ 2000</span>
								</div>
							</div>
						</li>
						
					<!-- 수량 -->
						<li class="formRow row">
							<div class="formList">
								<span>수량<span class="star">*</span></span>
							</div>

							<div class="formContent">
								<div class="priceArea">
									<input type="text" name="itemCount" id="itemCount" class="countInput itemInputs" value="${market.itemCount}" required /> 개
									<span class="errorMsg" id="countMsgq"></span>
								</div>
							</div>
						</li>
						</ul>
						<div id="btnArea">
								<button id="submitBtn" type="submit" class="btn-lg maincolor">등록하기</button>
								<button id="listBtn" type="button" class="btn-lg maincolor-re ">등록취소</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"/>
	

	<script>
	
	// 취소
	$("#listBtn").on("click", function(){
		if(confirm("목록으로 돌아가시겠습니까?")){
	         location.href = "${sessionScope.returnListURL}";
	  }
	});
	
	
	// 이전 이미지의 fileNo를 하나의 배열에 모아둠
	var beforeImages = [];
	<c:forEach items="${at}" var="image">
		beforeImages.push(${image.fileNo});
	</c:forEach>
	
	
	
	(function() {
        if("${market.transactionCategory}" == '1')
           $("#buy").prop("checked", true);
        else 
           $("#sell").prop("checked", true);
     })();
	
	(function() {
        if("${market.status}" == 'U')
           $("#usedStatus").prop("checked", true);
        else 
           $("#newStatus").prop("checked", true);
     })();
	
	(function() {
        if("${market.deliveryCharge}" == 'N')
           $("#noincluding").prop("checked", true);
        else 
           $("#including").prop("checked", true);
     })();
	
	// 카테고리
	$.each($("#SelectCategory > option"), function(index, item){
		if($(item).text() == "${market.categoryNm}"){
			$(item).prop("selected", "true");
		}
	});
	
	var imgCnt = ${fn:length(at)};
	var imgId = ${fn:length(at)};
	
	if(imgCnt <= 10) {
		function LoadImg(value) {
			if (value.files && value.files[0]) {
				var reader = new FileReader();
				reader.readAsDataURL(value.files[0]);
				imgId++;
				
				reader.onload = function(e) {
					var img = '<li class="itemImage"> <img id="images' + imgId + '" class="image" name="test1" src="' + e.target.result + '">' +
						'<button type="button" class="deleteBtn" onclick="deleteImg(this,'+imgId+');"></button>' + '</li>';
					$(".itemImages").append(img);
					$("#imgCnt").text(++imgCnt); 
					addImgInput();
				}
			}
		}
	}
	
	function addImgInput(){
		var input = '<label for="images' + imgId +'" class="imgLabel">' +
								'<span>이미지 등록</span></label>'+
								'<input type="file" id="images' + imgId + '" name="images" style="display:none;" onchange="LoadImg(this);">';
		$(".itemImageInsert label").css("display", "none");
		$('.itemImageInsert').append(input);
	}
	
    function deleteImg(value, num, fileNo) {
    	console.log("파일 번호 : " + num);
    	
    	if(fileNo != undefined){ // 전달된 파일 번호가 있다면
    		var idx = beforeImages.indexOf(fileNo);
    		if (idx > -1) beforeImages.splice(idx, 1)
    	}
    	
    	
   		$("#imgCnt").text(--imgCnt);  
        $(value).parent().remove();
        
        var id = "images" + num;
        $("label[for="+id+"]").remove();
        $("input[id="+id+"]").remove();
        
        $(".imgLabel").eq($(".imgLabel").length - 1).show();
     }
    
    
    $("#title").on("input", function(){
    	var cnt = $(this).val();
    	$("#currCnt").text(cnt.length);
    	
    	if(cnt.length == 40){
    		$("#currCnt").css("color", "red");
    	}
    });
    
    $("#cancelBtn").on("click", function(){
    	$("#title").val("");
    });
    
    $("#itemPrice").on("input", function(){
    	var regexp = /^[0-9]*$/;
    	if($("#itemPrice").val() < 100) {
    			$("#priceMsg").text("100원 이상 입력해주세요.");
    	} else if(!regexp.test($("#itemPrice").val())){
    		alert("숫자만 입력해주세요.");
    		$("#priceMsg").text("숫자만 입력해주세요.");
    		$("#priceMsg").text("숫자만 입력해주세요.");
    	} else{
    		$("#priceMsg").text("");
    	}
    });
    $(".itemInfoText").on("input", function() {
        var cnt = $(this).val();
        $("#ContentCurrCnt").text(cnt.length);
        if (cnt.length >= 2000) {
           $("#ContentCurrCnt").css("color", "red");
        }
     });
     
     
     $("#itemCount").on("input", function(){
     	var regexp = /^[0-9]*$/;
     	if($("#itemCount").val() <= 0) {
     			$("#countMsgq").text("1개 이상 입력해주세요.");
     	} else if(!regexp.test($("#itemCount").val())){
     		alert("숫자만 입력해주세요.");
     		$("#countMsgq").text("숫자만 입력해주세요.");
     		$("#countMsgq").text("숫자만 입력해주세요.");
     	} else{
     		$("#countMsgq").text("");
     	}
     });
     
     
      function validate(){
        if(imgCnt == 0){
           alert("1장 이상의 사진을 등록해주세요.");
           $("#images").focus();
           return false;
        }
        
        if($("#title").val().trim().length == 0){
           alert("제목을 입력해주세요.");
           $("#title").focus();
           return false;
        }
        
        if($("#locationInput").val().trim().length == 0){
           alert("거래지역을 입력해주세요.");
           $("#locationInput").focus();
           return false;
        }
        
        if($("#itemPrice").val().trim().length == 0){
           alert("가격을 입력해주세요.");
           $("#itemPrice").focus();
           return false;
        }
        
        if($(".itemInfoText").val().trim().length == 0){
           alert("상품 설명을 입력해주세요.");
           $(".itemInfoText").focus();
           return false;
        }
        
        if($("#itemCount").val().trim().length == 0){
           alert("상품 개수를 입력해주세요.");
           $(".itemCount").focus();
           return false;
        }
      
		// 유효성 검사에서 문제가 없을 경우 서버에 제출 전
      // beforeImages배열의 내용을 hidden 타입으로 하여 form태그 마지막에 추가하여 파라미터로 전달
      for(var i=0 ; i<beforeImages.length ; i++){
         $beforeImages = $("<input>", {type : "hidden", name : "beforeImages", value : beforeImages[i]});
         $("form[name=updateForm]").append($beforeImages);
      }
	
     }
      
		
      
		
      
	</script>


</body>
</html>