package br.com.jarvisfinance.infraestructure.data.provider.balance;

import br.com.jarvisfinance.domain.model.finance.Balance;
import br.com.jarvisfinance.domain.provider.finance.BalanceCrudDataProvider;
import br.com.jarvisfinance.infraestructure.data.entity.BalanceEntity;
import br.com.jarvisfinance.infraestructure.data.enums.Month;
import br.com.jarvisfinance.infraestructure.data.exception.EntityNotFoundException;
import br.com.jarvisfinance.infraestructure.data.provider.AbstractCrudDataProviderImpl;
import br.com.jarvisfinance.infraestructure.data.provider.InfraMapper;
import br.com.jarvisfinance.infraestructure.data.repository.BalanceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BalanceCrudDataProviderImpl extends AbstractCrudDataProviderImpl<Balance, BalanceEntity>
      implements BalanceCrudDataProvider {

    BalanceRepository repository;
    BalanceInfraMapper mapper;

    @Override
    public Balance findByYearAndMonth(String year, Month month) {
        return mapper.toModel(repository.findByYearAndMonth(year, month)
              .orElseThrow(() -> new EntityNotFoundException(String.format("Balance not found by %s and %s", year, month))));
    }

    @Override
    public Optional<Balance> findByYearAndMonthOptional(String year, Month month) {
        return repository.findByYearAndMonth(year, month).map(mapper::toModel);
    }

    @Override
    protected JpaRepository<BalanceEntity, Long> getRepository() {
        return repository;
    }

    @Override
    protected InfraMapper<Balance, BalanceEntity> getMapper() {
        return mapper;
    }

    @Override
    protected String getEntityName() {
        return Balance.class.getSimpleName();
    }
}
