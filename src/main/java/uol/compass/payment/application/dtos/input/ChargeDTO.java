package uol.compass.payment.application.dtos;

import uol.compass.payment.domain.enums.ChargeStatus;

import java.util.UUID;

public record ChargeDTO(
        String id,
        UUID sellerCod,
        Double amount,
        ChargeStatus status
) {
}
