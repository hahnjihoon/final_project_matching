<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="listCount" value="${ requestScope.listCount }" />
<c:set var="startPage" value="${ requestScope.startPage}" />
<c:set var="endPage" value="${ requestScope.endPage}" />
<c:set var="maxPage" value="${ requestScope.maxPage}" />
<c:set var="currentPage" value="${ requestScope.currentPage}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
   href="${ pageContext.request.contextPath}/resources/css/community.css">
<title></title>
<script type="text/javascript">
	function showWriterForm() {
		location.href = "${ pageContext.servletContext.contextPath}/cwform.do";
	}
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp" />
	<hr>
	<aside class="profile">
		<table border="1" style="border-collapse: collapse;">
			<tr>
				<td rowspan="2"><img
					src="${ pageContext.servletContext.contextPath }/resources/images/profile.jpg"></td>
				<td colspan="2">
					<h2 style="text-align: center">${loginUser.userid }</h2>
					<p style="text-align: center">일반회원</p>
				</td>
			</tr>
			<tr>
				<td><a href="logout.do">&nbsp;로그아웃&nbsp;</a></td>
				<td><c:url var="callMyInfo2" value="myinfo.do">
						<c:param name="userid" value="${ loginUser.userid }"></c:param>
					</c:url> <a href="${ callMyInfo2 }">&nbsp;회원정보수정&nbsp;</a></td>
			</tr>
		</table>
	</aside>
	<h2 align="center">${ community.com_num }번글</h2>
	<br>
	
	<div class="inner">
			<b>no.${community.com_num }</b>
			<span style="margin-left: 950px;">조회 <span
				style="color: red; fond-weight: bold;" class="readcount">${community.com_readcount}</span>
			</span>
			<br>
			<td>
				<div class="inputArea" >
					<img style="width:250px; height:250px;" src="${ pageContext.servletContext.contextPath }/resources/community_upfiles/${community.com_rename_file}"/>
				</div>
			</td>

			<td>${ community.com_content }</td>
			<table class="table table-bordered" style="width: 1050px;">
				<tr>
					<td>작성자 : ${community.com_writer} <span style="margin-left: 800px;">
							<fmt:formatDate value="${b.com_date}"
								pattern="yyyy-MM-dd HH:mm" />
					</span><br>
					</td>
				</tr>
			</table>
	</div>
	
	<tr><td colspan="2"><button onclick="javascript:location.href='clist.do?page=${ currentPage }';">목록</button> &nbsp;
	<!-- 본인이 등록한 게시글일 때는 수정과 삭제 기능 제공 -->
	 <c:if test="${ requestScope.community.com_writer eq sessionScope.loginUser.userid }">
		<c:url var="bup" value="/cupview.do">
			<c:param name="com_num" value="${ community.com_num }"/>
			<c:param name="page" value="${ currentPage }"/>
		</c:url>
		<a href="${ bup }">[수정페이지로 이동]]</a> &nbsp;
		<c:url var="bdt" value="/cdel.do">
			<c:param name="com_num" value="${ community.com_num }"/>
			<c:param name="com_rename_file" value="${ community.com_rename_file }"/>
		</c:url>
		<a href="${ bdt }">[글삭제]]</a> &nbsp;
	</c:if>
</body>
</html>