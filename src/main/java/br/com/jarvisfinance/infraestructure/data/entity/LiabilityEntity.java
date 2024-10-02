package br.com.jarvisfinance.infraestructure.data.entity;

import br.com.jarvisfinance.infraestructure.data.enums.TypeCard;
import br.com.jarvisfinance.infraestructure.data.enums.TypeLiability;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe que representa todos os Passivos (saida de dinheiro)
 */
@Entity(name = "Liability")
@Table(name = "liability")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class LiabilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_liability")
    Long id;
    @Column(name = "dat_liability", nullable = false)
    LocalDate liabilityDate;
    @Column(name = "des_description", nullable = false)
    String description;
    @Column(name = "des_label")
    String label;
    @Column(name = "dat_registration", nullable = false)
    LocalDateTime registrationDate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(name = "type_liability", nullable = false)
    TypeLiability typeLiability;
    @Column(name = "num_installments")
    Long installments;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_card")
    TypeCard typeCard;
}
