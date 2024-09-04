package entity;

import enumerations.Metropolis;
import enumerations.UniversityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @NotBlank
    @Enumerated(EnumType.STRING)
    private UniversityType universityType;

    @Column
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Metropolis metropolis;

    @OneToMany(cascade = CascadeType.ALL)
    @NotNull
    private List<Student> student;
}
