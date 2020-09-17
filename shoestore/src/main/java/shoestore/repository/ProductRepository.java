package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
}
