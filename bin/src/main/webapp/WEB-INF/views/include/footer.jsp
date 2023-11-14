<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${root}/css/common.css" />
	
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
	
<!-- bootstrap -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
	crossorigin="anonymous">
</script>
<!-- hamburger menu event -->
<script type="text/javascript">
document.querySelector("#hamburger").addEventListener("click", function () {
	  menulist.classList.toggle("open");
	});
const serviceKey =
	  "BpHm%2BH19L%2F5IJ6m23xn947NgUPboP9qmyyi%2Bxh2Gj53j3fla3Ok32WOv5mHzRl3xRsHaiDoi8JLhtPchbyNVwg%3D%3D";
</script>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f969680d19602de1fea5ade9c6d7e784"></script>
</body>
</html>