package br.com.jarvisfinance.domain.service.crud.asset;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.domain.provider.DataProvider;
import br.com.jarvisfinance.domain.provider.finance.AssetDataProvider;
import br.com.jarvisfinance.domain.service.crud.AbstractCrudService;
import br.com.jarvisfinance.domain.service.crud.DomainMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AssetCrudService extends AbstractCrudService<Asset> {

    AssetDataProvider provider;
    AssetDomainMapper mapper;

    @Override
    protected DataProvider<Asset> getDataProvider() {
        return provider;
    }

    @Override
    protected DomainMapper<Asset> getMapper() {
        return mapper;
    }
}
