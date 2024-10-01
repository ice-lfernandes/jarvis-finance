package br.com.jarvisfinance.domain.model.finance;

import br.com.jarvisfinance.domain.model.Model;
import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class Asset extends Model {
    Long id;
    LocalDate incomeDate;
    final LocalDateTime registrationDate = LocalDateTime.now();
    BigDecimal amount;
    TypeAsset typeAsset;

    public Asset(Long id, LocalDate incomeDate, BigDecimal amount, TypeAsset typeAsset) {
        this.id = id;
        this.incomeDate = incomeDate;
        this.amount = amount;
        this.typeAsset = typeAsset;
    }
}
