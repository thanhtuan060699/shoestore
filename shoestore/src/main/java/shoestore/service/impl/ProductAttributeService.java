package shoestore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.ProductAttributeConverter;
import shoestore.convert.ProductConverter;
import shoestore.dto.ProductAttributeDTO;
import shoestore.dto.ProductDTO;
import shoestore.entity.ProductAttributeEntity;
import shoestore.entity.ProductEntity;
import shoestore.repository.ProductAttributeRepository;
import shoestore.repository.ProductRepository;
import shoestore.service.IProductAttributeService;
@Service
public class ProductAttributeService implements IProductAttributeService{
	@Autowired
	ProductAttributeRepository productAttributeRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductAttributeConverter productAttributeConverter;
	
	@Override
	public List<ProductAttributeDTO> findAllByProductId(Long id) {
		ProductEntity productEntity=productRepository.findOne(id);
		List<ProductAttributeEntity> productAttributeEntities=productAttributeRepository.findAllByProductEntity(productEntity);
		return  productAttributeEntities.stream().map(item -> productAttributeConverter.convertToDTO(item)).collect(Collectors.toList());
	}

	@Override
	public ProductAttributeDTO findByColorSizeAndProductId(ProductAttributeDTO productAttributeDTO) {
		ProductEntity productEntity=productRepository.findOne(productAttributeDTO.getProductId());
		ProductAttributeEntity productAttributeEntity=productAttributeRepository.
				findByColorAndSizeAndProductEntity(productAttributeDTO.getColor(),productAttributeDTO.getSize(), productEntity);
		return productAttributeConverter.convertToDTO(productAttributeEntity);
	}

	@Override
	public List<ProductAttributeDTO> findSampleOfProduct(List<ProductDTO> productDTOs) {
		List<ProductAttributeDTO> productAttributeDTOs=new ArrayList<ProductAttributeDTO>();
		for(ProductDTO productDTO:productDTOs) {
			ProductEntity productEntity=productRepository.findOne(productDTO.getId());
			List<ProductAttributeEntity> productAttributes=productAttributeRepository.findAllByProductEntity(productEntity);
			productAttributeDTOs.add(productAttributeConverter.convertToDTO(productAttributes.get(0)));
		}
		return productAttributeDTOs;
	}

}
