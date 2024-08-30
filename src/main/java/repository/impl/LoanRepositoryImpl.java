package repository.impl;

import entity.Loan;
import entity.Student;
import enumerations.LoanType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.LoanRepository;

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
    public Loan findStudentLoanByDateAndType(Student student, LocalDate date, LoanType type) {
        TypedQuery<Loan> query = em.createQuery("select l from Loan l  WHERE l.student = :student and l.date = :date and l.loanType = :type", Loan.class);
        query.setParameter("student", student);
        query.setParameter("date", date);
        query.setParameter("type", type);
        return query.getSingleResult();
    }

    @Override
    public List<Loan> findByStudentAndTypeAndYear(Student student, LoanType type, Integer year) {
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l where l.student = :student AND l.loanType = :loanType AND l.year = :year", Loan.class);
        query.setParameter("student", student);
        query.setParameter("loanType", type);
        query.setParameter("year", year);
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
    public Loan findByStudentAndLoanTypeAndYear(Student student, LoanType type, Integer year) {
        TypedQuery<Loan> query = em.createQuery("SELECT l from Loan l where l.student = :student AND l.loanType = :loanType and l.year = :year", Loan.class);
        query.setParameter("student", student);
        query.setParameter("loanType", type);
        query.setParameter("year", year);
        return query.getSingleResult();
    }
}
