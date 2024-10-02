package br.com.jarvisfinance.domain.model.commons;

import br.com.jarvisfinance.domain.exception.DomainRuleException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Getter
@ToString
@EqualsAndHashCode
public class Amount {
    BigDecimal value;

    public Amount(BigDecimal value) {
        if (isNull(value) || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainRuleException("Amount must be greater than zero");
        }
        this.value = value;
    }
}
