package menu;

import entity.*;
import enumerations.LoanType;
import enumerations.UniversityType;
import service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoanMenu {
    private final LoginMenu loginMenu;
    private final LoanService loanService;
    private final CreditCardService creditCardService;
    private final StudentService studentService;
    private final CouplesService couplesService;
    private final InstallmentService installmentService;

    public LoanMenu(LoanService loanService, CreditCardService creditCardService, StudentService studentService, CouplesService couplesService, LoginMenu loginMenu, InstallmentService installmentService) {
        this.loanService = loanService;
        this.creditCardService = creditCardService;
        this.studentService = studentService;
        this.couplesService = couplesService;
        this.loginMenu = loginMenu;
        this.installmentService = installmentService;
    }

    public void showMenu(Student token) {
        try {
            Scanner input = new Scanner(System.in);
            boolean condition = true;
            while (condition) {
                System.out.println("""
                         Sign for Loan\s
                         1.Tuition Loan
                         2.Education Loan
                         3.Housing Loan
                         4.exit
                        \s""");
                int option = input.nextInt();
                input.nextLine();
                switch (option) {
                    case 1 -> takeTuitionLoan(input, token);
                    case 2 -> takeEducationLoan(input, token);
                    case 3 -> takeHousingLoan(input, token);
                    case 4 -> condition = false;
                    default -> System.out.println("Invalid option");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input ! please try again.");
            showMenu(token);
        }
    }

    private void takeTuitionLoan(Scanner input, Student token) {
        LoanType loanType = LoanType.TUITION;
        if (canTakeLoan(token, loanType) && canTakeTuitionLoan(token)) {
            if (creditCardService.doesStudentHaveCreditCard(token)) {
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = creditCardService.getCreditCardDetail(input, token);
                creditCardService.updateCardBalance(creditCard, loan);
                creditCardService.update(creditCard);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            } else {
                ifStudentDosentHaveCard(input, token, loanType);
            }

        } else {
            System.out.println("You cant take Tuition loan at the moment!");
        }
    }

    private boolean canTakeTuitionLoan(Student student) {
        if (student.getUniversity() != null) {
            return student.getUniversity().getUniversityType() == UniversityType.DOLATI_SHABANE ||
                    student.getUniversity().getUniversityType() == UniversityType.AZAD ||
                    student.getUniversity().getUniversityType() == UniversityType.QEIR_ENTEFAHI ||
                    student.getUniversity().getUniversityType() == UniversityType.PAYAMNOOR ||
                    student.getUniversity().getUniversityType() == UniversityType.PARDIS ||
                    student.getUniversity().getUniversityType() == UniversityType.ZARFIAT_MAZAD ||
                    student.getUniversity().getUniversityType() == UniversityType.ELMI_KARBORDI;
        } else {
            System.out.println("Student dosent have an active university ! ");
        }
        return false;
    }

    private void ifStudentDosentHaveCard(Scanner input, Student token, LoanType loanType) {
        System.out.println("You dont have a credit card \n Please enter details of a credit card : ");
        CreditCard creditCard = createCreditCard(token, input);
        token.setCreditCard(creditCard);
        studentService.update(token);
        Loan loan = createLoan(token, loanType);
        creditCardService.updateCardBalance(creditCard, loan);
        loanService.add(loan);
        System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
    }

    private void takeEducationLoan(Scanner input, Student token) {
        LoanType loanType = LoanType.EDUCATION;
        if (canTakeLoan(token, loanType)) {
            if (creditCardService.doesStudentHaveCreditCard(token)) {
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = creditCardService.getCreditCardDetail(input, token);
                creditCardService.updateCardBalance(creditCard, loan);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            } else {
                System.out.println("You dont have a credit card \n Please enter details of a credit card : ");
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = createCreditCard(token, input);
                token.setCreditCard(creditCard);
                studentService.update(token);
                creditCardService.updateCardBalance(creditCard, loan);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            }

        } else {
            System.out.println("You cant take Education loan at the moment!");
        }
    }

    private void takeHousingLoan(Scanner input, Student token) {
        LoanType loanType = LoanType.HOUSING;
        if (canTakeHousingLoan(input, token)) {
            if (creditCardService.doesStudentHaveCreditCard(token)) {
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = creditCardService.getCreditCardDetail(input, token);
                creditCardService.updateCardBalance(creditCard, loan);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            } else {
                ifStudentDosentHaveCard(input, token, loanType);
            }
        } else {
            System.out.println("You cant take housing loan !");
        }
    }

    private boolean canTakeHousingLoan(Scanner input, Student token) {
        try {
            LoanType loanType = LoanType.HOUSING;
            Integer countOfLoan = loanService.countHousingLoanByStudentAndEducationDegree(token, token.getEducationDegree(), loanType).intValue();
            if (token.isEngaged() && countOfLoan.equals(0) && !token.isUsesDormitory()) {
                System.out.println("Enter first name of your partner : ");
                String firstName = input.nextLine().trim().toLowerCase();
                System.out.println("Enter last name of your partner : ");
                String lastName = input.nextLine().trim().toLowerCase();
                Student partner = studentService.findByFirstNameAndLastName(firstName, lastName);
                if (partner != null && partner.isEngaged() && !partner.isUsesDormitory()) {
                    Couples couples = new Couples();
                    couples.setFirstStudent(token);
                    couples.setSecondStudent(partner);
                    System.out.println("Enter contract Number : ");
                    int contractNumber = input.nextInt();
                    couples.setContractNumber(contractNumber);
                    input.nextLine();
                    System.out.println("Enter home address : ");
                    String homeAddress = input.nextLine();
                    couples.setHomeAddress(homeAddress);

                    couplesService.addCouplesIfDoesNotExist(couples);
                    return true;
                }
            } else {
                return false;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input ! please try again.");
            showMenu(token);
        }
        return false;
    }

    private boolean canTakeLoan(Student token, LoanType loanType) {
        Integer countOfLoan = loanService.countLoanByStudentAndMonthAndYearAndType(token, loginMenu.getDate().getMonthValue(), loginMenu.getDate().getYear(), loanType).intValue();
        return countOfLoan.equals(0);
    }


    private Loan createLoan(Student student, LoanType loanType) {
        Loan loan = new Loan();
        loan.setLoanType(loanType);
        loan.setStudent(student);
        loan.setDate(loginMenu.getDate());
        loan.setYear(loginMenu.getDate().getYear());
        loan.setMonth(loginMenu.getDate().getMonthValue());
        switch (loanType) {
            case EDUCATION -> loan.setAmount(installmentService.getEducationLoanAmount(student));
            case TUITION -> loan.setAmount(installmentService.getTuitionLoanAmount(student));
            case HOUSING -> loan.setAmount(installmentService.getHousingLoanAmount(student));
            default -> System.out.println("Invalid loan type");
        }
        return loan;
    }

    private CreditCard createCreditCard(Student student, Scanner input) {
        CreditCard creditCard = creditCardService.createCreditCard(student, input);
        studentService.update(student);
        return creditCard;
    }

}