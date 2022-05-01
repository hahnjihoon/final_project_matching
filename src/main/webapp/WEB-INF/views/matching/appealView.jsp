<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style type="text/css">
div.lineA {
   height: 100px;
   border: 1px solid gray;
   float: left;
   position: relative;
   left: 120px;
   margin: 5px;
   padding: 5px;
}
div#banner {
   width: 750px;
   padding: 0;
}
div#banner img {
   margin: 0;
   padding: 0;
   width: 750px;
   height: 110px;
}
div#loginBox {
   width: 274px;
   font-size: 9pt;
   text-align: left;
   padding-left: 20px;
}
div#loginBox button {
   width: 250px;
   height: 35px;
   background-color: navy;
   color: white;
   margin-top: 10px;
   margin-bottom: 15px;
   font-size: 14pt;
   font-weight: bold;
}
section {
   display:block;
   text-align:center;
   align:center;
}
section>div {
   width: 360px;
   background: #ccffff;
   text-align:center;
   align:center;
}
section div table {
   width: 350px;
   background: white;
}

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

img{
width: 300px;
height: 300px;
}



#slideShow{
  width: 500px;
  height: 300px;
  position: relative;
  margin: 50px auto;
  overflow: hidden;   
}


.slides{
  position: absolute;
  left: 0;
  top: 0;
  width: 2500px; /* 슬라이드할 사진과 마진 총 넓이 */
  transition: left 0.5s ease-out; 
  /*ease-out: 처음에는 느렸다가 점점 빨라짐*/
}

.slides li:first-child{
  margin-left: 100px;
}

.slides li:not(:last-child){
  float: left;
  margin-right: 100px;
}

.slides li{
  float: left;
}

.controller span{
  position:absolute;
  background-color: transparent;
  color: black;
  text-align: center;
  border-radius: 50%;
  padding: 10px 20px;
  top: 50%;
  font-size: 1.3em;
  cursor: pointer;
}
.controller span:hover{
  background-color: rgba(128, 128, 128, 0.11);
}

.prev{
  left: 10px;
}

.prev:hover{
  transform: translateX(-10px);
}

.next{
  right: 10px;
}

/* 다음 화살표에 마우스 커서가 올라가 있을때 
이전 화살표가 살짝 오른쪽으로 이동하는 효과*/
.next:hover{
  transform: translateX(10px);
}

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
 <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
 
 </head> 
 <body> 
<c:import url="/WEB-INF/views/common/header.jsp" />

<h1 style="font-family:maintitle; font-size:30px; text-align:center; cursor:pointer; " >매력평가</h1>

 <div id="slideShow"> 
 <ul class="slides"> 
 <li><img src="${ pageContext.servletContext.contextPath}/resources/images/image11.jpg" width:"300" height:"300" alt="" onclick = "clickImg()"></li>
 <li><img src="${ pageContext.servletContext.contextPath}/resources/images/image22.jpg" width:"300" height:"300" alt="" onclick = "clickImg()"></li>
 <li><img src="${ pageContext.servletContext.contextPath}/resources/images/image33.jpg" width:"300" height:"300" alt="" onclick = "clickImg()"></li>
 <li><img src="${ pageContext.servletContext.contextPath}/resources/images/image44.jpg" width:"300" height:"300" alt="" onclick = "clickImg()"></li> 
 <li><img src="${ pageContext.servletContext.contextPath}/resources/images/image11.jpg" width:"300" height:"300" alt="" onclick = "clickImg()"></li> 
 <li><img src="${ pageContext.servletContext.contextPath}/resources/images/image22.jpg" width:"300" height:"300" alt="" onclick = "clickImg()"></li> 
 </ul> 
 <p class="controller">
 <!-- &lang: 왼쪽 방향 화살표 &rang: 오른쪽 방향 화살표 --> 
 <span class="prev" onclick = "delete_row()">&lang;</span> 
 <span class="next" onclick = "delete_row()">&rang;</span> 
 </p> 
 </div> 
 
<section>
<div id="bb" style="float:left; border: 1px solid navy; padding: 5px; margin: 5px;">
<h4>이미지 클릭하여 해당인물 정보보기</h4>
<table id="aa" border="1" cellspacing="0">
</table>
</div>
</section>
 </body>
  
  
  <script>
 const slides = document.querySelector('.slides'); //전체 슬라이드 컨테이너
 const slideImg = document.querySelectorAll('.slides li'); //모든 슬라이드들
 let currentIdx = 0; //현재 슬라이드 index
 const slideCount = slideImg.length; // 슬라이드 개수
 const prev = document.querySelector('.prev'); //이전 버튼
 const next = document.querySelector('.next'); //다음 버튼
 const slideWidth = 300; //한개의 슬라이드 넓이
 const slideMargin = 100; //슬라이드간의 margin 값

 //전체 슬라이드 컨테이너 넓이 설정
 slides.style.width = (slideWidth + slideMargin) * slideCount + 'px';

 function moveSlide(num) {
   slides.style.left = -num * 400 + 'px';
   currentIdx = num;
 }
 function delete_row() {
       var my_tbody = document.getElementById('aa');
       var t_length = my_tbody.rows.length;
       for(i=0; i<t_length; i++) {
       if (my_tbody.rows.length < 1) return;
       //my_tbody.deleteRow(i); // 상단부터 삭제
        my_tbody.deleteRow( my_tbody.rows.i ); 
       }
     }
 function clickImg(){
    $.ajax({
         url: "showInfo.do",
         type: "post",
         dataType: "json",
         success: function(data){
            var my_tbody = document.getElementById('aa');
             var t_length = my_tbody.rows.length;
            if(t_length ==0){
            console.log("success : " + data); //Object 로 받아짐
            
            //object => string 으로 바꿈
            var jsonStr = JSON.stringify(data);
            //string => json 객체로 바꿈
            var json = JSON.parse(jsonStr);
            
            var values = "";
            
            values += "<tr><th>번호</th><th>제목</th><th>날짜</th></tr>";
            for(var i in json.list){  //i(인덱스) 변수가 자동으로 1씩 증가 처리됨
               values += "<tr><td>" + json.list[i].noticeno 
                     + "</td><td><a href='ndetail.do?noticeno=" + json.list[i].noticeno + "'>"
                     + decodeURIComponent(json.list[i].noticetitle).replace(/\+/gi, " ") 
                     + "</a></td><td>" + json.list[i].noticedate + "</td></tr>";
            }  //for in
            
            $("#aa").html($("#aa").html() + values);
            }
            },
         error: function(jqXHR, textstatus, errorthrown){
            console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
         }
      });  //ajax
   }
 prev.addEventListener('click', function () {
   /*첫 번째 슬라이드로 표시 됐을때는 
   이전 버튼 눌러도 아무런 반응 없게 하기 위해 
   currentIdx !==0일때만 moveSlide 함수 불러옴 */

   if (currentIdx !== 0) moveSlide(currentIdx - 1);
 });

 next.addEventListener('click', function () {
   /* 마지막 슬라이드로 표시 됐을때는 
   다음 버튼 눌러도 아무런 반응 없게 하기 위해
   currentIdx !==slideCount - 1 일때만 
   moveSlide 함수 불러옴 */
   if (currentIdx !== slideCount - 1) {
     moveSlide(currentIdx + 1);
   }
 });
 </script>
   </html>