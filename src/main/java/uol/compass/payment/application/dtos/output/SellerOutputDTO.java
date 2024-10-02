package uol.compass.payment.application.dtos.input;

import jakarta.validation.constraints.Size;
import uol.compass.payment.domain.entities.Seller;

public record SellerDTO(
        @Size(min = 5, max = 255)
        String name
) {
    public SellerDTO(Seller entity) {
        this(entity.getName());
    }
}
