package entity;

import enumerations.InstallmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Installment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "loan_id")
    @NotNull
    private Loan loan;

    @Column
    @NotNull
    private double totalAmount;

    @Column
    @NotNull
    private double paidAmount;

    @Column(name = "installment_number")
    @NotNull
    private Integer installmentNumber;

    @Column
    @NotNull
    private LocalDate payedDate;

    @Column
    @NotNull
    private LocalDate dueDate;

    @Column
    @Enumerated(EnumType.STRING)
    private InstallmentStatus installmentStatus;

}
