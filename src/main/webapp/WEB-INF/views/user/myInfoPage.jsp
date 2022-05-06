<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<script>
   function validate() {
      var pwd1 = document.getElementById("userpwd").value;
      var pwd2 = document.getElementById("userpwd2").value;

      if (pwd1 !== pwd2) {
         alert("암호와 암호 확인이 일치하지 않습니다. \n 다시 입력하세요.");
         document.getElementById("userpwd").select();
      }
   }
   
   // 닉네임 중복 체크 확인을 위한 ajax 실행 처리용 함수
   function dupCheckNick() {
      $.ajax({
         url: "nickchk.do",
         type: "post",
         data: { nick: $("#nick").val() },      /* id가 nick 를 찾아라 */
         success: function(data){
            console.log("success : " + data);
            if(data == "ok"){
               alert("사용 가능한 닉네임입니다.");   
               $("#height").focus();
            }else {
               alert("이미 사용중인 닉네임입니다, \n 다시 입력하세요");
               $("#nick").select();
            }
         },
         error: function(jqXHR, textstatus, errorthrown){
            console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
         }         
      });
   }
   
</script>
</head>
<body>
   <c:import url="/WEB-INF/views/common/header.jsp" />
   <hr>
   <center>
      <h1 align="center">내 정보 보기</h1>
      <br>
      <form method="post" action="userUpdate.do">
         <input type="hidden" name="origin_userpwd" value="${ user.userpwd }">
         <table id="outer" align="center" width="500" cellspacing="5" cellpadding="0">
            <tr>
               <th width="120">아이디</th>
               <td><input type="text" name="userid" value="${ user.userid }" readonly>
            </tr>
            <tr>
               <th width="120">암 호</th>
               <td><input type="password" name="userpwd" id="userpwd" value=""></td>
            </tr>
            <tr>
               <th width="120">암호확인</th>
               <td><input type="password" id="userpwd2" onblur="validate();"></td>   <!-- onblur는 focus가 사라질때 작동되는 이벤트 설정 -->
            </tr>
            <tr>
               <th width="120">이 름</th>
               <td><input type="text" name="username" value="${ requestScope.user.username }" readonly></td>
            </tr>
            <tr>
               <th width="120">이메일</th>
               <td><input type="email" name="email" value="${ user.email }"></td>
            </tr>
            <tr>
               <th width="120">전화번호</th>
               <td><input type="tel" name="phone" value="${ user.phone }"></td>
            </tr>
            <tr>
               <th width="120">본인 사진</th>
               <td><input type="text" name="img_name" value="${ user.img_name }"></td>
            </tr>         
            <tr>
               <th width="120">얼굴 외형</th>
               <td>
                  <c:if test="${ user.myeyes eq 'big_eye' }">
                     <c:set var="checked1" value="checked" />
                  </c:if>
                  <c:if test="${ user.myeyes eq 'normal_eye' }">
                     <c:set var="checked2" value="checked" />
                  </c:if>
                  <c:if test="${ user.myeyes eq 'small_eye' }">
                     <c:set var="checked3" value="checked" />
                  </c:if>
                  <c:if test="${ user.mynose eq 'big_nose' }">
                     <c:set var="checked4" value="checked" />
                  </c:if>
                  <c:if test="${ user.mynose eq 'normal_nose' }">
                     <c:set var="checked5" value="checked" />
                  </c:if>
                  <c:if test="${ user.mynose eq 'small_nose' }">
                     <c:set var="checked6" value="checked" />
                  </c:if>
                  <c:if test="${ user.mymouth eq 'big_mouth' }">
                     <c:set var="checked7" value="checked" />
                  </c:if>
                  <c:if test="${ user.mymouth eq 'normal_mouth' }">
                     <c:set var="checked8" value="checked" />
                  </c:if>
                  <c:if test="${ user.mymouth eq 'small_mouth' }">
                     <c:set var="checked9" value="checked" />
                  </c:if>               
                  <table width="350">
                     <tr>
                        <td><input type="checkbox" name="myeyes" value="big_eye" ${ checked1 }>큰 눈</td>
                        <td><input type="checkbox" name="myeyes" value="normal_eye" ${ checked2 }>보통 눈</td>
                        <td><input type="checkbox" name="myeyes" value="small_eye" ${ checked3 }>작은 눈</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="mynose" value="big_nose"  ${ checked4 }>큰 코</td>
                        <td><input type="checkbox" name="mynose" value="normal_nose"  ${ checked5 }>보통 코</td>
                        <td><input type="checkbox" name="mynose" value="small_nose"  ${ checked6 }>작은 코</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="mymouth" value="big_mouth"  ${ checked7 }>큰 코</td>
                        <td><input type="checkbox" name="mymouth" value="normal_mouth"  ${ checked8 }>보통 코</td>
                        <td><input type="checkbox" name="mymouth" value="small_mouth"  ${ checked9 }>작은 코</td>
                     </tr>
                  </table>
               </td>
            </tr>      
            <tr>
               <th width="120">닉네임</th>
               <td><input type="text" name="nick" id="nick" value="${ user.nick }">
                  &nbsp; &nbsp; 
                  <input type="button" value="중복체크" onclick="return dupCheckNick();">
               </td>      
            </tr>
            <tr>
               <th width="120">키</th>
               <td><input type="number" name="height" id="height" value="${ user.height }"></td>
            </tr>         
            <tr>
               <th width="120">성 별</th>
               <td><c:if test="${ user.gender eq 'M' }">
                     <input type="radio" name="gender" value="M" checked> 남자 &nbsp; 
                     <input type="radio" name="gender" value="F"> 여자 
                  </c:if> 
                  <c:if test="${ user.gender eq 'F' }">
                     <input type="radio" name="gender" value="M"> 남자 &nbsp; 
                     <input type="radio" name="gender" value="F" checked> 여자 
                  </c:if>
               </td>
            </tr>
            <tr>
               <th width="120">주 소</th>
               <td><input type="text" name="address" value="${ user.address }"></td>
            </tr>
            <tr>
               <th width="120">나 이</th>
               <td><input type="number" name="age" min="19" max="100" value="${ user.age }"></td>
            </tr>
            <tr>
               <th width="120">취 미</th>
               <td><input type="text" name="hobby" value="${ user.hobby }"></td>
            </tr>
            <tr>
               <th width="120">얼굴 외형</th>
               <td>
                  <c:if test="${ user.figure eq 'thin' }">
                     <c:set var="checked1" value="checked" />
                  </c:if>
                  <c:if test="${ user.figure eq 'slim' }">
                     <c:set var="checked2" value="checked" />
                  </c:if>
                  <c:if test="${ user.figure eq 'normal' }">
                     <c:set var="checked3" value="checked" />
                  </c:if>
                  <c:if test="${ user.figure eq 'muscle' }">
                     <c:set var="checked4" value="checked" />
                  </c:if>
                  <c:if test="${ user.figure eq 'fat' }">
                     <c:set var="checked5" value="checked" />
                  </c:if>                     
                  <table width="350">
                     <tr>
                        <td><input type="checkbox" name="figure" value="thin"  ${ checked1 }>큰 눈</td>
                        <td><input type="checkbox" name="figure" value="slim" ${ checked2 }>슬림탄탄</td>
                        <td><input type="checkbox" name="figure" value="normal" ${ checked3 }>보통</td>
                        <td><input type="checkbox" name="figure" value="muscle" ${ checked4 }>근육질</td>
                        <td><input type="checkbox" name="figure" value="fat" ${ checked5 }>통통</td>                  
                     </tr>
                  </table>
               </td>
            </tr>      
            <tr>
               <th width="120">직 업</th>
               <td><input type="text" name="userjob" value="${ user.userjob }"></td>
            </tr>
            <tr>
               <th width="120">이상형 사진</th>
               <td><input type="text" name="img_name2" value="${ user.img_name2 }"></td>
            </tr>
            <tr>
               <th width="120">얼굴 외형</th>
               <td>
                  <c:if test="${ user.eyes eq 'big_eye' }">
                     <c:set var="checked1" value="checked" />
                  </c:if>
                  <c:if test="${ user.eyes eq 'normal_eye' }">
                     <c:set var="checked2" value="checked" />
                  </c:if>
                  <c:if test="${ user.eyes eq 'small_eye' }">
                     <c:set var="checked3" value="checked" />
                  </c:if>
                  <c:if test="${ user.nose eq 'big_nose' }">
                     <c:set var="checked4" value="checked" />
                  </c:if>
                  <c:if test="${ user.nose eq 'normal_nose' }">
                     <c:set var="checked5" value="checked" />
                  </c:if>
                  <c:if test="${ user.nose eq 'small_nose' }">
                     <c:set var="checked6" value="checked" />
                  </c:if>
                  <c:if test="${ user.mouth eq 'big_mouth' }">
                     <c:set var="checked7" value="checked" />
                  </c:if>
                  <c:if test="${ user.mouth eq 'normal_mouth' }">
                     <c:set var="checked8" value="checked" />
                  </c:if>
                  <c:if test="${ user.mouth eq 'small_mouth' }">
                     <c:set var="checked9" value="checked" />
                  </c:if>               
                  <table width="350">
                     <tr>
                        <td><input type="checkbox" name="eyes" value="big_eye" ${ checked1 }>큰 눈</td>
                        <td><input type="checkbox" name="eyes" value="normal_eye" ${ checked2 }>보통 눈</td>
                        <td><input type="checkbox" name="eyes" value="small_eye" ${ checked3 }>작은 눈</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="nose" value="big_nose"  ${ checked4 }>큰 코</td>
                        <td><input type="checkbox" name="nose" value="normal_nose"  ${ checked5 }>보통 코</td>
                        <td><input type="checkbox" name="nose" value="small_nose"  ${ checked6 }>작은 코</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="mouth" value="big_mouth"  ${ checked7 }>큰 코</td>
                        <td><input type="checkbox" name="mouth" value="normal_mouth"  ${ checked8 }>보통 코</td>
                        <td><input type="checkbox" name="mouth" value="small_mouth"  ${ checked9 }>작은 코</td>
                     </tr>
                  </table>
               </td>
            </tr>      
            <tr>
               <th colspan="2">
                  <a href="javascript:history.go(-1);">이전페이지로 이동</a> &nbsp;
                  <input type="submit" value="수정하기"> &nbsp;
                  <input type="reset" value="수정취소"> &nbsp;
                  <a href="main.do">시작페이지로 이동</a> &nbsp;
                  <!-- 탈퇴하기 요청 처리용 -->                  
                  <a onclick="location.href='findId.do'">탈퇴하기</a> 
               </th>                              
            </tr>
         </table>
      </form>
   </center>
   <hr>
   <c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>