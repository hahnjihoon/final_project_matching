package com.test.matching.qa.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
import com.test.matching.qa.model.service.QaService;
import com.test.matching.qa.model.vo.Qa;

@Controller
public class QaController {
	private static final Logger logger = LoggerFactory.getLogger(QaController.class);
	
	@Autowired
	private QaService qaService;
	
	@RequestMapping(value="btop3.do", method=RequestMethod.POST)
	@ResponseBody
	public String boardReadTop3Method(HttpServletResponse response) throws UnsupportedEncodingException {
		//조회수많은 게시원글 3개조회 해오기
		ArrayList<Qa> list = qaService.selectTop3();
		
		//전소용 json객체 준비
		JSONObject sendJson = new JSONObject();
		//list옮길 json배열 준비
		JSONArray jarr = new JSONArray();
		
		//list를 jarr로 옮기기(복사)
		for(Qa qa : list) {
			//notice 필드값 저장용 json객체 만들고
			JSONObject job = new JSONObject();
			
			//json객체저장할때 put, 대부분map구조니까 json이
			job.put("qa_num", qa.getQa_num());
			job.put("qa_title", URLEncoder.encode(qa.getQa_title(), "utf-8"));
			//한글은 인코딩 디코딩해야된다 여기서인코딩 저기받는뷰?에서 디코딩
			job.put("qa_readcount", qa.getQa_readcount());  //스트링으로안바꾸면null로됨 날짜만
			
			jarr.add(job); //배열에저장
		}
		//전송용객체에 jarr을 담음
		sendJson.put("list", jarr);
		
		return sendJson.toJSONString(); //제이슨객체를 제이슨문자열형태로 보냄
		//뷰리졸버에게 리턴됨
		
	}
	
	
	//게시글 페이지단위로 목록조회
	@RequestMapping("blist.do")
	public ModelAndView boardListMethod(@RequestParam(name="page", required=false) String page, 
			ModelAndView mv) {
		int currentPage = 1;
		if(page != null) { //널아니면 =페이지전송왔다면
			currentPage = Integer.parseInt(page);
		}
		
		//페이징처리 - 별도클래스작성해서사용하기도함-------------------------
		int limit = 10; //한페이지에 출력할목록갯수
		//총페이지수계산위해 총목록갯수조회
		int listCount = qaService.selectListCount();
		//페이지수계산
		//주의:목록이11개면페이수는2가됨 나머지목록1개도 페이지가1개필요
		int maxPage = (int)((double)listCount / limit + 0.9);
		//현재페이지가 포함된 페이지그룹의 시작값지정  페이지그룹=?몇까지출력되고이전후이인지 보통5페이지지
		//뷰아래쪽표시할페이지수를 10개씩한경우
		int startPage = (int)((double)currentPage / 10 + 0.9);
		//현재페이지가 포함된 페이지그룹의 끝값
		int endPage = startPage+10-1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		
		//쿼리문에 전달할 현재페이지출력목록의첫행과끝행객체처리
		int startRow = (currentPage -1) * limit + 1;
		int endRow = startRow + limit - 1;
		Paging paging = new Paging(startRow, endRow);
		
		
		// 별도의 클래스 작성끝-------------------------------------------------
		
		
		//서비스메소드실행, 결과받기
		ArrayList<Qa> list = qaService.selectList(paging);
		
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("listCount", listCount);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startPage", startPage);
			mv.addObject("endPage", endPage);
			mv.addObject("limit", limit);
			
			mv.setViewName("qa/boardListView");
		}else {
			mv.addObject("message", currentPage + "페이지 목록조회 실패");
			mv.setViewName("common/error");
		}
		
