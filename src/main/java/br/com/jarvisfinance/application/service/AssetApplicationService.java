package br.com.jarvisfinance.application.service;

import br.com.jarvisfinance.application.api.data.request.AssetRequest;
import br.com.jarvisfinance.application.api.data.response.AssetResponse;
import br.com.jarvisfinance.application.service.mapper.AssetApplicationMapper;
import br.com.jarvisfinance.domain.service.crud.asset.AssetCrudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AssetApplicationService {

    AssetCrudService service;
    AssetApplicationMapper mapper;

    public AssetResponse findById(Long id) {
        return mapper.toResponse(service.findById(id));
    }

    public Page<AssetResponse> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::toResponse);
    }

    public AssetResponse create(AssetRequest request) {
        var asset = mapper.toModel(request);
        var assetPersisted = service.create(asset);
        return mapper.toResponse(assetPersisted);
    }

    public void update(Long id, AssetRequest request) {
        var asset = mapper.toModel(request);
        service.update(id, asset);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
