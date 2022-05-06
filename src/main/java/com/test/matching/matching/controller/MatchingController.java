package com.test.matching.matching.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.matching.matching.model.service.MatchingService;
import com.test.matching.notice.model.service.NoticeService;
import com.test.matching.notice.model.vo.Notice;
import com.test.matching.user.model.service.UserService;
import com.test.matching.user.model.vo.User;

@Controller
public class MatchingController {
   private static final Logger logger = LoggerFactory.getLogger(MatchingController.class);
   
   @Autowired
   private MatchingService matchingService;
   
   @Autowired
   private NoticeService noticeService;
   
   @Autowired
   private UserService userService;
   
   
   @RequestMapping("matchingPage.do")
   public String moveLoginPage() {
      return "matching/matchingView";
   }
   
   @RequestMapping("appealPage.do")
   public String moveAppealPage() {
      return "matching/appealView";
   }
   
   //占싱삼옙占쏙옙 占쏙옙천  
   @RequestMapping(value="matching.do", method=RequestMethod.POST)
   public String matchingMethod(Model model, HttpServletRequest request) {
      
      HttpSession session = request.getSession();
      User loginMember = (User)session.getAttribute("loginMember");
      String userid = loginMember.getUserid();
      
      ArrayList<User> list = matchingService.selectMatch(userid);  
      
      if(userid !=null) {
         model.addAttribute("user", userid);
         return "matching/matchingView";
      }else {
         model.addAttribute("message", "占쏙옙칭占쏙옙占쏙옙占쏙옙占쏙옙 占싱듸옙 占쏙옙占쏙옙");
         return "common/error";
      }
   }
   
   
   //占신력억옙占쏙옙 占쏙옙천  
      @RequestMapping(value="appeal.do", method=RequestMethod.POST)
      public String appealMethod(Model model, HttpServletRequest request) {
         
         HttpSession session = request.getSession();
         User loginMember = (User)session.getAttribute("loginMember");
         String userid = loginMember.getUserid();
         
         ArrayList<User> list = matchingService.selectAppeal(userid);  
         
         if(userid !=null) {
            model.addAttribute("user", userid);
            return "matching/appealView";
         }else {
            model.addAttribute("message", "占쏙옙칭占쏙옙占쏙옙占쏙옙占쏙옙 占싱듸옙 占쏙옙占쏙옙");
            return "common/error";
         }
      }
   
      

      @RequestMapping(value="showInfo.do", method=RequestMethod.POST)
      @ResponseBody
      public String noticeNewTop3Method(HttpServletResponse response) throws UnsupportedEncodingException {
         //최근 등록 공지글 3개 조회해 옴
     ArrayList<User> list = userService.selectList2();
     
     //전송용 json 객체 생성
     JSONObject sendJson = new JSONObject();
     //list 옮길 json 배열 준비;
     JSONArray jarr = new JSONArray();
     
     //list 를 jarr 로 옮기기(복사)
     for(User user : list) {
        //notice 필드값 저장용 json 객체 생성
    JSONObject job = new JSONObject();
    
    job.put("username", user.getUsername());
    job.put("height", user.getHeight());
    job.put("userjob", user.getUserjob()); 
    // 날짜는 반드시 to스트링 으로 문자열로 바꿔서 json에 담아야함
    
    jarr.add(job); //job을 jarr에 저장
     }
     //전송용 객체에 jarr 을 담음
     sendJson.put("list", jarr);
     
     return sendJson.toJSONString(); // json을 json string 형으로 바꿔서 전송함
     //뷰리졸버에게로 리턴됨
         
         
      }

         
}