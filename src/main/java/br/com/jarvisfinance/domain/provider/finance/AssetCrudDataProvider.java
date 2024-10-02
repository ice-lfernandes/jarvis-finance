package br.com.jarvisfinance.domain.provider.finance;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.domain.provider.CrudDataProvider;

import java.util.Set;

public interface AssetCrudDataProvider extends CrudDataProvider<Asset> {

    Set<Asset> findByTypeAssetFixed();
}
