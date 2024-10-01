package br.com.jarvisfinance.application.service.mapper;

import br.com.jarvisfinance.application.api.data.request.AssetRequest;
import br.com.jarvisfinance.application.api.data.response.AssetResponse;
import br.com.jarvisfinance.domain.model.finance.Asset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetApplicationMapper {
    Asset toModel(AssetRequest assetRequest);

    AssetResponse toResponse(Asset asset);
}
