package uol.compass.payment.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uol.compass.payment.application.dtos.input.PaymentDTO;
import uol.compass.payment.application.dtos.input.PaymentRequestDTO;
import uol.compass.payment.controller.sqs.PaymentProducer;
import uol.compass.payment.domain.entities.Charge;
import uol.compass.payment.domain.enums.PaymentStatus;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private final ChargeService chargeService;
    private final SellerService sellerService;
    private final PaymentProducer paymentProducer;

    public PaymentService(ChargeService chargeService, SellerService sellerService, PaymentProducer paymentProducer) {
        this.sellerService = sellerService;
        this.paymentProducer = paymentProducer;
        this.chargeService = chargeService;
    }

    @Transactional
    public PaymentRequestDTO process(PaymentRequestDTO request) {
        sellerService.checkExistsByCodeOrElseThrow(request.sellerCode());
        request.payments().forEach(this::validateAndSetStatus);
        sendToQueue(request);

        return request;
    }

    private void validateAndSetStatus(PaymentDTO paymentDTO) {
        var charge = chargeService.findByCodeOrElseThrow(paymentDTO.getChargeCode());
        var status = determinePaymentStatus(charge, paymentDTO.getAmount());
        paymentDTO.setStatus(status);
    }

    private PaymentStatus determinePaymentStatus(Charge charge, BigDecimal paymentAmount) {
        var comparison = paymentAmount.compareTo(charge.getAmount());
        if (comparison < 0) {
            return PaymentStatus.PARTIAL;
        } else if (comparison == 0) {
            return PaymentStatus.TOTAL;
        } else {
            return PaymentStatus.EXCESSIVE;
        }
    }

    private void sendToQueue(PaymentRequestDTO paymentRequestDTO) {
        paymentRequestDTO.payments().stream().filter(payment -> payment.getStatus() == PaymentStatus.PARTIAL)
                .forEach(paymentProducer::sendToPartial);

        paymentRequestDTO.payments().stream().filter(payment -> payment.getStatus() == PaymentStatus.TOTAL)
                .forEach(paymentProducer::sendToTotal);

        paymentRequestDTO.payments().stream().filter(payment -> payment.getStatus() == PaymentStatus.EXCESSIVE)
                .forEach(paymentProducer::sendToExcessive);
    }
}
