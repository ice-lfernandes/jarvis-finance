package br.com.jarvisfinance.infraestructure.data.provider;

import br.com.jarvisfinance.domain.model.Model;

public interface InfraMapper<M extends Model, E> {

    E toEntity(M model);

    M toModel(E entity);
}

