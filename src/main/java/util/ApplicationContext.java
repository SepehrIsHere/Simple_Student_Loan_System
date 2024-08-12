package util;

import entity.Loan;
import entity.Student;
import entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import repository.LoanRepository;
import repository.StudentRepository;
import repository.UniversityRepository;
import repository.impl.LoanRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import repository.impl.UniversityRepositoryImpl;

public class ApplicationContext {
    private static ApplicationContext instance;
    private EntityManagerFactory emf;
    private EntityManager em;
    @Getter
    private final StudentRepository studentRepository;
    @Getter
    private final LoanRepository loanRepository;
    @Getter
    private final UniversityRepository universityRepository;

    private ApplicationContext() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        studentRepository = new StudentRepositoryImpl<Student>(em);
        loanRepository = new LoanRepositoryImpl<Loan>(em);
        universityRepository = new UniversityRepositoryImpl<University>(em);
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("default");
        }
        return emf;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = getEntityManagerFactory().createEntityManager();
        }
        return em;
    }
}
