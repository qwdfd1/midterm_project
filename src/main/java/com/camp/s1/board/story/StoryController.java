package com.camp.s1.board.story;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.camp.s1.board.BbsDTO;
import com.camp.s1.camping.CampDTO;
import com.camp.s1.util.Pager;


@Controller
@RequestMapping("/story/*")
public class StoryController {

	@Autowired
	private StoryService storyService;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		return "story";
	}
	
	@ModelAttribute("boardId")
	public Long getBoardId() {
		return 3L;
	}
	
	@GetMapping("list")
	public ModelAndView getListPage(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
		
		mv.setViewName("/story/list");
		mv.addObject("list", storyService.getBoardList(pager));
		
		
		return mv;
		
	}
	
	@GetMapping("listPage")
	public ModelAndView getBoardList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("common/storyList");
		mv.addObject("list", storyService.getBoardList(pager));
		
		
		
		return mv;
		
	}
	
	@GetMapping("detail")
	public ModelAndView getBoardDetail(StoryDTO storyDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/story/detail");
		mv.addObject("dto", storyService.getBoardDetail(storyDTO));
		
		return mv;
	}
	
	@GetMapping("add")
	public ModelAndView setBoardAdd() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/story/add");
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView setBoardAdd(StoryDTO storyDTO, MultipartFile [] files, HttpSession session) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		int result = storyService.setBoardAdd(storyDTO, files, session);
		
		String msg = "등록 실패";
		
		if(result > 0) {
			msg = "글이 등록되었습니다";
		}
		
		mv.setViewName("common/result");
		mv.addObject("result", msg);
		mv.addObject("url", "./list");
		
	
					
		return mv;
		
	}
	
	@PostMapping("delete")
	public ModelAndView setBoardDelete(StoryDTO storyDTO, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("common/ajaxResult");
		mv.addObject("result", storyService.setBoardDelete(storyDTO, session));
		
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setBoardUpdate(StoryDTO storyDTO) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/story/update");
		mv.addObject("dto", storyService.getBoardDetail(storyDTO));
		
		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView setBoardUpdate(StoryDTO storyDTO, MultipartFile [] files, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = storyService.setBoardUpdate(storyDTO, files, session);
		
		String msg = "수정 실패";
		if(result > 0) {
			msg = "수정되었습니다";
		}
		
		mv.setViewName("common/result");
		mv.addObject("result", msg);
		mv.addObject("url", "./list");
		
		return mv;
	}
	
	@PostMapping("boardFileDelete")
	public ModelAndView setBoardFileDelete(Long fileNum, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("common/ajaxResult");
		mv.addObject("result", storyService.setBoardFileDelete(fileNum, session));
		
		
		return mv;
	}
	
	@GetMapping("storyfeed")
	public ModelAndView getStoryFileList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		pager.setPerPage(3L);
		List<BbsDTO> ar = storyService.getBoardList(pager);
		mv.addObject("list", ar);
		mv.setViewName("common/storyfeed");
		return mv;
	}
	
	@GetMapping("storyListTop")
	public ModelAndView getStoryTopResult(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		pager.setPerPage(4L);
		
		mv.addObject("list", storyService.getBoardList(pager));
		mv.setViewName("common/storyTopResult");
		return mv;
	}
	
	
}
