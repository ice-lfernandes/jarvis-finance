package br.com.jarvisfinance.domain.service.crud;

import br.com.jarvisfinance.domain.model.Model;
import br.com.jarvisfinance.domain.provider.CrudDataProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractCrudService<M extends Model> {

    protected abstract CrudDataProvider<M> getDataProvider();

    protected abstract DomainMapper<M> getMapper();

    /**
     * Actions to be performed after creating a model
     *
     * @param model
     */
    protected void postActionsCreate(M model) {
    }

    public Page<M> findAll(Pageable pageable) {
        return getDataProvider().findAll(pageable);
    }

    public M findById(Long id) {
        return getDataProvider().findById(id);
    }

    public M create(M model) {
        var entityPersisted = getDataProvider().save(model);
        postActionsCreate(entityPersisted);
        return entityPersisted;
    }

    public void delete(Long id) {
        getDataProvider().delete(id);
    }

    public void update(Long id, M model) {
        var modelPersisted = findById(id);
        getMapper().update(modelPersisted, model);
        getDataProvider().save(modelPersisted);
    }
}
