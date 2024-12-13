package enit.hamdi.backend_student_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity

@AllArgsConstructor


@ToString
public class Student {
    @Id
    private  String id;
    @Column(unique=true)
    private String code;
    private String firstName;
    private  String lastName;
    private String programId;
    private String photo="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Student(){}

    public Student (String id, String code, String firstName, String programId ) {

        this.id = id;
         this.code=code;
        this.firstName=firstName;
        this.lastName=lastName;
        this.programId=programId;


    }


}
