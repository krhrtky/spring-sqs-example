package app.listener

import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class SampleListener {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @SqsListener(value = ["\${aws.sqs.sample-queue}"], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun loadMessageFromSQS(message: String?) {
        logger.info("message from SQS Queue {}", message)
    }
}
