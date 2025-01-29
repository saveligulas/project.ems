package fhv.team11.project.ems.domain.commons.interfaces;

import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ILineItem {
    BigDecimal getPrice();
    Long getId();
    String getName();
    @Nullable String getDescription();
    Integer getCount();
    @Nullable List<ILineItem> getSubLineItems();
    default BigDecimal getTotalPrice() {
        return getPrice().multiply(BigDecimal.valueOf(getCount()));
    }

    static List<ILineItem> toList(List<ILineItem> items) {
        List<ILineItem> flatList = new ArrayList<>();
        for (ILineItem item : items) {
            flatten(item, flatList);
        }
        return flatList;
    }

    static BigDecimal getTotalPriceShallow(List<ILineItem> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ILineItem item : items) {
            totalPrice = totalPrice.add(item.getPrice().multiply(BigDecimal.valueOf(item.getCount())));
        }
        return totalPrice;
    }

    static BigDecimal getTotalPrice(List<ILineItem> items) {
        return getTotalPriceShallow(toList(items));
    }

    private static void flatten(ILineItem item, List<ILineItem> flatList) {
        flatList.add(item);
        if (item.getSubLineItems() != null) {
            for (ILineItem subItem : item.getSubLineItems()) {
                flatten(subItem, flatList);
            }
        }
    }
}
