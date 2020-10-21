package shoestore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.PointConverter;
import shoestore.dto.PointDTO;
import shoestore.entity.PointEntity;
import shoestore.entity.ScreenEntity;
import shoestore.repository.PointRepository;
import shoestore.repository.ScreenRepository;
import shoestore.service.IPointService;

@Service
public class PointService implements IPointService {
	@Autowired
	PointConverter pointConverter;
	
	@Autowired
	PointRepository pointRepository;
	
	@Autowired
	ScreenRepository screenRepository;
	
	@Override
	public void addNewPoint(PointDTO pointDTO) {
		PointEntity pointEntity=pointConverter.convertToEntity(pointDTO);
		ScreenEntity screenEntity=screenRepository.findByUrl(pointDTO.getUrl());
		PointEntity pointCheck=pointRepository.findByXValueAndYValueAndScreenEntityAndType(pointDTO.getxValue(), pointDTO.getyValue(),screenEntity,pointDTO.getType());
		if(pointCheck==null) {
			pointEntity.setScreenEntity(screenEntity);
			pointRepository.save(pointEntity);
		}else {
			pointCheck.setValue(pointCheck.getValue()+1);
			pointRepository.save(pointCheck);
		}
		
	}
	
	@Override
	public List<PointDTO> getPointByUrl(PointDTO pointDTO) {
		ScreenEntity screenEntity=screenRepository.findByUrl(pointDTO.getUrl());
		List<PointEntity> pointEntities=pointRepository.getListPoitByScreenEntityAndType(screenEntity,pointDTO.getType()); 
		return pointEntities.stream().map(item -> pointConverter.convertToDTO(item)).collect(Collectors.toList());
	}

	@Override
	public Integer getMaxValueInScreen(PointDTO pointDTO) {
		ScreenEntity screenEntity=screenRepository.findByUrl(pointDTO.getUrl());
		Integer max=pointRepository.getMaxValueInScreen(screenEntity.getId(),pointDTO.getType());
		return max;
	}

	@Override
	public Integer getMinValueInScreen(PointDTO pointDTO) {
		ScreenEntity screenEntity=screenRepository.findByUrl(pointDTO.getUrl());
		Integer min=pointRepository.getMinValueInScreen(screenEntity.getId(),pointDTO.getType());
		return min;
	}

}
