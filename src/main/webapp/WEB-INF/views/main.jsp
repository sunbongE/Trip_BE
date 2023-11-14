<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${root}/css/common.css" />
    <link rel="stylesheet" href="${root}/jqueryFullPage.css">
   <!-- 
    -->
    <link rel="stylesheet" href="${root}/css/fullpage.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <script src="${root}/jqueryFullPage.js"></script>
    <script src="${root}/js/fullpage.js"></script>
</head>

<body>
    <main id="fullpage">
        <section id="sec1" class="section">
        <div id='base'>
            <div id="textbox">
            	<div>
            	<span>나는 지금 </span> 
            	<input type='text' name='' id='search' value=''>
            	</div>
             
            	<div>
            	<span>가고 싶다.</span>
            	</div>
            </div>
            <div id="simpleLogin">
            	<a><img alt="카카오 로그인" src=""></a>
            	<a><img alt="구글 로그인" src=""></a>
            	<a><img alt="네이버 로그인" src=""></a>
            </div>
        </div>
        </section>
        <section class="section">
            <div class="slide">
                <h3>slide 1</h3>
            </div>
            <div class="slide">
                <h3>slide 2</h3>
            </div>
            <div class="slide">
                <h3>slide 3</h3>
            </div>
        </section>
        <section class="section">
            <h2>section3</h2>
        </section>
        <section class="section">
            <h2>section4</h2>
        </section>
        <section class="section fp-auto-height">
           <footer>
  <div class="footer-container">
    <div>
      <img src="${root}/img/ssafy_logo.png" alt="" />
    </div>
    <div id="footer-right">
      <p>이용약관 | 개인정보방침</p>
      <div>
        본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포
        등을 금합니다.
      </div>
      <div>Copyright © SAMSUNG All Rights Reserved.</div>
    </div>
  </div>
</footer>	
        </section>
    </main>
</body>

</html>