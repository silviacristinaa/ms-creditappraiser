package com.github.silviacristinaa.mscreditappraiser.infra.clients;

import com.github.silviacristinaa.mscreditappraiser.domai.model.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<CustomerData> customerData(@RequestParam("cpf") String cpf);
}
