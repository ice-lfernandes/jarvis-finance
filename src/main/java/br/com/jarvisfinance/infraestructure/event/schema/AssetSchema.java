package br.com.jarvisfinance.infraestructure.event.schema;

import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AssetSchema(Long id, LocalDate dateReceived, String description, String label,
                          LocalDateTime registrationDate, BigDecimal amount, TypeAsset typeAsset,
                          BalanceSchema balance) {
}
