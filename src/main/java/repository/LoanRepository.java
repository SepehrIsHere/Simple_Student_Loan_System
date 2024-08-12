package repository;

import entity.Loan;
import entity.Student;
import entity.University;
import enumerations.EducationDegree;

import java.util.List;

public interface LoanRepository extends BaseEntityRepository<Loan> {
    Loan findById(Long id);

    List<Loan> findAll();

    List<Loan> findByStudent(Student student);

    List<Loan> findByDegree(EducationDegree degree);
}