		return mv;
	}
	
	
	
	//게시원글쓰기페이지로이동
	@RequestMapping("bwform.do")
	public String moveBoardWriteForm() {
		return "qa/boardWriteForm";		
	}
	
	
	
	//게시원글등록처리 : 파일첨부기능포함
	@RequestMapping(value="binsert.do", method=RequestMethod.POST)
	public String boardInsertMethod(Qa qa, HttpServletRequest request, ModelAndView mv) {
		
		//업로드된파일저장할 폴더지정
//		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
//		
//		//첨부파일있을때만 업로드된파일지정폴더로옮기기
//		if(!mfile.isEmpty()) {
//			String fileName=mfile.getOriginalFilename();
//			
//			//이름바꾸기처리 : 년월일시분초.확장자
//			if(fileName != null && fileName.length() > 0) {
//				
//				//바꿀파일명에대한문자열 만들기
//				//공지글 등록요청시점의 날짜정보이용
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//				
//				//변경할파일이름만들기
//				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
//				
//				//확장자붙여
//				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".")+1);
//				
//				//파일객체만들기
//				File originFile = new File(savePath+"\\"+fileName);
//				File renameFile = new File(savePath+"\\"+renameFileName);
//				
//				//업로드 파일저장시키고 바로이름바꾸기실행
//				try {
//					mfile.transferTo(renameFile);
//				} catch (Exception e) {
//					e.printStackTrace();
//					model.addAttribute("message", "전송파일 저장실패");
//					return "common/error";
//				}
//				
//				board.setBoard_original_filename(fileName);
//				board.setBoard_rename_filename(renameFileName);				
//			}
//			
//		} //첨부파일있을때만?
		
		if(qaService.insertOriginBoard(qa)>0) { //새공지등록성공시
			return "redirect:blist.do?page=1";
		}else {
			mv.addObject("message", "새 게시글 등록실패");
			
			return "common/error";
		}
		
		
	} 
	
	
	
	//게시글 상세보기
	@RequestMapping("bdetail.do")
	public ModelAndView boardDetailMethod(ModelAndView mv, @RequestParam("qa_num") int qa_num,
			@RequestParam(name="page", required=false) String page) {
		
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
		}
		
		//조회수1증가처리
		qaService.updateAddReadcount(qa_num);
		
		//해당 게시글 조회
		Qa qa = qaService.selectBoard(qa_num);
		
		if(qa != null) {
			mv.addObject("qa", qa);
			mv.addObject("currentPage", currentPage);
			mv.setViewName("qa/boardDetailView");;
			
			
		}else {
			mv.addObject("message", qa_num+"번 게시글 조회 실패");
			mv.setViewName("common/error");
		}
		
		
		return mv;
	}
	
	
	
	//첨부파일 다운처리
