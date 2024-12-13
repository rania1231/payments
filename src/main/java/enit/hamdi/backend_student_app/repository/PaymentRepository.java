package enit.hamdi.backend_student_app.repository;

import enit.hamdi.backend_student_app.entities.Payment;
import enit.hamdi.backend_student_app.entities.PaymentStatus;
import enit.hamdi.backend_student_app.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findByStudentCode(String code);

    List<Payment> findByStatus(PaymentStatus status);

    List<Payment> findByType(PaymentType type);
}