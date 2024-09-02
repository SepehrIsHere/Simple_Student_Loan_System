package repository;

import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import enumerations.LoanType;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends BaseEntityRepository<Loan> {
    Loan findById(Long id);

    List<Loan> findAll();

    List<Loan> findByStudent(Student student);

    List<Loan> findByStudentAndLoanType(Student student, LoanType type);

    Loan findByStudentMonthYearAndType(Student student, LoanType type, Integer month, Integer year);

    Loan findByStudentAndDegreeAndLoanType(Student student, EducationDegree educationDegree,LoanType loanType);

    Loan findByStudentAndLoanNumberAndType(Student student, Integer loanNumber, LoanType loanType);

    Long countLoanByStudentAndMonthAndYearAndType(Student student, Integer month, Integer year, LoanType loanType);

    Long countHousingLoanByStudentAndEducationDegree(Student student, EducationDegree educationDegree,LoanType loanType);
}
