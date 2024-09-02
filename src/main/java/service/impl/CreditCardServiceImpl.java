package service.impl;

import entity.CreditCard;
import entity.Loan;
import entity.Student;
import enumerations.Bank;
import repository.CreditCardRepository;
import service.CreditCardService;

import java.util.List;
import java.util.Scanner;

public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public void add(CreditCard creditCard) {
        try {
            creditCardRepository.add(creditCard);
        } catch (Exception e) {
            System.out.println("An error occured while adding a credit card" + e.getMessage());
        }
    }

    @Override
    public void delete(CreditCard creditCard) {
        try {
            creditCardRepository.delete(creditCard);
        } catch (Exception e) {
            System.out.println("An error occured while deleting a credit card" + e.getMessage());
        }
    }

    @Override
    public void update(CreditCard creditCard) {
        try {
            creditCardRepository.update(creditCard);
        } catch (Exception e) {
            System.out.println("An error occured while updating a credit card" + e.getMessage());
        }
    }

    @Override
    public List<CreditCard> findAll() {
        try {
            return creditCardRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occured while finding all credit cards");
        }
        return null;
    }

    @Override
    public CreditCard findById(Long id) {
        try {
            return creditCardRepository.findById(id);
        } catch (Exception e) {
            System.out.println("An error occured while finding a credit card" + e.getMessage());
        }
        return null;
    }

    @Override
    public CreditCard findByCardNumberAndCvv2(String cardNumber, Integer cvv2) {
        try {
            return creditCardRepository.findByCardNumberAndCvv2(cardNumber, cvv2);
        } catch (NullPointerException e) {
            System.out.println("Credit card does not exist or something went wrong " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CreditCard getCreditCardDetail(Scanner input) {
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

    @Override
    public void updateCardBalance(CreditCard creditCard, Loan loan) {
        CreditCard studentsCard = findByCardNumberAndCvv2(creditCard.getCardNumber(), creditCard.getCvv2());
        studentsCard.setBalance(studentsCard.getBalance() + (loan.getAmount()));
        update(studentsCard);
    }

    @Override
    public CreditCard createCreditCard(Student student, Scanner input) {
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
        add(creditCard);
        return creditCard;
    }

    @Override
    public boolean doesStudentHaveCreditCard(Student student) {
        return student.getCreditCard() != null;
    }
}
