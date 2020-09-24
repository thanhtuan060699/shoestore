package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>{

}
