package br.com.jarvisfinance.domain.provider;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DataProvider<Model> {
    Page<Model> findAll(Pageable pageable);

    Model findById(Long id);

    Model save(Model model);
}
