package fhv.team11.project.ems.domain.commons.interfaces;

import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

public interface ITaxLineItem {
    String getName();
    String getDescription();
    BigDecimal getRate();

    default BigDecimal getAppliedRateAmount(BigDecimal totalPrice) {
        return getRate().multiply(totalPrice);
    }

    default BigDecimal getAppliedRateAmount(List<ILineItem> iLineItems) {
        return getRate().multiply(
                ILineItem.toList(iLineItems).stream()
                        .map(ILineItem::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
