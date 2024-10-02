package br.com.jarvisfinance.infraestructure.data.repository;

import br.com.jarvisfinance.infraestructure.data.entity.BalanceEntity;
import br.com.jarvisfinance.infraestructure.data.enums.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {

    @Query("SELECT b FROM Balance b LEFT JOIN FETCH b.assets WHERE b.year = :year AND b.month = :month")
    Optional<BalanceEntity> findByYearAndMonth(String year, Month month);
}
