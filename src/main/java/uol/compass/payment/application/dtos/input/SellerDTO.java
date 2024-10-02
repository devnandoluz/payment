package uol.compass.payment.application.dtos;

import jakarta.validation.constraints.Size;
import uol.compass.payment.domain.entities.Seller;

import java.util.UUID;

public record SellerDTO(
        UUID id,
        @Size(min = 5, max = 255)
        String name
) {
    public SellerDTO(Seller entity) {
        this(entity.getId(), entity.getName());
    }
}
