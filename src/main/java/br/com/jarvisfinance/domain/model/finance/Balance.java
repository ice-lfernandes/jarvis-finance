package br.com.jarvisfinance.domain.model.finance;

import br.com.jarvisfinance.domain.model.Model;
import br.com.jarvisfinance.domain.model.commons.Amount;
import br.com.jarvisfinance.infraestructure.data.enums.Month;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class Balance extends Model {
    final Long id;
    final String year;
    final Month month;
    Amount amount;
    final Set<Asset> assets;

    public Balance(Long id, @NotNull String year, @NotNull Month month, @NotNull Set<Asset> assets) {
        this.id = id;
        this.year = requireStringValid(year, "Year is required for Balance");
        this.month = month;
        this.assets = assets;
        refreshRelationship();
        updateAmount();
    }

    private void updateAmount() {
        this.amount = new Amount(assets.stream()
              .map(Asset::getAmount)
              .map(Amount::getValue)
              .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public void addAsset(Set<Asset> assets) {
        this.assets.addAll(assets);
        refreshRelationship();
        updateAmount();
    }

    private void refreshRelationship() {
        this.assets.forEach(asset -> asset.setBalance(this));
    }
}
