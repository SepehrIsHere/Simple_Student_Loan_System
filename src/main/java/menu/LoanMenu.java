package menu;

import entity.*;
import enumerations.Bank;
import enumerations.LoanType;
import service.*;

import java.util.Scanner;

public class LoanMenu {
    private final LoginMenu loginMenu;
    private final LoanService loanService;
    private final CreditCardService creditCardService;
    private final StudentService studentService;
    private final CouplesService couplesService;

    public LoanMenu(LoanService loanService, CreditCardService creditCardService, StudentService studentService, CouplesService couplesService, LoginMenu loginMenu) {
        this.loanService = loanService;
        this.creditCardService = creditCardService;
        this.studentService = studentService;
        this.couplesService = couplesService;
        this.loginMenu = loginMenu;
    }

    public void showMenu(Student token) {
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
    }

    private void takeTuitionLoan(Scanner input, Student token) {
        LoanType loanType = LoanType.TUITION;
        if (canTakeLoan(token, loanType)) {
            if (doesStudentHaveACard(token)) {
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = getCardInfo(input);
                assert creditCard != null;
                updateCardBalance(creditCard, loan);
                creditCardService.update(creditCard);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            } else {
                System.out.println("You dont have a credit card \n Please enter details of a credit card : ");
                CreditCard creditCard = createCreditCard(token, input);
                token.setCreditCard(creditCard);
                studentService.update(token);
                Loan loan = createLoan(token, loanType);
                updateCardBalance(creditCard, loan);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            }

        } else {
            System.out.println("You cant take Tuition loan at the moment!");
        }
    }

