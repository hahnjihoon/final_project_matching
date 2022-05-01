package com.test.matching.matching.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	
	@RequestMapping("matchingPage.do")
	public String moveLoginPage() {
		return "matching/matchingView";
	}
	
	@RequestMapping("appealPage.do")
	public String moveAppealPage() {
		return "matching/appealView";
	}
	
	//�̻��� ��õ  
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
			model.addAttribute("message", "��Ī�������� �̵� ����");
			return "common/error";
		}
	}
	
	
	//�ŷ¾��� ��õ  
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
				model.addAttribute("message", "��Ī�������� �̵� ����");
				return "common/error";
			}
		}
	
		


		   
}
