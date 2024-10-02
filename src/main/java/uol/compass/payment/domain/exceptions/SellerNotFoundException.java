package uol.compass.payment.domain.exceptions;

import java.util.UUID;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(UUID code) {
        super("Seller with code " + code + " not found");
    }
}
