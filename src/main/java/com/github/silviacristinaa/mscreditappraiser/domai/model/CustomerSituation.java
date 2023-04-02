package com.github.silviacristinaa.mscreditappraiser.domai.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSituation {

    private CustomerData customer;
    private List<CustomerCard> cards;
}
