package shoestore.service;

import java.util.List;

import shoestore.dto.ProductAttributeDTO;
import shoestore.dto.ProductDTO;

public interface IProductAttributeService {
	public List<ProductAttributeDTO> findAllByProductId(Long id);
	public ProductAttributeDTO findByColorSizeAndProductId(ProductAttributeDTO productAttributeDTO);
	public List<ProductAttributeDTO> findSampleOfProduct(List<ProductDTO> productDTOs);
}
