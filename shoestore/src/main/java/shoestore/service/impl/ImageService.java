package shoestore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoestore.convert.ImageConverter;
import shoestore.dto.ImageDTO;
import shoestore.entity.ImageEntity;
import shoestore.entity.ProductEntity;
import shoestore.repository.ImageRepository;
import shoestore.repository.ProductRepository;
import shoestore.service.IImageService;

@Service
public class ImageService implements IImageService{
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ImageConverter imageConverter;

	@Override
	public List<ImageDTO> findImageByProductId(Long productId) {
		ProductEntity productEntity=productRepository.findOne(productId);
		List<ImageEntity> imageEntities=imageRepository.findAllByProductEntity(productEntity);
		return imageEntities.stream().map(item -> imageConverter.convertToDTO(item)).collect(Collectors.toList());
	}
	
	

}
