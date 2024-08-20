package repository;

import entity.Installment;

import java.util.List;

public interface InstallmentRepository extends BaseEntityRepository<Installment> {
    List<Installment> findAll();

    Installment findById(Long id);
}
