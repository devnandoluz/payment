package uol.compass.payment.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uol.compass.payment.application.dtos.input.PaymentRequestDTO;
import uol.compass.payment.application.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentApi {
    private final PaymentService service;

    public PaymentApi(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentRequestDTO> create(@RequestBody @Valid PaymentRequestDTO paymentRequestDTO) {
        return ResponseEntity.ok(service.process(paymentRequestDTO));
    }
}
