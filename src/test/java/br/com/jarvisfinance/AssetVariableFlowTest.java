package br.com.jarvisfinance;

import br.com.jarvisfinance.application.api.data.request.AssetRequest;
import br.com.jarvisfinance.application.api.resource.AssetResource;
import br.com.jarvisfinance.commons.BaseContext;
import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import br.com.jarvisfinance.infraestructure.data.provider.balance.BalanceCrudDataProviderImpl;
import br.com.jarvisfinance.infraestructure.data.repository.AssetRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AssetVariableFlowTest extends BaseContext {

    private static Long newObjectIdCreated;

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    BalanceCrudDataProviderImpl balanceCrudDataProvider;

    @Test
    @DisplayName("Should create a new asset variable and create new balance")
    @Order(1)
    void shouldCreateANewAssetVariable() throws Exception {
        var localDate = LocalDate.now();

        var assetRequest = new AssetRequest(
              localDate,
              BigDecimal.valueOf(50),
              "PIX",
              null,
              TypeAsset.VARIABLE
        );

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + AssetResource.ASSET_ENDPOINT_PATH))
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(assetRequest)))
              .build();

        HttpResponse<String> httpResponse = httpClient.send(
              httpRequest,
              HttpResponse.BodyHandlers.ofString()
        );


        Assertions.assertEquals(HttpStatus.CREATED.value(), httpResponse.statusCode());
    }
}
