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
<head>
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
	<!-- profile aside -->
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
		<div class="write">
			<a href="cwform.do"><img
				src="${ pageContext.servletContext.contextPath }/resources/images/write_over.jpg">
			</a>
		</div>
	</aside>
	<div class="outter">
		<h2>게시글 목록 : 총 ${ listCount }개</h2>
	</div>
	<br>
	<div class="inner">
		<c:forEach items="${ requestScope.list }" var="b">
			<b>no.${b.com_num }</b>
			<span style="margin-left: 950px;">조회 <span
				style="color: red; fond-weight: bold;" class="readcount">${b.com_readcount}</span>
			</span>
			<br>

			<c:url var="bdt" value="cdetail.do">
				<c:param name="com_num" value="${ b.com_num }" />
			</c:url>
			
			<td>
				<div class="inputArea" >
				<c:if test="${ !empty b.com_original_file }">
					<img style="width:500%; height:500%;" src="${ pageContext.servletContext.contextPath }/resources/community_upfiles/${b.com_rename_file}"/>
				</c:if>
				</div>
			</td>

			<td><a href="${ bdt }">${ b.com_content }</a></td>
			<table class="table table-bordered" style="width: 1050px;">
				<tr>
					<td>작성자 : ${b.com_writer} <span style="margin-left: 800px;">
							<fmt:formatDate value="${b.com_date}"
								pattern="yyyy-MM-dd HH:mm" />
					</span><br>
					</td>
				</tr>
			</table>
		</c:forEach>
	</div>
	<br>
	<!-- 페이징 처리 -->
	<!--  첫페이지로 이동 시키기 -->
	<div style="text-align: center;">
		<c:if test="${ currentPage eq 1 }"> 
	[맨처음] &nbsp;
</c:if>
		<c:if test="${ currentPage > 1 }">
			<c:url var="blf" value="/clist.do">
				<c:param name="page" value="1" />
			</c:url>
			<a href="${ blf }">[맨처음]</a>
		</c:if>
		<!--  첫페이지 이동 끝 -->

		<!--  이전 페이지 그룹으로 이동 처리 -->
		<c:if
			test="${ (currentPage - 10) < startPage and (currentPage - 10) > 1 }">
			<c:url var="blf2" value="/clist.do">
				<c:param name="page" value="${ startPage-10 }" />
			</c:url>
			<a href="${ blf2 }">[이전그룹]</a>
		</c:if>
		<c:if
			test="${ !((currentPage - 10) < startPage and (currentPage - 10) > 1)}">
	[이전그룹] &nbsp;
</c:if>
		<!--  이전 페이지 그룹으로 이동 처리 끝 -->

		<!-- 현재 페이지가 속한 페이지 그룹 출력 -->
		<c:forEach var="p" begin="${ startPage }" end="${ endPage }" step="1">
			<c:if test="${ p eq currentPage }">
				<font size="4" color="red"><b>[${ p }]</b></font>
			</c:if>
			<c:if test="${ p ne currentPage }">
				<c:url var="blf5" value="/clist.do">
					<c:param name="page" value="${ p }" />
				</c:url>
				<a href="${ blf5 }">${ p }</a>
			</c:if>
		</c:forEach>


		<!-- 현재 페이지가 속한 페이지 그룹 출력  끝-->



		<!--  다음 페이지 그룹으로 이동 처리 -->
		<c:if
			test="${ (currentPage + 10) > endPage and (currentPage + 10) <maxPage }">
			<c:url var="blf3" value="/clist.do">
				<c:param name="page" value="${ endPage + 10 }" />
			</c:url>
			<a href="${ blf3 }">[다음그룹]</a>
		</c:if>
		<c:if
			test="${ !((currentPage + 10) > endPage and (currentPage + 10) <maxPage)}">
	[다음그룹] &nbsp;
</c:if>
		<!--  다음 페이지 그룹으로 이동 처리 끝 -->

		<!-- 끝페이지로 이동 -->
		<c:if test="${ currentPage eq maxPage }"> 
	[맨끝] &nbsp;
</c:if>
		<c:if test="${ currentPage < maxPage }">
			<c:url var="blf5" value="/clist.do">
				<c:param name="page" value="${ maxPage }" />
			</c:url>
			<a href="${ blf5 }">[맨끝]</a>
		</c:if>
		<!-- 끝페이지로 이동 끝 -->

	</div>

	<hr>
</body>
</html>