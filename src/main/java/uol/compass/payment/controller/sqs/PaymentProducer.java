package uol.compass.payment.controller.sqs;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uol.compass.payment.EventQueueProperties;
import uol.compass.payment.application.dtos.input.PaymentDTO;

@Component
public class PaymentProducer {
    private final EventQueueProperties eventQueueProperties;
    private final SqsTemplate sqsTemplate;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public PaymentProducer(EventQueueProperties eventQueueProperties, SqsTemplate sqsTemplate) {
        this.eventQueueProperties = eventQueueProperties;
        this.sqsTemplate = sqsTemplate;
    }

    public void sendToPartial(PaymentDTO payload) {
        logger.info("Sending to partial payment queue");
        sqsTemplate.send(eventQueueProperties.getPartialPayments(), payload);
        logger.info("Sent to partial payment queue");
    }

    public void sendToTotal(PaymentDTO payload) {
        logger.info("Sending to total payment queue");
        sqsTemplate.send(eventQueueProperties.getTotalPayments(), payload);
        logger.info("Sent to total payment queue");
    }

    public void sendToExcessive(PaymentDTO payload) {
        logger.info("Sending to excessive payment queue");
        sqsTemplate.send(eventQueueProperties.getExcessivePayments(), payload);
        logger.info("Sent to excessive payment queue");
    }
}
