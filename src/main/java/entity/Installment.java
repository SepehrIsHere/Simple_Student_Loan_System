package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Installment extends BaseEntity{
    @Column
    @NotNull
    @Size(min = 6,max = 9)
    @DefaultValue("0")
    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Loan loan;

    @ManyToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;

    @Column
    @NotNull
    @DefaultValue("false")
    private boolean paymentStatus;

    @Column
    @NotNull
    private LocalDate paymentDate;
}
