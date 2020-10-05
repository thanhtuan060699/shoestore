package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.BrandDTO;
import shoestore.entity.BrandEntity;

@Component
public class BrandConverter {
	public BrandDTO convertToDTO(BrandEntity brandEntity) {
		ModelMapper modelMapper=new ModelMapper();
		BrandDTO brandDTO=modelMapper.map(brandEntity, BrandDTO.class);
		return brandDTO;
	}
	public BrandEntity convertToEntity(BrandDTO brandDTO) {
		ModelMapper modelMapper =new ModelMapper();
		BrandEntity brandEntity=modelMapper.map(brandDTO, BrandEntity.class);
		return brandEntity;
	}
}
