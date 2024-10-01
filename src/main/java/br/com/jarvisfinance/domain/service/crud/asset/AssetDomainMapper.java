package br.com.jarvisfinance.domain.service.crud.asset;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.domain.service.crud.DomainMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetDomainMapper extends DomainMapper<Asset> {
}
