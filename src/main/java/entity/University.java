package entity;

import enumerations.UniversityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class University extends BaseEntity{
    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Only characters and spaces allowed")
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private UniversityType universityType;  

    @Column
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
