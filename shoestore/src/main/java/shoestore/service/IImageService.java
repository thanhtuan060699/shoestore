package shoestore.service;

import java.util.List;

import shoestore.dto.ImageDTO;

public interface IImageService {
	public List<ImageDTO> findImageByProductId(Long productId);
}
