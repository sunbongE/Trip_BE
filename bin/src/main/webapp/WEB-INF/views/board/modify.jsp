<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/include/head.jsp" %>
  <link rel="stylesheet" href="${root}/css/boardCreate.css" />
</head>
<body>
<%@ include file="/include/nav.jsp" %>
<section>
  <article>
    <div>
      <h2 id="ment">여행정보 글 수정하기</h2>
      <h2 id="subment">내가 작성한 글을 수정할 수 있어요.</h2>
    </div>
    <form id="articleForm" method="post">
      <input type="hidden" name="action" value="modify">
      <input type="hidden" name="articleNo" value="${boardDto.articleNo}">
      <div id="formBox">
        <label for="userId">작성자 ID</label>
        <input type="text" id="userId" name="userId" readonly required="required" value="${member.userId}"/>

        <label for="subject">제목</label>
        <input type="text" id="subject" name="subject" autocomplete="off" value="${boardDto.subject}"/>

        <label for="content">내용</label>
        <textarea id="content" name="content" cols="50" rows="20" required="required">${boardDto.content}</textarea>
      </div>
      <div id="btnBox">
        <button type="button" id="modify-btn">수정하기</button>
        <button type="button" id="delete-btn">삭제하기</button>
      </div>
    </form>
  </article>
  <script>
    document.querySelector("#modify-btn").addEventListener('click', function() {
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
    document.querySelector("#delete-btn").addEventListener('click', function () {
      location.href = "${root}/board?action=delete&no=${boardDto.articleNo}";
    });
  </script>

</section>

<%@ include file="/include/footer.jsp" %>