<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>

<style type="text/css">
	* {margin: 0;padding: 0;}
	
	button.top_button {
		
	    background-color: black; /* Green */
	    border: none;
	    color: white;
	    padding: 15px;	/*내부여백*/
	    text-align: center;
	    text-decoration: none;
	    white-space: nowrap;	/* 글자 한줄로 */
	    display: inline-block;
	    font-size: 12px;
	    font-weight: bold;
	    margin: 50px 10px 0px;	/*바깥여백*/
	    cursor: pointer;
	    -webkit-transition-duration: 0.4s; /* Safari */
	    transition-duration: 0.4s;
	}
	
	button.top_button:hover {
	    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
	}
	
	@font-face {
	      src : url("resources/font/Blacksword.otf");
	      font-family: "maintitle"
	}

</style>
</head>
<body>
	<header>
		<!-- 상단 페이지 이름 및 로그인, 회원가입 버튼 -->
		<c:if test="${ empty loginUser }">	<!-- 로그인 안 했을때 -->
			<div style = "float:right; width:15%;">
					<button class="top_button" onclick="location.href='loginPage.do'" style="width:90px;">로그인</button>
					<button class="top_button" onclick="location.href='enrollPage.do'" style="width:90px;">회원가입</button>
			</div>
		</c:if>
		<c:if test="${ !empty loginUser }">	<!-- 로그인 했을때 -->
			<div style = "float:right; width:15%;">		
				<c:url var = "callMyInfo" value="/myinfo.do">	<!-- 내 정보 보기 -->
					<c:param name="userid" value="${ loginUser.userid }"/>
				</c:url>		
				<a class="top_button" href="${ callMyInfo }" style="width:90px;">${ sessionScope.loginUser.username } 님</a>
				<button class="top_button" onclick="location.href='logout.do'" style="width:90px;">로그아웃</button>
			</div>
		</c:if>
		<div style = "float:right; width:80%;">
			<h1 onclick="location.href='main.do'" style="font-family:maintitle; font-size:80px; text-align:center; cursor:pointer; " >Smart Matching</h1>
		</div><div style="clear:both;"></div>
		<c:import url="/WEB-INF/views/common/menubar.jsp" />
	</header>
</body>
</html>