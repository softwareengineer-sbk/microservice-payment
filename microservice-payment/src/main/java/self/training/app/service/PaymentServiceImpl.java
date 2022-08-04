package self.training.app.service;

import org.springframework.stereotype.Service;
import self.training.app.dto.Payment;
import self.training.app.model.PaymentDBO;
import self.training.app.repository.PaymentRepository;

import java.util.Optional;

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
}
