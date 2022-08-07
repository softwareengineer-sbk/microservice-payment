package self.training.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.training.app.configuration.ServerConfiguration;
import self.training.app.dto.Payment;
import self.training.app.dto.Properties;
import self.training.app.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final ServerConfiguration serverConfiguration;

    public PaymentController(PaymentService paymentService, ServerConfiguration serverConfiguration){
        this.paymentService = paymentService;
        this.serverConfiguration = serverConfiguration;
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable int id){
        return paymentService.getById(id);
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(serverConfiguration.getMsg(), serverConfiguration.getBuildVersion(), serverConfiguration.getMailDetails());
        return ow.writeValueAsString(properties);
    }
}
