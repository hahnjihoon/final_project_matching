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
	// �� Ŭ������ �޼ҵ� �ȿ��� �α� ����� ���ϸ� �αװ�ü ����
	// src/main/resources/log4j.xml �� ������ �������� ��µ�
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// ��й�ȣ ��ȣȭó��
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

// �α���, �ƿ� ���� �޼ҵ�� ���� *********************************************************************************************	

	// �α��������� �̵�ó����޼ҵ� --------------------------------------------------
	@RequestMapping("loginPage.do")
	public String moveLoginPage() {
		return "user/loginPage";
	}

	// �α���ó����޼ҵ�
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(User user, HttpSession session, SessionStatus status, Model model) {
		logger.info("login.do" + user);

		// ��ȣȭ ó���� �н����� ��ġ ��ȸ�� select �ؿ� ������ ���ؾ� ��
		// ȸ�� ���̵�� ȸ�� ������ ���� ��ȸ��
		User loginUser = userService.selectUser(user.getUserid());

		// ��ȣȭ�� �н������ ���޵� �н����尡 ��ġ�ϴ��� Ȯ�� : matches(�Ϲݱ��� �н�����, ��ȣȭ�� �н�����)
		if (loginUser != null && bcryptPasswordEncoder.matches(user.getUserpwd(), loginUser.getUserpwd())
				&& loginUser.getLogin_ok().equals("Y")) {
			// ���� ��ü ���� > ���� �ȿ� ȸ������ ����
			session.setAttribute("loginUser", loginUser);
			status.setComplete(); // �α��� ��û ����, 200 ���۵�
			return "common/main";
		} else {
			model.addAttribute("message", "�α��� ����! ���̵� ��ȣ�� Ȯ���ϼ���. \n �Ǵ� �α��� ���� ȸ������ �����ڿ��� �����ϼ���.");
			return "common/error";
		}
	}

	// �α׾ƿ� ó����
	@RequestMapping("logout.do")
	public String logoutMethod(HttpServletRequest request, Model model) {
		// �α����� �� ������ ���ǰ�ü�� ����
		HttpSession session = request.getSession(false);
		// ���� ��ü�� ������, ���Ϲ���
		// ���� ��ü�� ������, null ���ϵ�
		if (session != null) {
			session.invalidate(); // �ش� ���ǰ�ü ����
			return "common/main";
		} else {
			model.addAttribute("message", "�α��� ������ �������� �ʽ��ϴ�.");
			return "common/error";
		}
	}

//	�α���, �ƿ� ���� �޼ҵ�� �� *********************************************************************************************

//	ȸ������ ���� �޼ҵ�� ����  *********************************************************************************************

	// ȸ������������ �̵��� �޼ҵ�
	@RequestMapping("enrollPage.do")
	public String enrollPage() {
		return "user/enrollPage"; // ������ �����ϸ� ����
	}

	// ȸ������ ó����
	@RequestMapping(value = "enroll.do", method = RequestMethod.POST)
	public String memberInsertMethod(User user, Model model) {
		// �޼ҵ� �Ű������� vo�� ���� ��ü�� �ۼ��ϸ�,
		// �� form �±� input�� name �� vo�� �ʵ���� ������
		// �ڵ� ���� �������� ��ü�� �Ű� ��� �����.
		// Ŀ�ǵ� ��ü(command object)��� ��
		logger.info("enroll.do : " + user);

		// �н����� ��ȣȭ ó��
		user.setUserpwd(bcryptPasswordEncoder.encode(user.getUserpwd()));
		logger.info("after encode : " + user);

		if (userService.insertUser(user) > 0) {
			return "common/main";
		} else {
			model.addAttribute("message", "ȸ������ ����!");
			return "common/error";
		}

	}

	// ���̵� �ߺ� üũ ajax ��� ��û ó����
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

		// response�� �̿��ؼ� Ŭ���̾�Ʈ�� ��½�Ʈ�� ����� �� ������
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}

	// �г��� �ߺ� üũ ajax ��� ��û ó����
	@RequestMapping(value = "nickchk.do", method = RequestMethod.POST)
	public void dubNickCheckMethod(@RequestParam("nick") String nick, HttpServletResponse response) throws IOException {
		int nickcount = userService.selectDupCheckNick(nick);

		String returnValue = null;
		if (nickcount == 0) {
			returnValue = "ok";
		} else {
			returnValue = "dup";
		}

		// response�� �̿��ؼ� Ŭ���̾�Ʈ�� ��½�Ʈ�� ����� �� ������
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}

