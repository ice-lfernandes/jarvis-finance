package br.com.jarvisfinance.application.service.mapper;

import br.com.jarvisfinance.application.api.data.response.BalanceResponse;
import br.com.jarvisfinance.domain.model.finance.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceApplicationMapper {

    @Mapping(source = "amount.value", target = "amount")
    BalanceResponse toResponse(Balance balance);
}
