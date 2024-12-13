package enit.hamdi.backend_student_app.security;

import enit.hamdi.backend_student_app.entities.Payment;
import enit.hamdi.backend_student_app.entities.PaymentStatus;
import enit.hamdi.backend_student_app.entities.PaymentType;
import enit.hamdi.backend_student_app.entities.Student;
import enit.hamdi.backend_student_app.repository.PaymentRepository;
import enit.hamdi.backend_student_app.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;


    public PaymentService(StudentRepository studentRepository,PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
        this.studentRepository=studentRepository;
    }

    public Payment savePayment( MultipartFile file ,
                               LocalDate date,
                               double amount,
                               PaymentType type, String studentCode) throws IOException {

        Path folderPath= Paths.get(System.getProperty("user.home"),"enit-data","payments");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName= UUID.randomUUID().toString();
        Student student=studentRepository.findByCode(studentCode);
        Path filePath= Paths.get(System.getProperty("user.home"),"enit-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Payment payment=new Payment(
                (int)amount,
                date,
                student,
                PaymentStatus.CREATED,
                type,
                filePath.toUri().toString());
        return paymentRepository.save(payment);

    }
    public Payment updatePaymentStatus(PaymentStatus status, Long id){
        Payment payment =paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public byte[] getPaymentFile(  Long paymentId) throws IOException {
        Payment payment=paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }

}
