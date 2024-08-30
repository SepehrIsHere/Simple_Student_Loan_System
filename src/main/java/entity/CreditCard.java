package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Entity
@Table(name = CreditCard.TABLE_NAME)
public class CreditCard extends BaseEntity {
    public static final String TABLE_NAME = "credit_card";

    @Column
    @NotBlank
    @DefaultValue("0")
    @Size(min = 1, max = 15)
    private Double balance;

    @Column
    @NotBlank
    @DefaultValue("000")
    @Size(min = 3, max = 4)
    private Integer cvv2;

    @Column
    @NotBlank
    @Size(min = 12, max = 16)
    @DefaultValue("000000000000")
    private String cardNumber;

    @Column
    @NotBlank
    private String expirationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
}
