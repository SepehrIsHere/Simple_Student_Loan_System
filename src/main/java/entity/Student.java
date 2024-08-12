package entity;

import enumerations.EducationDegree;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends BaseEntity {
    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed")
    @Size(min = 3, max = 20)
    private String firstName;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed")
    @Size(min = 3, max = 20)
    private String lastName;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed")
    @Size(min = 3, max = 20)
    private String fathersName;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed")
    @Size(min = 3, max = 20)
    private String mothersName;

    @Column
    @NotBlank
    @Size(min = 3, max = 20)
    private String certificateNumber;

    @Column
    @NotBlank
    private Integer nationalCode;

    @Column
    @NotBlank
    @Size(min = 4, max = 7)
    private String birthDate;

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

    @OneToOne(cascade = CascadeType.ALL)
    @Column
    private University university;

    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private List<Loan> loan;
}
