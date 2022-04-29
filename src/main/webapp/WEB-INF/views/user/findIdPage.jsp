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
	
	.userName {
		margin-top: 20px;
		width: 80%;
	}
	
	.userName input {
		width: 100%;
		height: 50px;
		border-radius: 30px;
		margin-top: 10px;
		padding: 0px 20px;
		border: 1px solid lightgray;
		outline: none;
	}
	
	.oneLine {
		margin-top: 20px;
		width: 100%;
	}
	
	.userEmail {
		width: 75%;		
		display: inline-block;
	}
	
 	.userEmail input {
		width: 75%;
		height: 50px;
		border-radius: 30px;
		margin-top: 10px;
		padding: 0px 10px;
		border: 1px solid lightgray;
		outline: none;
	}
	
	.send_code {
		width: 20%;		
		display: inline-block;
	}
	
 	.send_code button {
		height: 50px;
		border: 0;
		outline: none;
		border-radius: 40px;
		background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
		color: white;
		font-size: 15px;
		letter-spacing: 1px;
		cursor: pointer;
	}
	
	.vertificationCode {
		width: 80%;
	}
	
	.vertificationCode input {
		width: 100%;
		height: 50px;
		border-radius: 30px;
		margin-top: 10px;
		padding: 0px 20px;
		border: 1px solid lightgray;
		outline: none;
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
	
</style>
</head>
<body>
	<!-- Header 부분. 제목과 버튼 -->
	<c:import url="/WEB-INF/views/common/project_header.jsp" />

	<div class="box">
		<!-- slide 부분 -->
		<c:import url="/WEB-INF/views/common/project_slide.jsp" />

		<!-- 로그인 화면 -->
		<div class="wrap">
			<div class="login">
				<h2>아이디 찾기 테스트</h2>
				
				<div class="userName">
					<h4>이름</h4>
					<input type="email" name="userName" placeholder="이름을 입력하세요.">
				</div>
				<div class="oneLine">
					<div class="userEmail"  >
						<h4>E-mail</h4>
						<input type="password" name="userEmail" placeholder="이메일을 입력하세요.">
					</div>
					<div class="send_code" >
						<button>인증번호 발송</button>
					</div>
				</div>
				<div class="vertificationCode">
					<input type="password" name="vertificationCode" placeholder="인증번호를 입력하세요.">
				</div>
				<div class="submit">
					<input type="submit" value="로그인">
				</div>
				<div class="enroll">
					<input onclick="moveEnrollPage();" type="submit" value="회원 가입">
				</div>
				
			</div>
		</div>

	</div>

	<%-- <!-- footer 부분 -->
	<c:import url="/WEB-INF/views/common/project_footer.jsp" /> --%>

</body>
</html>