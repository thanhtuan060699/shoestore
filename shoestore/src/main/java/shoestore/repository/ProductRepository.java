package shoestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.BrandEntity;
import shoestore.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	public List<ProductEntity> findAllByBrandEntity(BrandEntity brandEntity);
}
