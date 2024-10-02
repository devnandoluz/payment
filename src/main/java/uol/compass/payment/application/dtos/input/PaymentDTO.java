package uol.compass.payment.application.dtos.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import uol.compass.payment.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentDTO {
    @NotNull
    private final UUID chargeCode;

    @Min(1)
    private final BigDecimal amount;

    private PaymentStatus status;

    public PaymentDTO(UUID chargeCode, BigDecimal amount, PaymentStatus status) {
        this.chargeCode = chargeCode;
        this.amount = amount;
        this.status = status;
    }

    public UUID getChargeCode() {
        return chargeCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentDTO{chargeCode=" + chargeCode + ", amount=" + amount + ", status=" + status + '}';
    }
}
