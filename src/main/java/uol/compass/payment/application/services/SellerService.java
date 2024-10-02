package uol.compass.payment.application.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uol.compass.payment.application.dtos.PaymentDTO;
import uol.compass.payment.domain.entities.Payment;
import uol.compass.payment.infrastructure.persistence.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void create(PaymentDTO dto) {
        var payment = new Payment(dto);
        repository.save(payment);
    }
}
