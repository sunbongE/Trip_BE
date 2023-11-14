<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<link rel="stylesheet" href="${root}/css/auth.css" />
<c:set var='session' value='${session }'></c:set>
</head>
<body>
	<%@ include file="/include/nav.jsp"%>
	<section>
		<div class="auth-top">
	



			<h2>마이페이지</h2>
			<div id="auth-mypage">
				<h3>나의 정보를 수정하고 관리할 수 있어요.</h3>
				<h4 id="logout-btn">로그아웃</h4>
			</div>
		</div>
		<!-- form -->
		<form action="#" method="post" id="auth-form-container">
			<div class="auth-input-box">
				<div class="input-title">이름</div>
				<input type="text" id="userName" name="userName"
					value="${member.userName }" />
			</div>
			<div class="auth-input-box">
				<div class="input-title">생년월일 6자리</div>
				<input type="text" name="birth" id="birth" placeholder="예) 960922"  value="${member.birth }"/>
			</div>
			<div class="auth-input-box">
				<div class="input-title">아이디</div>
				<input type="text" id="userId" name="userId"
					value="${member.userId }" readonly />
			</div>
			<div class="auth-input-box">
				<div class="input-title">비밀번호</div>
				<input type="password" id="userPwd" name="userPwd" required />
			</div>
			<div class="auth-input-box">
				<div class="input-title">비밀번호 확인 <span id='pwdChk'></span></div>
				<input type="password" id="userPwdChk" name="userPwdChk" required />
			</div>
			<div class="auth-input-box">
				<div class="input-title">이메일</div>
				<div class="email-box">
					<input type="text" id="email1" name="email1"
						value="${member.emailId }" /> <span>@</span> <input type="text"
						id="email2" name="email2" value="${member.emailDomain }" />
				</div>
			</div>
			<div class="auth-input-box">
				<div class="input-title">지역</div>
				<div class="region-box">
					<select name="region1" id="region1">
						<option value="">지역</option>
						<c:forEach var="item" items="${sidoList}">
							<option value="${item.sidoCode}">${item.sidoName}</option>
						</c:forEach>
					</select> <select name="region2" id="region2">
						<option value="">시군구</option>
					</select>
				</div>
			</div>
			<!-- 버튼 영역 -->
			<div class="auth-btn-box">
				<input type="submit" value="수정하기" id="update-btn" />
			</div>
			<p id="withdraw-btn">회원 탈퇴하기</p>
		</form>
	</section>
	<script type="text/javascript">
	//============회원 정보 수정=====================
    document.querySelector("#update-btn").addEventListener("click",(e)=>{
    	e.preventDefault();
    	console.log(document.querySelector("#userPwd").value)
    	if(document.querySelector("#userName").value===""||document.querySelector("#userPwd").value===""||document.querySelector("#userPwdChk").value===""){
    		alert("필수 항목을 확인하세요.")
    	}else{
    		let form = document.querySelector("#auth-form-container")
        	form.setAttribute("action","${root}/member?action=modify")
    		form.submit();    
    	}
    	
    })
	
	//================회원 탈퇴=======================
    document.querySelector("#withdraw-btn").addEventListener("click",()=>{
    	if(confirm("회원 탈퇴하시겠습니까?")){
    		location.href = "${root}/member?action=delete&userId=${member.userId }";
    	}
    	
    })
	
    // ==================로그아웃 요청===================
    document.querySelector("#logout-btn").addEventListener("click",()=>{
    	location.href = "${root}/member?action=logout"
    })
    
    // ==================비밀번호 확인 로직.====================
    	
    document.querySelector("#userPwdChk").addEventListener("keyup",()=>{
    	let pwd1 =document.querySelector("#userPwd").value;
    	let pwd2 =document.querySelector("#userPwdChk").value;

    	
    	if(pwd1){
    		
	    	if(pwd1===pwd2){
				document.querySelector("#pwdChk").innerText = '';
	    		
	    	}else{
				document.querySelector("#pwdChk").innerText = '비밀번호가 일치하지 않습니다.';
				document.querySelector("#pwdChk").style.color='red'
	    	}
    	}
    	
    })
	
		// ===========지역변하면 시군구 받아오기======================
	document.querySelector("#region1").addEventListener("change", function(e) {
	  let subOptionsLen = document.querySelector("#region2").options.length;
	  let target = e.target.value;
	  let url = "${root}/trip?action=gugunList&sidoCode="+target;
	  fetch(url)
	  	.then(function(res) {return res.json()})
	  	.then(function(data) {return makeSubOption(data)});
	  
	});

	// =========== 시군구 채우기 ======================
	function makeSubOption(data) {
		let sel = document.getElementById("region2");
		sel.innerHTML = '<option value="-1">시군구</option>';
		data.forEach(function(city) {
		   let opt = document.createElement("option");
		    opt.setAttribute("value", city.gugunCode);
		    opt.setAttribute("class", "opcity");
		    opt.appendChild(document.createTextNode(city.gugunName));
		    sel.appendChild(opt);
		});
	}
	
	// ======지역, 시군구 표시======
	const areaSelector = document.querySelector("#region1");
	const areaList = areaSelector.querySelectorAll('option');
	var getGunguInterval
	areaList.forEach((area) =>{
		// 변수는 세션에 있는 사용자의 si_code
		if(area.value==${sessionScope.member.userSidoId}){
			area.selected="true";
			areaSelector.dispatchEvent(new Event('change'));
			getGunguInterval = setInterval(() => {
				getGungu();
				}, 1000);
			return;
		}
	})

	// DB에서 군구를 불러왔다면.
	function getGungu(){
		if(areaList.length!==1){
		const areaSelector = document.querySelector("#region2");
		const areaList = areaSelector.querySelectorAll('option');
			clearInterval(getGunguInterval);
			areaList.forEach((area) =>{
				if(area.value==${sessionScope.member.userGuId}){
					area.selected="true";
					return;
				}
			})			
		}
	}

    </script>
	<%@ include file="/include/footer.jsp"%>