package uol.compass.payment.application.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PaymentsMadeDTO(
        @NotNull
        UUID sellerCode,

        @NotEmpty
        List<PaymentDTO> payments
) {
}
