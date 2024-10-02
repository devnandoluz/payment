package uol.compass.payment.application.dtos.input;

import jakarta.validation.constraints.Size;

public record SellerDTO(
        @Size(min = 5, max = 255)
        String name
) {
}
