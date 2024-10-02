package br.com.jarvisfinance.infraestructure.event.configuration;

import io.awspring.cloud.sqs.listener.AsyncAdapterBlockingExecutionFailedException;
import io.awspring.cloud.sqs.listener.errorhandler.AsyncErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.Message;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class SqsErrorHandler<T> implements AsyncErrorHandler<T> {

    @Override
    public @NotNull CompletableFuture<Void> handle(Message<T> message, @NotNull Throwable throwable) {
        log.error("stage=error-handler, msg=error-consuming-message, message=" + message.getPayload(), throwable);

        // Check the cause of the throwable
        if (throwable.getCause() instanceof AsyncAdapterBlockingExecutionFailedException) {
            // Exception is non-retryable
            return CompletableFuture.completedFuture(null);
        } else {
            // For all other exceptions, call the superclass handle method
            return AsyncErrorHandler.super.handle(message, throwable);
        }
    }
}
