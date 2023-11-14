<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp" %>
</head>
<body>
<%@ include file="/include/nav.jsp" %>
<c:set var="loginFailMsg" value="${loginFailMsg}"></c:set>
<c:set var="modifyFailMsg" value="${modifyFailMsg}"></c:set>
<script type="text/javascript">
    <c:choose>
        <c:when test="${not empty loginFailMsg}">
            alert("${loginFailMsg}");
            location.href = "${root}/member/login.jsp";
        </c:when>
        <c:otherwise>
            <c:if test="${not empty modifyFailMsg}">
                alert("${modifyFailMsg}");
                location.href = "${root}/member/mypage.jsp";
            </c:if>
        </c:otherwise>
    </c:choose>
</script>

<%@ include file="/include/footer.jsp" %>