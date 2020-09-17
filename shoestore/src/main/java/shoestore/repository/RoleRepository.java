package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	public RoleEntity findById(Long id);
}
