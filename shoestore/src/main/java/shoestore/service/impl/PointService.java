package shoestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.PointConverter;
import shoestore.dto.PointDTO;
import shoestore.entity.PointEntity;
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
		
		
	}

}
