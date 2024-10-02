package br.com.jarvisfinance.application.api.resource;

import br.com.jarvisfinance.application.api.data.response.BalanceResponse;
import br.com.jarvisfinance.application.service.BalanceApplicationService;
import br.com.jarvisfinance.infraestructure.data.enums.Month;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
      name = "Balance API",
      description = "API para Consulta de Saldo"
)
@RestController
@RequestMapping(BalanceResource.BALANCE_ENDPOINT_PATH)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BalanceResource {

    public static final String BALANCE_ENDPOINT_PATH = "/api/balances";

    BalanceApplicationService service;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BalanceResponse> findByYearAndMonth(@RequestParam Long year, @RequestParam Month month) {
        return ResponseEntity.ok(service.findByYearAndMonth(year, month));
    }
}
