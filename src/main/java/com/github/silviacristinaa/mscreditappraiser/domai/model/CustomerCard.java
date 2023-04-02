package com.github.silviacristinaa.mscreditappraiser.domai.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerCard {

    private String name;
    private String cardFlag;
    private BigDecimal limitReleased;
}
