package br.com.jarvisfinance.infraestructure.provider.asset;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.domain.provider.finance.AssetDataProvider;
import br.com.jarvisfinance.infraestructure.data.entity.AssetEntity;
import br.com.jarvisfinance.infraestructure.data.repository.AssetRepository;
import br.com.jarvisfinance.infraestructure.provider.AbstractDataProviderImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AssetDataProviderImpl extends AbstractDataProviderImpl<Asset, AssetEntity> implements AssetDataProvider {

    AssetRepository repository;
    AssetInfraMapper mapper;

    @Override
    protected AssetRepository getRepository() {
        return repository;
    }

    @Override
    protected AssetInfraMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getEntityName() {
        return Asset.class.getSimpleName();
    }
}
