package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.ProductAttributeDTO;
import shoestore.entity.ProductAttributeEntity;

@Component
public class ProductAttributeConverter {
	public ProductAttributeDTO convertToDTO(ProductAttributeEntity productAttributeEntity) {
		ModelMapper modelMapper=new ModelMapper();
		ProductAttributeDTO productAttributeDTO=modelMapper.map(productAttributeEntity, ProductAttributeDTO.class);
		return productAttributeDTO;
	}
	public ProductAttributeEntity convertToEntity(ProductAttributeDTO productAttributeDTO) {
		ModelMapper modelMapper=new ModelMapper();
		ProductAttributeEntity productAttributeEntity=modelMapper.map(productAttributeDTO,ProductAttributeEntity.class);
		return productAttributeEntity;
		
	}
}
