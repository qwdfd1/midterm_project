package com.camp.s1.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp.s1.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	// List출력
	public List<ProductDTO> getProductList(Pager pager) throws Exception {
		pager.setPerPage(15L);
		pager.makeRow();
		
		
		pager.makeNum(productDAO.getTotalCount(pager));
		return productDAO.getProductList(pager);
	}
	
	// Detail 상세페이지 출력
	public ProductDTO getProductDetail(ProductDTO productDTO) throws Exception {
		productDTO = productDAO.getProductDetail(productDTO);
		productDTO.setProductGradeDTOs(productDAO.getProductGrade(productDTO));
		return productDTO;
	}
	
	// Add 물품 추가
	public int setProductAdd(ProductDTO productDTO) throws Exception {
		return productDAO.setProductAdd(productDTO);
	}
	
	// Update 물품 수정
	public int setProductUpdate(ProductDTO productDTO) throws Exception {
		return productDAO.setProductUpdate(productDTO);
	}
	
	public int setProductDelete(ProductDTO productDTO) throws Exception {
		return productDAO.setProductDelete(productDTO);
	}

}
