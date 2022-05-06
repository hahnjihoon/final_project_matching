package com.test.matching.community.controller;

import java.io.File;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.matching.common.Paging;
import com.test.matching.community.model.service.CommunityService;
import com.test.matching.community.model.vo.Community;
import com.test.matching.user.model.service.UserService;
import com.test.matching.user.model.vo.User;

@Controller
public class CommunityController {
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityService CommunityService;

	// �Խñ� ������������ ��� ��ȸ ó����
	@RequestMapping("clist.do")
	public ModelAndView CommunityListMethod(@RequestParam(name = "page", required = false) String page, ModelAndView mv,
			HttpSession session) {
//		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		String userid = user.getUserid();
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// ����¡ ��� ó�� -- ������ Ŭ������ �ۼ��ؼ� ����ص���
		int limit = 5; // �� �������� ����� ��� ����
		// ������ �� ����� ���� �� ��� ���� ��ȸ
		int listCount = CommunityService.selectListCount();
		// ������ �� ���
		// ���� : ����� 11���̸�, ������ ���� 2�� �� (������ ��� 1���� ����������� 1���� �ʿ���)
		int maxPage = (int) ((double) listCount / limit + 0.9);
		// ���� �������� ���Ե� ������ �׷��� ���۰� ����(�� �Ʒ��ʿ� ǥ���� ������ ���� 5���� �� ���)
		int startPage = (int) ((double) currentPage / 5 + 0.9);
		// ���� �������� ���Ե� ������ �׷��� ����
		int endPage = startPage + 5 - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}

		// �������� ������ ���� �������� ����� ����� ù��� ���� ��ü ó��
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		Paging paging = new Paging(startRow, endRow, userid);

		// ������ Ŭ���� �ۼ� �� -----------------------------------

		// ���� �޼ҵ� �����ϰ� ����ޱ�
		ArrayList<Community> list = CommunityService.selectList(paging);

		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("listCount", listCount);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startPage", startPage);
			mv.addObject("endPage", endPage);
			mv.addObject("limit", limit);

