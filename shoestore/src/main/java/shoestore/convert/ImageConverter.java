package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.ImageDTO;
import shoestore.entity.ImageEntity;

@Component
public class ImageConverter {
	public ImageDTO convertToDTO(ImageEntity imageEntity) {
		ModelMapper modelMapper=new ModelMapper();
		ImageDTO imageDTO=modelMapper.map(imageEntity, ImageDTO.class);
		return imageDTO;
	}
	public ImageEntity convertToEntity(ImageDTO imageDTO) {
		ModelMapper modelMapper =new ModelMapper();
		ImageEntity imageEntity=modelMapper.map(imageDTO, ImageEntity.class);
		return imageEntity;
	}
}
