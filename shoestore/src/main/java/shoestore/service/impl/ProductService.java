package shoestore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shoestore.convert.ProductConverter;
import shoestore.dto.ProductDTO;
import shoestore.entity.ProductEntity;
import shoestore.repository.ProductRepository;
import shoestore.service.IProductService;

@Service
public class ProductService implements IProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductConverter productConverter;
	
	@Override
	public int getTotalItem() {
		return (int)productRepository.count();
	}


	@Override
	public List<ProductDTO> findAll(Pageable pageable) {
	
		List<ProductEntity> productEntities=(List<ProductEntity>) productRepository.findAll(pageable).getContent();
		return  productEntities.stream().map(item -> productConverter.convertToDTO(item)).collect(Collectors.toList());
	}


	@Override
	public ProductDTO findById(Long id) {
		ProductEntity productEntity=productRepository.findOne(id);
		return productConverter.convertToDTO(productEntity);
	}

}
