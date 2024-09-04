package repository.impl;

import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import enumerations.LoanType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.LoanRepository;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class LoanRepositoryImpl<T extends Loan> extends BaseEntityRepositoryImpl<Loan> implements LoanRepository {
    private final EntityManager em;

    public LoanRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public Loan findById(Long id) {
        return em.find(Loan.class, id);
    }

    @Override
    public List<Loan> findAll() {
        TypedQuery<Loan> query = em.createQuery("FROM Loan", Loan.class);
        return query.getResultList();
    }

    @Override
    public List<Loan> findByStudent(Student student) {
        TypedQuery<Loan> query = em.createQuery("FROM Loan l WHERE l.student = :student", Loan.class);
        query.setParameter("student", student);
        return query.getResultList();
    }


    @Override
    public List<Loan> findByStudentAndLoanType(Student student, LoanType type) {
        TypedQuery<Loan> query = em.createQuery("from Loan l WHERE l.student = :student and l.loanType = :type", Loan.class);
        query.setParameter("student", student);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public Loan findByStudentMonthYearAndType(Student student, LoanType type, Integer month, Integer year) {
        TypedQuery<Loan> query = em.createQuery("SELECT l from Loan l where l.student = :student and l.loanType = :loanType and l.year = :year and l.month = :month", Loan.class);
        query.setParameter("student", student);
        query.setParameter("loanType", type);
        query.setParameter("month", month);
        query.setParameter("year", year);
        return query.getSingleResult();
    }

    @Override
    public Loan findByStudentAndDegreeAndLoanType(Student student, EducationDegree educationDegree, LoanType loanType) {
        TypedQuery<Loan> query = em.createQuery("SELECT l from Loan l WHERE l.student = :student AND l.student.educationDegree = :educationDegree AND l.loanType = :loanType ", Loan.class);
        query.setParameter("student", student);
        query.setParameter("educationDegree", educationDegree);
        query.setParameter("loanType", loanType);
        return query.getSingleResult();
    }

    @Override
    public Loan findByStudentAndLoanNumberAndType(Student student, Integer loanNumber, LoanType loanType) {
        TypedQuery<Loan> query = em.createQuery("SELECT l from Loan l where l.student = :student AND l.loanNumber = :loanNumber AND l.loanType = :loanType", Loan.class);
        query.setParameter("student", student);
        query.setParameter("loanNumber", loanNumber);
        query.setParameter("loanType", loanType);
        return query.getSingleResult();
    }

    @Override
    public Long countLoanByStudentAndMonthAndYearAndType(Student student, Integer month, Integer year, LoanType loanType) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(l) from Loan l where l.student = :student AND l.year = :year AND l.month = :month AND l.loanType = :loanType", Long.class);
        query.setParameter("student", student);
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("loanType", loanType);
        return query.getSingleResult();
    }

    @Override
    public Long countHousingLoanByStudentAndEducationDegree(Student student, EducationDegree educationDegree, LoanType loanType) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(l) FROM Loan l where l.student = :student AND l.student.educationDegree = :educationDegree AND l.loanType = :loanType", Long.class);
        query.setParameter("student", student);
        query.setParameter("educationDegree", educationDegree);
        query.setParameter("loanType", loanType);
        return query.getSingleResult();
    }
}
