package com.board.icia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.service.BoardManagement;

@Controller
public class BoardController {
	@Autowired
	private BoardManagement bm;  //DI 
	
	private ModelAndView mav;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/boardlist")
	public ModelAndView boardList() {
		mav= bm.execute(1); 
		return mav;
	}
	@RequestMapping(value = "/contents")
	public ModelAndView contents() {
		System.out.println("contents 진입");
		mav= bm.execute(2); 
		return mav;
	}
	@RequestMapping(value = "/writeFrm")
	public ModelAndView writeFrm() {
		System.out.println("writeFrm 진입");
		mav=new ModelAndView();
		mav.setViewName("writeFrm");
		return mav;
	}
	@RequestMapping(value = "/boardWrite")
	//form태그 enctype설정된 경우 컨트롤러 메소드의 빈에 전달안됨...
	public ModelAndView boardWrite(MultipartHttpServletRequest multi) {
		System.out.println("boardWrite 진입1");
		//System.out.println("bcon="+b.getBcontents());
		//System.out.println("bfile="+b.getBfile());
		mav=bm.execute(multi,1);
		return mav;
	}
}
