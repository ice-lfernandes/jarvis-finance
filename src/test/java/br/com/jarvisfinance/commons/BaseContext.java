package br.com.jarvisfinance.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.test.context.ActiveProfiles;

import java.net.http.HttpClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseContext {

    @BeforeAll
    static void beforeAll() {
        System.setProperty("spring.profiles.active", "test");
    }

    protected HttpClient httpClient = HttpClient.newBuilder().build();
    protected String baseUrl = "http://localhost:8080";
    protected ObjectMapper objectMapper = new ObjectMapper()
          .findAndRegisterModules()
          .registerModule(new JavaTimeModule())
          .registerModule(new PageJacksonModule())
          .registerModule(new SortJacksonModule());
}
