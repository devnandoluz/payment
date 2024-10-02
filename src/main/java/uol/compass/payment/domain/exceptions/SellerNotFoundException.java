package uol.compass.payment.domain.exceptions;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class ChargeNotFoundException extends EntityNotFoundException {
    public ChargeNotFoundException(UUID code) {
        super("Charge with code " + code + " not found");
    }
}
