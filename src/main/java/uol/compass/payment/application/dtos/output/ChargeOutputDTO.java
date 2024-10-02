package uol.compass.payment.application.dtos.input;

import java.util.UUID;

public record ChargeDTO(
        String description,
        UUID sellerCod,
        Double amount
) {
}
