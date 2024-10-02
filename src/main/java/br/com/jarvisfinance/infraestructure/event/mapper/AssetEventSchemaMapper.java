package br.com.jarvisfinance.infraestructure.event.mapper;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.infraestructure.event.schema.AssetSchema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetEventSchemaMapper {

    @Mapping(source = "amount.value", target = "amount")
    @Mapping(source = "balance.amount.value", target = "balance.amount")
    AssetSchema toEventSchema(Asset asset);

    @Mapping(source = "amount", target = "amount.value")
    Asset toModel(AssetSchema assetSchema);
}
