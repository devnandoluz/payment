package uol.compass.payment.application.dtos.output;

import uol.compass.payment.domain.entities.Seller;

import java.util.UUID;

public record SellerOutputDTO(
        UUID code,
        String name
) {
    public SellerOutputDTO(Seller entity) {
        this(entity.getCode(), entity.getName());
    }
}
