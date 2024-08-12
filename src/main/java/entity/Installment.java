package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Installment extends BaseEntity{
    @Column
    @NotBlank
    @Size(min = 6,max = 9)
    @DefaultValue("0")
    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Loan loan;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
}
