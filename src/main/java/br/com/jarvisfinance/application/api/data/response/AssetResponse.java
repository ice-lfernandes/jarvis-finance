package br.com.jarvisfinance.application.api.data.response;

import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AssetResponse(
      Long id,
      LocalDate dateReceived,
      LocalDateTime registrationDate,
      String description,
      String label,
      BigDecimal amount,
      TypeAsset typeAsset
) {
}
