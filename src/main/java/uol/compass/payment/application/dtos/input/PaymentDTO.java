package uol.compass.payment.application.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import uol.compass.payment.domain.entities.Payment;
import uol.compass.payment.domain.enums.PaymentStatus;

import java.util.UUID;

public record PaymentDTO(
        UUID id,
        @NotNull
        SellerDTO seller,
        @Min(1)
        Double amount,
        PaymentStatus status
) {
    public PaymentDTO(Payment entity) {
        this(entity.getId(), new SellerDTO(entity.getSeller()), entity.getAmount(), entity.getStatus());
    }
}
