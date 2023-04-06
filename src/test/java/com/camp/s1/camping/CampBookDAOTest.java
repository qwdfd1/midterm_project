package com.camp.s1.camping;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.camp.s1.MyTestCase;
import com.camp.s1.camping.book.CampBookDAO;
import com.camp.s1.camping.book.CampBookDTO;

public class CampBookDAOTest extends MyTestCase{
	
	@Autowired
	private CampBookDAO campBookDAO;
	
	//test 메서드 작성
	
	
	//getOrderNumberTest
	//@Test - 이상없음
	public void getOrderNumberTest() throws Exception{
		Long orderNum = campBookDAO.getOrderNumber();
		System.out.println(orderNum);
	}
	
	
	//setOrderNumberTest
	//@Test - 이상없음
	public void setOrderNumberTest() throws Exception{
		CampBookDTO campBookDTO = new CampBookDTO();
		//orderNum 먼저 받아오기
		Long orderNum = campBookDAO.getOrderNumber();
		//campbookDTO에 orderNum 넣고 orders 테이블에 orderNum 값 넣기
		campBookDTO.setOrderNum(orderNum);
		int result = campBookDAO.setOrderNumber(campBookDTO);
		
		assertNotEquals(0, result);
	}
	
	
	//CampBookAddTest
	//@Test - 이상없음
	public void setCampBookAddTest() throws Exception{
		CampBookDTO campBookDTO = new CampBookDTO();
		//orderNum 먼저 받아오기
		Long orderNum = campBookDAO.getOrderNumber();
		//campbookDTO에 orderNum 넣고 orders 테이블에 orderNum 값 넣기
		campBookDTO.setOrderNum(orderNum);
		int result = campBookDAO.setOrderNumber(campBookDTO);
		System.out.println(result);
		
		campBookDTO.setAreaNum(905L);
		campBookDTO.setId("user01");
		campBookDTO.setIndexCode(1L);
		//campBookDTO.setOrderNum(orderNum);
		campBookDTO.setPrice(80000L);
		campBookDTO.setStartDate("2023-04-10");
		campBookDTO.setLastDate("2023-04-11");
		campBookDTO.setAccount("112-01-6656");
		campBookDTO.setDayInfo(1L);
		
		result = campBookDAO.setCampBookAdd(campBookDTO);
		assertNotEquals(0, result);
	}
}
