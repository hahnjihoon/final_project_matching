<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<!-- <script type="text/javascript">

	function moveMainPage() {
		location.href = "main.do";
	}
	
	function moveLogInPage() {
		location.href = "loginpage.do";
	}
	
	function moveEnrollPage() {
		location.href = "enroll.do";
	}

</script> -->
<style type="text/css">
/* * {margin: 0;padding: 0;} */

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



a{
	text-decoration:none;
	color:#404040;
	
}

li{
	list-style:none;
}

#menu{
	background:black;
}

#menu ul{
	width:1000px;
	margin:0 auto;
	overflow:hidden;
}

#menu ul li{
	float:left;
	width:12.5%;
	height:50px;
	line-height:50px;
	text-align:center;
	background:black;
	color:white;
}

#menu ul li a{
	display:block;
	color:white;
}

#menu ul li a:hover{
	background:white;
	color:black;
}
</style>
</head>
<body>
	<header>
		<!-- 상단 페이지 이름 및 로그인, 회원가입 버튼 -->
		<c:if test="${ empty loginMember }">	<!-- 로그인 안 했을때 -->
			<div style = "float:right; width:15%;">
					<button class="top_button" onclick="location.href='loginPage.do'" style="width:80px;">로그인</button>
					<button class="top_button" onclick="location.href='enroll.do'" style="width:80px;">회원가입</button>
			</div>
		</c:if>
		<c:if test="${ !empty loginMember }">	<!-- 로그인 했을때 -->
			<div style = "float:right; width:15%;">
					<button class="top_button" onclick="location.href='logout.do'" style="width:80px;">로그아웃</button>
			</div>
		</c:if>
		
		<div style = "float:right; width:80%;">
			<h1 onclick="location.href='main.do'" style="font-family:maintitle; font-size:80px; text-align:center; cursor:pointer; " >Smart Matching</h1>
		</div><div style="clear:both;"></div>
		
		
	</header>
	
	 <!-- 로그인 안 한 경우 -->
		<c:if test="${ empty sessionScope.loginMember }">
		<div id="menu">
			<ul id="menubar">
				<li><a href="${ pageContext.servletContext.contextPath }/nlist.do">공지사항</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/loginPage.do">이상형추천</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/loginPage.do">매력평가</a></li>
				<li><a href="">1:1대화방</a></li>
				<li><a href="">커뮤니티</a></li>
				<li><a href="">Q&A</a></li>
				<li><a href="">개인정보수정</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/main.do">홈</a></li>
			</ul>
		</div>
		</c:if>
	
		<!-- 로그인 한 경우 : 일반회원인 경우 -->
		<c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.admin eq 'N' }">
			<div id="menu">
			<ul id="menubar">
				<li><a href="${ pageContext.servletContext.contextPath }/nlist.do">공지사항</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/matchingPage.do">이상형추천</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/appealPage.do">매력평가</a></li>
				<li><a href="">1:1대화방</a></li>
				<li><a href="">커뮤니티</a></li>
				<li><a href="">Q&A</a></li>
				<li><a href="">개인정보수정</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/main2.do">홈</a></li>
			</ul>
			</div>
		</c:if>
		
		<!-- 로그인 한 경우 : 관리자인 경우 -->
		<c:if test="${ !empty sessionScope.loginMember and sessionScope.loginMember.admin eq 'Y' }">
			<div id="menu">
			<ul id="menubar">
				<li><a href="${ pageContext.servletContext.contextPath }/nlist.do">공지사항</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/matchingPage.do">이상형추천</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/appealPage.do">매력평가</a></li>
				<li><a href="">1:1대화방</a></li>
				<li><a href="">커뮤니티</a></li>
				<li><a href="">Q&A</a></li>
				<li><a href="">개인정보수정</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/main2.do">홈</a></li>
			</ul>
			</div>
		</c:if>
		
		
</body>
</html>