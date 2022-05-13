package com.test.matching.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.matching.common.CopyOrDel;
import com.test.matching.processBuilder.testProcessBuilder;
import com.test.matching.user.model.service.UserService;
import com.test.matching.user.model.vo.User;

@Controller
public class UserController {

   
   static String renameFilename = null;
   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

   @Autowired
   private UserService userService;



   @Autowired
   private BCryptPasswordEncoder bcryptPasswordEncoder;

//        *********************************************************************************************   

   //    --------------------------------------------------
   @RequestMapping("loginPage.do")
   public String moveLoginPage() {
      return "user/loginPage";
   }

 
   @RequestMapping(value = "login.do", method = RequestMethod.POST)
   public String loginMethod(User user, HttpSession session, SessionStatus status, Model model) {
      logger.info("login.do" + user);


      User loginUser = userService.selectUser(user.getUserid());

 
      if (loginUser != null && bcryptPasswordEncoder.matches(user.getUserpwd(), loginUser.getUserpwd())
            && loginUser.getLogin_ok().equals("Y")) {
              
         session.setAttribute("loginUser", loginUser);
         status.setComplete(); 
         return "common/main2";
      } else {
         model.addAttribute("message", " α        !    ̵    ȣ   Ȯ   ϼ   . \n  Ǵ   α         ȸ            ڿ         ϼ   .");
         return "common/error";
      }
   }

   
   @RequestMapping("logout.do")
   public String logoutMethod(HttpServletRequest request, Model model) {
          
      HttpSession session = request.getSession(false);

      if (session != null) {
         session.invalidate();  
         return "common/main";
      } else {
         model.addAttribute("message", " α                     ʽ  ϴ .");
         return "common/error";
      }
   }

//          *********************************************************************************************

//       *********************************************************************************************


   @RequestMapping("enrollPage.do")
   public String enrollPage() {
      return "user/enrollPage"; 
   }


   @RequestMapping(value = "enroll.do", method = RequestMethod.POST)
   public String memberInsertMethod(User user, Model model, HttpServletRequest request, @RequestParam(name="upfile", required=false) MultipartFile mfile, @RequestParam(name="upfile2", required=false) MultipartFile mfile2) throws IOException {
      
      
      String savePath1 = request.getSession().getServletContext().getRealPath("resources/myImage");
      String savePath2 = request.getSession().getServletContext().getRealPath("resources/idealimage");
      String renameFileName = null;
      String renameFileName1 = null;
      if(!mfile.isEmpty()) {
         String fileName=mfile.getOriginalFilename();
         
         
         //내 이미지
         //이름바꾸기처리 : 년월일시분초.확장자
         if(fileName != null && fileName.length() > 0) {
            
            //바꿀파일명에대한문자열 만들기
            //공지글 등록요청시점의 날짜정보이용
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            
            //변경할파일이름만들기
            renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
            
            //확장자붙여
            renameFileName += "." + fileName.substring(fileName.lastIndexOf(".")+1);
            
            //파일객체만들기
            File originFile = new File(savePath1+"\\"+fileName);
            File renameFile = new File(savePath1+"\\"+renameFileName);
            
            //업로드 파일저장시키고 바로이름바꾸기실행
            try {
               mfile.transferTo(renameFile);
            } catch (Exception e) {
               e.printStackTrace();
               model.addAttribute("message", "전송파일 저장실패");
               return "common/error";
            }
            
            user.setImg_name(renameFileName);
            

         }


         
      } 
      
      int count = userService.count() + 1;
      /*
       * int[] data = {401};
       * 
       * for (int i =0; i< data.length; i++ ) { if(data[i]%2 ==0) { count++; } }
       */
        String oriFilePath = "C:\\Users\\ict02-17\\Desktop\\final_fromgit\\final_project_matching\\src\\main\\webapp\\resources\\myImage" + renameFileName;
        //복사될 파일경로
        String copyFilePath = "C:\\find_simface\\image\\" + count +".jpg";

        
        //파일객체생성
        File oriFile = new File(oriFilePath);
        //복사파일객체생성
        File copyFile = new File(copyFilePath);
        
        try {
            
            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
            
            int fileByte = 0; 
            // fis.read()가 -1 이면 파일을 다 읽은것
            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }
            //자원사용종료
            fis.close();
            fos.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        
    }
   
