package br.com.jarvisfinance.domain.event;

import br.com.jarvisfinance.domain.model.finance.Asset;
import org.jetbrains.annotations.NotNull;

public interface AssetChangedEvent {

    void notifyAssetAfterChanged(@NotNull Asset asset);
}
