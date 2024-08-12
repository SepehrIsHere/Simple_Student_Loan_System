package service;

import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;

import java.util.List;

public interface LoanService {
    void add(Loan loan);

    void update(Loan loan);

    void delete(Loan loan);

    Loan findById(Long id);

    List<Loan> findAll();

    List<Loan> findByStudent(Student student);

    List<Loan> findByDegree(EducationDegree degree);
}
