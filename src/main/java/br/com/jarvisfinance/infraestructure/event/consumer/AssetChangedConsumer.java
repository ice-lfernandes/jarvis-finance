package br.com.jarvisfinance.infraestructure.event.consumer;

import br.com.jarvisfinance.domain.aspect.annotation.MethodLogExecution;
import br.com.jarvisfinance.domain.service.balance.BalanceService;
import br.com.jarvisfinance.infraestructure.event.configuration.SqsErrorHandler;
import br.com.jarvisfinance.infraestructure.event.mapper.AssetEventSchemaMapper;
import br.com.jarvisfinance.infraestructure.event.schema.AssetSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AssetChangedConsumer {

    SqsAsyncClient sqsAsyncClient;
    ObjectMapper objectMapper;
    AssetEventSchemaMapper mapper;
    BalanceService balanceService;

    @MethodLogExecution
    @SqsListener(
          value = "${jarvis.finance.aws.sqs.queue.asset-changed}",
          factory = "assetSqsListenerContainerFactory"
    )
    public void consumeAssetChanged(String payload) throws JsonProcessingException {
        AssetSchema assetSchema = objectMapper.readValue(payload, AssetSchema.class);
        balanceService.createOrUpdatedBalanceFromNewAsset(mapper.toModel(assetSchema));
    }

    @Bean
    public SqsMessageListenerContainerFactory<AssetSchema> assetSqsListenerContainerFactory() {
        return SqsMessageListenerContainerFactory.<AssetSchema>builder()
              .configure(options -> {
                  // Define ACK policy as on success, meaning the message is deleted from the queue only after successful processing
                  options.acknowledgementMode(AcknowledgementMode.ON_SUCCESS);
              })
              .errorHandler(new SqsErrorHandler<AssetSchema>())
              .sqsAsyncClient(sqsAsyncClient)
              .build();
    }
}