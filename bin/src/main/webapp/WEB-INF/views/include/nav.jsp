<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

 <header>
   <div class="header-container">
     <div id="logo"><a href="${root}/index.jsp">Enjoy Trip</a></div>
     <div id="menulist">
		<div id="menuitem"><a href="${root}/trip?action=tourinfo">지역별여행지</a></div>
		<div id="menuitem"><a href="${root}/trip/plan.jsp">나의여행계획</a></div>
		<div id="menuitem"><a href="${root}/board?action=list">공유게시판</a></div>
		<!-- 로그인 전 메뉴 -->
		<c:if test="${empty member}">
			<div id="menuitem"><a href="${root}/member?action=loginForm">로그인</a></div>
			<div id="menuitem"><a href="${root}/member?action=joinForm">회원가입</a></div>
		</c:if>
		<!-- 로그인 후 메뉴 -->
		<c:if test="${not empty member}">
			<div id="menuitem"><a href="${root}/member?action=mypage">마이페이지</a></div>
		</c:if>
     </div>
   </div>
   <i
     class="fa-solid fa-bars fa-2x"
     style="color: #454545"
     id="hamburger"
   ></i>
 </header>

