package br.com.jarvisfinance.application.api.documentation;

import br.com.jarvisfinance.application.api.data.request.AssetRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Operation(
      summary = "Cria novo ativo",
      description = "Cria novo ativo. Retorna CREATED (201) (sucesso)",
      responses = {
            @ApiResponse(
                  responseCode = "201",
                  description = "Created",
                  headers = @Header(
                        name = "Location",
                        description = "URI para acessar a avaliação criada",
                        schema = @Schema(type = "string")
                  )
            ),
            @ApiResponse(
                  responseCode = "400",
                  description = "Bad Request"
            ),
            @ApiResponse(
                  responseCode = "500",
                  description = "Internal Server Error"
            )
      },
      requestBody = @RequestBody(
            content = @Content(
                  schema = @Schema(implementation = AssetRequest.class),
                  examples = {
                        @ExampleObject(
                              name = "Valid Create Asset Type Fixed Request",
                              value = CreateAssetDoc.VALID_ASSET_TYPE_FIXED_REQUEST
                        ),
                        @ExampleObject(
                              name = "Valid Create Asset ype Variable Request",
                              value = CreateAssetDoc.VALID_ASSET_TYPE_VARIABLE_REQUEST
                        )
                  }
            )
      )
)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CreateAssetDoc {
    String VALID_ASSET_TYPE_FIXED_REQUEST = """
          {
               "dateReceived": "2024-10-01",
               "amount" : 1000.00,
               "description": "Salário",
               "label": "Salário 1° parcela",
               "typeAsset": "FIXED"
           }""";

    String VALID_ASSET_TYPE_VARIABLE_REQUEST = """
          {
               "dateReceived": "2024-10-01",
               "amount" : 50,
               "description": "PIX",
               "typeAsset": "VARIABLE"
           }""";
}
