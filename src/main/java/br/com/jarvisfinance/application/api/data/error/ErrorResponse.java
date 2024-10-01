package br.com.jarvisfinance.application.api.data.error;

public record ErrorResponse(
      String status,
      String message
) {
}
