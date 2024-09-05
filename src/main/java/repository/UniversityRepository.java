package repository;

import entity.University;
import enumerations.UniversityType;

import java.util.List;

public interface UniversityRepository extends BaseEntityRepository<University> {
    University findById(Long id);

    University findByName(String name);

    List<University> findAll();

    University findByNameAndUniversityType(String universityName, UniversityType universityType);
}
