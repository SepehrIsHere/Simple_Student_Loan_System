package service;

import entity.Installment;
import enumerations.InstallmentStatus;
import enumerations.LoanType;

import java.util.List;

public interface InstallmentService {
    void add(Installment installment);

    void update(Installment installment);

    void delete(Installment installment);

    List<Installment> findAll();

    Installment findById(Long id);

    List<Installment> findByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus);

    Long findInstallmentCountByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus);
}
