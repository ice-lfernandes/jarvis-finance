package br.com.jarvisfinance.domain.provider.finance;

import br.com.jarvisfinance.domain.model.finance.Balance;
import br.com.jarvisfinance.domain.provider.CrudDataProvider;
import br.com.jarvisfinance.infraestructure.data.enums.Month;

import java.util.Optional;

public interface BalanceCrudDataProvider extends CrudDataProvider<Balance> {

    Balance findByYearAndMonth(String year, Month month);

    Optional<Balance> findByYearAndMonthOptional(String year, Month month);
}
