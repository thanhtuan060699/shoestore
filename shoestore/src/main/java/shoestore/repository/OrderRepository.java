package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
