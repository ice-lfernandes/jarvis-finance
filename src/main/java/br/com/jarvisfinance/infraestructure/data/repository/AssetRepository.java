package br.com.jarvisfinance.infraestructure.data.repository;

import br.com.jarvisfinance.infraestructure.data.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

    @Query("SELECT a FROM Asset a WHERE a.typeAsset = 'FIXED'")
    Set<AssetEntity> findByTypeAssetFixed();

}
