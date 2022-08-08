package self.training.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.training.app.model.PaymentDBO;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDBO, Integer> {

    PaymentDBO findByPersonId(Integer personId);

}
