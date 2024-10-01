package br.com.jarvisfinance.infraestructure.data.repository;

import br.com.jarvisfinance.infraestructure.data.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
}
