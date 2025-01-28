package fhv.team11.project.ems.domain.commons.interfaces;

import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

public interface ILineItem {
    BigDecimal getPrice();
    Long getId();
    String getName();
    @Nullable String getDescription();
    Integer getCount();
    @Nullable List<ILineItem> getSubLineItems();
}
