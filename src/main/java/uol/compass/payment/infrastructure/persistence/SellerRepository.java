package uol.compass.payment.domain.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.payment.controller.api.Payment;

@Repository
public interface SalerRepository extends JpaRepository<Payment, Long> {
}
