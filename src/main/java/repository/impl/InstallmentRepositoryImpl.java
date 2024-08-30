package repository.impl;

import entity.Installment;
import enumerations.InstallmentStatus;
import enumerations.LoanType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.InstallmentRepository;

import java.util.List;

public class InstallmentRepositoryImpl<T extends Installment> extends BaseEntityRepositoryImpl<Installment> implements InstallmentRepository {
    private final EntityManager em;

    public InstallmentRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public List<Installment> findAll() {
        TypedQuery<Installment> query = em.createQuery("SELECT i FROM Installment i", Installment.class);
        return query.getResultList();
    }

    @Override
    public Installment findById(Long id) {
        return em.find(Installment.class, id);
    }

    @Override
    public List<Installment> findByLoanTypeAndPaymentStatus(LoanType loanType,InstallmentStatus installmentStatus) {
        TypedQuery<Installment> query = em.createQuery("SELECT i FROM Installment i WHERE i.installmentStatus = :PAYED AND i.loan.loanType = :loanType", Installment.class);
        query.setParameter("PAYED", installmentStatus);
        query.setParameter("loanType", loanType);
        return query.getResultList();
    }

    @Override
    public Long findInstallmentCountByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT (i) FROM Installment i where i.loan.loanType = :loanType AND i.installmentStatus = :PAYED",Long.class);
        query.setParameter("PAYED", installmentStatus);
        query.setParameter("loanType", loanType);
        return query.getSingleResult();
    }
}
