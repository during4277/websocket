package com.board.icia.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.bean.Board;
import com.board.icia.bean.Member;
import com.board.icia.bean.Reply;
import com.board.icia.dao.BoardDao;
import com.board.icia.dao.MemberDao;
import com.board.icia.userClass.Paging;
import com.board.icia.userClass.UploadFile;
import com.google.gson.Gson;
@Service
public class BoardManagement {
	@Autowired
	private BoardDao bDao;
	@Autowired
	private HttpSession session;  //스프링-인터셉터
	@Autowired
	private HttpServletRequest req;
	
	private ModelAndView mav;
	
	private String jsonStr;
	private Map<String, List<Reply>> jsonObj;
	public ModelAndView execute(int cmd){
		switch(cmd){
		case 1:
			getBoardList();	break;
		case 2:
			getContents();	break;	
		}
		return mav;
	}
	public String executeAjax(int cmd){
		switch(cmd){
		case 3:
			getReplyInsert();	break;	
		}
		return jsonStr;
	}
	/*public Map<String, List<Reply>> executeAjax(int cmd){
		switch(cmd){
		case 3:
			getReplyInsert();	break;	
		}
		return jsonObj;
	}*/
	private void getReplyInsert() {
		String view=null;
		mav=new ModelAndView();
		if(session.getAttribute("id")!=null){
			Reply reply=new Reply();
			int bnum=Integer.parseInt(req.getParameter("bnum"));
			reply.setRnum(getReplyMaxNum()+1);
			reply.setBnum(bnum);
			reply.setRid(session.getAttribute("id").toString());
			reply.setRcontents(req.getParameter("comment"));
			if(bDao.replyInsert(reply)!=0){ //답글추가 성공시
				List<Reply> rlist=getReplyList(bnum);
				Gson jsonObj=new Gson();
				jsonStr=jsonObj.toJson(rlist);
				System.out.println(jsonStr);
				//jsonObj=new HashMap<String,List<Reply>>();
				//jsonObj.put("rlist",rlist);
				//System.out.println(jsonObj);
				//mav.addObject("rlist",getReplyList(bnum));
				//view="replyListAjax";
			}
		}else{
			view="home";
		}
		mav.setViewName(view);
 	}	
	private int getReplyMaxNum(){
		return bDao.getReplyMaxNum();
	}
	private List<Reply> getReplyList(int bnum){
		return bDao.getReplyList(bnum);
	}
	private void getContents() {
	
		String view=null;
		mav=new ModelAndView();
		int bnum=Integer.parseInt(req.getParameter("bnum"));
		if(session!=null && session.getAttribute("id")!=null){
			mav.addObject("board", bDao.getContents(bnum));
			mav.addObject("rlist", bDao.getReplyList(bnum));
			
			view="boardcontentsAjax";
		}else{
			view="errorAjax";
		}
		mav.setViewName(view);
	}

	private void getBoardList() {
		String view=null;
		List<Board> blist=null;
		mav=new ModelAndView();
		if(session!=null && session.getAttribute("id")!=null){
			System.out.println("로그인 성공");
			int pageNum=(req.getParameter("pageNum")!=null)
			?Integer.parseInt(req.getParameter("pageNum"))
			: 1;
			blist=bDao.getBoardList(pageNum);
			mav.addObject("blist",blist);
			mav.addObject("pageNum", pageNum);//현재 페이지 번호
			mav.addObject("paging", getPaging(pageNum));
			view="boardlist";
		}else{
			view="home";
		}
		mav.setViewName(view);
	}
	private String getPaging(int pageNum) {
		int maxNum=bDao.getBoardCount();
		//현재 페이지번호 pageNum
		int listCount=10;  //페이지당 글의 개수
		int pageCount=2;    //그룹당 페이지 갯수
		String boardName="boardlist"; //여러개일때 의미있다.
		Paging paging=new Paging(maxNum,pageNum,listCount,pageCount,boardName);
		String pageStr=paging.makeHtmlPaging();
		return pageStr;
	}
	public ModelAndView execute(MultipartHttpServletRequest multi, int cmd) {
		switch(cmd){
		case 1: boardWrite(multi); break;
		}
		return mav;
	}
	private void boardWrite(MultipartHttpServletRequest multi) {
		System.out.println("service boardWrite");
		String title=multi.getParameter("btitle");
		String contents=multi.getParameter("bcontents");
		String id=session.getAttribute("id").toString();
		int check=Integer.parseInt(multi.getParameter("fileCheck"));
		System.out.println("check="+check);
		//file tag list반환
		Map<String, String> fMap=null;
		if(check==1){
			UploadFile upload=new UploadFile();
			fMap=upload.fileUp(multi); //오리지널파일명, 시스템파일명을 리턴
		}
		//DB저장한다.
		Board board=new Board();
		board.setBtitle(title);
		board.setBcontents(contents);
		board.setBid(id);
		mav=new ModelAndView();
		String view=null;
		String msg=null;
		if(bDao.boardWrite(board, fMap)==1){ //되도록 빈으로 할것..
			view="redirect:boardlist";
			msg="파일쓰기 성공";
		}else{
			view="writeFrm";
			msg="파일쓰기 실패";
		}
		mav.addObject("msg",msg);
		mav.setViewName(view);
	}
	
}
