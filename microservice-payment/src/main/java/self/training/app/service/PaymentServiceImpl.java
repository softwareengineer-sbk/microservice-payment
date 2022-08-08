package self.training.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.training.app.dto.Payment;
import self.training.app.model.PaymentDBO;
import self.training.app.repository.PaymentRepository;

import java.util.Optional;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment getById(int id) {
        Optional<PaymentDBO> paymentDBO = paymentRepository.findById(id);
        Payment payment = new Payment();
        if (paymentDBO.isPresent()){
            payment.setAmount(paymentDBO.get().getAmount());
            payment.setRole(paymentDBO.get().getRole());
        }
        return payment;
    }

    @Override
    public Payment getPaymentsWithPersonId(int personId) {
        log.info("Service method: getPaymentsWithPersonId");
        PaymentDBO paymentDBO = paymentRepository.findByPersonId(personId);
        Payment payment = new Payment();
        if (paymentDBO!=null){
            int amount = paymentDBO.getAmount();
            String role = paymentDBO.getRole();
            payment = new Payment(amount, role);
        }
        return payment;
    }
}
