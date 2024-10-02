package br.com.jarvisfinance.domain.service.balance;

import br.com.jarvisfinance.domain.aspect.annotation.MethodLogExecution;
import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.domain.model.finance.Balance;
import br.com.jarvisfinance.domain.provider.finance.AssetCrudDataProvider;
import br.com.jarvisfinance.domain.provider.finance.BalanceCrudDataProvider;
import br.com.jarvisfinance.infraestructure.data.enums.Month;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class BalanceService {

    BalanceCrudDataProvider provider;
    AssetCrudDataProvider assetProvider;

    @MethodLogExecution
    public void createOrUpdatedBalanceFromNewAsset(@NotNull Asset asset) {
        var year = String.valueOf(asset.getDateReceived().getYear());
        var month = Month.of(asset.getDateReceived().getMonth().getValue());

        // adiciona o ativo variavel aos ativos fixos para montar novo balanço
        var assetsFixed = assetProvider.findByTypeAssetFixed();
        assetsFixed.add(asset);

        var balancePersisted = provider.findByYearAndMonthOptional(year, month).map(balance -> {
            balance.addAsset(assetsFixed);
            return balance;
        }).orElseGet(() -> new Balance(null, year, month, assetsFixed));

        assetProvider.findById(asset.getId()).setBalance(balancePersisted);
        assetProvider.save(asset);
    }

    public Balance findByYearAndMonth(String year, Month month) {
        return provider.findByYearAndMonth(year, month);
    }

}
