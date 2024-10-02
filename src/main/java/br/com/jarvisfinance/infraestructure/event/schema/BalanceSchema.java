package br.com.jarvisfinance.infraestructure.event.schema;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.infraestructure.data.enums.Month;

import java.math.BigDecimal;
import java.util.Set;

public record BalanceSchema(
      Long id,
      Long year,
      Month month,
      BigDecimal amount,
      Set<Asset> assets
) {
}
