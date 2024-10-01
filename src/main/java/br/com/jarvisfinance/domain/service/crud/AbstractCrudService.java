package br.com.jarvisfinance.domain.service.crud;

import br.com.jarvisfinance.domain.model.Model;
import br.com.jarvisfinance.domain.provider.DataProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractCrudService<M extends Model> {

    protected abstract DataProvider<M> getDataProvider();

    protected abstract DomainMapper<M> getMapper();

    public Page<M> findAll(Pageable pageable) {
        return getDataProvider().findAll(pageable);
    }

    public M findById(Long id) {
        return getDataProvider().findById(id);
    }

    public M create(M model) {
        return getDataProvider().save(model);
    }

    public void update(Long id, M model) {
        var modelPersisted = findById(id);
        getMapper().update(modelPersisted, model);
        getDataProvider().save(modelPersisted);
    }
}
