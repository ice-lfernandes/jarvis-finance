package br.com.jarvisfinance.domain.service.crud;

import br.com.jarvisfinance.domain.model.Model;
import org.mapstruct.MappingTarget;


public interface DomainMapper<M extends Model> {

    void update(@MappingTarget M target, M source);
}
