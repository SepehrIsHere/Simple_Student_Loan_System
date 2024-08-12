package entity;

import enumerations.LoanType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Loan extends BaseEntity {
    @Column
    @NotBlank
    @Size(min = 4, max = 25)
    private Double amount;

    @Column
    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @Column
    @OneToOne
    private Student student;
}
