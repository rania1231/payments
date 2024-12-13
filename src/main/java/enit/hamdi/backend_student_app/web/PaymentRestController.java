package enit.hamdi.backend_student_app.web;

import enit.hamdi.backend_student_app.entities.Payment;
import enit.hamdi.backend_student_app.entities.PaymentStatus;
import enit.hamdi.backend_student_app.entities.PaymentType;
import enit.hamdi.backend_student_app.entities.Student;
import enit.hamdi.backend_student_app.repository.PaymentRepository;
import enit.hamdi.backend_student_app.repository.StudentRepository;

import enit.hamdi.backend_student_app.security.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;




@RestController
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
        this.studentRepository=studentRepository;
    }


    @GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }



    @GetMapping(path = "/payments/byType")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/byStatus")
    public List<Payment> paymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }

    @GetMapping(path="/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }
    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path="/studentsByProgramId")
    public List<Student> getStudentByProgramId(@RequestHeader String programId){
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        return this.paymentService.updatePaymentStatus(status,id);
    }


    @PostMapping(path="/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file ,
                               LocalDate date,
                               double amount,
                               PaymentType type,String studentCode) throws IOException {

        return this.paymentService.savePayment(file,date,amount,type,studentCode);

    }

    @GetMapping(path="/paymentFile/{paymentId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable  Long paymentId) throws IOException {
       return paymentService.getPaymentFile(paymentId);
    }


}
