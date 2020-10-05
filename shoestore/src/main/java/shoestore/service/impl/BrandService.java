package shoestore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.BrandConverter;
import shoestore.dto.BrandDTO;
import shoestore.entity.BrandEntity;
import shoestore.repository.BrandRepository;
import shoestore.service.IBrandService;

@Service
public class BrandService implements IBrandService{
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	BrandConverter brandConverter;
	
	@Override
	public List<BrandDTO> listBrand() {
		List<BrandEntity> brandEntities=brandRepository.findAll();
		return brandEntities.stream().map(item -> brandConverter.convertToDTO(item)).collect(Collectors.toList());
	}

}
