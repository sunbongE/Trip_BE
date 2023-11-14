<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<link rel="stylesheet" href="${root}/css/boardCreate.css" />
	<link rel="stylesheet" href="${root}/css/boardDetail.css" />
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
  <section>
    <article>
      <div id="mentBox">
        <p id="ment">${boardDto.subject}</p>
        <div>
          <span id="subment">${boardDto.registerTime}</span><span id="cnt">조회수 : ${boardDto.hit}</span>
        </div>
      </div>
      <form id="articleForm">
      <div id="formBox">
          <label for="userId">작성자 ID</label>
          <input type="text" id="userId" name="userId" disabled required="required" value="${boardDto.userId}">

          <label for="content">내용</label>
          <textarea id="content" name="content" cols="50" rows="20" required="required" readonly>${boardDto.content}</textarea>

      </div>
      <div id="btnBox">
        <a href="${root}/board?action=list">
          <button type="button">목록으로</button>
        </a>
        <c:if test="${member.userId eq boardDto.userId}">
          <button type="button" id="update-btn">수정하기</button>
        </c:if>
      </div>
      </form>

    </article>


  </section>
<script>
  document.querySelector("#update-btn").addEventListener('click', function () {
    location.href = "${root}/board?action=modifyForm&no=${boardDto.articleNo}";
  });
</script>
	
<%@ include file="/include/footer.jsp" %>