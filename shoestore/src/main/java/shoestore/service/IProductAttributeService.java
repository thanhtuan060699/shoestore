package shoestore.service;

import java.util.List;

import shoestore.dto.BrandDTO;
import shoestore.dto.CartDTO;
import shoestore.dto.ProductAttributeDTO;
import shoestore.dto.ProductDTO;

public interface IProductAttributeService {
	public List<ProductAttributeDTO> findAllByProductId(Long id);
	public ProductAttributeDTO findByColorSizeAndProductId(ProductAttributeDTO productAttributeDTO);
	public List<ProductAttributeDTO> findSampleOfProduct(List<ProductDTO> productDTOs);
	public void reduceQuantity(List<CartDTO> cartDTOs);
	public List<BrandDTO> quantityOfBrand(List<BrandDTO> brandDTOs);
}
