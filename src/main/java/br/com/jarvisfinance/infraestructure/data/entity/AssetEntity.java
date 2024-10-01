package br.com.jarvisfinance.infraestructure.data.entity;

import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe que representa todos os Ativos (entrada de dinheiro)
 */
@Entity(name = "asset")
@Table(name = "asset")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "dat_income", nullable = false)
    LocalDate incomeDate;
    @Column(name = "dat_registration", nullable = false)
    LocalDateTime registrationDate = LocalDateTime.now();
    @Column(name = "num_amount", nullable = false)
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_asset", nullable = false)
    TypeAsset typeAsset;
}
