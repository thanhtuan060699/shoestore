package shoestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.ImageEntity;
import shoestore.entity.ProductEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
	public List<ImageEntity> findAllByTypeAndProductEntity(String type,ProductEntity productEntity);
	public List<ImageEntity> findAllByProductEntity(ProductEntity productEntity);
}
