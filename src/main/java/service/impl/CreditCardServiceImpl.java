package service.impl;

import entity.CreditCard;
import entity.Student;
import repository.CreditCardRepository;
import service.CreditCardService;

import java.util.List;

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
    public CreditCard findByCardNumber(Integer cardNumber) {
        try {
            return creditCardRepository.findByCardNumber(cardNumber);
        } catch (Exception e) {
            System.out.println("An error occured while finding a credit card" + e.getMessage());
        }
        return null;
    }

    @Override
    public CreditCard findByStudent(Student student) {
        try {
            return creditCardRepository.findByStudent(student);
        } catch (Exception e) {
            System.out.println("An error occured while finding a credit card" + e.getMessage());
        }
        return null;
    }
}
