<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<link rel="stylesheet" href="${root }/css/board.css" />
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	  <section>
    <!-- 게시글 작성 -->
    <article>
      <div id="ment">
        <h2>여행 정보를 공유해볼까요?</h2>
      </div>
      <div id="subment">
        <h2>프로 여행러들이 알려주는 여행 꿀팁을 확인하고</h2>
        <h2>나만의 꿀팁을 공유해보세요.</h2>
      </div>
      <div id="btnBox">
        <button id="goCreate">작성하기</button>
      </div>
    </article>
    <!-- 게시판 -->
    <article>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">조회수</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="item" items="${list}">
            <tr>
              <th scope="row">${item.hit}</th>
              <td><a style="cursor: pointer;color: black;text-decoration-line: blink;" href="${root}/board?action=view&no=${item.articleNo}">${item.subject}</a></td>
              <td>${item.userId}</td>
              <td>${item.registerTime}</td>
            </tr>
          </c:forEach>

        </tbody>
      </table>

    </article>
  </section>
<script>
  document.querySelector("#goCreate").addEventListener('click', function() {
    if("${member}" != "") {
      location.href = "${root}/board?action=registForm";
    } else {
      alert("로그인 후 작성가능합니다 !")
      location.href = "${root}/member?action=loginForm";
    }

  });
</script>
<%@ include file="/include/footer.jsp" %>