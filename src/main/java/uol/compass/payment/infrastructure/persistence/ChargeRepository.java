package uol.compass.payment.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.payment.domain.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
