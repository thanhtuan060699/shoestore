package shoestore.service;

import java.util.List;

import shoestore.dto.ProductAttributeDTO;

public interface IProductAttributeService {
	public List<ProductAttributeDTO> findAllByProductId(Long id);
	public ProductAttributeDTO findByColorSizeAndProductId(ProductAttributeDTO productAttributeDTO);
}
