package uol.compass.payment.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uol.compass.payment.application.dtos.input.PaymentDTO;
import uol.compass.payment.application.dtos.input.PaymentsMadeDTO;
import uol.compass.payment.application.dtos.output.PaymentOutputDTO;
import uol.compass.payment.domain.entities.Payment;
import uol.compass.payment.domain.entities.Seller;

@Service
public class PaymentMadeService {
    private final ChargeService chargeService;
    private final SellerService sellerService;
    private final PaymentService paymentService;

    public PaymentMadeService(ChargeService chargeService, SellerService sellerService, PaymentService paymentService) {
        this.sellerService = sellerService;
        this.paymentService = paymentService;
        this.chargeService = chargeService;
    }

    @Transactional
    public PaymentsMadeDTO create(PaymentsMadeDTO paymentsMadeDTO) {
        sellerService.checkExistsByCodeOrElseThrow(paymentsMadeDTO.sellerCode());

        paymentsMadeDTO.payments().forEach(this::create);

        sendToQueue(entity);
    }

    private void create(PaymentDTO paymentDTO) {
        var charge = chargeService.findByCodeOrElseThrow(paymentDTO.chargeCode());
        paymentService.create(charge, paymentDTO);
    }

    private void sendToQueue(PaymentDTO paymentDTO) {
        //TODO send to queue
    }
}