        // 이상형
      if(!mfile2.isEmpty()) {
         String fileName=mfile2.getOriginalFilename();
         
         //이름바꾸기처리 : 년월일시분초.확장자
         if(fileName != null && fileName.length() > 0) {
            
            //바꿀파일명에대한문자열 만들기
            //공지글 등록요청시점의 날짜정보이용
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss2");
            
            //변경할파일이름만들기
            renameFileName1 = sdf.format(new java.sql.Date(System.currentTimeMillis()));
            
            //확장자붙여
            renameFileName1 += "." + fileName.substring(fileName.lastIndexOf(".")+1);
            
            //파일객체만들기
            File originFile = new File(savePath2+"\\"+fileName);
            File renameFile = new File(savePath2+"\\"+renameFileName1);
            
            //업로드 파일저장시키고 바로이름바꾸기실행
            try {
               mfile2.transferTo(renameFile);
            } catch (Exception e) {
               e.printStackTrace();
               model.addAttribute("message", "전송파일 저장실패");
               return "common/error";
            }
            
            user.setImg_name2(renameFileName1);
            
            
         }


         
      }

      int count1 = userService.count1() + 1;
      /*
       * int[] data1 = {2};
       * 
       * for (int i =0; i< data1.length; i++ ) { if(data1[i]%2 ==0) { count1++; }
       */
      
        String oriFilePath1 = "C:\\Users\\ict02-17\\OneDrive\\Desktop\\final_fromgit\\final_project_matching\\src\\main\\webapp\\resources\\idealimage\\" + renameFileName1;
        //복사될 파일경로
        String copyFilePath1 = "C:\\find_simface\\ideal_image\\" + count1 +".jpg";
        count1 = count1++;
        //파일객체생성
        File oriFile1 = new File(oriFilePath1);
        //복사파일객체생성
        File copyFile1 = new File(copyFilePath1);
        
        try {
            
            FileInputStream fis1 = new FileInputStream(oriFile1); //읽을파일
            FileOutputStream fos1 = new FileOutputStream(copyFile1); //복사할파일
            
            int fileByte1 = 0; 
            // fis.read()가 -1 이면 파일을 다 읽은것
            while((fileByte1 = fis1.read()) != -1) {
                fos1.write(fileByte1);
            }
            //자원사용종료
            fis1.close();
            fos1.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        
    }

      
      
     
  
      logger.info("enroll.do : " + user);


