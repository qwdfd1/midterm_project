package com.camp.s1.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.camp.s1.board.BbsDTO;
import com.camp.s1.board.BoardDTO;
import com.camp.s1.board.BoardFileDTO;
import com.camp.s1.board.BoardService;
import com.camp.s1.util.FileManager;
import com.camp.s1.util.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public List<BbsDTO> getBoardList(Pager pager) throws Exception {
			
			Long totalCount = noticeDAO.getTotalCount(pager);
			
			
			
			pager.makeNum(totalCount);
			
			
			pager.makeRow();
			
			
		
				
		return noticeDAO.getBoardList(pager);		
	}

	@Override
	public int setBoardAdd(BbsDTO bbsDTO, MultipartFile[] files, HttpSession session) throws Exception {
		int result =  noticeDAO.setBoardAdd(bbsDTO);
		
		//file HDD에 저장
		String realPath = session.getServletContext().getRealPath("resources/upload/notice");
		
		System.out.println(realPath);
		
		for (MultipartFile multipartFile : files) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(multipartFile, realPath);
			
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setNum(null)
			
			noticeDAO.setBoardFileAdd(null);
		}
		
		
		return result;
	}

	@Override
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBoardDelete(BbsDTO bbsDTO, HttpSession session) throws Exception {
		return noticeDAO.setBoardDelete(bbsDTO);
	}

	@Override
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception {
		return noticeDAO.getBoardDetail(boardDTO);
	}

	@Override
	public BoardFileDTO getBoardFileDetail(BoardFileDTO boardFileDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setBoardUpdate(BbsDTO bbsDTO, MultipartFile[] multipartFiles, HttpSession session, Long[] fileNum)
			throws Exception {
		return noticeDAO.setBoardUpdate(bbsDTO);
	}
	
	
	
	
}
