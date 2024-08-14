package util;

import entity.Loan;
import entity.Student;
import entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import repository.CreditCardRepository;
import repository.LoanRepository;
import repository.StudentRepository;
import repository.UniversityRepository;
import repository.impl.CreditCardRepositoryImpl;
import repository.impl.LoanRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import repository.impl.UniversityRepositoryImpl;
import service.CreditCardService;
import service.LoanService;
import service.StudentService;
import service.UniversityService;
import service.impl.CreditCardServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.UniversityServiceImpl;

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
    @Getter
    private final CreditCardRepository creditCardRepository;
    @Getter
    private final StudentService studentService;
    @Getter
    private final LoanService loanService;
    @Getter
    private final UniversityService universityService;
    @Getter
    private final CreditCardService creditCardService;

    private ApplicationContext() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        studentRepository = new StudentRepositoryImpl<>(em);
        loanRepository = new LoanRepositoryImpl<>(em);
        universityRepository = new UniversityRepositoryImpl<>(em);
        creditCardRepository = new CreditCardRepositoryImpl<>(em);

        studentService = new StudentServiceImpl(studentRepository);
        loanService = new LoanServiceImpl(loanRepository);
        universityService = new UniversityServiceImpl(universityRepository);
        creditCardService = new CreditCardServiceImpl(creditCardRepository);
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
