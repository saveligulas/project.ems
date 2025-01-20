package fhv.team11.project.ems.domain.commons.interfaces;

import java.math.BigDecimal;

public interface ILineItem {
    BigDecimal getPrice();
    Long getId();
    String getName();
    String getDescription();
}
