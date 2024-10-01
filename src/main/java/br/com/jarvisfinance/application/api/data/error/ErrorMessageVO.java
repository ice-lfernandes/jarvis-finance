package br.com.jarvisfinance.application.api.data.error;

public record ErrorMessageVO(
      String message,
      String field
) {
}
