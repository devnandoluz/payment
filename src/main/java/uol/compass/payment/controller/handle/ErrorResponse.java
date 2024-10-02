package uol.compass.payment.controller.handle;

public record ErrorResponse(
        String message,
        int status
) {
}
