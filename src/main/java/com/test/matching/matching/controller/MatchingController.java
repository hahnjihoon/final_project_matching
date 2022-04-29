package com.test.matching.matching.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.matching.matching.model.service.MatchingService;
import com.test.matching.user.model.vo.User;

@Controller
public class MatchingController {
	private static final Logger logger = LoggerFactory.getLogger(MatchingController.class);
	
	@Autowired
	private MatchingService matchingService;
	
	//이상형 추천  
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
			model.addAttribute("message", "매칭페이지로 이동 실패");
			return "common/error";
		}
	}
	
}
