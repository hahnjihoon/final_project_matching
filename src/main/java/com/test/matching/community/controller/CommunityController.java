package com.test.matching.community.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.matching.common.Paging;
import com.test.matching.community.model.service.CommunityService;
import com.test.matching.community.model.vo.Community;
import com.test.matching.user.model.vo.User;

public class CommunityController {
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityService CommunityService;

	// 게시글 페이지단위로 목록 조회 처리용
	@RequestMapping("clist.do")
	public ModelAndView CommunityListMethod(@RequestParam(name = "page", required = false) String page, ModelAndView mv,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginMember = (User) session.getAttribute("loginMember");
		String userid = loginMember.getUserid();
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 페이징 계산 처리 -- 별도의 클래스로 작성해서 사용해도됨
		int limit = 5; // 한 페이지에 출력할 목록 갯수
		// 페이지 수 계산을 위해 총 목록 갯수 조회
		int listCount = CommunityService.selectListCount();
		// 페이지 수 계산
		// 주의 : 목록이 11개이면, 페이지 수는 2가 됨 (나머지 목록 1개도 출력페이지가 1개가 필요함)
		int maxPage = (int) ((double) listCount / limit + 0.9);
		// 현재 페이지가 포함된 페이지 그룹의 시작값 지정(뷰 아래쪽에 표시할 페이지 수를 5개씩 한 경우)
		int startPage = (int) ((double) currentPage / 5 + 0.9);
		// 현재 페이지가 포함된 페이지 그룹의 끝값
		int endPage = startPage + 5 - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}

		// 쿼리문에 전달할 현재 페이지에 출력할 목록의 첫행과 끝행 객체 처리
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		Paging paging = new Paging(startRow, endRow, userid);

		// 별도의 클래스 작성 끝 -----------------------------------

		// 서비스 메소드 실행하고 결과받기
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
			mv.addObject("message", currentPage + "페이지 목록 조회 실패");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 게시 원글 쓰기 페이지로 이동
	@RequestMapping("cwform.do")
	public String moveCommunityWriteForm() {
		return "community/communityWriteForm";
	}

