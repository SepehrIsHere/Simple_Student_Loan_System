package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import menu.*;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import java.time.LocalDate;

public class ApplicationContext {
    private static ApplicationContext instance;
    private EntityManagerFactory emf;
    private EntityManager em;
    private LocalDate currentDate;
    @Getter
    private final StudentRepository studentRepository;

    @Getter
    private final LoanRepository loanRepository;

    @Getter
    private final UniversityRepository universityRepository;

    @Getter
    private final CreditCardRepository creditCardRepository;

    @Getter
    private final CouplesRepository couplesRepository;

    @Getter
    private final InstallmentRepository installmentRepository;

    @Getter
    private final StudentService studentService;

    @Getter
    private final LoanService loanService;

    @Getter
    private final UniversityService universityService;

    @Getter
    private final CreditCardService creditCardService;

    @Getter
    private final CouplesService couplesService;

    @Getter
    private final InstallmentService installmentService;

    @Getter
    private final LoginMenu loginMenu;

    @Getter
    private final StudentMenu studentMenu;

    @Getter
    private final LoanMenu loanMenu;

    @Getter
    private final MainMenu mainMenu;

    @Getter
    private final RepaymentMenu repaymentMenu;

    private ApplicationContext() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        studentRepository = new StudentRepositoryImpl<>(em);
        loanRepository = new LoanRepositoryImpl<>(em);
        universityRepository = new UniversityRepositoryImpl<>(em);
        creditCardRepository = new CreditCardRepositoryImpl<>(em);
        couplesRepository = new CouplesRepositoryImpl<>(em);
        installmentRepository = new InstallmentRepositoryImpl<>(em);

        studentService = new StudentServiceImpl(studentRepository);
        loanService = new LoanServiceImpl(loanRepository);
        universityService = new UniversityServiceImpl(universityRepository);
        creditCardService = new CreditCardServiceImpl(creditCardRepository);
        couplesService = new CouplesServiceImpl(couplesRepository);
        installmentService = new InstallmentServiceImpl(installmentRepository);


        loginMenu = new LoginMenu(studentService, universityService);
        repaymentMenu = new RepaymentMenu(loginMenu,loanService,creditCardService,installmentService);
        loanMenu = new LoanMenu(loanService, creditCardService, studentService, couplesService,loginMenu);
        studentMenu = new StudentMenu( creditCardService,  repaymentMenu,  loanMenu,  loginMenu,  loanService);
        mainMenu = new MainMenu(loginMenu, studentMenu);
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
