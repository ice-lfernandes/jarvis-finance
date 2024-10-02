package br.com.jarvisfinance.infraestructure.data.provider.balance;

import br.com.jarvisfinance.domain.model.finance.Balance;
import br.com.jarvisfinance.infraestructure.data.entity.BalanceEntity;
import br.com.jarvisfinance.infraestructure.data.provider.InfraMapper;
import br.com.jarvisfinance.infraestructure.data.provider.asset.AssetInfraMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AssetInfraMapper.class})
public interface BalanceInfraMapper extends InfraMapper<Balance, BalanceEntity> {

    @Mapping(source = "amount.value", target = "amount")
    BalanceEntity toEntity(Balance model);

    Balance toModel(BalanceEntity entity);
}
