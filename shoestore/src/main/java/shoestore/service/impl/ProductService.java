package shoestore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shoestore.constant.SystemConstant;
import shoestore.convert.ProductConverter;
import shoestore.dto.ProductDTO;
import shoestore.entity.ImageEntity;
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
		List<ProductDTO> productDTOs=new ArrayList<ProductDTO>();
		for(ProductEntity productEntity:productEntities) {
			ProductDTO productDTO=productConverter.convertToDTO(productEntity);
			for(ImageEntity imageEntity:productEntity.getImageEntities()) {
				if(imageEntity.getType().equals(SystemConstant.IMAGE_THUMBNAIL)) {
					productDTO.setImage(imageEntity.getImage());
				}
			}
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}


	@Override
	public ProductDTO findById(Long id) {
		ProductEntity productEntity=productRepository.findOne(id);
		ProductDTO productDTO=productConverter.convertToDTO(productEntity);
		productDTO.setBrand(productEntity.getBrandEntity().getName());
		return productDTO;
	}

}
