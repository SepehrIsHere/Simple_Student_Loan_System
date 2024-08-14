package entity.builder;

import entity.Student;
import enumerations.EducationDegree;
import enumerations.EducationStatus;

public class StudentBuilder implements Builder {
    private String firstName;
    private String lastName;
    private String fathersName;
    private String mothersName;
    private String certificateNumber;
    private Integer nationalCode;
    private String birthDate;
    private String username;
    private String password;
    private Integer studentId;
    private Integer yearOfEnter;
    private EducationDegree educationDegree;
    private EducationStatus educationStatus;
    private boolean usesDormitory;

    @Override
    public StudentBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public StudentBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public StudentBuilder setFatherName(String fatherName) {
        this.fathersName = fatherName;
        return this;
    }

    @Override
    public StudentBuilder setMotherName(String motherName) {
        this.mothersName = motherName;
        return this;
    }

    @Override
    public StudentBuilder setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
        return this;
    }

    @Override
    public StudentBuilder setNationalCode(Integer nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    @Override
    public StudentBuilder setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public StudentBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public StudentBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public StudentBuilder setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    @Override
    public StudentBuilder setYearOfEnter(Integer yearOfEnter) {
        this.yearOfEnter = yearOfEnter;
        return this;
    }

    @Override
    public StudentBuilder setEducationDegrees(EducationDegree educationDegrees) {
        this.educationDegree = educationDegrees;
        return this;
    }

    @Override
    public StudentBuilder setEducationStatus(EducationStatus educationStatus) {
        this.educationStatus = educationStatus;
        return this;
    }

    @Override
    public StudentBuilder setUsesDormitory(boolean usesDormitory) {
        this.usesDormitory = usesDormitory;
        return this;
    }

    public Student build(){
        return new Student(firstName,lastName,mothersName,fathersName,certificateNumber,nationalCode,birthDate,username,password,studentId,yearOfEnter,educationDegree,educationStatus,usesDormitory);
    }
}
