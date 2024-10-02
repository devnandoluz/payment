package uol.compass.payment.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uol.compass.payment.application.dtos.input.PaymentDTO;
import uol.compass.payment.application.dtos.input.PaymentRequestDTO;
import uol.compass.payment.controller.sqs.PaymentProducer;
import uol.compass.payment.domain.entities.Charge;
import uol.compass.payment.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    @Mock
    private ChargeService chargeService;

    @Mock
    private SellerService sellerService;

    @Mock
    private PaymentProducer paymentProducer;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Process payment with TOTAL status")
    void processPaymentWithTotalStatus() {
        PaymentDTO paymentDTO = new PaymentDTO(UUID.randomUUID(), BigDecimal.TEN, null);
        PaymentRequestDTO requestDTO = new PaymentRequestDTO(UUID.randomUUID(), List.of(paymentDTO));
        Charge charge = new Charge("description", BigDecimal.TEN);

        when(chargeService.findByCodeOrElseThrow(any())).thenReturn(charge);

        paymentService.process(requestDTO);

        assertEquals(PaymentStatus.TOTAL, paymentDTO.getStatus());
        verify(paymentProducer).sendToTotal(paymentDTO);
    }

    @Test
    @DisplayName("Process payment with PARTIAL status")
    void processPaymentWithPartialStatus() {
        var paymentDTO = new PaymentDTO(UUID.randomUUID(), BigDecimal.valueOf(5), null);
        var requestDTO = new PaymentRequestDTO(UUID.randomUUID(), List.of(paymentDTO));
        Charge charge = new Charge("description", BigDecimal.TEN);

        when(chargeService.findByCodeOrElseThrow(any())).thenReturn(charge);

        paymentService.process(requestDTO);

        assertEquals(PaymentStatus.PARTIAL, paymentDTO.getStatus());
        verify(paymentProducer).sendToPartial(paymentDTO);
    }

    @Test
    @DisplayName("Process payment with EXCESSIVE status")
    void processPaymentWithExcessiveStatus() {
        PaymentDTO paymentDTO = new PaymentDTO(UUID.randomUUID(), BigDecimal.valueOf(15), null);
        PaymentRequestDTO requestDTO = new PaymentRequestDTO(UUID.randomUUID(), List.of(paymentDTO));
        Charge charge = new Charge("description", BigDecimal.TEN);

        when(chargeService.findByCodeOrElseThrow(any())).thenReturn(charge);

        paymentService.process(requestDTO);

        assertEquals(PaymentStatus.EXCESSIVE, paymentDTO.getStatus());
        verify(paymentProducer).sendToExcessive(paymentDTO);
    }

    @Test
    @DisplayName("Should check seller existence")
    void checkSellerExistence() {
        PaymentRequestDTO requestDTO = new PaymentRequestDTO(UUID.randomUUID(), List.of());

        paymentService.process(requestDTO);

        verify(sellerService).checkExistsByCodeOrElseThrow(requestDTO.sellerCode());
    }

    @Test
    @DisplayName("Should send all payments to queue")
    void sendToQueue() {
        var paymentDTO1 = new PaymentDTO(UUID.randomUUID(),  BigDecimal.valueOf(5), null);
        var paymentDTO2 = new PaymentDTO(UUID.randomUUID(), BigDecimal.TEN, null);
        var paymentDTO3 = new PaymentDTO(UUID.randomUUID(), BigDecimal.valueOf(15), null);
        var requestDTO = new PaymentRequestDTO(UUID.randomUUID(), List.of(paymentDTO1, paymentDTO2, paymentDTO3));

        when(chargeService.findByCodeOrElseThrow(any())).thenReturn(new Charge("description", BigDecimal.TEN));

        paymentService.process(requestDTO);

        verify(paymentProducer).sendToPartial(any());
        verify(paymentProducer).sendToTotal(any());
        verify(paymentProducer).sendToExcessive(any());
    }
}