//	@RequestMapping("bfdown.do")
//	public ModelAndView fileDownMethod(HttpServletRequest request,
//			@RequestParam("ofile") String originFileName,
//			@RequestParam("rfile") String renameFileName, ModelAndView mv) {
//		
//		//공지사항 첨부파일 저장 폴더 지정
//		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
//		
//		//저장폴더에서 읽을 파일에대해 경로추가하면서 파일객체생성
//		File renameFile = new File(savePath + "\\" + renameFileName);
//		
//		//다운을위해 내보내는 파일객체 생성(원본파일)
//		File originFile = new File(originFileName);
//		
//		mv.setViewName("filedown"); //동록된 파일다운로드 처리용 뷰클래스
//		mv.addObject("renameFile", renameFile); //전달할 파일객체 저장
//		mv.addObject("originFile", originFile);
//		
//		return mv;
//	}
	
	
	//댓글달기페이지이동
	@RequestMapping("breplyform.do")
	public String moveReplyForm(@RequestParam("qa_num") int origin_num, @RequestParam("page") int currentPage,
			Model model) {
		model.addAttribute("qa_num", origin_num);
		model.addAttribute("currentPage", currentPage);
		
		return "qa/boardReplyForm";
		
	}
	
	
	
	//댓글등록처리
	@RequestMapping(value="breply.do", method=RequestMethod.POST)
	public String replyInsertMethod(Qa reply, @RequestParam("page") int page, Model model) {
		
		//해당댓글에대한 원글조회
		Qa origin = qaService.selectBoard(reply.getQa_ref());
		
		//현재등록글의 레벨을 설정
		reply.setQa_lev(origin.getQa_lev()+1);
		
		//대댓글일때는 board_reply_ref값지정 : 참조하는댓글번호
		if(reply.getQa_lev()==3) {
			reply.setQa_ref(origin.getQa_ref()); //참조원글번호
			reply.setQa_reply_ref(origin.getQa_reply_ref()); //참조댓글번호
			
		}
		
		//댓글과 대댓글은 최근등록을 seq1로 지정
		reply.setQa_reply_seq(1);
		
		//기존댓글또는대댓글 순번seq는 모두1증가처리
		qaService.updateReplySeq(reply);
		
		if(qaService.insertReply(reply)>0) {
			return "redirect:blist.do?pate="+page;
		}else {
			model.addAttribute("message", reply.getQa_ref()+"번 글에 댓글등록실패");
			return "common/error";
		}
		
	}
	
	@RequestMapping("bdel.do")
	public String boardDeleteMethod(Qa qa, HttpServletRequest request, ModelAndView mv) {
		
		if(qaService.deleteBoard(qa) > 0) {
			//글삭제시 첨부파일도삭제
//			if(qa.getBoard_rename_filename() != null) {
//				new File(request.getSession().getServletContext().getRealPath("resources/board_upfiles")
//						+"\\"+board.getBoard_rename_filename()).delete();
//			}
			
			return "redirect:blist.do?page=1";
		}else {
			mv.addObject("message", qa.getQa_num()+ "번 글 삭제실패");
			return "common/error";
		}
	}
	
	@RequestMapping("bupview.do")
	public String moveBoardUpdateView(@RequestParam("qa_num") int qa_num,
			@RequestParam("page") int currentPage, Model model) {
		
		Qa qa = qaService.selectBoard(qa_num);
		if(qa != null) {
			model.addAttribute("qa", qa);
			model.addAttribute("page", currentPage);
			return "qa/boardUpdateView";
		}else {
			model.addAttribute("message", qa_num+"번 글 수정페이지 이동실패");
			return "common/error";
		}
	}
	
	
	//댓글 대댓글 수정 처리
	@RequestMapping(value="boreplyup.do", method=RequestMethod.POST)
	public String replyUpdateMethod(Qa reply, @RequestParam("page") int page, Model model) {
		if(qaService.updateReply(reply)>0) {
			//댓글 대댓글 수정 성공시 다시상세페이지로
			model.addAttribute("qa_num", reply.getQa_num());
			model.addAttribute("page", page);
			return "redirect:bdetail.do";
		}else {
			model.addAttribute("message", reply.getQa_num()+"번 글 수정실패");
			return "common/error";
		}
	}
	
	
	//원글수정처리용
	@RequestMapping(value="boriginup.do", method=RequestMethod.POST)
	public String boardUpdateMethod(Qa qa, HttpServletRequest request, Model model,
			@RequestParam(name="page") int page) {
		//업로드된파일저장폴더 지정하기
//			String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
//			
//			//첨부파일 수정처리
//			//원래첨부파일이 있을때 삭제선택한경우
//			if(board.getBoard_original_filename() != null && delFlag != null && delFlag.equals("yes")) {
//				//저장폴더에서일단지움
//				new File(savePath+"\\"+board.getBoard_rename_filename()).delete();
//				
//				//보드저장정보도 지움
//				board.setBoard_original_filename(null);
//				board.setBoard_rename_filename(null);
//			}
//			
//			//새로운 첨부있을때
//			if(!mfile.isEmpty()) {
//				//저장폴더의 이전파일을 삭제
//				if(board.getBoard_original_filename() != null) {
//					
//					//저장폴더에서일단지움
//					new File(savePath+"\\"+board.getBoard_rename_filename()).delete();
//					
//					//보드저장정보도 지움
//					board.setBoard_original_filename(null);
//					board.setBoard_rename_filename(null);
//					
//				}
//				
//				//이전첨부파일이 없는경우 - - - - - - - - - - - - - - - - - - -
//				String fileName=mfile.getOriginalFilename();
//					
//				//이름바꾸기처리 : 년월일시분초.확장자
//				if(fileName != null && fileName.length() > 0) {
//					
//					//바꿀파일명에대한문자열 만들기
//					//공지글 등록요청시점의 날짜정보이용
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//					
//					//변경할파일이름만들기
//					String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
//					
//					//확장자붙여
//					renameFileName+="."+fileName.substring(fileName.lastIndexOf(".")+1);
//					
//					//파일객체만들기
//					File originFile = new File(savePath+"\\"+fileName);
//					File renameFile = new File(savePath+"\\"+renameFileName);
//					
//					//업로드 파일저장시키고 바로이름바꾸기실행
//					try {
//						mfile.transferTo(renameFile);
//					} catch (Exception e) {
//						e.printStackTrace();
//						model.addAttribute("message", "전송파일 저장실패");
//						return "common/error";
//					}
//					
//					board.setBoard_original_filename(fileName);
//					board.setBoard_rename_filename(renameFileName);				
//				} //이름바꾸기해서 저장처리
//				
//			} //새로첨부된파일있다면
//			
			//---------------------------------------------------------------
			
			//서비스 메소드 실행시키고 결과받아서 성공실패 페이지 내보내기
			if(qaService.updateOrigin(qa) > 0) {
				//원글수정성공시 상세보기페이지 내보내는경우
				model.addAttribute("page",page);
				model.addAttribute("qa_num", qa.getQa_num());
				return "redirect:bdetail.do";
			}else {
				model.addAttribute("message", qa.getQa_num()+" 번 게시원글 수정실패");
				return "common/error";
			}
	}
	
}
