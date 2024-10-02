package uol.compass.payment.domain.exceptions;

public class ChargeNotFoundException extends RuntimeException {
  public ChargeNotFoundException(String message) {
    super(message);
  }
}
