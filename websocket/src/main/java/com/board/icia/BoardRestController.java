package com.board.icia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.service.BoardManagement;

@Controller//("RestController")
@RequestMapping(value="/rest")
public class BoardRestController {
	@Autowired
	private BoardManagement bm;  //DI 
	
	private ModelAndView mav;
	
	@RequestMapping(value = "/replyInsert")
	public @ResponseBody String replyInsert() {
		System.out.println("rest/replyInsert");
		String jsonStr=bm.executeAjax(3); 
		return jsonStr; //'[Object, Object]'
	}
	/*@RequestMapping(value = "/replyInsert")
	public @ResponseBody Map<String, List<Reply>> replyInsert() {
		logger.info("replyInsert 로 요청 들어옴");	
		Map<String, List<Reply>> rMap=bm.executeAjax(3); 
		return rMap; //{'rlist', rlist}
	}*/
}
