package br.com.jarvisfinance.domain.exception;

public class DomainRuleException extends RuntimeException {
    public DomainRuleException(String message) {
        super(message);
    }
}
