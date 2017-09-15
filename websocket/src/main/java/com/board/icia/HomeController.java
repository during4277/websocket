package com.board.icia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.bean.Member;
import com.board.icia.service.MemberManagement;

@Controller
@SessionAttributes("member")
//member로 모델객체를 생성하면 request영역은 물론
//session영역에 저장한다.
public class HomeController {
	@Autowired
	private MemberManagement mm;  //DI 
	
	private ModelAndView mav;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav=new ModelAndView();
		//mav.addObject("test", "test");
		mav.setViewName("home"); //로그인 페이지 home
		return mav;
	}
	@RequestMapping(value = "/chfrm", method = RequestMethod.GET)
	public ModelAndView ch() {
		System.out.println("chfrm");
		ModelAndView mav=new ModelAndView();
		//mav.addObject("test", "test");
		mav.setViewName("chatService"); //로그인 페이지 home
		return mav;
	}
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb) {//@ModelAttribute
		return mm.execute(mb, 1);
	}
	@RequestMapping(value = "/logout")
	public ModelAndView logOut() {//@ModelAttribute
		return mm.execute(2);
	}
	@RequestMapping(value = "/joinFrm")
	public ModelAndView joinFrm() {
		mav=new ModelAndView();
		mav.setViewName("joinFrm");//.jsp
		return mav;
	}
	@RequestMapping(value = "/memberInsert")
	public ModelAndView memberInsert(Member mb) {
		mav= mm.execute(mb, 2);
		return mav;
	}
	@RequestMapping(value = "/chatroom")
	public ModelAndView chatroomList(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("chatroomList");
		return mav;
	}
}
