package app.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SQSConfig {
    @Value("\${cloud.aws.region.static}")
    private val region: String? = null

    @Value("\${cloud.aws.credentials.access-key}")
    private val awsAccessKey: String? = null

    @Value("\${cloud.aws.credentials.secret-key}")
    private val awsSecretKey: String? = null

    @Value("\${aws.endpoint}")
    private val endPoint: String? = null

    @Value("\${aws.sqs.sample-queue}")
    val sampleQueueName: String = ""

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate? {
        return QueueMessagingTemplate(amazonSQSAsync())
    }

    @Primary
    @Bean
    fun amazonSQSAsync(): AmazonSQSAsync? = AmazonSQSAsyncClientBuilder
        .standard()
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(
                    awsAccessKey,
                    awsSecretKey
                )
            )
        )
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                endPoint,
                region,
            )
        )
        .build()

    companion object {
    }
}
