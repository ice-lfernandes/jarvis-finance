package br.com.jarvisfinance.infraestructure.data.provider.asset;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.domain.provider.finance.AssetCrudDataProvider;
import br.com.jarvisfinance.infraestructure.data.entity.AssetEntity;
import br.com.jarvisfinance.infraestructure.data.provider.AbstractCrudDataProviderImpl;
import br.com.jarvisfinance.infraestructure.data.repository.AssetRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AssetCrudDataProviderImpl extends AbstractCrudDataProviderImpl<Asset, AssetEntity> implements AssetCrudDataProvider {

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

    @Override
    public Set<Asset> findByTypeAssetFixed() {
        return repository.findByTypeAssetFixed()
              .stream()
              .map(mapper::toModel)
              .collect(toSet());
    }
}
