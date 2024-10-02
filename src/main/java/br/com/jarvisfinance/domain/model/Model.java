package br.com.jarvisfinance.domain.model;


import br.com.jarvisfinance.domain.exception.DomainRuleException;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Objects.isNull;

public abstract class Model {

    protected String requireStringValid(String string, String message) {
        if (isNullOrEmpty(string)) {
            throw new DomainRuleException(message);
        }
        return string;
    }

    protected Long requireLongValid(Long longValue, String message) {
        if (isNull(longValue)) {
            throw new DomainRuleException(message);
        }
        return longValue;
    }

}
