package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.ContactEntity;
import shoestore.entity.UserEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Long>{
	public ContactEntity findByColorAndShoeNameAndSizeAndUserEntity(String color,String shoeName,Double size,UserEntity userEntity);
}
