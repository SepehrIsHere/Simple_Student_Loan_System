package entity;

import enumerations.LoanStatus;
import enumerations.LoanType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    private Term term;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @NotBlank
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
}
