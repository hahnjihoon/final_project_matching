<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<style>
   table th { background-color: #99ffff; }
   
   table#outer { border: 2px solid navy;}
   
</style>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
   // 전송(submit) 버튼 눌러졌을 때, 입력값들에 대한 유효성 검사
   function validate() {
      // 암호와 암호확인이 같은 값인지 확인
      var pwdValue = document.getElementById("userpwd").value;   /* document는 body 영역을 뜻함 */
      var pwdValue2 = document.getElementById("userpwd2").value;
      
      if (pwdValue !== pwdValue2) {
         alert("암호와 암호확인의 값이 일치하지 않습니다. \n 다시 입력하세요.")
         docunemt.getElementById("userpwd").select();   /* 암호값 부분이 선택되라는 뜻 */
         return false;   // 전송취소
      }
      
      return true;   // 전송함
   }
   
   // 아이디 중복 체크 확인을 위한 ajax 실행 처리용 함수
   function dupCheckId() {
      $.ajax({
         url: "idchk.do",
         type: "post",
         data: { userid: $("#userid").val() },      /* id가 userid 를 찾아라 */
         success: function(data){
            console.log("success : " + data);
            if(data == "ok"){
               alert("사용 가능한 아이디입니다.");
               $("#userpwd").focus();
            }else {
               alert("이미 사용중인 아이디입니다, \n 다시 입력하세요");
               $("#userid").select();
            }
         },
         error: function(jqXHR, textstatus, errorthrown){
            console.log("error : " + jqXHR + ", " + textstatus + ", " + errorthrown);
         }         
      });
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
   <center>
      <h1 align="center">회원 가입 페이지</h1>
      <br>
      <form action="enroll.do" method="post" onsubmit="return validate();" >   <!-- submit 될 때 유효한 데이터인지 확인할 수 있음 유효성 검사-->
         <table id="outer" align="center" width="500" cellspacing="5" cellpadding="0">
            <tr>
               <th colspan="2">회원 정보를 입력해 주세요. (* 표시는 필수입력 항목입니다,)</th>                              
            </tr>            
            <tr>
               <th width="120">* 아이디</th>
               <td><input type="text" name="userid" id="userid" required>
               &nbsp; &nbsp; 
               <input type="button" value="중복체크" 
               onclick="return dupCheckId();"></td>                  
            </tr>
            <tr>
               <th width="120">* 암 호</th>
               <td><input type="password" name="userpwd" id="userpwd" required></td>                  
            </tr>
            <tr>
               <th width="120">* 암호확인</th>
               <td><input type="password" id="userpwd2"></td>   <!-- required 영역에 없으면 전송 안됨 -->               
            </tr>
            <tr>
               <th width="120">* 이 름</th>
               <td><input type="text" name="username" required></td>   <!-- required 영역에 없으면 전송 안됨 -->               
            </tr>
            <tr>
               <th width="120">* 이메일</th>
               <td><input type="email" name="email" required> </td>   <!-- required 영역에 없으면 전송 안됨 -->               
            </tr>
            <tr>
               <th width="120">* 전화번호</th>
               <td><input type="tel" name="phone" required></td>   <!-- required 영역에 없으면 전송 안됨 -->               
            </tr>   
            <tr>
               <th width="120">본인 사진</th>
               <td><input type="text" name="img_name" required></td>   <!-- required 영역에 없으면 전송 안됨 -->               
            </tr>   
            <tr>
               <th width="120">얼굴 외형</th>
               <td>
                  <table width="350"  >
                     <tr>
                        <td><input type="checkbox" name="myeyes" value="big_eye" checked>큰 눈</td>
                        <td><input type="checkbox" name="myeyes" value="normal_eye" >일반 눈</td>
                        <td><input type="checkbox" name="myeyes" value="small_eye" >작은 눈</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="mynose" value="big_nose" checked>큰 코</td>
                        <td><input type="checkbox" name="mynose" value="normal_nose" >일반 코</td>
                        <td><input type="checkbox" name="mynose" value="small_nose" >작은 코</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="mymouth" value="big_mouth" checked>큰 입</td>
                        <td><input type="checkbox" name="mymouth" value="normal_mouth" >일반 입</td>
                        <td><input type="checkbox" name="mymouth" value="small_mouth" >작은 입</td>
                     </tr>
                  </table>
               </td>               
            </tr>
            <tr>
               <th width="120">* 닉네임</th>
               <td><input type="text" name="nick" id="nick" required>      
               &nbsp; &nbsp; 
               <input type="button" value="중복체크" 
               onclick="return dupCheckNick();"></td>               
            </tr>
            <tr>
               <th width="120">* 키</th>
               <td><input type="number" name="height" id="height" required></td>               
            </tr>
            <tr>
               <th width="120">* 성 별</th>
               <td><input type="radio" name="gender" value="M" checked> 남자
               &nbsp; <input type="radio" name="gender" value="F"> 여자 
               </td>   <!-- required 영역에 없으면 전송 안됨 -->                                 
            </tr>
            <tr>
               <th width="120">* 주 소</th>
               <td><input type="text" name="address" required></td>               
            </tr>
            <tr>
               <th width="120">* 나 이</th>
               <td><input type="number" name="age" min="19" max="100" value="20" required></td>               
            </tr>                     
            <tr>
               <th width="120">취 미</th>
               <td>
                  <td><input type="text" name="hobby" ></td>
               </td>               
            </tr>
            <tr>
               <th width="120">* 체 형</th>
               <td>
                  <table width="350"  >
                     <tr>
                        <td><input type="checkbox" name="figure" value="thin" >마름</td>
                        <td><input type="checkbox" name="figure" value="slim" >슬림탄탄</td>
                        <td><input type="checkbox" name="figure" value="normal" >보통</td>
                        <td><input type="checkbox" name="figure" value="muscle" >근육질</td>
                        <td><input type="checkbox" name="figure" value="fat" >통통</td>
                     </tr>
                  </table>
               </td>               
            </tr>
            <tr>
               <th width="120">직 업</th>
               <td>
                  <td><input type="text" name="userjob" ></td>
               </td>               
            </tr>
            <tr>
               <th width="120">이상형 사진</th>
               <td><input type="text" name="img_name2" required></td>   <!-- required 영역에 없으면 전송 안됨 -->               
            </tr>   
            <tr>
               <th width="120">이상형 외모</th>
               <td>
                  <table width="350"  >
                     <tr>
                        <td><input type="checkbox" name="eyes" value="big_eye" checked>큰 눈</td>
                        <td><input type="checkbox" name="eyes" value="normal_eye" >일반 눈</td>
                        <td><input type="checkbox" name="eyes" value="small_eye" >작은 눈</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="nose" value="sport" checked>큰 코</td>
                        <td><input type="checkbox" name="nose" value="music" >일반 코</td>
                        <td><input type="checkbox" name="nose" value="movie" >작은 코</td>
                     </tr>
                     <tr>
                        <td><input type="checkbox" name="mouth" value="big_mouth" checked>큰 입</td>
                        <td><input type="checkbox" name="mouth" value="normal_mouth" >일반 입</td>
                        <td><input type="checkbox" name="mouth" value="small_mouth" >작은 입</td>
                     </tr>
                  </table>
               </td>               
            </tr>
            <tr>
               <th colspan="2">
                  <input type="submit" value="가입하기"> &nbsp;
                  <input type="reset" value="작성취소"> &nbsp;
                  <a href="main.do">시작페이지로 이동</a>
               </th>                              
            </tr>
         </table>
      </form>
   </center>
</body>
</html>