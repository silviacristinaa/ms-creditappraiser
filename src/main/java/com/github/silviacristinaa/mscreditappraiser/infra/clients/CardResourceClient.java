package com.github.silviacristinaa.mscreditappraiser.infra.clients;

import com.github.silviacristinaa.mscreditappraiser.domai.model.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("cpf") String cpf);

//    @GetMapping(params = "income")
//    ResponseEntity<List<Card>> getCardsByIncome(@RequestParam("income") Long income);



}
