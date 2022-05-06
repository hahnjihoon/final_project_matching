<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- 상대경로로 대상파일의 위치지정 -->
<c:import url="../common/header.jsp" />
<hr>
<h2 align="center">${ notice.notice_num } 번 공지 상세보기</h2>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
	<tr><th>제목</th><td>${ notice.notice_title }</td></tr>
	<tr><th>작성자</th><td>${ notice.notice_id }</td></tr>
	<tr><th>날짜</th><td>${ notice.notice_date }</td></tr>
				
	<tr><th>글내용</th><td>${ notice.notice_content }</td></tr>
	<tr><td colspan="2"><button onclick="javascript:history.go(-1);">목록</button></td></tr>
</table>

<br>
<hr>
<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>