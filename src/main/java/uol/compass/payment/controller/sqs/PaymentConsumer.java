package uol.compass.payment.controller.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uol.compass.payment.application.dtos.input.PaymentDTO;

@Component
public class PaymentConsumer {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @SqsListener("${queues.partial-payments}")
    public void consume(PaymentDTO event) {
        logger.info("Received partial Payment: {}", event);
    }

    @SqsListener("${queues.total-payments}")
    public void consumeTotal(PaymentDTO event) {
        logger.info("Received Total Payment: {}", event);
    }

    @SqsListener("${queues.excessive-payments}")
    public void consumeExcessive(PaymentDTO event) {
        logger.info("Received Excessive Payment: {}", event);
    }
}
