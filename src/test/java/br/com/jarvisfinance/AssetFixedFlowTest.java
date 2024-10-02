package br.com.jarvisfinance;

import br.com.jarvisfinance.application.api.data.request.AssetRequest;
import br.com.jarvisfinance.application.api.resource.AssetResource;
import br.com.jarvisfinance.commons.BaseContext;
import br.com.jarvisfinance.infraestructure.data.enums.TypeAsset;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AssetFixedFlowTest extends BaseContext {

    private static Long newObjectIdCreated;

    @Test
    @DisplayName("Should return not found when trying to get asset by id that does not exist")
    void shouldReturnNotFoundWhenTryingToGetAssetByIdThatDoesNotExist() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + AssetResource.ASSET_ENDPOINT_PATH + "/999999"))
              .header("Content-Type", "application/json")
              .GET()
              .build();

        HttpResponse<String> httpResponse = httpClient.send(
              httpRequest,
              HttpResponse.BodyHandlers.ofString()
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), httpResponse.statusCode());
    }

    @Test
    @DisplayName("Should create a new asset fixed")
    @Order(1)
    void shouldCreateANewAssetFixed() throws Exception {
        var assetRequest = new AssetRequest(
              LocalDate.now(),
              BigDecimal.valueOf(1000),
              "salario",
              "salario 1° parcela",
              TypeAsset.FIXED
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

        httpResponse.headers().firstValue("Location").ifPresent(location -> newObjectIdCreated =
              Long.parseLong(location.split("/")[location.split("/").length - 1]));
    }

    @Test
    @DisplayName("Should return the created asset")
    @Order(2)
    void shouldReturnTheCreatedAsset() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + AssetResource.ASSET_ENDPOINT_PATH + "/" + newObjectIdCreated))
              .header("Content-Type", "application/json")
              .GET()
              .build();

        HttpResponse<String> httpResponse = httpClient.send(
              httpRequest,
              HttpResponse.BodyHandlers.ofString()
        );

        Assertions.assertEquals(HttpStatus.OK.value(), httpResponse.statusCode());
    }

    @Test
    @DisplayName("Should return all assets")
    @Order(3)
    void shouldReturnAllAssets() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + AssetResource.ASSET_ENDPOINT_PATH))
              .header("Content-Type", "application/json")
              .GET()
              .build();

        HttpResponse<String> httpResponse = httpClient.send(
              httpRequest,
              HttpResponse.BodyHandlers.ofString()
        );

        Assertions.assertEquals(HttpStatus.OK.value(), httpResponse.statusCode());
    }

    @Test
    @DisplayName("Should update the created asset")
    @Order(4)
    void shouldUpdateTheCreatedAsset() throws Exception {
        var assetRequest = new AssetRequest(
              LocalDate.now(),
              BigDecimal.valueOf(2000),
              "salario",
              "salario 1° parcela",
              TypeAsset.FIXED
        );

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + AssetResource.ASSET_ENDPOINT_PATH + "/" + newObjectIdCreated))
              .header("Content-Type", "application/json")
              .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(assetRequest)))
              .build();

        HttpResponse<String> httpResponse = httpClient.send(
              httpRequest,
              HttpResponse.BodyHandlers.ofString()
        );

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), httpResponse.statusCode());
    }

    @Test
    @DisplayName("Should delete the created asset")
    @Order(5)
    void shouldDeleteTheCreatedAsset() throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + AssetResource.ASSET_ENDPOINT_PATH + "/" + newObjectIdCreated))
              .header("Content-Type", "application/json")
              .DELETE()
              .build();

        HttpResponse<String> httpResponse = httpClient.send(
              httpRequest,
              HttpResponse.BodyHandlers.ofString()
        );

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), httpResponse.statusCode());
    }

    @Test
    @DisplayName("Should throw bad request when creating asset with invalid data")
    void shouldThrowBadRequestWhenCreatingAssetWithInvalidData() throws Exception {
        var assetRequest = new AssetRequest(
              LocalDate.now(),
              BigDecimal.valueOf(-1000),
              "salario",
              "salario 1° parcela",
              TypeAsset.FIXED
        );

        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(baseUrl + AssetResource.ASSET_ENDPOINT_PATH))
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(assetRequest)))
              .build();

        HttpResponse<String> httpResponse = httpClient.send(
              httpRequest,
              HttpResponse.BodyHandlers.ofString()
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), httpResponse.statusCode());
    }

}
