package uol.compass.payment.domain.exceptions;

import java.util.UUID;

public class ChargeNotFoundException extends RuntimeException {
    public ChargeNotFoundException(UUID code) {
        super("Charge with code " + code + " not found");
    }
}
