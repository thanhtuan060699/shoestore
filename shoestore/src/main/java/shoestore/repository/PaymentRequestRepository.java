package shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.PaymentRequestEntity;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequestEntity, Long>{

}
