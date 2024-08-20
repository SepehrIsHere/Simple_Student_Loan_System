package repository;

import entity.Couples;

import java.util.List;

public interface CouplesRepository extends BaseEntityRepository<Couples> {
    List<Couples> findAll();

    Couples findById(Long id);
}
