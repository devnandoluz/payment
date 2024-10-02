package uol.compass.payment.application.dtos.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ChargeDTO(
        @Size(min = 5, max = 255)
        String description,

        @NotNull
        UUID sellerCode,

        @Min(1)
        BigDecimal amount
) {
}