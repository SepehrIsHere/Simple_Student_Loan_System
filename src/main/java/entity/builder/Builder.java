package entity.builder;

import enumerations.EducationDegree;
import enumerations.EducationStatus;

public interface Builder {
    StudentBuilder setFirstName(String firstName);

    StudentBuilder setLastName(String lastName);

    StudentBuilder setFatherName(String fatherName);

    StudentBuilder setMotherName(String motherName);

    StudentBuilder setCertificateNumber(String certificateNumber);

    StudentBuilder setNationalCode(Integer nationalCode);

    StudentBuilder setBirthDate(String birthDate);

    StudentBuilder setUsername(String username);

    StudentBuilder setPassword(String password);

    StudentBuilder setStudentId(Integer studentId);

    StudentBuilder setYearOfEnter(Integer yearOfEnter);

    StudentBuilder setEducationDegrees(EducationDegree educationDegrees);

    StudentBuilder setEducationStatus(EducationStatus educationStatus);

    StudentBuilder setUsesDormitory(boolean usesDormitory);

}
