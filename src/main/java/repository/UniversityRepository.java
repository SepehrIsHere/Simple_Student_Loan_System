package repository;

import entity.University;

import java.util.List;

public interface UniversityRepository extends BaseEntityRepository<University> {
    University findById(Long id);

    University findByName(String name);

    List<University> findAll();

}
