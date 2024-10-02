package br.com.jarvisfinance.domain.provider;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudDataProvider<M> {
    Page<M> findAll(Pageable pageable);

    M findById(Long id);

    M save(M model);

    void delete(Long id);
}
