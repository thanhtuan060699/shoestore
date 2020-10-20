package shoestore.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import shoestore.dto.PointDTO;
import shoestore.entity.PointEntity;

@Component
public class PointConverter {
	public PointDTO convertToDTO(PointEntity pointEntity) {
		ModelMapper modelMapper=new ModelMapper();
		PointDTO pointDTO=modelMapper.map(pointEntity, PointDTO.class);
		return pointDTO;
	}
	public PointEntity convertToEntity(PointDTO pointDTO) {
		ModelMapper modelMapper =new ModelMapper();
		PointEntity pointEntity=modelMapper.map(pointDTO, PointEntity.class);
		return pointEntity;
	}
}
