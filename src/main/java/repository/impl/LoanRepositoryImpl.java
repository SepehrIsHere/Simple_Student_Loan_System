package repository.impl;

import entity.Loan;
import entity.Student;
import entity.University;
import enumerations.EducationDegree;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.LoanRepository;

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
    public List<Loan> findByDegree(EducationDegree degree) {
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.student.educationDegree = :degree", Loan.class);
        query.setParameter("degree", degree);
        return query.getResultList();
    }
}
