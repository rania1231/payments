package enit.hamdi.backend_student_app.web;

import enit.hamdi.backend_student_app.entities.Payment;
import enit.hamdi.backend_student_app.entities.PaymentStatus;
import enit.hamdi.backend_student_app.entities.PaymentType;
import enit.hamdi.backend_student_app.entities.Student;
import enit.hamdi.backend_student_app.repository.PaymentRepository;
import enit.hamdi.backend_student_app.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
        this.studentRepository=studentRepository;
    }


    @GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentsByStudent(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
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
        return studentRepository.findById(code).get();
    }

    @GetMapping(path="/studentsByProgramId")
    public List<Student> getStudentByProgramId(@RequestHeader String programId){
        return studentRepository.findByProgramId(programId);
    }
}
