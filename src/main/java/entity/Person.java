package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import util.PassGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {
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
    @Size(min = 4, max = 20)
    private String username = nationalCode.toString();

    @Column
    @NotBlank
    @Size(min = 8, max = 20)
    private String password = new PassGenerator().generatePassword();

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;
}
