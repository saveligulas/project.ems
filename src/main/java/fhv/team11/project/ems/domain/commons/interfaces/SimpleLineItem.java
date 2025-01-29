package fhv.team11.project.ems.domain.commons.interfaces;

import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

public class SimpleLineItem implements ILineItem{

    private BigDecimal price;
    private Long id;
    private String name;
    private String description;
    private Integer count;
    private List<ILineItem> subLineItems;

    public SimpleLineItem(BigDecimal price, Long id, String name, String description, Integer count, List<ILineItem> subLineItems) {
        this.price = price;
        this.id = id;
        this.name = name;
        this.description = description;
        this.count = count;
        this.subLineItems = subLineItems;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public @Nullable String getDescription() {
        return description;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public @Nullable List<ILineItem> getSubLineItems() {
        return subLineItems;
    }
}
