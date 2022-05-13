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
import com.test.matching.notice.model.vo.Notice;
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
		int maxPage = (int) ((double)listCount / limit + 0.9);
		// ���� �������� ���Ե� ������ �׷��� ���۰� ����(�� �Ʒ��ʿ� ǥ���� ������ ���� 5���� �� ���)
		int startPage = (int) ((double)currentPage / 5 + 0.9);
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

	//게시글 상세보기 처리
	   @RequestMapping("cdetail.do")
	   public ModelAndView communityDetailMethod(ModelAndView mv, @RequestParam("com_num") int com_num, @RequestParam(name="page", required=false) String page) {
	      int currentPage = 1;
	      if(page != null) {
	         currentPage = Integer.parseInt(page);
	         
	      }
	      //조회수 1증가 처리
	      CommunityService.updateAddReadcount(com_num);
	      
	      //해당 게시글 조회
	      Community community = CommunityService.selectCommunity(com_num);
	      
	      if(community != null) {
	         mv.addObject("community", community);
	         mv.addObject("currentPage", currentPage);
	         mv.setViewName("community/communityDetailView");
	      }else {
	         mv.addObject("message", com_num + "번 게시글 조회 실패.");
	         mv.setViewName("common/error");
	      }
	      
	      return mv;
	   }
//		
//		int currentPage = 1;
//		if (page != null) {
//			currentPage = Integer.parseInt(page);
//
//		}
//		// ��ȸ�� 1���� ó��
//		CommunityService.updateAddReadcount(com_num);
//		
//
//		if (community != null) {
//			mv.addObject("Community", community);
//			mv.addObject("currentPage", currentPage);
//			mv.setViewName("community/communityDetailView");
//		} else {
//			mv.addObject("message", com_num + "�� �Խñ� ��ȸ ����.");
//			mv.setViewName("common/error");
//		}
//
//		return mv;

	@RequestMapping("cfdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request,
			@RequestParam("ofile") String originFileName,
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
	//수정페이지로 이동
	   @RequestMapping("cupview.do")
	   public String moveCommunityUpdateView(@RequestParam("com_num") int com_num, @RequestParam("page") int currentPage, Model model) {
	      Community community = CommunityService.selectCommunity(com_num);
	      
	      if(community != null) {
	         model.addAttribute("community", community);
	         model.addAttribute("page", currentPage);
	         return "community/communityUpdateForm";
	      }else {
	         model.addAttribute("message", com_num + "번 글 수정페이지로 이동 실패.");
	         return "common/error";
	      }
	   }

	   @RequestMapping(value="coriginup.do", method=RequestMethod.POST)
	   public String communityUpdateMethod(Community community, HttpServletRequest request, Model model,
	         @RequestParam(name="page", required=false) String page,
	         @RequestParam(name="delFlag", required=false) String delFlag, 
	         @RequestParam(name="upfile", required=false) MultipartFile mfile){
	            //업로드된 파일 저장 폴더 지정하기
	            String savePath = request.getSession().getServletContext().getRealPath("resources/community_upfiles");
	            
	            //첨부 파일 수정 처리 ---------------------------
	            //원래 첨부파일이 있는데 삭제를 선택한 경우
	            if(community.getCom_original_file() != null && delFlag != null && delFlag.equals("yes")) {
	               //저장 폴더에서 해당 파일을 삭제함
	               new File(savePath + "\\" + community).delete();
	               //board 의 파일정보도 제거함
	               community.setCom_original_file(null);
	               community.setCom_rename_file(null);
	      
	            }
	            //새로운 첨부파일이 있을 때 
	            if(!mfile.isEmpty()) {
	               //저장 폴더의 이전 파일을 삭제함
	               if(community.getCom_original_file() !=null) {
	                  //저장 폴더에서 해당 파일을 삭제함
	                  new File(savePath + "\\" + community.getCom_rename_file()).delete();
	                  //community 의 파일정보도 제거함
	                  community.setCom_original_file(null);
	                  community.setCom_rename_file(null);
	               }
	               //이전 첨부파일이 없는 경우 --------------------
	               String fileName = mfile.getOriginalFilename();
	               //이름 바꾸기 처리 : 년 월 시 분 초.확장자
	               if(fileName != null && fileName.length() > 0) {
	                  //바꿀 파일명에 대한 문자열 만들기
	                  //공지글 등록 요청시점의 날짜정보를 이용함
	                  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	                  //변경할 파일 이름 만들기
	                  String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
	                  //원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
	                  renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	                  
	                  //파일객체 만들기
	                  File originFile = new File(savePath + "\\" + fileName);
	                  File renameFile = new File(savePath + "\\" + renameFileName);
	                  
	                  //업로드 파일 저장시키고, 바로 이름바꾸기 실행함
	                  try {
	                     mfile.transferTo(renameFile);
	                  } catch (Exception e) {
	                     e.printStackTrace();
	                     model.addAttribute("message", "전송파일 저장 실패");
	                     return "common/error";
	                  }
	                  community.setCom_original_file(fileName);
	                  community.setCom_rename_file(renameFileName);
	               }
	               }
	            
	            // -----------------------------------------
	            //서비스 메소드 실행시키고 결과받아서 성공|실패 페이지 내보내기
	            if(CommunityService.updateOrigin(community) > 0) {
	               //원글 수정 성공시 상세보기 페이지 내보내는 경우
	               model.addAttribute("page", page);
	               model.addAttribute("com_num", community.getCom_num());
	               return "redirect:cdetail.do";
	            }else {
	               model.addAttribute("message", community.getCom_num() + "번 공지 수정 실패");
	               return "commom/error";
	            }
	         }

}
