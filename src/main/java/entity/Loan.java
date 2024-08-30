package entity;

import enumerations.LoanStatus;
import enumerations.LoanType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


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
    @Column
    private LocalDate date;

    @NotNull
    @Column
    private Integer year;

    @NotNull
    @Column
    private Integer month;

    @Column
    @NotNull
    private String loanNumber;

    @NotBlank
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
}
