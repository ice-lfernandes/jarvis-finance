package br.com.jarvisfinance.infraestructure.event.publish;

import br.com.jarvisfinance.domain.aspect.annotation.MethodLogExecution;
import br.com.jarvisfinance.domain.event.AssetChangedEvent;
import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.infraestructure.event.mapper.AssetEventSchemaMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Slf4j
@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AssetChangeEventImpl implements AssetChangedEvent {

    SqsAsyncClient sqsAsyncClient;
    String queueUrl;
    ObjectMapper objectMapper;
    AssetEventSchemaMapper mapper;

    public AssetChangeEventImpl(SqsAsyncClient sqsAsyncClient,
                                @Value("${jarvis.finance.aws.sqs.queue.asset-changed}") String queueUrl,
                                ObjectMapper objectMapper, AssetEventSchemaMapper mapper) {
        this.sqsAsyncClient = sqsAsyncClient;
        this.queueUrl = queueUrl;
        this.objectMapper = objectMapper;
        this.mapper = mapper;
    }

    @MethodLogExecution
    @Override
    public void notifyAssetAfterChanged(@NotNull Asset asset) {
        var request = SendMessageRequest.builder()
              .queueUrl(queueUrl)
              .messageBody(buildSchemaEvent(asset))
              .build();

        sqsAsyncClient.sendMessage(request)
              .whenComplete((result, error) -> {
                  if (error != null) {
                      log.error("Error sending message to SQS", error);
                  } else {
                      log.info("Message sent to SQS with id: " + result.messageId());
                  }
              });
    }

    private String buildSchemaEvent(@NotNull Asset asset) {
        try {
            return objectMapper.writeValueAsString(mapper.toEventSchema(asset));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing message", e);
        }
    }
}
