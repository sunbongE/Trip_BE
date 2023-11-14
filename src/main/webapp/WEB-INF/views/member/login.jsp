<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<link rel="stylesheet" href="${root}/css/auth.css" />
<%-- 쿠키 가져온다. --%>
<c:set var='ck' value="${cookie.userId }"></c:set>
</head>
<body>
	<%@ include file="/include/nav.jsp"%>
	<section>
		<div class="auth-top">
		
			<h2>로그인</h2>
			<h3>반갑습니다! 즐겁게 여행떠날 준비가 되었나요?</h3>
		</div>
		<!-- form -->
		<form action="${root}/member" method="post" id="auth-form-container">
		<input type="hidden" name="action" value="login">
			<div style="display:flex; justify-content:end;">
			<c:if test="${empty ck }">
				<input type="checkbox" id="saveId" name="saveId" value='ok' /> 
			</c:if>
			<c:if test="${not empty ck }">
				<input type="checkbox" id="saveId" name="saveId" value='ok' checked=true/> 
			</c:if>
				<label for="saveId">아이디 저장</label>
					
			</div>
			<div class="auth-input-box">
				<div class="input-title">아이디</div>
				
				<c:if test="${not empty ck}">
				<input type="text" name="userId" id="userId" value="${ck.value}" required autofocus />
				</c:if>
				<c:if test="${empty ck}">
				<input type="text" name="userId" id="userId" required autofocus />
				</c:if>
				
			</div>
			<div class="auth-input-box">
				<div class="input-title">비밀번호</div>
				<input type="password" name="userPwd" id="userPwd" required />
			</div>
			<!-- 버튼 영역 -->
			<div class="auth-btn-box">
				<input type="submit" value="로그인" id="login-btn" />
			</div>
			<a href="${root}/member?action=joinForm" id="withdraw-btn"><p>회원이
					아니신가요? 여기를 클릭해주세요.</p></a>
		</form>
	</section>
	<%@ include file="/include/footer.jsp"%>