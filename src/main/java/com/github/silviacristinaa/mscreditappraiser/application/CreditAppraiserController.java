package com.github.silviacristinaa.mscreditappraiser.application;

import com.github.silviacristinaa.mscreditappraiser.application.ex.CommunicationErrorMicroservicesException;
import com.github.silviacristinaa.mscreditappraiser.application.ex.CustomerDataNotFoundException;
import com.github.silviacristinaa.mscreditappraiser.domai.model.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity getCustomerSituation(@RequestParam("cpf") String cpf){
        try {
            CustomerSituation customerSituation = creditAppraiserService.getCustomerSituation(cpf);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CommunicationErrorMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

//    @PostMapping
//    public ResponseEntity performCreditAssessment(@RequestBody CustomerEvaluateDataRequest request)
//            throws NotFoundException, InternalServerErrorException {
//        CustomerEvaluateResponse response = creditAppraiserService.performCreditAssessment(request.getCpf(),
//                request.getIncome());
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("request-cards")
//    public ResponseEntity requestCard(@RequestBody CardRequestData data) throws InternalServerErrorException {
//        CardRequestProtocolResponse response = creditAppraiserService.requestCardIssuance(data);
//        return ResponseEntity.ok(response);
//    }
}