    private void takeEducationLoan(Scanner input, Student token) {
        LoanType loanType = LoanType.EDUCATION;
        if (canTakeLoan(token, loanType)) {
            if (doesStudentHaveACard(token)) {
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = getCardInfo(input);
                assert creditCard != null;
                updateCardBalance(creditCard, loan);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            } else {
                System.out.println("You dont have a credit card \n Please enter details of a credit card : ");
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = createCreditCard(token, input);
                token.setCreditCard(creditCard);
                studentService.update(token);
                updateCardBalance(creditCard, loan);
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
            if (doesStudentHaveACard(token)) {
                Loan loan = createLoan(token, loanType);
                CreditCard creditCard = getCardInfo(input);
                assert creditCard != null;
                updateCardBalance(creditCard, loan);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            } else {
                System.out.println("You dont have a credit card \n Please enter details of a credit card : ");
                CreditCard creditCard = createCreditCard(token, input);
                token.setCreditCard(creditCard);
                studentService.update(token);
                Loan loan = createLoan(token, loanType);
                updateCardBalance(creditCard, loan);
                loanService.add(loan);
                System.out.println("You have successfully taken a " + loan.getLoanType().toString() + " with total amount of : " + loan.getAmount());
            }
        } else {
            System.out.println("You cant take housing loan !");
        }
    }

    private boolean canTakeHousingLoan(Scanner input, Student token) {
        LoanType loanType = LoanType.HOUSING;
        Loan housingLoan = loanService.findByStudentAndLoanTypeAndYear(token, loanType, loginMenu.getDate().getYear());
        if (token.isEngaged() && housingLoan != null) {
            System.out.println("Enter first name of your partner : ");
            String firstName = input.nextLine();
            System.out.println("Enter last name of your partner : ");
            String lastName = input.nextLine();

            Student partner = studentService.findByFirstNameAndLastName(firstName, lastName);
            if (partner != null) {
                Couples couples = new Couples();
                couples.setFirstStudent(token);
                couples.setSecondStudent(partner);
                System.out.println("Enter contract Number : ");
                int contractNumber = input.nextInt();
                System.out.println("Enter home address : ");
                String homeAddress = input.nextLine();
                couples.setHomeAddress(homeAddress);
                couples.setContractNumber(contractNumber);

                couplesService.add(couples);
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean canTakeLoan(Student token, LoanType loanType) {
        Loan loan = loanService.findByStudentMonthYearAndType(token, loanType, loginMenu.getDate().getMonthValue(), loginMenu.getDate().getYear());
        return loan != null && loan.getMonth() != loginMenu.getDate().getMonthValue() && loan.getYear() != loginMenu.getDate().getYear();
    }

    private Double getEducationLoanAmount(Student token) {
        double loanAmount;
        switch (token.getEducationDegree()) {
            case ASSOCIATE, BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> loanAmount = 1900000.0;
            case MASTER_PEYVASTE, MASTER_NAPEYVASTE, PHD_PEYVASTER -> loanAmount = 2250000.0;
            case PHD_NAPEYVASTE -> loanAmount = 2600000.0;
            default -> loanAmount = 0.0;
        }
        return loanAmount;
    }

    private Double getTuitionLoanAmount(Student token) {
        double loanAmount;
        switch (token.getEducationDegree()) {
            case ASSOCIATE, BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> loanAmount = 1300000.0;
            case MASTER_PEYVASTE, MASTER_NAPEYVASTE, PHD_PEYVASTER -> loanAmount = 2600000.0;
            case PHD_NAPEYVASTE -> loanAmount = 65000000.0;
            default -> loanAmount = 0.0;
        }
        return loanAmount;
    }

    private Double getHousingLoanAmount(Student token) {
        double loanAmount;
        switch (token.getUniversity().getMetropolis()) {
            case TEHRAN -> loanAmount = 32000000.0;
            case ALBORZ, AZARBAYEJAN_SHARQI, ESFEHAN, FARS, GILLAN, QOM, KHOOZESTAN, KHORASAN_RAZAVI ->
                    loanAmount = 26000000.0;
            default -> loanAmount = 19500000.0;
        }
        return loanAmount;
    }

    private Loan createLoan(Student student, LoanType loanType) {
        Loan loan = new Loan();
        loan.setLoanType(loanType);
        loan.setStudent(student);
        loan.setDate(loginMenu.getDate());
        loan.setYear(loginMenu.getDate().getYear());
        loan.setMonth(loginMenu.getDate().getMonthValue());
        switch (loanType) {
            case EDUCATION -> loan.setAmount(getEducationLoanAmount(student));
            case TUITION -> loan.setAmount(getTuitionLoanAmount(student));
            case HOUSING -> loan.setAmount(getHousingLoanAmount(student));
            default -> System.out.println("Invalid loan type");
        }
        return loan;
    }

    private CreditCard createCreditCard(Student student, Scanner input) {
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
            if (((cvv2 > 4) || (cvv2 < 3))) {
                creditCard.setCvv2(cvv2);
                break;
            }
            System.out.println("Invalid input! cvv2 must be between 3 to 4 digits");
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
        System.out.println("""
                Select Cards Bank :
                1.Tejarat
                2.Meli
                3.Refah
                4.Maskan
                """);
        int option = input.nextInt();
        switch (option) {
            case 1 -> creditCard.setBank(Bank.TEJARAT);
            case 2 -> creditCard.setBank(Bank.MELI);
            case 3 -> creditCard.setBank(Bank.REFAH);
            case 4 -> creditCard.setBank(Bank.MASKAN);
            default -> System.out.println("Invalid option");
        }
        System.out.println("Enter balance : ");
        double balance = input.nextDouble();
        creditCard.setBalance(balance);
        creditCard.setStudent(student);
        student.setCreditCard(creditCard);
        creditCardService.add(creditCard);
        studentService.update(student);
        return creditCard;
    }

    private boolean doesStudentHaveACard(Student student) {
        return student.getCreditCard() != null;
    }

    private CreditCard getCardInfo(Scanner input) {
        String cardNumber;
        while (true) {
            System.out.println("Enter Card Number : ");
            cardNumber = input.nextLine().trim();
            if (cardNumber.matches("^\\d{12,16}$")) {
                break;
            }
            System.out.println("Invalid input. card number must have at least 12 number and in most 16 !");
        }
        int cvv2;
        while (true) {
            System.out.println("Enter CVV2 : ");
            cvv2 = input.nextInt();
            if (((cvv2 > 4) || (cvv2 < 3))) {
                break;
            }
            System.out.println("Invalid input! cvv2 must be between 3 to 4 digits");

        }
        CreditCard studentCard = creditCardService.findByCardNumberAndCvv2(cardNumber, cvv2);
        if (studentCard != null) {
            return studentCard;
        } else {
            System.out.println("Card does not exist");
            return null;
        }
    }

    private void updateCardBalance(CreditCard creditCard, Loan loan) {
        CreditCard studentsCard = creditCardService.findByCardNumberAndCvv2(creditCard.getCardNumber(), creditCard.getCvv2());
        if (studentsCard != null) {
            studentsCard.setBalance(studentsCard.getBalance() + loan.getAmount());
            creditCardService.update(studentsCard);
        } else {
            System.out.println("Card is invalid !");
        }
    }
}