package service;

import entity.Installment;
import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import enumerations.InstallmentStatus;
import enumerations.LoanType;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentService {
    void add(Installment installment);

    void update(Installment installment);

    void delete(Installment installment);

    List<Installment> findAll();

    Installment findById(Long id);

    List<Installment> findByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus);

    Long findInstallmentCountByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus);

    double getEducationLoanAmount(Student student);

    double getTuitionLoanAmount(Student student);

    double getHousingLoanAmount(Student student);

    double calculateInstallmentForYear(int year, double baseInstallmentAmount);

    double findTotalAmount(Student student, Loan loan);

    Installment createInstallment(Student student, Loan loan, double paidAmount, LocalDate payedDate);

    int graduationYear(Student student, EducationDegree educationDegree);
}
