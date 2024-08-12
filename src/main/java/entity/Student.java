package entity;

import enumerations.EducationDegree;
import enumerations.EducationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
}
