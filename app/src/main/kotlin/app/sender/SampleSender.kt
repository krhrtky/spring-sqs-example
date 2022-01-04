package app.sender

import app.config.SQSConfig
import org.slf4j.LoggerFactory
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class SampleSender(
    private val queueMessagingTemplate: QueueMessagingTemplate,
    private val sqsConfig: SQSConfig,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun send(message: String) {
        logger.info("send message to SQS {}", message)

        queueMessagingTemplate.send(
            sqsConfig.sampleQueueName,
            MessageBuilder.withPayload(message).build(),
        )
    }
}
