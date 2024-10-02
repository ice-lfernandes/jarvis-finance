package br.com.jarvisfinance.domain.model.finance;

import br.com.jarvisfinance.domain.model.Model;
import br.com.jarvisfinance.domain.model.commons.Amount;
import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class Asset extends Model {
    final Long id;
    final LocalDate dateReceived;
    final String description;
    final String label;
    final LocalDateTime registrationDate = LocalDateTime.now();
    final Amount amount;
    final TypeAsset typeAsset;
    @Setter
    @ToString.Exclude
    Balance balance;

    public Asset(Long id, @NotNull LocalDate dateReceived, @NotNull String description, String label,
                 @NotNull Amount amount, @NotNull TypeAsset typeAsset) {
        this.id = id;
        this.dateReceived = dateReceived;
        this.description = requireStringValid(description, "Description is required for Asset");
        this.label = label;
        this.amount = amount;
        this.typeAsset = typeAsset;
    }

    public boolean isVariable() {
        return typeAsset.isVariable();
    }

}