      user.setUserpwd(bcryptPasswordEncoder.encode(user.getUserpwd()));
      logger.info("after encode : " + user);
      
      

      
/*      //파일객체생성
        File oriFile = new File(oriFilePath);
        //복사파일객체생성
        File copyFile = new File(copyFilePath);
        
        try {
            
            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

      if (userService.insertUser(user) > 0) {
         
         //위파일 다 저장되면 프로세스빌더로 py파일 실행해서 분석후 디비저장완료됨
//        testProcessBuilder tb = new testProcessBuilder();
//        tb.myface();
//        tb.idealface();
         
         return "redirect:main.do";
      } else {
         model.addAttribute("message", "디비저장실패!");
         return "common/error";
      }
      

         
      
         
      

   }
   
   
   
   
   
   
   
   @RequestMapping(value = "naverEnroll.do", method = RequestMethod.POST)
	public String memberInsertMethod2(User user, Model model, HttpServletRequest request, @RequestParam(name="upfile", required=false) MultipartFile mfile, @RequestParam(name="upfile2", required=false) MultipartFile mfile2) {
		
		String savePath1 = request.getSession().getServletContext().getRealPath("resources/myImage");
		String savePath2 = request.getSession().getServletContext().getRealPath("resources/idealimage");
		
		if(!mfile.isEmpty()) {
			String fileName=mfile.getOriginalFilename();
			

			if(fileName != null && fileName.length() > 0) {
				
	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				
				
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				
		
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".")+1);
				
			
				File originFile = new File(savePath1+"\\"+fileName);
				File renameFile = new File(savePath1+"\\"+renameFileName);
				
		
				try {
					mfile.transferTo(renameFile);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "�쟾�넚�뙆�씪 ���옣�떎�뙣");
					return "common/error";
				}
				
				user.setImg_name(renameFileName);
			}
			
		} 
		
		if(!mfile2.isEmpty()) {
			String fileName=mfile2.getOriginalFilename();
			

			if(fileName != null && fileName.length() > 0) {
				
		
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss2");
				
			
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				
				
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".")+1);
				
			
				File originFile = new File(savePath2+"\\"+fileName);
				File renameFile = new File(savePath2+"\\"+renameFileName);
				

				try {
					mfile2.transferTo(renameFile);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "파일이름변경오류");
					return "common/error";
				}
				
				user.setImg_name2(renameFileName);
			}
			
		} 
		
		logger.info("enroll.do : " + user);


		user.setUserpwd(bcryptPasswordEncoder.encode(user.getUserpwd()));
		logger.info("after encode : " + user);

		if (userService.insertUser(user) > 0) {
			return "redirect:main.do";
		} else {
			model.addAttribute("message", "회占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙!");
			return "common/error";
		}

	}

   
  
   @RequestMapping(value = "idchk.do", method = RequestMethod.POST)
   public void dubIdCheckMethod(@RequestParam("userid") String userid, HttpServletResponse response)
         throws IOException {
      int idcount = userService.selectDupCheckId(userid);

      String returnValue = null;
      if (idcount == 0) {
         returnValue = "ok";
      } else {
         returnValue = "dup";
      }
               
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      out.append(returnValue);
      out.flush();
      out.close();
   }
 
   @RequestMapping(value = "nickchk.do", method = RequestMethod.POST)
   public void dubNickCheckMethod(@RequestParam("nick") String nick, HttpServletResponse response) throws IOException {
      int nickcount = userService.selectDupCheckNick(nick);

      String returnValue = null;
      if (nickcount == 0) {
         returnValue = "ok";
      } else {
         returnValue = "dup";
      }
                
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      out.append(returnValue);
      out.flush();
      out.close();
   }

//              *********************************************************************************************

//          *********************************************************************************************

 
   @RequestMapping("myinfo.do")
   public ModelAndView myInfoMethod(@RequestParam(value="userid") String userid, ModelAndView mv) {
      User user = userService.selectUser(userid);

      if (user != null) { //   ȸ        ߴٸ 
         mv.addObject("user", user);
         mv.setViewName("user/myInfoPage");
      } else { //   ȸ        ߴٸ 
         mv.addObject("message", userid + " : ȸ          ȸ     ! ");
         mv.setViewName("common/error");
      }

      return mv;
   }

  
   @RequestMapping(value = "userUpdate.do", method = RequestMethod.POST)
   public String userUpdateMethod(User user, Model model, @RequestParam("origin_userpwd") String originUserpwd) {
      logger.info("userUpdate.do : " + user);
      logger.info("origin_userpwd : " + originUserpwd);

 
      String userpwd = user.getUserpwd().trim(); 
      if (userpwd != null && userpwd.length() > 0) {

         if (!bcryptPasswordEncoder.matches(userpwd, originUserpwd)) {

            user.setUserpwd(bcryptPasswordEncoder.encode(userpwd));
         }
      } else {
    
         user.setUserpwd(originUserpwd);
      }

      logger.info("after : " + user);

      if (userService.updateUser(user) > 0) {

         return "redirect: myinfo.do?userid=" + user.getUserid();
      } else {
         model.addAttribute("message", "ȸ                !");
         return "common/error.jsp";
      }

   }


   @RequestMapping("udel.do")
   public String memberDeleteMethod(@RequestParam("userid") String userid, Model model) {
      if (userService.deleteUser(userid) > 0) {
         return "redirect:logout.do";
      } else {
         model.addAttribute("message", userid + "ȸ                 !");
         return "common/error";
      }
   }

//                 *********************************************************************************************

//   naver로그인   *********************************************************************************************
   
   @RequestMapping(value="naverLogin.do", method= RequestMethod.GET) 
   public String index() { 
      logger.info("naverLogin.do : "); 
      return "user/naverLoginPage"; 
   } 
   
   @RequestMapping(value="naverCallBack.do", method=RequestMethod.GET) 
   public String loginPOSTNaver(HttpSession session) { 
      logger.info("naverLoginCallBack.do : "); 
      return "user/naverLoginCallBack"; 
   }
   
   
   
// *********************************************************************************************
   
   
   

   @RequestMapping("findId.do")
   public String findIdPage() {
      return "user/findIdPage"; //             ϸ      
   }


   @RequestMapping("findPwd.do")
   public String findPwdPage() {
      return "user/findPwdPage"; //             ϸ      
   }

}