//	ȸ������ ���� �޼ҵ�� ���� ��  *********************************************************************************************

//	�� ���� ���� ���� �޼ҵ�� ���� *********************************************************************************************

	// �� �������� ������ �̵� ó����
	@RequestMapping("myinfo.do")
	public ModelAndView myInfoMethod(@RequestParam(value="userid") String userid, ModelAndView mv) {
		User user = userService.selectUser(userid);

		if (user != null) { // ��ȸ�� �����ߴٸ�
			mv.addObject("user", user);
			mv.setViewName("user/myInfoPage");
		} else { // ��ȸ�� �����ߴٸ�
			mv.addObject("message", userid + " : ȸ�� ���� ��ȸ ����! ");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// ���� �����ϱ� ó����
	@RequestMapping(value = "userUpdate.do", method = RequestMethod.POST)
	public String userUpdateMethod(User user, Model model, @RequestParam("origin_userpwd") String originUserpwd) {
		logger.info("userUpdate.do : " + user);
		logger.info("origin_userpwd : " + originUserpwd);

		// ���ο� ��ȣ�� ������ �Դٸ�
		String userpwd = user.getUserpwd().trim(); // ��������ó��
		if (userpwd != null && userpwd.length() > 0) {
			// ���� ��ȣ�� �ٸ� ���̸�
			if (!bcryptPasswordEncoder.matches(userpwd, originUserpwd)) {
				// ����� ���ο� ��ȣ�� ���� : ��ȣȭó��
				user.setUserpwd(bcryptPasswordEncoder.encode(userpwd));
			}
		} else {
			// ���ο� ��ȣ�� ���ٸ� ���� ��ȣ �����
			user.setUserpwd(originUserpwd);
		}

		logger.info("after : " + user);

		if (userService.updateUser(user) > 0) {
			// ������ �����ߴٸ�, ��Ʈ�ѷ��� �޼ҵ带 ���� ȣ��(����)�� ���� ����
			// �� �������� �������� ������ ȸ�������� �ٽ� ��ȸ�� �ͼ� ��µǰ� ó��
			return "redirect: myinfo.do?userid=" + user.getUserid();
		} else {
			model.addAttribute("message", "ȸ������ ���� ����!");
			return "common/error.jsp";
		}

	}

	// ȸ�� Ż�� ó�� (����ó��)
	@RequestMapping("udel.do")
	public String memberDeleteMethod(@RequestParam("userid") String userid, Model model) {
		if (userService.deleteUser(userid) > 0) {
			return "redirect:logout.do";
		} else {
			model.addAttribute("message", userid + "ȸ�� ���� ���� ����!");
			return "common/error";
		}
	}

//	�� ���� ���� ���� �޼ҵ�� ���� �� *********************************************************************************************

//	���̹� �α��� ���� ���� �޼ҵ�� ����   *********************************************************************************************
	
	@RequestMapping(value="naverLogin.do", method= RequestMethod.GET) 
	public String index(HttpServletRequest request) { 
		logger.info("naverLogin.do : "); 
		HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        String userid = loginUser.getUserid();
		
		
		
		return "user/naverLoginPage"; 
	}
	
	@RequestMapping(value="naverCallBack.do", method=RequestMethod.GET) 
	public String loginPOSTNaver(HttpServletRequest request) { 
		logger.info("naverCallBack.do : ");

		HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        String userid = loginUser.getUserid();
		return "user/naverLoginCallBack"; 
	}
	
	
	
//	���̹� �α��� ���� ���� �޼ҵ�� ���� ��  *********************************************************************************************
	
	
	
	// IDã�� ������ �̵��� �޼ҵ�
	@RequestMapping("findId.do")
	public String findIdPage() {
		return "user/findIdPage"; // ������ �����ϸ� ����
	}

	// ��й�ȣã�� �̵��� �޼ҵ�
	@RequestMapping("findPwd.do")
	public String findPwdPage() {
		return "user/findPwdPage"; // ������ �����ϸ� ����
	}

}
