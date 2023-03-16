package com.camp.s1.board.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.camp.s1.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	
	@GetMapping("list")
	public ModelAndView getBoardList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/board/list");
		
		mv.addObject("list", qnaService.getBoardList(pager));
		
		
		return mv;
		
		
	}
}