package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.ProductDTO;
import shoestore.entity.ProductEntity;

@Component
public class ProductConverter {
	public ProductDTO convertToDTO(ProductEntity productEntity) {
		ModelMapper modelMapper=new ModelMapper();
		ProductDTO productDTO=modelMapper.map(productEntity, ProductDTO.class);
		return productDTO;
	}
	public ProductEntity convertToEntity(ProductDTO productDTO) {
		ModelMapper modelMapper=new ModelMapper();
		ProductEntity productEntity=modelMapper.map(productDTO,ProductEntity.class);
		return productEntity;
		
	}
}
