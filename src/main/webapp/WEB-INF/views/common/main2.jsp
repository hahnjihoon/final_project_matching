<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<style type="text/css">
section {
	text-align:center;
	align:center;
}
section>div {
	width: 360px;
	background: #ccffff;
}
section div table {
	width: 350px;
	background: white;
}
</style>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	/*주기적으로자동실행하려면 자바스크립트 내장함수 setInterval(실행시킬함수명, 시간간격밀리초) 사용*/
	/* setInterval(function(){
		console.log("setInterval() 에 의해 자동실행 확인");
		
	}, 100); */
	
	
	//최근 등록한 공지글 3개출력되게하는 함수
	$.ajax({
		url: "ntop3.do",
		type: "post",
		dataType: "json",
		success: function(data){
			console.log("success : "+data);  //Object로 받아짐 기본적으로
			
			//Object로받은걸 String으로 바꿈
			var jsonStr = JSON.stringify(data);
			
			//String으로바꾼걸 실제사용할 json객체로 바꿈
			var json = JSON.parse(jsonStr);
			
			var values = "";
			for(var i in json.list){ //i변수가 자동으로1씩증가
				values += "<tr><td>"+json.list[i].notice_num+"</td><td><a href='ndetail.do?notice_num="+json.list[i].notice_num+"'>"
						+decodeURIComponent(json.list[i].notice_title).replace(/\+/gi, " ")+"</a></td><td>"+json.list[i].notice_date+"</td></tr>";
						
				
				
			} //for in문 닫기
			
			$("#newnotice").html($("#newnotice").html()+values);
			
		},
		error: function(jqXHR, textstatus, errorthrown){
			console.log("error : "+jqXHR+", "+textstatus+", "+errorthrown);
		}
		
	});
	
	
	//조회수 많은 인기게시원글 상위3개 조회출력
	$.ajax({
		url: "btop3.do",
		type: "post",
		dataType: "json",
		success: function(data){
			console.log("success : "+data);  //Object로 받아짐 기본적으로
			
			//Object로받은걸 String으로 바꿈
			var jsonStr = JSON.stringify(data);
			
			//String으로바꾼걸 실제사용할 json객체로 바꿈
			var json = JSON.parse(jsonStr);
			
			var values = "";
			for(var i in json.list){ //i변수가 자동으로1씩증가
				values += "<tr><td>"+json.list[i].qa_num+"</td><td><a href='bdetail.do?qa_num="+json.list[i].qa_num+"'>"
						+decodeURIComponent(json.list[i].qa_title).replace(/\+/gi, " ")+"</a></td><td>"+json.list[i].qa_readcount+"</td></tr>";
						
				
				
			} //for in문 닫기
			
			$("#toplist").html($("#toplist").html()+values);
			
		},
		error: function(jqXHR, textstatus, errorthrown){
			console.log("error : "+jqXHR+", "+textstatus+", "+errorthrown);
		}
		
	});
	
});
</script>
</head>
<body>
<c:import url="/WEB-INF/views/common/header.jsp" />


<section>
	<!-- 노티스보드끝나면 최근등록 공지글3개조회출력할것 -->
	<div style="display:inline-block; border: 1px solid navy; padding: 5px; margin: 5px;">
		<h4>최근 공지글 top3</h4>
		<table id="newnotice" border="1" cellspacing="0">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
			</tr>
		</table>
	</div>
	
	<!-- 보드끝나면 조회수많은 게시글 3개 조회출력할것 -->
	<div style="display:inline-block; border: 1px solid navy; padding: 5px; margin: 5px;">
		<h4>QnA top3</h4>
		<table id="toplist" border="1" cellspacing="0">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
			</tr>
		</table>
	</div>
</section>


</body>
</html>