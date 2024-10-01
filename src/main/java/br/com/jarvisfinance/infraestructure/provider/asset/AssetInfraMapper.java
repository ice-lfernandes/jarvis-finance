package br.com.jarvisfinance.infraestructure.provider.asset;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.infraestructure.data.entity.AssetEntity;
import br.com.jarvisfinance.infraestructure.provider.InfraMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetInfraMapper extends InfraMapper<Asset, AssetEntity> {

    AssetEntity toEntity(Asset model);

    Asset toModel(AssetEntity entity);
}
