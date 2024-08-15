package entity;

import enumerations.EducationDegree;
import enumerations.EducationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.DefaultValue;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends Person {

    @Column
    @NotBlank
    @Size(min = 1, max = 10)
    private Integer studentId;

    @Column
    @NotBlank
    @Size(min = 4, max = 5)
    private Integer yearOfEnter;

    @Column
    @NotBlank
    @Enumerated(EnumType.STRING)
    private EducationDegree educationDegree;

    @Column
    @NotBlank
    @Enumerated(EnumType.STRING)
    private EducationStatus educationStatus;

    @Column
    @NotBlank
    @DefaultValue("false")
    private boolean usesDormitory;

    @OneToOne(cascade = CascadeType.ALL)
    @Column
    private University university;

    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<Loan> loan;

    public Student(@NotBlank @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed") @Size(min = 3, max = 20) String firstName, @NotBlank @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed") @Size(min = 3, max = 20) String lastName, @NotBlank @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed") @Size(min = 3, max = 20) String fathersName, @NotBlank @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed") @Size(min = 3, max = 20) String mothersName, @NotBlank @Size(min = 3, max = 20) String certificateNumber, @NotBlank Integer nationalCode, @NotBlank @Size(min = 4, max = 7) String birthDate, @NotBlank @Size(min = 4, max = 20) String username, @NotBlank @Size(min = 8, max = 20) String password,  Integer studentId, Integer yearOfEnter, EducationDegree educationDegree, EducationStatus educationStatus, boolean usesDormitory,University university) {
        super(firstName, lastName, fathersName, mothersName, certificateNumber, nationalCode, birthDate, username, password);
        this.studentId = studentId;
        this.yearOfEnter = yearOfEnter;
        this.educationDegree = educationDegree;
        this.educationStatus = educationStatus;
        this.usesDormitory = usesDormitory;
        this.university = university;
    }

}
