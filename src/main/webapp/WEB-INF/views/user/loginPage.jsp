<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>

<style type="text/css">

	/* 로그인 부분 */
	.login a {
		text-decoration: none;
		color: black;
	}
	
	.login_sns li {
		list-style: none;
	}
	
	.wrap {
		width: 100%;
		height: 100vh;
		display: flex;
		align-items: center;
		justify-content: center;
		background: rgba(0, 0, 0, 0.1);
	}
	
	.login {
		width: 30%;
		height: 600px;
		background: white;
		border-radius: 20px;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}
	
	h2 {
		color: tomato;
		font-size: 2em;
	}
	
	.login_sns {
		padding: 20px;
		display: flex;
	}
	
	.login_sns img {
		height: 60px;
		width: 60px;
		padding: 0px 15px;
	}
	
	.login_sns a {
		width: 50px;
		height: 50px;
		display: flex;
		align-items: center;
		justify-content: center;
		padding: 20px;
		border-radius: 50px;
		background: white;
		font-size: 20px;
		box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.4), -3px -3px 5px
			rgba(0, 0, 0, 0.1);
		margin: 10px 20px;
	}
	
	.login_id {
		width: 100%;
		margin-top: 20px;
	}
	
	.login_id input {
		width: 100%;
		height: 50px;
		border-radius: 30px;
		margin-top: 10px;
		padding: 0px 20px;
		border: 1px solid lightgray;
		outline: none;
		align-items: center;
	}
	
	.login_pw {
		margin-top: 20px;
		width: 100%;
	}
	
	.login_pw input {
		width: 100%;
		height: 50px;
		border-radius: 30px;
		margin-top: 10px;
		padding: 0px 20px;
		border: 1px solid lightgray;
		outline: none;
	}
	
	.login_etc {
		padding: 10px;
		width: 90%;
		font-size: 14px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-weight: bold;
	}
	
	.submit {
		margin-top: 20px;
		width: 80%;
	}
	
	.submit input {
		width: 100%;
		height: 50px;
		border: 0;
		outline: none;
		border-radius: 40px;
		background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
		color: white;
		font-size: 1.2em;
		letter-spacing: 2px;
		cursor: pointer;
	}
	
	.enroll {
		margin-top: 20px;
		margin-bottom: 10px;
		width: 80%;
	}
	
	.enroll input {
		width: 100%;
		height: 50px;
		border: 0;
		outline: none;
		border-radius: 40px;
		background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
		color: white;
		font-size: 1.2em;
		letter-spacing: 2px;
		cursor: pointer;
	}
	
	.wrap {
		position: absolute;
		z-index: 3;
		left: 0;
		bottom: -13%;
		width: 100%;
		text-align: center;
	}
	
	.login_etc p {
		cursor: pointer;
	}
	
</style>
</head>
<body>
	<!-- Header 부분. 제목과 버튼 -->
	<c:import url="/WEB-INF/views/common/header.jsp" />

	<div class="box">
		<!-- slide 부분 -->
		<c:import url="/WEB-INF/views/common/slide.jsp" />

		<!-- 로그인 화면 -->
		<div class="wrap">
			<div class="login">
				<form action="login.do" method="post">					
					<h2>로그인</h2>
					<div class="login_sns">
						<a	href="https://accounts.google.com/signin/v2/identifier?hl=ko&passive=true&continue=https%3A%2F%2Fwww.google.com%2Fwebhp%3Fhl%3Dko%26sa%3DX%26ved%3D0ahUKEwiwsd7e7633AhV1yosBHZ5uDSYQPAgI&ec=GAZAmgQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin">
							<img class="google" src="resources/images/googleLogo.png"></img>
						</a> 
						<a href="https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com">
							<img class="naver" src="resources/images/naverLogo.png"></img>
						</a>
					</div>
					<div class="login_id">
						<h4>E-mail</h4>
						<input type="text" name="userid" placeholder="Id를 입력하세요.">
					</div>
					<div class="login_pw">
						<h4>Password</h4>
						<input type="password" name="userpwd" placeholder="비밀번호를 입력하세요.">
					</div>
					<div class="login_etc">
						<div class="forgot_id">
							<p onClick="location.href='findId.do'" >아이디를 잊으셨나요?<p>
						</div>
						<div class="forgot_pw">
							<p onClick="location.href='findPwd.do'">비밀번호를 잊으셨나요?</p>
						</div>
					</div>
					<div class="submit">
							<input type="submit" value="로그인">
					</div>
					<div class="enroll">
						<input onClick="location.href='enroll.do'" type="submit" value="회원 가입">
					</div>
				</form>
			</div>
		</div>

	</div>

	<%-- <!-- footer 부분 -->
	<c:import url="/WEB-INF/views/common/project_footer.jsp" /> --%>

</body>
</html>