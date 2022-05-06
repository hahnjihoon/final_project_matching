package com.test.matching.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.test.matching.user.model.service.UserService;
import com.test.matching.user.model.vo.User;

@Controller
public class UserController {
	// 이 클래스의 메소드 안에서 로그 출력을 원하면 로그객체 생성
	// src/main/resources/log4j.xml 에 설정된 내용으로 출력됨
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// 비밀번호 암호화처리
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

// 로그인, 아웃 관련 메소드들 모음 *********************************************************************************************	

	// 로그인페이지 이동처리용메소드 --------------------------------------------------
	@RequestMapping("loginPage.do")
	public String moveLoginPage() {
		return "user/loginPage";
	}

	// 로그인처리용메소드
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(User user, HttpSession session, SessionStatus status, Model model) {
		logger.info("login.do" + user);

		// 암호화 처리된 패스워드 일치 조회는 select 해온 값으로 비교해야 함
		// 회원 아이디로 회원 정보를 먼저 조회함
		User loginUser = userService.selectUser(user.getUserid());

		// 암호화된 패스워드와 전달된 패스워드가 일치하는지 확인 : matches(일반글자 패스워드, 암호화된 패스워드)
		if (loginUser != null && bcryptPasswordEncoder.matches(user.getUserpwd(), loginUser.getUserpwd())
				&& loginUser.getLogin_ok().equals("Y")) {
			// 세션 객체 생성 > 세션 안에 회원정보 저장
			session.setAttribute("loginUser", loginUser);
			status.setComplete(); // 로그인 요청 성공, 200 전송됨
			return "common/main";
		} else {
			model.addAttribute("message", "로그인 실패! 아이디나 암호를 확인하세요. \n 또는 로그인 제한 회원인지 관리자에게 문의하세요.");
			return "common/error";
		}
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

//	로그인, 아웃 관련 메소드들 끝 *********************************************************************************************

//	회원가입 관련 메소드들 모음  *********************************************************************************************

	// 회원가입페이지 이동용 메소드
	@RequestMapping("enrollPage.do")
	public String enrollPage() {
		return "user/enrollPage"; // 내보낼 뷰파일명 리턴
	}

	// 회원가입 처리용
	@RequestMapping(value = "enroll.do", method = RequestMethod.POST)
	public String memberInsertMethod(User user, Model model) {
		// 메소드 매개변수에 vo에 대한 객체를 작성하면,
		// 뷰 form 태그 input의 name 과 vo의 필드명이 같으면
		// 자동 값이 꺼내져서 객체에 옮겨 기록 저장됨.
		// 커맨드 객체(command object)라고 함
		logger.info("enroll.do : " + user);

		// 패스워드 암호화 처리
		user.setUserpwd(bcryptPasswordEncoder.encode(user.getUserpwd()));
		logger.info("after encode : " + user);

		if (userService.insertUser(user) > 0) {
			return "common/main";
		} else {
			model.addAttribute("message", "회원가입 실패!");
			return "common/error";
		}

	}

	// 아이디 중복 체크 ajax 통신 요청 처리용
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

		// response를 이용해서 클라이언트로 출력스트림 만들고 값 보내기
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}

	// 닉네임 중복 체크 ajax 통신 요청 처리용
	@RequestMapping(value = "nickchk.do", method = RequestMethod.POST)
	public void dubNickCheckMethod(@RequestParam("nick") String nick, HttpServletResponse response) throws IOException {
		int nickcount = userService.selectDupCheckNick(nick);

		String returnValue = null;
		if (nickcount == 0) {
			returnValue = "ok";
		} else {
			returnValue = "dup";
		}

		// response를 이용해서 클라이언트로 출력스트림 만들고 값 보내기
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}

//	회원가입 관련 메소드들 모음 끝  *********************************************************************************************

//	내 정보 수정 관련 메소드들 모음 *********************************************************************************************

	// 내 정보보기 페이지 이동 처리용
	@RequestMapping("myinfo.do")
	public ModelAndView myInfoMethod(@RequestParam(value="userid") String userid, ModelAndView mv) {
		User user = userService.selectUser(userid);

		if (user != null) { // 조회가 성공했다면
			mv.addObject("user", user);
			mv.setViewName("user/myInfoPage");
		} else { // 조회가 실패했다면
			mv.addObject("message", userid + " : 회원 정보 조회 실패! ");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 정보 수정하기 처리용
	@RequestMapping(value = "userUpdate.do", method = RequestMethod.POST)
	public String userUpdateMethod(User user, Model model, @RequestParam("origin_userpwd") String originUserpwd) {
		logger.info("userUpdate.do : " + user);
		logger.info("origin_userpwd : " + originUserpwd);

		// 새로운 암호가 전송이 왔다면
		String userpwd = user.getUserpwd().trim(); // 공백제거처리
		if (userpwd != null && userpwd.length() > 0) {
			// 기존 암호와 다른 값이면
			if (!bcryptPasswordEncoder.matches(userpwd, originUserpwd)) {
				// 멤버에 새로운 암호를 저장 : 암호화처리
				user.setUserpwd(bcryptPasswordEncoder.encode(userpwd));
			}
		} else {
			// 새로운 암호가 없다면 원래 암호 기록함
			user.setUserpwd(originUserpwd);
		}

		logger.info("after : " + user);

		if (userService.updateUser(user) > 0) {
			// 수정이 성공했다면, 컨트롤러의 메소드를 직접 호출(실행)할 수도 있음
			// 내 정보보기 페이지에 수정된 회원정보를 다시 조회해 와서 출력되게 처리
			return "redirect: myinfo.do?userid=" + user.getUserid();
		} else {
			model.addAttribute("message", "회원정보 수정 실패!");
			return "common/error.jsp";
		}

	}

	// 회원 탈퇴 처리 (삭제처리)
	@RequestMapping("udel.do")
	public String memberDeleteMethod(@RequestParam("userid") String userid, Model model) {
		if (userService.deleteUser(userid) > 0) {
			return "redirect:logout.do";
		} else {
			model.addAttribute("message", userid + "회원 정보 삭제 실패!");
			return "common/error";
		}
	}

//	내 정보 수정 관련 메소드들 모음 끝 *********************************************************************************************

//	네이버 로그인 연동 관련 메소드들 모음   *********************************************************************************************
	
	@RequestMapping(value="naverLogin.do", method= RequestMethod.GET) 
	public String index() { 
		logger.info("naverLogin.do : "); 
		return "user/naverLoginPage"; 
	}
	
	@RequestMapping(value="naverCallBack.do", method=RequestMethod.GET) 
	public String loginPOSTNaver(HttpSession session) { 
		logger.info("naverCallBack.do : "); 
		return "user/naverLoginCallBack"; 
	}
	
	
	
//	네이버 로그인 연동 관련 메소드들 모음 끝  *********************************************************************************************
	
	
	
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
