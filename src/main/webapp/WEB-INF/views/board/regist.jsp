<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="com.ssafy.board.dto.*" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<link rel="stylesheet" href="${root}/css/boardCreate.css" />
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	<c:set var="boardDto" value="${boardDto}"></c:set>
	<section>
    <article>
      <div>
        <h2 id="ment">여행정보 글 작성하기</h2>
        <h2 id="subment">나만의 여행 꿀팁을 공유해보세요.</h2>
      </div>
      <form id="articleForm" method="post">
        <input type="hidden" name="action" value="regist">
        <div id="formBox">
            <label for="userId">작성자 ID</label>
            <input type="text" id="userId" name="userId" readonly required="required" value="${member.userId}"/>

            <label for="subject">제목</label>
			<c:if test="${not empty boardDto }">
            <input type="text" id="subject" name="subject" autocomplete="off" value="${boardDto.subject }"/> 
            <label for="content">내용</label>
            <textarea id="content" name="content" cols="50" rows="20" required="required">${boardDto.content}</textarea>
			</c:if>
			<c:if test="${empty boardDto }">
            <input type="text" id="subject" name="subject" autocomplete="off"/>
            <label for="content">내용</label>
            <textarea id="content" name="content" cols="50" rows="20" required="required"></textarea>
			</c:if>
			

        </div>
        <div id="btnBox">
          <a href="${root}/board?action=list"><button type="button">닫기</button></a>
          <button type="button" id="createBtn">작성하기</button>
        </div>
      </form>
    </article>
    <script>
      document.querySelector("#createBtn").addEventListener('click', function() {
        if(!document.querySelector("#subject").value) {
          alert("제목을 입력해주세요 !");
        } else if(!document.querySelector("#content").value) {
          alert("내용을 입력해주세요 !");
        } else {
          let form = document.querySelector("#articleForm");
          form.setAttribute("action", "${root}/board");
          form.submit();
        }
      })
    </script>

  </section>
	
<%@ include file="/include/footer.jsp" %>