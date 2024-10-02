package br.com.jarvisfinance.application.service.mapper;

import br.com.jarvisfinance.application.api.data.request.AssetRequest;
import br.com.jarvisfinance.application.api.data.response.AssetResponse;
import br.com.jarvisfinance.domain.model.finance.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssetApplicationMapper {

    @Mapping(source = "amount", target = "amount.value")
    Asset toModel(AssetRequest assetRequest);

    @Mapping(source = "amount.value", target = "amount")
    AssetResponse toResponse(Asset asset);
}
