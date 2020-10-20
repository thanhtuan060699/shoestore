package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.PointEntity;

public interface PointRepository extends JpaRepository<PointEntity, Long> {

}
