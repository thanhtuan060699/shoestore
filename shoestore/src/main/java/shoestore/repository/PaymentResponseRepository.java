package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.PaymentResponseEntity;

public interface PaymentResponseRepository extends JpaRepository<PaymentResponseEntity, Long>{

}
