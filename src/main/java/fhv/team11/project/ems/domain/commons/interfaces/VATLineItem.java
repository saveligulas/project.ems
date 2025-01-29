package fhv.team11.project.ems.domain.commons.interfaces;

import java.math.BigDecimal;

public class VATLineItem implements ITaxLineItem {
    public static final VATLineItem INSTANCE = new VATLineItem();

    private final String IDENTIFIER;
    private final String NAME;
    private final String DESCRIPTION;
    private final BigDecimal RATE;

    private VATLineItem() {
        this.IDENTIFIER = "VAT_TAX_LINE_ITEM";
        this.NAME = "VAT";
        this.DESCRIPTION = "Value-Added Tax";
        this.RATE = BigDecimal.valueOf(0.20);
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public String getDescription() {
        return this.DESCRIPTION;
    }

    @Override
    public BigDecimal getRate() {
        return this.RATE;
    }
}
