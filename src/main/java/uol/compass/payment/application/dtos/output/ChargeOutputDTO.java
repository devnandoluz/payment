package uol.compass.payment.application.dtos.output;

import uol.compass.payment.domain.entities.Charge;

import java.math.BigDecimal;
import java.util.UUID;

public record ChargeOutputDTO(
        String description,
        UUID code,
        UUID sellerCode,
        BigDecimal amount
) {
    public ChargeOutputDTO(Charge entity) {
        this(entity.getDescription(), entity.getCode(), entity.getSeller().getCode(), entity.getAmount());
    }
}
