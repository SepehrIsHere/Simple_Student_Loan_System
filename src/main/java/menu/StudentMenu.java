package menu;

import entity.CreditCard;
import entity.Loan;
import entity.Student;
import enumerations.Bank;
import service.CreditCardService;
import service.InstallmentService;
import service.LoanService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentMenu {
    private final CreditCardService creditCardService;
    private final LoanMenu loanMenu;
    private final RepaymentMenu repaymentMenu;
    private final LoginMenu loginMenu;
    private final LoanService loanService;
    private final InstallmentService installmentService;

    public StudentMenu(CreditCardService creditCardService, RepaymentMenu repaymentMenu, LoanMenu loanMenu, LoginMenu loginMenu, LoanService loanService, InstallmentService installmentService) {
        this.creditCardService = creditCardService;
        this.loginMenu = loginMenu;
        this.loanService = loanService;
        this.installmentService = installmentService;
        this.repaymentMenu = repaymentMenu;
        this.loanMenu = loanMenu;
    }

    public void showMenu(Student token) {
        try {
            Scanner input = new Scanner(System.in);
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.println("""
                         Student Menu\s
                         1.Signing for Loan\s
                         2.RepayMenu
                         3.Add Credit Card
                         4.Display Loans
                         5.Exit
                        \s""");
                int option = input.nextInt();
                input.nextLine();
                switch (option) {
                    case 1 -> {
                        if (loginMenu.getDate().getYear() >= installmentService.graduationYear(token, token.getEducationDegree())) {
                            System.out.println("You cannot take loan you have been graduated ! ");
                        } else {
                            signForLoan(token);
                        }
                    }
                    case 2 -> {
                        if (loginMenu.getDate().getYear() >= installmentService.graduationYear(token, token.getEducationDegree())) {
                            repaymentMenu.showMenu(token);
                        } else {
                            System.out.println("You can repay only when you are graduated ! ");
                        }
                    }
                    case 3 -> {
                        if (token.getCreditCard() != null) {
                            addCreditCard(input, token);
                        } else {
                            System.out.println("You have already submitted a credit card");
                        }
                    }
                    case 4 -> displayLoans(token);
                    case 5 -> continueRunning = false;
                    default -> System.out.println("Invalid option");
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid Input please try again ! ");
            showMenu(token);
        }
    }

    private void signForLoan(Student token) {
        displayMenuBasedOnDate(loginMenu.getDate(), token);
    }

    private void addCreditCard(Scanner input, Student token) {
        creditCardService.createCreditCard(token, input);
    }


    private void displayMenuBasedOnDate(LocalDate date, Student token) {
        // Define date ranges for Aban and Bahman in the Gregorian calendar
        LocalDate abanStart = LocalDate.of(date.getYear(), 10, 23);  // Aban 1st
        LocalDate abanEnd = LocalDate.of(date.getYear(), 10, 30);    // Aban 8th
        LocalDate bahmanStart = LocalDate.of(date.getYear(), 2, 14); // Bahman 25th
        LocalDate esfandEnd = LocalDate.of(date.getYear(), 2, 21);   // Esfand 2nd

        // Check if the date falls within the Aban range
        if ((date.isEqual(abanStart) || date.isAfter(abanStart)) && date.isBefore(abanEnd.plusDays(1))) {
            loanMenu.showMenu(token);
        }
        // Check if the date falls within the Bahman to Esfand range
        else if ((date.isEqual(bahmanStart) || date.isAfter(bahmanStart)) && date.isBefore(esfandEnd.plusDays(1))) {
            loanMenu.showMenu(token);
        } else {
            System.out.println("Invalid date!");
        }
    }

    private void displayLoans(Student token) {
        List<Loan> studentLoan = loanService.findByStudent(token);
        if (studentLoan != null && !(studentLoan.isEmpty())) {
            for (Loan loan : studentLoan) {
                System.out.println("=========================================================================================================================================");
                System.out.println(" Loan " + loan.getLoanType().toString());
                System.out.println(" Loan Number " + loan.getId());
                System.out.println(" Total Loan Amount  : " + installmentService.findTotalAmount(token, loan));
                System.out.println(" Loan Date " + loan.getDate());
                System.out.println(" Installment for first year : " + installmentService.calculateInstallmentForYear(1, installmentService.findTotalAmount(token, loan)));
                System.out.println(" Installment for first year : " + installmentService.calculateInstallmentForYear(2, installmentService.findTotalAmount(token, loan)));
                System.out.println(" Installment for first year : " + installmentService.calculateInstallmentForYear(3, installmentService.findTotalAmount(token, loan)));
                System.out.println(" Installment for first year : " + installmentService.calculateInstallmentForYear(4, installmentService.findTotalAmount(token, loan)));
                System.out.println(" Installment for first year : " + installmentService.calculateInstallmentForYear(5, installmentService.findTotalAmount(token, loan)));
                System.out.println("=========================================================================================================================================");
            }
        } else {
            System.out.println("You dont have active Loans ! ");
        }
    }
}
