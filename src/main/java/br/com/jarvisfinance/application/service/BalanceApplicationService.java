package br.com.jarvisfinance.application.service;

import br.com.jarvisfinance.application.api.data.response.BalanceResponse;
import br.com.jarvisfinance.application.service.mapper.BalanceApplicationMapper;
import br.com.jarvisfinance.domain.service.balance.BalanceService;
import br.com.jarvisfinance.infraestructure.data.enums.Month;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BalanceApplicationService {

    BalanceService balanceService;
    BalanceApplicationMapper mapper;

    public BalanceResponse findByYearAndMonth(Long year, Month month) {
        return mapper.toResponse(balanceService.findByYearAndMonth(String.valueOf(year), month));
    }
}
