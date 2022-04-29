package com.test.matching.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.matching.user.model.service.UserService;
import com.test.matching.user.model.vo.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// 뷰페이지 이동처리용메소드 --------------------------------------------------
	@RequestMapping("loginPage.do")
	public String moveLoginPage() {
		return "user/loginPage";
	}

	// 로그인처리용메소드
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(HttpServletRequest request, HttpServletResponse response, Model model) {

		// 서비스로 전달할 멤버객체생성
		User user = new User();
		user.setUserid(request.getParameter("userid"));
		user.setUserpwd(request.getParameter("userpwd"));

		User loginMember = userService.selectLogin(user);
		String viewName = null;
		if (loginMember != null) {
			// 로그인상태관리방법 : 상태관리 매커니즘 이라고함
			// 세션 쿠키 url재작성 3가지방법있음
			HttpSession session = request.getSession();
			// getSession() == getSession(true)
			// 세션객체가 없으면 자동으로 생성시킴
			// 세션객체가 있으면 해당세션의 정보를 리턴받음
			session.setAttribute("loginMember", loginMember);
			viewName = "matching/matchingView";
		} else { // 로그인 실패시
			model.addAttribute("message", "로그인 실패 : 아이디나 비번 확인하시오");
			viewName = "common/error";
		}

		return viewName;
	}

	// 로그아웃 처리용
	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		// 로그인할 때 생성한 세션객체를 없앰
		HttpSession session = request.getSession(false);
		// 세션 객체가 있으면, 리턴받음
		// 세션 객체가 없으면, null 리턴됨
		if (session != null) {
			session.invalidate(); // 해당 세션객체 없앰
			return "common/main";
		} else {
			model.addAttribute("message", "로그인 세션이 존재하지 않습니다.");
			return "common/error";
		}
	}

	// 회원가입페이지 이동용 메소드
	@RequestMapping("enroll.do")
	public String enrollPage() {
		return "user/enrollPage"; // 내보낼 뷰파일명 리턴
	}

	// ID찾기 페이지 이동용 메소드
	@RequestMapping("findId.do")
	public String findIdPage() {
		return "user/findIdPage"; // 내보낼 뷰파일명 리턴
	}

	// 비밀번호찾기 이동용 메소드
	@RequestMapping("findPwd.do")
	public String findPwdPage() {
		return "user/findPwdPage"; // 내보낼 뷰파일명 리턴
	}
	
	

}
