package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.UserEntity;
import shoestore.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{
	public UserRoleEntity  findByUserEntity(UserEntity userEntity);
}
