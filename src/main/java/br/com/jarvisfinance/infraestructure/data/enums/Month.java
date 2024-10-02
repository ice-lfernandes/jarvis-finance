package br.com.jarvisfinance.infraestructure.data.enums;

import br.com.jarvisfinance.domain.exception.DomainRuleException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Month {
    JANUARY("Janeiro", 0), //
    FEBRUARY("Fevereiro", 1), //
    MARCH("Março", 2),
    APRIL("Abril", 3),
    MAY("Maio", 4),
    JUNE("Junho", 5),
    JULY("Julho", 6),
    AUGUST("Agosto", 7),
    SEPTEMBER("Setembro", 8),
    OCTOBER("Outubro", 9),
    NOVEMBER("Novembro", 10),
    DECEMBER("Dezembro", 11);

    String description;
    Integer monthCalendarValue;

    Month(String description, Integer monthCalendarValue) {
        this.description = description;
        this.monthCalendarValue = monthCalendarValue;
    }

    public static Month of(Integer monthCalendarValue) {
        for (Month month : Month.values()) {
            if (month.getMonthCalendarValue().equals(monthCalendarValue)) {
                return month;
            }
        }
        throw new DomainRuleException("Mês inválido");
    }

    public static Month of(String month) {
        for (Month monthEnum : Month.values()) {
            if (monthEnum.name().equalsIgnoreCase(month)) {
                return monthEnum;
            }
        }
        throw new DomainRuleException("Mês inválido");
    }
}
