package br.com.jarvisfinance.infraestructure.provider;

import br.com.jarvisfinance.domain.model.Model;
import br.com.jarvisfinance.domain.provider.DataProvider;
import br.com.jarvisfinance.infraestructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractDataProviderImpl<M extends Model, E> implements DataProvider<M> {

    protected abstract JpaRepository<E, Long> getRepository();

    protected abstract InfraMapper<M, E> getMapper();

    protected abstract String getEntityName();

    @Override
    public Page<M> findAll(Pageable pageable) {
        return getRepository().findAll(pageable).map(getMapper()::toModel);
    }

    @Override
    public M findById(Long id) {
        return getRepository().findById(id).map(getMapper()::toModel)
              .orElseThrow(() -> new EntityNotFoundException(String.format("%s not found", getEntityName())));
    }

    @Override
    public M save(M m) {
        var entity = getMapper().toEntity(m);
        var entityPersisted = getRepository().save(entity);
        return getMapper().toModel(entityPersisted);
    }
}
