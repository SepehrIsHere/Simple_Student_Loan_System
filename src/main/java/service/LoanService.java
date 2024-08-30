package service;

import entity.Loan;
import entity.Student;
import enumerations.LoanType;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {
    void add(Loan loan);

    void update(Loan loan);

    void delete(Loan loan);

    Loan findById(Long id);

    List<Loan> findAll();

    List<Loan> findByStudent(Student student);

    List<Loan> findByStudentAndLoanType(Student student, LoanType type);

    List<Loan> findByStudentAndTypeAndYear(Student student, LoanType type, Integer year);

    Loan findByStudentMonthYearAndType(Student student, LoanType type, Integer month, Integer year);

    Loan findByStudentAndLoanTypeAndYear(Student student, LoanType type, Integer year);

}
