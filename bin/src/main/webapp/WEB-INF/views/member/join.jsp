<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<link rel="stylesheet" href="${root}/css/auth.css" />
</head>
<body>
	<%@ include file="/include/nav.jsp"%>
	<section>
		<div class="auth-top">
			<h2>회원가입</h2>
			<h3>EnjoyTrip에서 제공하는 다양한 서비스를 이용해보세요.</h3>
		</div>
		<!-- form -->
		<form method="post" action="${root}/member " id="auth-form-container">
			<input type="hidden" name='action' value='join'>
			<div class="auth-input-box">
				<div class="input-title">이름</div>
				<input type="text" name="userName" id="userName" autofocus required />
			</div>
			<div class="auth-input-box">
				<div class="input-title">생년월일 6자리</div>
				<input type="text" name="birth" id="birth" placeholder="예) 960922" />
			</div>
			<div class="auth-input-box">
				<div class="input-title">
					아이디 <span id="validMsg"></span>
				</div>
				<div class="id-box">
					<input type="text" name="userId" id="userId" required /> <input
						type="button" name="userIdChkBtn" id="userIdChkBtn" value="중복확인" />
				</div>
			</div>
			<div class="auth-input-box">
				<div class="input-title">비밀번호 </div>
				<input type="password" name="userPwd" id="userPwd" required />
			</div>
			<div class="auth-input-box">
				<div class="input-title">비밀번호 확인 <span id='pwdChk'></span></div>
				<input type="password" name="userPwdChk" id="userPwdChk" required />
			</div>
			<div class="auth-input-box">
				<div class="input-title">이메일</div>
				<div class="email-box">
					<input type="text" name="email1" id="email1" required /> <span>@</span>
					<input type="text" name="email2" id="email2" required />
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
				<input type="submit" value="가입하기" id="signup-btn" />
			</div>
		</form>

	</section>

	<script>
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
    
    
    document.querySelector("#userIdChkBtn").addEventListener("click",()=>{
		let userId = document.querySelector("#userId").value;	

		let url = "${root}/member?action=checkId&userId="+userId;
		fetch(url)
			.then((response)=>response.json())
			.then((data) => {
				console.log(data)
				if(data===true){
					document.querySelector("#validMsg").innerText = "사용 가능"
						document.querySelector("#validMsg").style.color = "green"
					console.log('사용가능')
				}else{
					document.querySelector("#validMsg").innerText = "사용 불가능"
						document.querySelector("#validMsg").style.color = "red"
					console.log('사용불가')
				}
			})
		
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
    </script>
	<%@ include file="/include/footer.jsp"%>