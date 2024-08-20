package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only letters and spaces")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String firstName;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only letters and spaces")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String lastName;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only letters and spaces")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String fathersName;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only letters and spaces")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String mothersName;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only letters and spaces")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String certificateNumber;

    @Column
    @NotNull
    @Digits(integer = 10,fraction = 0,message = "National Code must be at max 6 number")
    private Integer nationalCode;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only letters and spaces")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String birthDate;

    @Column
    @NotBlank
    @NotNull
//    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Must contain only letters and spaces")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
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
