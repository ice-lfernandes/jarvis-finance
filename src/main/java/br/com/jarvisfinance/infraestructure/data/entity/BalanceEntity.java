package br.com.jarvisfinance.infraestructure.data.entity;

import br.com.jarvisfinance.infraestructure.data.enums.Month;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Classe que representa todos os Ativos (entrada de dinheiro)
 */
@Entity(name = "Balance")
@Table(name = "balance")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_balance")
    Long id;
    @Column(name = "year_reference", nullable = false)
    String year;
    @Enumerated(EnumType.STRING)
    @Column(name = "des_month", nullable = false)
    Month month;
    @Column(name = "num_balance", nullable = false, columnDefinition = "DECIMAL(10,2)")
    BigDecimal amount;
    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    Set<AssetEntity> assets;
}
