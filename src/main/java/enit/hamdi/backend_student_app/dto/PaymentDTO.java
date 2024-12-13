package enit.hamdi.backend_student_app.dto;

import enit.hamdi.backend_student_app.entities.PaymentStatus;
import enit.hamdi.backend_student_app.entities.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;



@Entity
@AllArgsConstructor

@Getter
@Setter
@ToString
public class PaymentDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status = PaymentStatus.CREATED;



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



    public PaymentDTO(){}

    public PaymentDTO(int amount, PaymentType paymentsType, PaymentStatus paymentStatus, LocalDate now) {
        this.amount=(double) amount;
        this.date=now;
        this.status=paymentStatus;
        this.type=paymentsType;
    }

}