			mv.setViewName("community/communityListView");
		} else {
			mv.addObject("message", currentPage + "������ ��� ��ȸ ����");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// �Խ� ���� ���� �������� �̵�
	@RequestMapping("cwform.do")
	public String moveCommunityWriteForm() {
		return "community/communityWriteForm";
	}

	// �Խÿ��� ��� ó���� : ����÷�α�� ����
	@RequestMapping(value = "cinsert.do", method = RequestMethod.POST)
	public String CommunityInsertMethod(Community community, HttpServletRequest request, Model model,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// ���ε�� ���� ���� ���� ����
		String savePath = request.getSession().getServletContext().getRealPath("resources/community_upfiles");

		// ÷�������� �������� ���ε�� ������ ���� ������ �ű��
		if (!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();
			if (fileName != null && fileName.length() > 0) {
				// �ٲ� ���ϸ� ���� ���ڿ� �����
				// ������ ��� ��û������ ��¥������ �̿���
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// ������ ���� �̸� �����
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				// ���� ������ Ȯ���ڸ� �����ؼ�, ���� ���ϸ� �ٿ���
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

				// ���ϰ�ü �����
				File originFile = new File(savePath + "\\" + fileName);
				File renameFile = new File(savePath + "\\" + renameFileName);

				// ���ε� ���� �����Ű��, �ٷ� �̸��ٲٱ� ������
				try {
					mfile.transferTo(renameFile);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "�������� ���� ����");
					return "common/error";
				}
				community.setCom_original_file(fileName);
				community.setCom_rename_file(renameFileName);
			}
		} // ÷�� ������ ��������

		if (CommunityService.insertOriginCommunity(community) > 0) { // �Խ� ���� ��� ������
			return "redirect:clist.do";
		} else {
			model.addAttribute("message", "�Խ� ���� ��� ����");
			return "common/error";
		}
	}

	// �Խñ� �󼼺��� ó��
	@RequestMapping("cdetail.do")
	public ModelAndView CommunityDetailMethod(ModelAndView mv, @RequestParam("com_num") int com_num,
			@RequestParam(name = "page", required = false) String page) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);

		}
		// ��ȸ�� 1���� ó��
		CommunityService.updateAddReadcount(com_num);

		// �ش� �Խñ� ��ȸ
		Community community = CommunityService.selectCommunity(com_num);

		if (community != null) {
			mv.addObject("Community", community);
			mv.addObject("currentPage", currentPage);
			mv.setViewName("community/communityDetailView");
		} else {
			mv.addObject("message", com_num + "�� �Խñ� ��ȸ ����.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	@RequestMapping("cfdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request, @RequestParam("ofile") String originFileName,
			@RequestParam("rfile") String renameFileName, ModelAndView mv) {
		// �������� ÷������ ���� ���� ��� ����
		String savePath = request.getSession().getServletContext().getRealPath("resources/community_upfiles");
		// ���� �������� ���� ���Ͽ� ���� ��� �߰��ϸ鼭 File ��ü ����
		File renameFile = new File(savePath + "\\" + renameFileName);
		// �ٿ��� ���� �������� ���� ��ü ����
		File originFile = new File(originFileName);

		mv.setViewName("filedown"); // ��ϵ� ���� �ٿ�ε� ó���� �� Ŭ���� id ��
		mv.addObject("renameFile", renameFile); // ������ ���ϰ�ü Model �� ����
		mv.addObject("originFile", originFile);

		return mv;
	}

	// �Խñ� ����
	@RequestMapping("cdel.do")
	public String CommunityDeleteMethod(Community Community, HttpServletRequest request, Model model) {

		if (CommunityService.deleteCommunity(Community) > 0) {

			// �ۻ��� ������ ���������� ÷�����ϵ� ���� ����ó��
			if (Community.getCom_rename_file() != null) {
				new File(request.getSession().getServletContext().getRealPath("resources/Community_upfiles") + "\\"
						+ Community.getCom_rename_file()).delete();
			}

			return "redirect:clist.do?page=1";

		} else {
			model.addAttribute("message", Community.getCom_num() + "�� �� ���� ����");
			return "common/error";
		}
	}

	// ������������ �̵�
	@RequestMapping("cupview.do")
	public String moveCommunityUpdateView(@RequestParam("com_num") int com_num, @RequestParam("page") int currentPage,
			Model model) {
		Community Community = CommunityService.selectCommunity(com_num);
		if (Community != null) {
			model.addAttribute("Community", Community);
			model.addAttribute("page", currentPage);
			return "Community/communityUpdateView";
		} else {
			model.addAttribute("message", com_num + "�� �� ������������ �̵� ����.");
			return "common/error";
		}
	}


	@RequestMapping(value = "coriginup.do", method = RequestMethod.POST)
	public String CommunityUpdateMethod(Community community, HttpServletRequest request, Model model,
			@RequestParam("page") int page, @RequestParam(name = "delFlag", required = false) String delFlag,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// ���ε�� ���� ���� ���� �����ϱ�
		String savePath = request.getSession().getServletContext().getRealPath("resources/community_upfiles");

		// ÷�� ���� ���� ó�� ---------------------------
		// ���� ÷�������� �ִµ� ������ ������ ���
		if (community.getCom_original_file() != null && delFlag != null && delFlag.equals("yes")) {
			// ���� �������� �ش� ������ ������
			new File(savePath + "\\" + community.getCom_rename_file()).delete();
			// Community �� ���������� ������
			community.setCom_original_file(null);
			community.setCom_rename_file(null);

		}
		// ���ο� ÷�����Ϥ� ������ ��
		if (!mfile.isEmpty()) {
			// ���� ������ ���� ������ ������
			if (community.getCom_original_file() != null) {
				// ���� �������� �ش� ������ ������
				new File(savePath + "\\" + community.getCom_rename_file()).delete();
				// Community �� ���������� ������
				community.setCom_original_file(null);
				community.setCom_rename_file(null);
			}
			// ���� ÷�������� ���� ��� --------------------
			String fileName = mfile.getOriginalFilename();
			// �̸� �ٲٱ� ó�� : �� �� �� �� ��.Ȯ����
			if (fileName != null && fileName.length() > 0) {
				// �ٲ� ���ϸ� ���� ���ڿ� �����
				// ������ ��� ��û������ ��¥������ �̿���
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// ������ ���� �̸� �����
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				// ���� ������ Ȯ���ڸ� �����ؼ�, ���� ���ϸ� �ٿ���
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

				// ���ϰ�ü �����
				File originFile = new File(savePath + "\\" + fileName);
				File renameFile = new File(savePath + "\\" + renameFileName);

				// ���ε� ���� �����Ű��, �ٷ� �̸��ٲٱ� ������
				try {
					mfile.transferTo(renameFile);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "�������� ���� ����");
					return "common/error";
				}
				community.setCom_original_file(fileName);
				community.setCom_rename_file(renameFileName);
			}
		}

		// -----------------------------------------
		// ���� �޼ҵ� �����Ű�� ����޾Ƽ� ����|���� ������ ��������
		if (CommunityService.updateOrigin(community) > 0) {
			// ���� ���� ������ �󼼺��� ������ �������� ���
			model.addAttribute("page", page);
			model.addAttribute("com_num", community.getCom_num());
			return "redirect:cdetail.do";
		} else {
			model.addAttribute("message", community.getCom_num() + "�� ���� ���� ����");
			return "commom/error";
		}
	}

}
