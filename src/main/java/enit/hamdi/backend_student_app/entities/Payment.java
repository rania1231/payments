package enit.hamdi.backend_student_app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor

@Getter
@Setter
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private double amount;


    private PaymentType type;


    private PaymentStatus status = PaymentStatus.CREATED;

    private String file=" ";

    @ManyToOne
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public  Payment(){}

    public Payment(int amount, PaymentType paymentsType, PaymentStatus paymentStatus, LocalDate now, Student st) {

        this.amount=(double) amount;
        this.date=now;
        this.student=st;
        this.status=paymentStatus;
     this.type=paymentsType;
    }
    public Payment(int amount, LocalDate now, Student st,PaymentStatus paymentStatus, PaymentType paymentsType,  String file) {

        this.amount=(double) amount;
        this.date=now;
        this.student=st;
        this.status=paymentStatus;
        this.type=paymentsType;
        this.file=file;
    }


}
