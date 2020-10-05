package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
	
}
