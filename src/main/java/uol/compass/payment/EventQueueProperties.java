package uol.compass.payment;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("queues")
public class EventQueueProperties {
    private final String partialPayments;
    private final String totalPayments;
    private final String excessivePayments;

    public EventQueueProperties(String partialPayments, String totalPayments, String excessivePayments) {
        this.partialPayments = partialPayments;
        this.totalPayments = totalPayments;
        this.excessivePayments = excessivePayments;
    }

    public String getPartialPayments() {
        return partialPayments;
    }

    public String getTotalPayments() {
        return totalPayments;
    }

    public String getExcessivePayments() {
        return excessivePayments;
    }
}
