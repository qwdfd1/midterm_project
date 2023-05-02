package com.camp.s1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView Error404Exception() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "요청하신 페이지가 존재하지 않습니다.");
		mv.setViewName("common/exception");
		return mv;
	}
	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView Error500Exception() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("message", "잘못된 접근입니다.");
//		mv.setViewName("common/exception");
//		return mv;
//	}
	
}
