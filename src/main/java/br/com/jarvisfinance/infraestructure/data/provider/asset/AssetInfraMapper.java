package br.com.jarvisfinance.infraestructure.data.provider.asset;

import br.com.jarvisfinance.domain.model.finance.Asset;
import br.com.jarvisfinance.domain.model.finance.Balance;
import br.com.jarvisfinance.infraestructure.data.entity.AssetEntity;
import br.com.jarvisfinance.infraestructure.data.entity.BalanceEntity;
import br.com.jarvisfinance.infraestructure.data.provider.InfraMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface AssetInfraMapper extends InfraMapper<Asset, AssetEntity> {

    @Mapping(source = "amount.value", target = "amount")
    @Mapping(source = "balance", target = "balance", qualifiedByName = "convertToBalance")
    AssetEntity toEntity(Asset model);

    @Mapping(source = "amount", target = "amount.value")
    @Mapping(target = "balance", ignore = true)
    Asset toModel(AssetEntity entity);

    @Named("convertToBalance")
    default BalanceEntity convertToBalance(Balance balance) {
        if (isNull(balance)) {
            return null;
        }
        var balanceEntity = new BalanceEntity(balance.getId(), balance.getYear(), balance.getMonth(),
              balance.getAmount().getValue(), Collections.emptySet());
        balanceEntity.setAssets(balance.getAssets()
              .stream()
              .map(asset -> {
                  var assetEntity = new AssetEntity();
                  assetEntity.setId(asset.getId());
                  assetEntity.setBalance(balanceEntity);
                  assetEntity.setTypeAsset(asset.getTypeAsset());
                  assetEntity.setAmount(asset.getAmount().getValue());
                  assetEntity.setDateReceived(asset.getDateReceived());
                  assetEntity.setDescription(asset.getDescription());
                  assetEntity.setLabel(asset.getLabel());
                  assetEntity.setRegistrationDate(asset.getRegistrationDate());
                  return assetEntity;
              })
              .collect(Collectors.toSet()));
        return balanceEntity;
    }
}
