package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.ScreenEntity;

public interface ScreenRepository extends JpaRepository<ScreenEntity, Long> {
	public ScreenEntity findByUrl(String url);
}
