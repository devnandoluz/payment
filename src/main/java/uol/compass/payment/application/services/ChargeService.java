package uol.compass.payment.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uol.compass.payment.application.dtos.input.PaymentDTO;
import uol.compass.payment.domain.entities.Payment;
import uol.compass.payment.infrastructure.persistence.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final ChargeService chargeService;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PaymentDTO create(PaymentDTO paymentDTO) {

        var entity = new Payment(paymentDTO);
        return new PaymentDTO(save(entity));
    }

    private Payment save(Payment payment) {
        return repository.save(payment);
    }
}
