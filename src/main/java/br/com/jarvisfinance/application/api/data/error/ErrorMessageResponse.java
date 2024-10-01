package br.com.jarvisfinance.application.api.data.error;

import java.util.List;

public record ErrorMessageResponse(List<ErrorMessageVO> errors) {
}
