package uol.compass.payment.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uol.compass.payment.domain.entities.Charge;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {
    Optional<Charge> findByCode(UUID code);
}
