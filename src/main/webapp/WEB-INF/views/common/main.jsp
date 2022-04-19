<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>smart matching</title>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	var winW=cnt=setId=0;
	
	resizeFn(); //함수 호출
	setTimeout(resizeFn, 100); //오픈하자마다 실행

	function resizeFn(){ //반응형 이미지크기 조정함수
		winW=$(window).innerWidth(); 
		
		$(".slide").css({width: winW}); //창크기에 슬라이드이미지 맞춤
		
	};
	
	$(window).resize(function(){
			resizeFn();	//창크기 변경될 때 마다 함수 반복 실행
	});
	
	autoplayFn(); //함수 호출
	
	function autoplayFn(){ //4초마다 슬라이드 자동 작동
		setId = setInterval(nextCountFn, 4000);
	};
	
	
	
	$(".pageBt").each(function(idx){ //page버튼 클릭시마다 해당 이미지로 이동
		$(this).click(function(){
			clearInterval(setId); //autoplay함수 정지
			cnt = idx; 
			mainslideFn();
		});
	});
	
	
	function nextCountFn(){ //count(cnt)가 증가될때마다 슬라이드 작동
		cnt++;
		mainslideFn();
	};
	
	function prevCountFn(){ //count가 감소될때마다 슬라이드 작동
		cnt--;
		mainslideFn();
	};
	
	
	function mainslideFn(){ //메인슬라이드 함수
		$(".slideWrap").stop().animate({left: (-100*cnt)+"%"},600, function(){
			if(cnt>3){
				cnt=0; //count가 끝까지 이동했을때 다시 처음으로 돌아감
			}
			if(cnt<0){
				cnt=3
			}
			$(".slideWrap").stop().animate({left: (-100*cnt)+"%"},0)
		});
		$(".pageBt").removeClass("addPageBt");
		$(".pageBt").eq(cnt>3?cnt=0:cnt).addClass("addPageBt");
	};
	//animation사용시에는 stop을 넣어 부드럽게(덜컹거리지 않음)
	//count 변경시마다 버튼색깔 변경됨
	
});
</script>
<style type="text/css">
* {margin: 0;padding: 0;}
li {list-style: none;}
.slideContainer {width: 100%;position: relative;}
/*-아래 pageBt abosolute로 위치를 잡기 위하여 부모에 position: relative 필요*/
.slideWrap {width: calc(100%*6);overflow: hidden;margin-left: calc(-100%*1);position: relative;z-index: 1}
/*총600장을 나열하기위하여 calc(100%*6) 또는 600%, 맨처음 1번째 이미지 가기위하여 margin-left: calc(-100%*0)*/
.slide {float: left;}
.slide img {width: 100%;}
.pageBtWrap {z-index:2; position: absolute;left: 0;bottom: 7%;width: 100%;text-align: center;}
.pageBtWrap li {display: inline;}
/*부모요소를 text-align: center, li를 inline요소로 바꿔 중간 정렬, float: left 사용할 필요없음*/
.pageBtWrap li .pageBt{display: inline-block;width: 12px; height: 12px; border-radius: 50%;margin-left: 10px;background-color: #fff;}	
.pageBtWrap li .pageBt.addPageBt {background-color: #ff0;}


button {
	width:200px;
    background-color: black; /* Green */
    border: none;
    color: white;
    padding: 30px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 30px 30px;
    cursor: pointer;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
}
button:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
}

@font-face {
      src : url("resources/font/Blacksword.otf");
      font-family: "maintitle"
}
</style>
</head>
<body>
<div align="right">
		<button>로그인</button>
		<button>회원가입</button>
		<button>경욱 확인용</button>
		
		<button>성제 확인ddd</button>


</div>
<h1 style="font-family:maintitle; font-size:100px" align="center">Smart Matching</h1>

<div class="slideContainer" style="overflow:hidden">
	<ul class="slideWrap">
		<li class="slide slide4"><img src="resources/images/image44.jpg" ></li>
		<li class="slide slide1"><img src="resources/images/image11.jpg"></li>
		<li class="slide slide2"><img src="resources/images/image22.jpg"></li>
		<li class="slide slide3"><img src="resources/images/image33.jpg"></li>
		<li class="slide slide4"><img src="resources/images/image44.jpg"></li>
		<li class="slide slide1"><img src="resources/images/image11.jpg"></li>
	</ul>
	<ul class="pageBtWrap">
		<li><a href="#" class="pageBt addPageBt"></a></li>
		<li><a href="#" class="pageBt"></a></li>
		<li><a href="#" class="pageBt"></a></li>
		<li><a href="#" class="pageBt"></a></li>
	</ul>
</div>
	
</body>
</html>