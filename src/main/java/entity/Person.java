package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String username;

    @Column
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @Column
    @NotBlank
    @DefaultValue("false")
    private boolean isEngaged;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;

    public Person(String firstName, String lastName, String fathersName, String mothersName, String certificateNumber, Integer nationalCode, String birthDate, String username, String password,boolean isEngaged) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.certificateNumber = certificateNumber;
        this.nationalCode = nationalCode;
        this.birthDate = birthDate;
        this.username = username;
        this.password = password;
        this.isEngaged = isEngaged;
    }
}
