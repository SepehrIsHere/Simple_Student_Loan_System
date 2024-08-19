package menu;

import entity.Loan;
import entity.Student;
import entity.Term;
import enumerations.LoanType;
import enumerations.TermSeason;
import service.LoanService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

public class LoanMenu {
    private LocalDate currentDate;
    private final LoanService loanService;
    private final int currentYear = LocalDate.now().getYear();

    public LoanMenu(LocalDate currentDate, LoanService loanService) {
        this.currentDate = currentDate;
        this.loanService = loanService;
    }

    public void showMenu(Student token) {
        Scanner input = new Scanner(System.in);
        boolean condition = true;
        while (condition) {
            System.out.println("""
                    Sign for Loan 
                    1.Tuition Loan
                    2.Education Loan
                    3.Housing Loan
                    """);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> takeTuitionLoan(input, token);
                case 2 -> takeEducationLoan(input, token);
                case 3 -> takeHousingLoan(input, token);
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void takeTuitionLoan(Scanner input, Student token) {
    }

    private void takeEducationLoan(Scanner input, Student token) {
        int currentYear = currentDate.getYear();//2024
        LoanType loanType = LoanType.EDUCATION;

        //Is it right time to sign ?
        if (!(inAbanRange() || inBahmanRange())) {
            System.out.println("Time for signing for loans is not here yet");
            return;
        }
        List<Loan> educationLoans = loanService.findByStudentAndLoanType(token, loanType);
        //Has he ever taken a loan ?
        if (educationLoans == null || educationLoans.isEmpty()) {
            Term term = new Term();
            term.setYear(currentYear);
            term.setTermSeason(getCurrentTermSeason(currentDate));

            Loan loan = createEducationLoan(token, loanType, term);
            loanService.add(loan);
            //if he has taken a loan can he take it again ?
        } else if (educationLoans != null || !educationLoans.isEmpty()) {
            Loan loan = loanService.findStudentLoanByTermYearAndType(token, getCurrentTermSeason(currentDate), currentYear, loanType);
            if (loan != null) {
                System.out.println("you already have a loan for that ! ");
            } else {
                Term term = new Term();
                term.setYear(currentYear);
                term.setTermSeason(getCurrentTermSeason(currentDate));

                Loan loan1 = createEducationLoan(token, loanType, term);
                loanService.add(loan1);
            }
        }


    }

    private void takeHousingLoan(Scanner input, Student token) {

    }

    private boolean canTakeHousingLoan() {
        return false;
    }

    private void switchDate(LocalDate newDate) {
        this.currentDate = newDate;
    }

    private boolean inAbanRange() {
        return !currentDate.isBefore(LocalDate.of(currentYear, Month.NOVEMBER, 21)) &&
                !currentDate.isAfter(LocalDate.of(currentYear, Month.NOVEMBER, 29));
    }


    private boolean inBahmanRange() {
        return !currentDate.isBefore(LocalDate.of(currentYear, Month.FEBRUARY, 13)) &&
                !currentDate.isAfter(LocalDate.of(currentYear, Month.FEBRUARY, 21));
    }


    private Double getLoanAmount(Student token) {
        Double loanAmount = 0.0;
        switch (token.getEducationDegree()) {
            case ASSOCIATE, BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> loanAmount = 1300000.0;
            case MASTER_PEYVASTE, MASTER_NAPEYVASTE, PHD_PEYVASTER -> loanAmount = 2600000.0;
            case PHD_NAPEYVASTE -> loanAmount = 65000000.0;
            default -> loanAmount = 0.0;
        }
        return loanAmount;
    }

    private TermSeason getCurrentTermSeason(LocalDate currentDate) {
        if (inAbanRange()) {
            return TermSeason.AUTUMN;
        } else if (inBahmanRange()) {
            return TermSeason.WINTER;
        } else {
            return TermSeason.SUMMER;
        }
    }

    private Loan createEducationLoan(Student student, LoanType loanType, Term term) {
        Loan loan = new Loan();
        loan.setLoanType(loanType);
        loan.setStudent(student);
        loan.setTerm(term);
        loan.setAmount(getLoanAmount(student));
        return loan;
    }
}

