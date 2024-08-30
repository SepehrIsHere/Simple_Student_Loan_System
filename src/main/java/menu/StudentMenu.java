package menu;

import entity.CreditCard;
import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import service.CreditCardService;
import service.LoanService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentMenu {
    private LocalDate date;
    private final CreditCardService creditCardService;
    private final RepaymentMenu repaymentMenu;
    private final LoanMenu loanMenu;
    private final LoginMenu loginMenu;
    private final LoanService loanService;

    public StudentMenu(CreditCardService creditCardService, RepaymentMenu repaymentMenu, LoanMenu loanMenu, LoginMenu loginMenu, LoanService loanService) {
        this.creditCardService = creditCardService;
        this.repaymentMenu = repaymentMenu;
        this.loanMenu = loanMenu;
        this.loginMenu = loginMenu;
        this.loanService = loanService;
    }

    public void showMenu(Student token) {
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
                    if (loginMenu.getDate().getYear() >= graduationYear(token, token.getEducationDegree())) {
                        System.out.println("You cannot take loan you have been graduated ! ");
                    } else {
                        signForLoan(token);
                    }
                }
                case 2 -> {
                    if (loginMenu.getDate().getYear() >= graduationYear(token, token.getEducationDegree())) {
                        repaymentMenu.showMenu(token);
                    } else {
                        System.out.println("You can repay only when you are graduated ! ");
                    }
                }
                case 3 -> addCreditCard(input, token);
                case 4 -> displayLoans(token);
                case 5 -> continueRunning = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void signForLoan(Student token) {
        displayMenuBasedOnDate(loginMenu.getDate(), token);
    }

    private void addCreditCard(Scanner input, Student token) {
        if (token.getCreditCard() == null) {
            CreditCard creditCard = new CreditCard();
            while (true) {
                System.out.println("Enter Card Number : ");
                String cardNumber = input.nextLine().trim();
                if (cardNumber.matches("^\\d{12,16}$")) {
                    creditCard.setCardNumber(cardNumber);
                    break;
                }
                System.out.println("Invalid input!. input must be digits and between 12 to 16");
            }

            while (true) {
                System.out.println("Enter CVV2 : ");
                int cvv2 = input.nextInt();
                if (!((cvv2 > 4) || (cvv2 < 3))) {
                    creditCard.setCvv2(cvv2);
                    break;
                }
                System.out.println("Invalid input! cvv2 must be between 3 to 4 digits");

            }
            while (true) {
                System.out.println("Enter balance : ");
                double balance = input.nextDouble();
                if (!(balance < 0)) {
                    creditCard.setBalance(balance);
                    break;
                }
                System.out.println("Balance should be positive!");
            }
            while (true) {
                System.out.println("Enter expire date : ");
                String expireDate = input.nextLine().trim();
                String datePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
                if (expireDate.matches(datePattern)) {
                    creditCard.setExpirationDate(expireDate);
                    break;
                }
                System.out.println("Invalid date!");
            }
            creditCard.setStudent(token);
            creditCardService.add(creditCard);
        } else {
            System.out.println("You already have a credit card saved !");
        }
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

    private int graduationYear(Student student, EducationDegree educationDegree) {
        int graduationYear = 0;
        switch (educationDegree) {
            case BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> {
                graduationYear = student.getYearOfEnter() + 4;
            }
            case ASSOCIATE, MASTER_NAPEYVASTE -> {
                graduationYear = student.getYearOfEnter() + 2;
            }
            case MASTER_PEYVASTE -> {
                graduationYear = student.getYearOfEnter() + 6;
            }
            case PHD_NAPEYVASTE, PHD_PEYVASTER -> {
                graduationYear = student.getYearOfEnter() + 5;
            }
        }
        return graduationYear;
    }

    private void displayLoans(Student token) {
        List<Loan> studentLoan = loanService.findByStudent(token);
        if (studentLoan != null && !(studentLoan.isEmpty())) {
            for (Loan loan : studentLoan) {
                System.out.println(" Loan " + loan.getLoanType().toString());
                System.out.println(" Loan Number " + loan.getLoanNumber());
                System.out.println(" Total Loan Amount  : " + repaymentMenu.findTotalAmount(token, loan));
                System.out.println(" Loan Date " + loan.getDate());
                System.out.println(" Installment for first year : " + repaymentMenu.calculateInstallmentForYear(repaymentMenu.findTotalAmount(token, loan), 1));
                System.out.println(" Installment for second year : " + repaymentMenu.calculateInstallmentForYear(repaymentMenu.findTotalAmount(token, loan), 2));
                System.out.println(" Installment for third year : " + repaymentMenu.calculateInstallmentForYear(repaymentMenu.findTotalAmount(token, loan), 3));
                System.out.println(" Installment for fourth year : " + repaymentMenu.calculateInstallmentForYear(repaymentMenu.findTotalAmount(token, loan), 4));
                System.out.println(" Installment for fifth year : " + repaymentMenu.calculateInstallmentForYear(repaymentMenu.findTotalAmount(token, loan), 5));
            }
        } else {
            System.out.println("You dont have active Loans ! ");
        }
    }
}
