package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUserNameAndStatus(String name, int status);
	UserEntity findByUserName(String name);
}
