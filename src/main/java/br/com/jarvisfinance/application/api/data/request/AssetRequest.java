package br.com.jarvisfinance.application.api.data.request;

import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssetRequest(
      @NotNull
      LocalDate incomeDate,
      @NotNull
      @Min(1)
      BigDecimal amount,
      @NotNull
      TypeAsset typeAsset
) {
}
