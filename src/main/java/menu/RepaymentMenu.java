package menu;

import entity.CreditCard;
import entity.Installment;
import entity.Loan;
import entity.Student;
import enumerations.Bank;
import enumerations.InstallmentStatus;
import enumerations.LoanType;
import service.CreditCardService;
import service.InstallmentService;
import service.LoanService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class RepaymentMenu {
    private final LoginMenu loginMenu;
    private final LoanService loanService;
    private final CreditCardService creditCardService;
    private final InstallmentService installmentService;

    public RepaymentMenu(LoginMenu loginMenu, LoanService loanService, CreditCardService creditCardService, InstallmentService installmentService) {
        this.loginMenu = loginMenu;
        this.loanService = loanService;
        this.creditCardService = creditCardService;
        this.installmentService = installmentService;
    }

    public void showMenu(Student token) {
        Scanner input = new Scanner(System.in);
        boolean continueRunning = true;
        while (continueRunning) {
            System.out.println("""
                    Repayment Menu!
                    1.Payed Installments
                    2.Unpaid Installments
                    3.Pay an installment
                    4.Exit
                    """);
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> displayPayedInstallments(input, token);
                case 2 -> displayedUnpaidInstallments(input, token);
                case 3 -> payInstallment(input, token);
                case 4 -> continueRunning = false;
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void displayPayedInstallments(Scanner input, Student token) {
        System.out.println("""
                Which payed installments you would like to see?
                1.Education Loan
                2.Tuition Loan
                3.Housing Loan
                """);
        int option = input.nextInt();
        switch (option) {
            case 1 -> findAndDisplayPayedInstallments(token, LoanType.EDUCATION);

            case 2 -> findAndDisplayPayedInstallments(token, LoanType.TUITION);

            case 3 -> findAndDisplayPayedInstallments(token, LoanType.HOUSING);

            default -> System.out.println("Invalid option!");
        }
    }

    private boolean doesLoanExist(Loan loan) {
        return loan != null;
    }

    private void findAndDisplayPayedInstallments(Student token, LoanType loanType) {
        Loan loan = loanService.findByStudentAndLoanType(token, loanType).getLast();
        if (doesLoanExist(loan)) {
            List<Installment> payedInstallment = installmentService.findByLoanTypeAndPaymentStatus(loan.getLoanType(), InstallmentStatus.PAYED);
            if (payedInstallment != null && !(payedInstallment.isEmpty())) {
                for (Installment installment : payedInstallment) {
                    System.out.println(installment.getLoan().getLoanType() + " Loan");
                    System.out.println("Payed at : " + installment.getPayedDate());
                    System.out.println("Payed amount : " + installment.getPaidAmount());
                }
            } else {
                System.out.println("You dont have a loan of " + loan.getLoanType());
            }
        } else {
            System.out.println("You dont have a loan  to see its installments !");
        }

    }

    private void displayedUnpaidInstallments(Scanner input, Student token) {
        System.out.println("""
                 Which installments for loan do you want to see ?\s
                 1.Education Installment
                 2.Housing Installment
                 3.Tuition Installment
                \s""");
        int option = input.nextInt();
        switch (option) {
            case 1 -> {
                List<Loan> loans = loanService.findByStudentAndLoanType(token, LoanType.EDUCATION);
                if (loans != null && !loans.isEmpty()) {
                    displayUnpaidInstallments(token, LoanType.EDUCATION);
                } else {
                    System.out.println("You dont have an active Education loan");
                }
            }
            case 2 -> {
                List<Loan> loans = loanService.findByStudentAndLoanType(token, LoanType.HOUSING);
                if (loans != null && !loans.isEmpty()) {
                    displayUnpaidInstallments(token, LoanType.HOUSING);
                } else {
                    System.out.println("You dont have an active Housing loan");
                }
            }
            case 3 -> {
                List<Loan> loans = loanService.findByStudentAndLoanType(token, LoanType.TUITION);
                if (loans != null && !loans.isEmpty()) {
                    displayUnpaidInstallments(token, LoanType.TUITION);
                } else {
                    System.out.println("You dont have an active Tuition loan");
                }
            }
            default -> System.out.println("Invalid option ! ");
        }
    }

    private void displayUnpaidInstallments(Student token, LoanType loanType) {
        Loan loan = loanService.findByStudentAndLoanType(token, loanType).getLast();
        if (doesLoanExist(loan)) {
            int countOfPayed = installmentService.findInstallmentCountByLoanTypeAndPaymentStatus(loanType, InstallmentStatus.PAYED).intValue();
            for (int i = 0; i < 60 - countOfPayed; i++) {
                System.out.println(loanType.toString() + " Loan ");
                System.out.println("Installment number : " + i);
                LocalDate paymentDate = loginMenu.getDate();
                paymentDate = paymentDate.plusMonths(1);
                System.out.println("Payment date : " + paymentDate);

                double installmentAmount = 0.0;
                if (i > 0 && i <= 12) {
                    installmentAmount = calculateInstallmentForYear(findTotalAmount(token, loan), 1);
                } else if (i >= 13 && i <= 24) {
                    installmentAmount = calculateInstallmentForYear(findTotalAmount(token, loan), 2);
                } else if (i >= 25 && i <= 36) {
                    installmentAmount = calculateInstallmentForYear(findTotalAmount(token, loan), 3);
                } else if (i >= 37 && i <= 48) {
                    installmentAmount = calculateInstallmentForYear(findTotalAmount(token, loan), 4);
                } else if (i >= 49 && i <= 60) {
                    installmentAmount = calculateInstallmentForYear(findTotalAmount(token, loan), 5);
                }
                System.out.println("Installment amount : " + installmentAmount);
            }
        }
    }

    private void payInstallment(Scanner input, Student token) {
        System.out.println("""
                 Which loan do you want to pay ?\s
                 1.Education\s
                 2.Tuition
                 3.Housing
                \s""");
        int option = input.nextInt();
        input.nextLine();
        switch (option) {
            case 1 -> {
                LoanType loanType = LoanType.EDUCATION;
                Loan loan = loanService.findByStudentAndLoanType(token, loanType).getLast();
                if (loan != null) {
                    CreditCard creditCard = getCreditCardDetail(input);
                    if (isCardValid(creditCard.getCardNumber(), creditCard.getCvv2())) {
                        Installment installment = createInstallment(token, loan);
                        System.out.println("You have successfully payed installment!");
                    } else {
                        System.out.println("Wrong card Number ! ");
                    }

                } else {
                    System.out.println("You dont have an active loan in that area ! ");
                }
            }
            case 2 -> {
                LoanType loanType = LoanType.TUITION;
                Loan loan = loanService.findByStudentAndLoanType(token, loanType).getLast();
                if (loan != null) {
                    CreditCard creditCard = getCreditCardDetail(input);
                    if (isCardValid(creditCard.getCardNumber(), creditCard.getCvv2())) {
                        Installment installment = createInstallment(token, loan);
                        System.out.println("You have successfully payed installment!");
                    } else {
                        System.out.println("Wrong card Number ! ");
                    }

                } else {
                    System.out.println("You dont have an active loan in that area ! ");
                }
            }
            case 3 -> {
                LoanType loanType = LoanType.HOUSING;
                Loan loan = loanService.findByStudentAndLoanType(token, loanType).getLast();
                if (loan != null) {
                    CreditCard creditCard = getCreditCardDetail(input);
                    if (isCardValid(creditCard.getCardNumber(), creditCard.getCvv2())) {
                        Installment installment = createInstallment(token, loan);
                        System.out.println("You have successfully payed installment!");
                    } else {
                        System.out.println("Wrong card Number ! ");
                    }

                } else {
                    System.out.println("You dont have an active loan in that area ! ");
                }
            }
            default -> System.out.println("Invalid Input ! ");
        }
    }

    private CreditCard getCreditCardDetail(Scanner input) {
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
                System.out.println("Invalid input! cvv2 must be between 3 to 4 digits");
            }
            creditCard.setCvv2(cvv2);
            break;
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

        System.out.println("Enter balance : ");
        double balance = input.nextDouble();
        creditCard.setBalance(balance);
        return creditCard;
    }

    private Installment createInstallment(Student token, Loan loan) {
        Installment installment = new Installment();
        installment.setLoan(loan);
        installment.setInstallmentStatus(InstallmentStatus.PAYED);
        installment.setPayedDate(LocalDate.now());
        installment.setPaidAmount(calculateInstallmentForYear(findTotalAmount(token, loan), LocalDate.now().getYear() - loan.getYear()));
        installment.setTotalAmount(findTotalAmount(token, loan));
        installmentService.add(installment);
        return installment;
    }

    private boolean isCardValid(String cardNumber, Integer cvv2) {
        CreditCard creditCard = creditCardService.findByCardNumberAndCvv2(cardNumber, cvv2);
        return creditCard != null;
    }

    double findTotalAmount(Student token, Loan loan) {
        double totalAmount = 0.0;
        switch (loan.getLoanType()) {
            case EDUCATION ->
                    totalAmount = calculateInstallmentForYear(getEducationLoanAmount(token), loginMenu.getDate().getYear());
            case TUITION ->
                    totalAmount = calculateInstallmentForYear(getTuitionLoanAmount(token), loginMenu.getDate().getYear());
            case HOUSING ->
                    totalAmount = calculateInstallmentForYear(getHousingLoanAmount(token), loginMenu.getDate().getYear());
        }
        return totalAmount;
    }

    private Double getEducationLoanAmount(Student token) {
        double loanAmount = 0.0;
        switch (token.getEducationDegree()) {
            case ASSOCIATE, BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> loanAmount = 1900000.0 + (0.04 * 1900000.0);
            case MASTER_PEYVASTE, MASTER_NAPEYVASTE, PHD_PEYVASTER -> loanAmount = 2250000.0 + (0.04 * 2250000.0);
            case PHD_NAPEYVASTE -> loanAmount = 2600000.0 + (0.04 * 2600000.0);
            default -> loanAmount = 0.0;
        }
        return loanAmount;
    }

    private Double getTuitionLoanAmount(Student token) {
        double loanAmount = 0.0;
        switch (token.getEducationDegree()) {
            case ASSOCIATE, BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> loanAmount = 1300000.0 + (0.04 * 1300000.0);
            case MASTER_PEYVASTE, MASTER_NAPEYVASTE, PHD_PEYVASTER -> loanAmount = 2600000.0 + (0.04 * 2600000.0);
            case PHD_NAPEYVASTE -> loanAmount = 65000000.0 + (0.04 * 65000000.0);
            default -> loanAmount = 0.0;
        }
        return loanAmount;
    }

    private Double getHousingLoanAmount(Student token) {
        double loanAmount = 0.0;
        switch (token.getUniversity().getMetropolis()) {
            case TEHRAN -> loanAmount = 32000000.0 + (0.04 * 32000000.0);
            case ALBORZ, AZARBAYEJAN_SHARQI, ESFEHAN, FARS, GILLAN, QOM, KHOOZESTAN, KHORASAN_RAZAVI ->
                    loanAmount = 26000000.0 + (0.04 * 26000000.0);
            default -> loanAmount = 19500000.0 + (0.04 * 19500000.0);
        }
        return loanAmount;
    }

    double calculateInstallmentForYear(double totalRepayment, int currentYear) {
        double initialInstallment = totalRepayment / (12 * (Math.pow(2, 5) - 1));

        return initialInstallment * Math.pow(2, currentYear - 1);
    }
}
