package uol.compass.payment;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api.rabbit")
public class EventQueueProperties {
    private String queuePaymentPartial;
    private String queuePaymentTotal;
    private String queuePaymentExcessive;
    private String exchange;
    private String routingKeyPaymentPartial;
    private String routingKeyPaymentTotal;
    private String routingKeyPaymentExcessive;

    public String getQueuePaymentPartial() {
        return queuePaymentPartial;
    }

    public void setQueuePaymentPartial(String queuePaymentPartial) {
        this.queuePaymentPartial = queuePaymentPartial;
    }

    public String getQueuePaymentTotal() {
        return queuePaymentTotal;
    }

    public void setQueuePaymentTotal(String queuePaymentTotal) {
        this.queuePaymentTotal = queuePaymentTotal;
    }

    public String getQueuePaymentExcessive() {
        return queuePaymentExcessive;
    }

    public void setQueuePaymentExcessive(String queuePaymentExcessive) {
        this.queuePaymentExcessive = queuePaymentExcessive;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKeyPaymentPartial() {
        return routingKeyPaymentPartial;
    }

    public void setRoutingKeyPaymentPartial(String routingKeyPaymentPartial) {
        this.routingKeyPaymentPartial = routingKeyPaymentPartial;
    }

    public String getRoutingKeyPaymentTotal() {
        return routingKeyPaymentTotal;
    }

    public void setRoutingKeyPaymentTotal(String routingKeyPaymentTotal) {
        this.routingKeyPaymentTotal = routingKeyPaymentTotal;
    }

    public String getRoutingKeyPaymentExcessive() {
        return routingKeyPaymentExcessive;
    }

    public void setRoutingKeyPaymentExcessive(String routingKeyPaymentExcessive) {
        this.routingKeyPaymentExcessive = routingKeyPaymentExcessive;
    }
}
