package menu;

import entity.CreditCard;
import entity.Installment;
import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import enumerations.InstallmentStatus;
import enumerations.LoanType;
import service.CreditCardService;
import service.InstallmentService;
import service.LoanService;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RepaymentMenu {
    private final LoanService loanService;
    private final LoginMenu loginMenu;
    private final CreditCardService creditCardService;
    private final InstallmentService installmentService;

    public RepaymentMenu(LoanService loanService, CreditCardService creditCardService, InstallmentService installmentService, LoginMenu loginMenu) {
        this.loanService = loanService;
        this.creditCardService = creditCardService;
        this.installmentService = installmentService;
        this.loginMenu = loginMenu;
    }

    public void showMenu(Student token) {
        try {
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
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! please try again later.");
            showMenu(token);
        }
    }

    private void displayPayedInstallments(Scanner input, Student token) {
        try {
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
        } catch (InputMismatchException e) {
            System.out.println("Invalid input ! please try again.");
            displayPayedInstallments(input, token);
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
                System.out.println("You dont have a payed installment of  " + loan.getLoanType() + " loan ");
            }
        } else {
            System.out.println("You dont have a loan  to see its installments !");
        }

    }

    private void displayedUnpaidInstallments(Scanner input, Student token) {
        try {
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
                        displayUnpaidInstallments(input, token, LoanType.EDUCATION);
                    } else {
                        System.out.println("You dont have an active Education loan");
                    }
                }
                case 2 -> {
                    List<Loan> loans = loanService.findByStudentAndLoanType(token, LoanType.HOUSING);
                    if (loans != null && !loans.isEmpty()) {
                        displayUnpaidInstallments(input, token, LoanType.HOUSING);
                    } else {
                        System.out.println("You dont have an active Housing loan");
                    }
                }
                case 3 -> {
                    List<Loan> loans = loanService.findByStudentAndLoanType(token, LoanType.TUITION);
                    if (loans != null && !loans.isEmpty()) {
                        displayUnpaidInstallments(input, token, LoanType.TUITION);
                    } else {
                        System.out.println("You dont have an active Tuition loan");
                    }
                }
                default -> System.out.println("Invalid option ! ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input ! please try again.");
            displayedUnpaidInstallments(input, token);
        }
    }

    private void displayUnpaidInstallments(Scanner input, Student token, LoanType loanType) {
        List<Loan> loans = loanService.findByStudentAndLoanType(token, loanType);
        Loan loan = loanService.displayAndChooseLoan(input, token, loans, loanType);
        if (doesLoanExist(loan)) {
            int countOfPayed = installmentService.findInstallmentCountByLoanTypeAndPaymentStatus(loanType, InstallmentStatus.PAYED).intValue();
            for (int i = countOfPayed + 1; i <= 60; i++) {
                System.out.println(loanType.toString() + " Loan ");
                System.out.println("Installment number : " + i);
                LocalDate paymentDate = loan.getDate();
                paymentDate = paymentDate.plusMonths(1);
                System.out.println("Payment date : " + paymentDate);

                long installmentAmount = 0;
                if (loanType == LoanType.EDUCATION) {
                    if (i <= 12) {
                        installmentAmount = installmentService.calculateInstallmentForYear(1, installmentService.getBaseEducationLoanAmount(token));
                    } else if (i <= 24) {
                        installmentAmount = installmentService.calculateInstallmentForYear(2, installmentService.getBaseEducationLoanAmount(token));
                    } else if (i <= 36) {
                        installmentAmount = installmentService.calculateInstallmentForYear(3, installmentService.getBaseEducationLoanAmount(token));
                    } else if (i <= 48) {
                        installmentAmount = installmentService.calculateInstallmentForYear(4, installmentService.getBaseEducationLoanAmount(token));
                    } else if (i <= 60) {
                        installmentAmount = installmentService.calculateInstallmentForYear(5, installmentService.getBaseEducationLoanAmount(token));
                    }
                } else if (loanType == LoanType.TUITION) {
                    if (i <= 12) {
                        installmentAmount = installmentService.calculateInstallmentForYear(1, installmentService.getBaseTuitionLoanAmount(token));
                    } else if (i <= 24) {
                        installmentAmount = installmentService.calculateInstallmentForYear(2, installmentService.getBaseTuitionLoanAmount(token));
                    } else if (i <= 36) {
                        installmentAmount = installmentService.calculateInstallmentForYear(3, installmentService.getBaseTuitionLoanAmount(token));
                    } else if (i <= 48) {
                        installmentAmount = installmentService.calculateInstallmentForYear(4, installmentService.getBaseTuitionLoanAmount(token));
                    } else if (i <= 60) {
                        installmentAmount = installmentService.calculateInstallmentForYear(5, installmentService.getBaseTuitionLoanAmount(token));
                    }
                } else if (loanType == LoanType.HOUSING) {
                    if (i <= 12) {
                        installmentAmount = installmentService.calculateInstallmentForYear(1, installmentService.getBaseHousingLoanAmount(token));
                    } else if (i <= 24) {
                        installmentAmount = installmentService.calculateInstallmentForYear(2, installmentService.getBaseHousingLoanAmount(token));
                    } else if (i <= 36) {
                        installmentAmount = installmentService.calculateInstallmentForYear(3, installmentService.getBaseHousingLoanAmount(token));
                    } else if (i <= 48) {
                        installmentAmount = installmentService.calculateInstallmentForYear(4, installmentService.getBaseHousingLoanAmount(token));
                    } else if (i <= 60) {
                        installmentAmount = installmentService.calculateInstallmentForYear(5, installmentService.getBaseHousingLoanAmount(token));
                    }
                }
                System.out.println("Installment amount : " + installmentAmount);
            }
        }
    }

    private void payInstallment(Scanner input, Student token) {
        try {
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
                    if (token.getCreditCard() != null) {
                        List<Loan> educationLoans = loanService.findByStudentAndLoanType(token, LoanType.EDUCATION);
                        Loan loan = loanService.displayAndChooseLoan(input, token, educationLoans, LoanType.EDUCATION);
                        long paidAmount = installmentService.calculateInstallmentForYear(whichYearIsIt(token, token.getEducationDegree()), installmentService.getBaseEducationLoanAmount(token));
                        Installment installment = installmentService.createInstallment(token, loan, paidAmount, loginMenu.getDate());
                        installmentService.add(installment);
                        System.out.println("You have successfully payed a installment");
                    } else {
                        while (true) {
                            CreditCard creditCard = creditCardService.getCreditCardDetail(input, token);
                            if (creditCard != null) {
                                List<Loan> educationLoans = loanService.findByStudentAndLoanType(token, LoanType.EDUCATION);
                                Loan loan = loanService.displayAndChooseLoan(input, token, educationLoans, LoanType.EDUCATION);
                                long paidAmount = installmentService.calculateInstallmentForYear(whichYearIsIt(token, token.getEducationDegree()), installmentService.getEducationLoanAmount(token));
                                Installment installment = installmentService.createInstallment(token, loan, paidAmount, loginMenu.getDate());
                                installmentService.add(installment);
                                System.out.println("You have successfully payed a installment");
                                break;
                            }
                            System.out.println("Card does not exist ! ");
                        }

                    }
                }
                case 2 -> {
                    if (token.getCreditCard() != null) {
                        tuitionLoanIfDosentHaveCard(input, token);
                    } else {
                        while (true) {
                            CreditCard creditCard = creditCardService.getCreditCardDetail(input, token);
                            if (creditCard != null) {
                                tuitionLoanIfDosentHaveCard(input, token);
                                break;
                            }
                            System.out.println("Card does not exist ! ");
                        }

                    }
                }
                case 3 -> {
                    if (token.getCreditCard() != null) {
                        housingLoanIfDosentHaveCard(input, token);
                    } else {
                        while (true) {
                            CreditCard creditCard = creditCardService.getCreditCardDetail(input, token);
                            if (creditCard != null) {
                                housingLoanIfDosentHaveCard(input, token);
                                break;
                            }
                            System.out.println("Card does not exist ! ");
                        }

                    }
                }
                default -> System.out.println("Invalid Input ! ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input ! please try again later.");
            payInstallment(input,token);
        }
    }

    private void housingLoanIfDosentHaveCard(Scanner input, Student token) {
        List<Loan> housingLoans = loanService.findByStudentAndLoanType(token, LoanType.HOUSING);
        Loan loan = loanService.displayAndChooseLoan(input, token, housingLoans, LoanType.HOUSING);
        long paidAmount = installmentService.calculateInstallmentForYear(whichYearIsIt(token, token.getEducationDegree()), installmentService.getBaseHousingLoanAmount(token));
        Installment installment = installmentService.createInstallment(token, loan, paidAmount, loginMenu.getDate());
        installmentService.add(installment);
        System.out.println("You have successfully payed a installment");
    }

    private void tuitionLoanIfDosentHaveCard(Scanner input, Student token) {
        List<Loan> tutionLoans = loanService.findByStudentAndLoanType(token, LoanType.TUITION);
        Loan loan = loanService.displayAndChooseLoan(input, token, tutionLoans, LoanType.TUITION);
        long paidAmount = installmentService.calculateInstallmentForYear(whichYearIsIt(token, token.getEducationDegree()), installmentService.getBaseTuitionLoanAmount(token));
        Installment installment = installmentService.createInstallment(token, loan, paidAmount, loginMenu.getDate());
        installmentService.add(installment);
        System.out.println("You have successfully payed a installment");
    }

    private Integer whichYearIsIt(Student student, EducationDegree educationDegree) {
        int installmentStartYear = installmentService.graduationYear(student, educationDegree);
        if (loginMenu.getDate().getYear() - installmentStartYear == 0) {
            return 1;
        } else if (loginMenu.getDate().getYear() - installmentStartYear == 1) {
            return 2;
        } else if (loginMenu.getDate().getYear() - installmentStartYear == 2) {
            return 3;
        } else if (loginMenu.getDate().getYear() - installmentStartYear == 3) {
            return 4;
        } else if (loginMenu.getDate().getYear() - installmentStartYear == 4) {
            return 5;
        }
        return 0;
    }

}
