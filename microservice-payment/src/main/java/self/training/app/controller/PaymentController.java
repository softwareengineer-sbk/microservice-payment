package self.training.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import self.training.app.configuration.ServerConfiguration;
import self.training.app.dto.Payment;
import self.training.app.dto.Properties;
import self.training.app.service.PaymentService;

import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final ServerConfiguration serverConfiguration;

    public PaymentController(PaymentService paymentService, ServerConfiguration serverConfiguration){
        this.paymentService = paymentService;
        this.serverConfiguration = serverConfiguration;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Payment getById(@PathVariable int id){
        return paymentService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Payment getPaymentsWithPersonId(@RequestBody int personId){
        log.info("Controller method: getPaymentsWithPersonId");
        return paymentService.getPaymentsWithPersonId(personId);
    }

    @GetMapping(value = "/properties", produces = MediaType.APPLICATION_JSON)
    public String getPropertyDetails() throws JsonProcessingException{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(serverConfiguration.getMsg(), serverConfiguration.getBuildVersion(), serverConfiguration.getMailDetails());
        return ow.writeValueAsString(properties);
    }
}
