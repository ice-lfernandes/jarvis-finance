package br.com.jarvisfinance.application.api.data.response;

import br.com.jarvisfinance.infraestructure.data.enums.Month;

import java.math.BigDecimal;

public record BalanceResponse(
      Long id,
      Long year,
      Month month,
      BigDecimal amount
) {
}
