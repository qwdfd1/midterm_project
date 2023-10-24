package com.camp.s1.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.s1.board.BbsDTO;
import com.camp.s1.board.BoardDTO;
import com.camp.s1.board.BoardTypeDTO;
import com.camp.s1.board.CommentDTO;
import com.camp.s1.util.Pager;

@Service
public class ReportService {
	
	@Autowired
	private ReportDAO reportDAO;
	
	public int setReportAdd(ReportDTO reportDTO) throws Exception {
		
		// 중복신고 방지 (result가 2면 중복신고)
		int result = 0;
		
		// 게시글 신고시
		if(reportDTO.getCommentNum() == null) {
			List<ReportDTO> ar = reportDAO.getReporterList(reportDTO);
			for (ReportDTO reportDTO2 : ar) {
				if(reportDTO2.getReporter().equals(reportDTO.getReporter())) {
					result = 2;
					return result;
				}
				
			}
			return reportDAO.setReportAdd(reportDTO);
		} 
		
		// 댓글 신고시
		else {
			List<ReportDTO> ar = reportDAO.getReporterList(reportDTO);
			for (ReportDTO reportDTO2 : ar) {
				if(reportDTO2.getReporter().equals(reportDTO.getReporter())) {
					result = 2;
					return result;
				}
				
			}
			return reportDAO.setCommentReportAdd(reportDTO);
		}
	
	}
	
	public List<ReportDTO> getReportList(Pager pager) throws Exception {
		
		pager.makeNum(reportDAO.getTotalCount(pager));
		
		pager.makeRow();
		
		return reportDAO.getReportList(pager);
	}
	
	public ReportDTO getReportDetail(ReportDTO reportDTO) throws Exception {
		return reportDAO.getReportDetail(reportDTO);
	}
	
	public int setReportResult(ReportDTO reportDTO) throws Exception {
		int result = 0;
		
		// 신고 처리상태가 미신청인 DTO가 들어왔을 떄(비정상적인 접근)
		if(reportDTO.getReportResult() == 1) {
			return result;
		}
		
		//신고 상태 처리 결과
		result = reportDAO.setReportResult(reportDTO);
		
		// 처리에 실패 했을 경우
		if(result == 0) {
			return result;
		}
		
		//신고 승인했을떄의 처리를 위해 승인거부된 상태면 결과 반환후 메서드 종료
		if(reportDTO.getReportResult() == 2) {
			return result;
		}

		//신고 승인했을 때
		if(reportDTO.getReportResult() == 3) {
			// 게시글 신고 승인의 경우 컨텐츠와 타이틀 모두 빈 문자열로 업데이트
			// 게시판 리스트에서 컨텐츠와 타이틀 비어있으면 보이지 않게 처리
			// 댓글은 바로 삭제, 대댓글이 있는 경우 댓글은 블라인드 처리 
			
			//게시글에 대한 신고였을 때
			if(reportDTO.getCommentNum() == null) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setTitle("");
				boardDTO.setContents("");
				boardDTO.setNum(reportDTO.getNum());
				result = reportDAO.setBoardBlind(boardDTO);
				return result;
			}
			
			//댓글에 대한 신고였을 때
			else {
				//해당 댓글의 부모글을 참조하는 댓글들을 전부 조회
				List<BbsDTO> bbsDTOs = reportDAO.getCommentList(reportDTO);
				CommentDTO commentDTO = null;
				for (BbsDTO bbsDTO : bbsDTOs) {
					
					commentDTO = (CommentDTO)bbsDTO;
					if(reportDTO.getCommentNum().equals(commentDTO.getCommentNum())) {	
						if(commentDTO.getStep() > 0) {
							commentDTO.setContents("");
							break;
						}
						else {
							commentDTO.setContents("");
							break;

						}
					}
					else {
						commentDTO = null;
					}
				}
				
				if(commentDTO != null) {
					result = reportDAO.setCommentBlind(commentDTO);
					return result;	
				}

			}
			
		}
		
		
		
		return result;
	}
}
