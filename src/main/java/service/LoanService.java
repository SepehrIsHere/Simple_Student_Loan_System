package service;

import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import enumerations.LoanType;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public interface LoanService {
    void add(Loan loan);

    void update(Loan loan);

    void delete(Loan loan);

    Loan findById(Long id);

    List<Loan> findAll();

    List<Loan> findByStudent(Student student);

    List<Loan> findByStudentAndLoanType(Student student, LoanType type);

    Long countLoanByStudentAndMonthAndYearAndType(Student student, Integer month, Integer year, LoanType loanType);

    Long countHousingLoanByStudentAndEducationDegree(Student student, EducationDegree educationDegree, LoanType loanType);

    Loan displayAndChooseLoan(Scanner input, Student student, List<Loan> loans, LoanType loanType);

}
