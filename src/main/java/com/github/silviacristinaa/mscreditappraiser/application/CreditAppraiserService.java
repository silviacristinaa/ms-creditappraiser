package com.github.silviacristinaa.mscreditappraiser.application;


import com.github.silviacristinaa.mscreditappraiser.application.ex.CommunicationErrorMicroservicesException;
import com.github.silviacristinaa.mscreditappraiser.application.ex.CustomerDataNotFoundException;
import com.github.silviacristinaa.mscreditappraiser.domai.model.CustomerCard;
import com.github.silviacristinaa.mscreditappraiser.domai.model.CustomerData;
import com.github.silviacristinaa.mscreditappraiser.domai.model.CustomerSituation;
import com.github.silviacristinaa.mscreditappraiser.infra.clients.CardResourceClient;
import com.github.silviacristinaa.mscreditappraiser.infra.clients.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customerClient;
    private final CardResourceClient cardClient;

    public CustomerSituation getCustomerSituation(String cpf)
            throws CustomerDataNotFoundException, CommunicationErrorMicroservicesException{
        try {
            ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
            ResponseEntity<List<CustomerCard>> cardsResponse = cardClient.getCardsByCustomer(cpf);

            return CustomerSituation
                    .builder()
                    .customer(customerDataResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new CommunicationErrorMicroservicesException(e.getMessage(), status);
        }
    }
}
