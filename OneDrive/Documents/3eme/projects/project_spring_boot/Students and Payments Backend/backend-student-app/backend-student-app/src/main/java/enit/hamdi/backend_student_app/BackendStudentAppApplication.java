package enit.hamdi.backend_student_app;

import enit.hamdi.backend_student_app.entities.Payment;
import enit.hamdi.backend_student_app.entities.PaymentStatus;
import enit.hamdi.backend_student_app.entities.PaymentType;
import enit.hamdi.backend_student_app.entities.Student;
import enit.hamdi.backend_student_app.repository.PaymentRepository;
import enit.hamdi.backend_student_app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendStudentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendStudentAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository
										){
		return args -> {
			studentRepository.save(new Student(UUID.randomUUID().toString(),"112233","Mohamed","SDIA"));
			studentRepository.save(new Student(UUID.randomUUID().toString(),"112255","Imen","SDIA"));
			studentRepository.save(new Student(UUID.randomUUID().toString(),"112244","Maissa","GLSID"));
			studentRepository.save(new Student(UUID.randomUUID().toString(),"112266","Rania","BDCC"));

			PaymentType[] paymentsTypes=PaymentType.values();
			Random random=new Random();
			studentRepository.findAll().forEach(st->{
				for(int i=0;i<10;i++){
					int index=random.nextInt(paymentsTypes.length);
					Payment payment= new Payment( 1000+(int)(Math.random()+20000)
							,paymentsTypes[index]
							, PaymentStatus.CREATED
							, LocalDate.now()
							,st
							);
					paymentRepository.save(payment);
				}
			});
		};

	}
}


