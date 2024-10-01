package br.com.jarvisfinance.application.api.data.response;

import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AssetResponse(
      Long id,
      LocalDate incomeDate,
      LocalDateTime registrationDate,
      BigDecimal amount,
      TypeAsset typeAsset
) {
}