	// 게시원글 등록 처리용 : 파일첨부기능 있음
	@RequestMapping(value = "cinsert.do", method = RequestMethod.POST)
	public String CommunityInsertMethod(Community Community, HttpServletRequest request, Model model,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// 업로드된 파일 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/community_upfiles");

		// 첨부파일이 있을때만 업로드된 파일을 지정 폴더로 옮기기
		if (!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 만들기
				// 공지글 등록 요청시점의 날짜정보를 이용함
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// 변경할 파일 이름 만들기
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				// 원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

				// 파일객체 만들기
				File originFile = new File(savePath + "\\" + fileName);
				File renameFile = new File(savePath + "\\" + renameFileName);

				// 업로드 파일 저장시키고, 바로 이름바꾸기 실행함
				try {
					mfile.transferTo(renameFile);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "전송파일 저장 실패");
					return "common/error";
				}
				Community.setCom_original_file(fileName);
				Community.setCom_rename_file(renameFileName);
			}
		} // 첨부 파일이 있을때만

		if (CommunityService.insertOriginCommunity(Community) > 0) { // 게시 원글 등록 성공시
			return "redirect:blist.do?page=1";
		} else {
			model.addAttribute("message", "게시 원글 등록 실패");
			return "common/error";
		}
	}

	// 게시글 상세보기 처리
	@RequestMapping("cdetail.do")
	public ModelAndView CommunityDetailMethod(ModelAndView mv, @RequestParam("com_num") int com_num,
			@RequestParam(name = "page", required = false) String page) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);

		}
		// 조회수 1증가 처리
		CommunityService.updateAddReadcount(com_num);

		// 해당 게시글 조회
		Community community = CommunityService.selectCommunity(com_num);

		if (community != null) {
			mv.addObject("Community", community);
			mv.addObject("currentPage", currentPage);
			mv.setViewName("Community/CommunityDetailView");
		} else {
			mv.addObject("message", com_num + "번 게시글 조회 실패.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	@RequestMapping("cfdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request, @RequestParam("ofile") String originFileName,
			@RequestParam("rfile") String renameFileName, ModelAndView mv) {
		// 공지사항 첨부파일 저장 폴더 경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/community_upfiles");
		// 저장 폴더에서 읽을 파일에 대해 경로 추가하면서 File 객체 생성
		File renameFile = new File(savePath + "\\" + renameFileName);
		// 다운을 위해 내보내는 파일 객체 생성
		File originFile = new File(originFileName);

		mv.setViewName("filedown"); // 등록된 파일 다운로드 처리용 뷰 클래서 id 명
		mv.addObject("renameFile", renameFile); // 전달할 파일객체 Model 에 저장
		mv.addObject("originFile", originFile);

		return mv;
	}

	// 게시글 삭제
	@RequestMapping("cdel.do")
	public String CommunityDeleteMethod(Community Community, HttpServletRequest request, Model model) {

		if (CommunityService.deleteCommunity(Community) > 0) {

			// 글삭제 성공시 저장폴더에 첨부파일도 같이 삭제처리
			if (Community.getCom_rename_file() != null) {
				new File(request.getSession().getServletContext().getRealPath("resources/Community_upfiles") + "\\"
						+ Community.getCom_rename_file()).delete();
			}

			return "redirect:clist.do?page=1";

		} else {
			model.addAttribute("message", Community.getCom_num() + "번 글 삭제 실패");
			return "common/error";
		}
	}

	// 수정페이지로 이동
	@RequestMapping("cupview.do")
	public String moveCommunityUpdateView(@RequestParam("com_num") int com_num, @RequestParam("page") int currentPage,
			Model model) {
		Community Community = CommunityService.selectCommunity(com_num);
		if (Community != null) {
			model.addAttribute("Community", Community);
			model.addAttribute("page", currentPage);
			return "Community/communityUpdateView";
		} else {
			model.addAttribute("message", com_num + "번 글 수정페이지로 이동 실패.");
			return "common/error";
		}
	}


	@RequestMapping(value = "coriginup.do", method = RequestMethod.POST)
	public String CommunityUpdateMethod(Community community, HttpServletRequest request, Model model,
			@RequestParam("page") int page, @RequestParam(name = "delFlag", required = false) String delFlag,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile) {
		// 업로드된 파일 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("resources/community_upfiles");

		// 첨부 파일 수정 처리 ---------------------------
		// 원래 첨부파일이 있는데 삭제를 선택한 경우
		if (community.getCom_original_file() != null && delFlag != null && delFlag.equals("yes")) {
			// 저장 폴더에서 해당 파일을 삭제함
			new File(savePath + "\\" + community.getCom_rename_file()).delete();
			// Community 의 파일정보도 제거함
			community.setCom_original_file(null);
			community.setCom_rename_file(null);

		}
		// 새로운 첨부파일ㅇ ㅣ있을 때
		if (!mfile.isEmpty()) {
			// 저장 폴더의 이전 파일을 삭제함
			if (community.getCom_original_file() != null) {
				// 저장 폴더에서 해당 파일을 삭제함
				new File(savePath + "\\" + community.getCom_rename_file()).delete();
				// Community 의 파일정보도 제거함
				community.setCom_original_file(null);
				community.setCom_rename_file(null);
			}
			// 이전 첨부파일이 없는 경우 --------------------
			String fileName = mfile.getOriginalFilename();
			// 이름 바꾸기 처리 : 년 월 시 분 초.확장자
			if (fileName != null && fileName.length() > 0) {
				// 바꿀 파일명에 대한 문자열 만들기
				// 공지글 등록 요청시점의 날짜정보를 이용함
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// 변경할 파일 이름 만들기
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				// 원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

				// 파일객체 만들기
				File originFile = new File(savePath + "\\" + fileName);
				File renameFile = new File(savePath + "\\" + renameFileName);

				// 업로드 파일 저장시키고, 바로 이름바꾸기 실행함
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
		// 서비스 메소드 실행시키고 결과받아서 성공|실패 페이지 내보내기
		if (CommunityService.updateOrigin(community) > 0) {
			// 원글 수정 성공시 상세보기 페이지 내보내는 경우
			model.addAttribute("page", page);
			model.addAttribute("com_num", community.getCom_num());
			return "redirect:cdetail.do";
		} else {
			model.addAttribute("message", community.getCom_num() + "번 공지 수정 실패");
			return "commom/error";
		}
	}

}
