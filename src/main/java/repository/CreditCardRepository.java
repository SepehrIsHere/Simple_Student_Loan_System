package repository;

import entity.CreditCard;
import entity.Student;

import java.util.List;

public interface CreditCardRepository extends BaseEntityRepository<CreditCard> {
    List<CreditCard> findAll();

    CreditCard findById(Long id);

    CreditCard findByCardNumber(Integer cardNumber);

    CreditCard findByStudent(Student student);
}
