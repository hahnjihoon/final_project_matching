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

	// �������� �̵�ó����޼ҵ� --------------------------------------------------
	@RequestMapping("loginPage.do")
	public String moveLoginPage() {
		return "user/loginPage";
	}

	// �α���ó����޼ҵ�
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String loginMethod(HttpServletRequest request, HttpServletResponse response, Model model) {

		// ���񽺷� ������ �����ü����
		User user = new User();
		user.setUserid(request.getParameter("userid"));
		user.setUserpwd(request.getParameter("userpwd"));

		User loginMember = userService.selectLogin(user);
		String viewName = null;
		if (loginMember != null) {
			// �α��λ��°������ : ���°��� ��Ŀ���� �̶����
			// ���� ��Ű url���ۼ� 3�����������
			HttpSession session = request.getSession();
			// getSession() == getSession(true)
			// ���ǰ�ü�� ������ �ڵ����� ������Ŵ
			// ���ǰ�ü�� ������ �ش缼���� ������ ���Ϲ���
			session.setAttribute("loginMember", loginMember);
			viewName = "common/main2";
		} else { // �α��� ���н�
			model.addAttribute("message", "�α��� ���� : ���̵� ��� Ȯ���Ͻÿ�");
			viewName = "common/error";
		}

		return viewName;
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

	// ȸ������������ �̵��� �޼ҵ�
	@RequestMapping("enroll.do")
	public String enrollPage() {
		return "user/enrollPage"; // ������ �����ϸ� ����
	}

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
