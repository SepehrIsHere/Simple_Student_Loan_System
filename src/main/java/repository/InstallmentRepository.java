package repository;

import entity.Installment;
import enumerations.InstallmentStatus;
import enumerations.LoanType;

import java.util.List;

public interface InstallmentRepository extends BaseEntityRepository<Installment> {
    List<Installment> findAll();

    Installment findById(Long id);

    List<Installment> findByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus);

    Long findInstallmentCountByLoanTypeAndPaymentStatus(LoanType loanType,InstallmentStatus installmentStatus);
}
