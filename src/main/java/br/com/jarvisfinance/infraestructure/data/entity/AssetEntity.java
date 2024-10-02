package br.com.jarvisfinance.infraestructure.data.entity;

import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe que representa todos os Ativos (entrada de dinheiro)
 */
@Entity(name = "Asset")
@Table(name = "asset", uniqueConstraints = @UniqueConstraint(name = "uk_asset", columnNames = {"dat_received", "des_description", "num_amount"}))
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asset")
    Long id;
    @Column(name = "dat_received", nullable = false)
    LocalDate dateReceived;
    @Column(name = "des_description", nullable = false)
    String description;
    @Column(name = "des_label")
    String label;
    @Column(name = "dat_registration", nullable = false)
    @Builder.Default
    LocalDateTime registrationDate = LocalDateTime.now();
    @Column(name = "num_amount", nullable = false, columnDefinition = "DECIMAL(10,2)")
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_asset", nullable = false)
    TypeAsset typeAsset;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_balance")
    BalanceEntity balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetEntity that = (AssetEntity) o;
        return Objects.equal(dateReceived, that.dateReceived) || Objects.equal(description, that.description) || Objects.equal(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dateReceived, description, amount);
    }

    @Override
    public String toString() {
        return "AssetEntity{" + "id=" + id + ", dateReceived=" + dateReceived + ", description='" + description + '\'' + ", label='" + label + '\'' + ", registrationDate=" + registrationDate + ", amount=" + amount + ", typeAsset=" + typeAsset + '}';
    }
}
