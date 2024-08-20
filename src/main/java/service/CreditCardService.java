package service;

import entity.CreditCard;
import entity.Student;

import java.util.List;

public interface CreditCardService {
    void add(CreditCard creditCard);

    void delete(CreditCard creditCard);

    void update(CreditCard creditCard);

    List<CreditCard> findAll();

    CreditCard findById(Long id);

    CreditCard findByCardNumber(Integer cardNumber);

    CreditCard findByStudent(Student student);

    CreditCard findByCardNumberAndCvv2(String cardNumber, Integer cvv2);
}
