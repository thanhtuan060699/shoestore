package shoestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.ProductAttributeEntity;
import shoestore.entity.ProductEntity;

public interface ProductAttributeRepository extends JpaRepository<ProductAttributeEntity, Long>{
	public List<ProductAttributeEntity> findAllByProductEntity(ProductEntity productEntity);
	public ProductAttributeEntity findByColorAndSizeAndProductEntity(String color,Double size,ProductEntity productEntity);
}
