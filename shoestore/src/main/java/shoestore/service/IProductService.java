package shoestore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shoestore.dto.ProductDTO;

public interface IProductService {
	public int getTotalItem();
	public List<ProductDTO> findAll(Pageable pageable);
	public ProductDTO findById(Long id);
}
