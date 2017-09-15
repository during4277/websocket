package com.board.icia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.bean.Member;
import com.board.icia.dao.MemberDao;

@Service  //@Component
public class MemberManagement {
	@Autowired
	private MemberDao mDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private EncryptionService es;


	private ModelAndView  mav;


	public ModelAndView execute(Member mb, int cmd){
		switch(cmd){
		case 1:
			memberAccess(mb);break;
		case 2:
			memberInsert(mb);break;	
		}
		return mav;
	}
	public ModelAndView execute(int cmd){
		switch(cmd){
		case 2:
			logOut();break;

		}
		return mav;
	}
	private void logOut() {
		session.invalidate();
		mav=new ModelAndView();
		mav.setViewName("home");
	}
	private void memberInsert(Member mb) {
		try{
			String view=null;
			mb=es.encodingMember(mb); //회원정보 암호화
			mav=new ModelAndView();

			if(mDao.memberInsert(mb)==1){
				view="home";
				mav.addObject("check",1); //회원가입 성공
			}else{
				view="joinFrm";
			}
			mav.setViewName(view);
		}catch(Exception e){
			System.out.println("암호화 예외 발생");
			e.printStackTrace();
		}
	}
	private void memberAccess(Member mb) {
		try{
			String view=null;
			mav=new ModelAndView();
			//mb.setPw(es.encodingOne(mb.getPw()));
			//mb.setId(es.encodingOne(mb.getId()));
			if(mDao.getLoginResult(mb)==1){
				System.out.println("login 성공");
				mb=mDao.getMemberInfo(mb.getId()); //회원정보
				session.setAttribute("id", mb.getId());
				//mb=es.decodingMember(mb);
				mav.addObject("member", mb);
				//session.setAttribute("mb", mb);
				view="redirect:boardlist";//-->서비스-->Dao-->boarlist.jsp
			}else{
				System.out.println("login 실패");
				mav.addObject("check", 2);
				view="home";
			}
			mav.setViewName(view);
		}catch(Exception e){
			System.out.println("암호화 예외");
		}
	}
}
