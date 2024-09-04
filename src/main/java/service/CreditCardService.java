package service;

import entity.CreditCard;
import entity.Loan;
import entity.Student;
import enumerations.LoanType;

import java.util.List;
import java.util.Scanner;

public interface CreditCardService {
    void add(CreditCard creditCard);

    void delete(CreditCard creditCard);

    void update(CreditCard creditCard);

    List<CreditCard> findAll();

    CreditCard findById(Long id);

    CreditCard findByCardNumberAndCvv2(String cardNumber, Integer cvv2);

    CreditCard getCreditCardDetail(Scanner input,Student student);

    void updateCardBalance(CreditCard creditCard, Loan loan);

    CreditCard createCreditCard(Student student, Scanner input);

    boolean doesStudentHaveCreditCard(Student student);
}
