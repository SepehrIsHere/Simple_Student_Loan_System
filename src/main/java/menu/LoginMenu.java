package menu;

import entity.Student;
import entity.University;
import enumerations.EducationDegree;
import enumerations.EducationStatus;
import enumerations.UniversityType;
import lombok.Getter;
import service.StudentService;
import service.UniversityService;
import util.PassGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LoginMenu {
    @Getter
    private LocalDate date;
    @Getter
    private Student token = null;
    private final StudentService studentService;
    private final UniversityService universityService;

    public LoginMenu(StudentService studentService, UniversityService universityService) {
        this.studentService = studentService;
        this.universityService = universityService;
    }


    public void dateFinder() {
        Scanner scanner = new Scanner(System.in);
        date = null;
        while (date == null) {
            System.out.println("Enter a date (yyyy-MM-dd) to simulate or leave empty to use the current date:");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                date = LocalDate.now();
            } else {
                String datePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
                if (input.matches(datePattern)) {
                    try {
                        date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date. Please try again.");
                    }
                } else {
                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                    dateFinder();
                }
            }
            showMenu();
        }
    }

    public void showMenu() {
        try {
            Scanner input = new Scanner(System.in);
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.println("""
                        Student Loan System
                        1.SignUp
                        2.Login
                        3.Exit
                        """);
                int option = input.nextInt();
                input.nextLine();
                switch (option) {
                    case 1 -> continueRunning = !signUp(input);
                    case 2 -> continueRunning = !login(input);
                    case 3 -> {
                        token = null;
                        continueRunning = false;
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input ! Please try again.");
        }
    }

    private boolean login(Scanner input) {
        try {
            System.out.println("Enter username: ");
            String username = input.nextLine().trim();
            System.out.println("Enter password: ");
            String password = input.nextLine().trim();

            token = studentService.login(username, password);
            if (token == null || token.getId() == null) {
                System.out.println("Invalid username or password");
                return false;
            }
            return true;
        } catch (InputMismatchException e) {
            System.out.println("Invalid username or password. Please try again.");
        }
        return false;
    }

    private boolean signUp(Scanner input) {
        try {
            Student student = new Student();
            while (true) {
                System.out.println("Enter first name : ");
                String firstName = input.nextLine().trim().toLowerCase();

                if (firstName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setFirstName(firstName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain only letters and spaces.");
            }

            while (true) {
                System.out.println("Enter last name : ");
                String lastName = input.nextLine().trim().toLowerCase();
                if (lastName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setLastName(lastName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain only letters and spaces.");
            }

            while (true) {
                System.out.println("Enter father's name : ");
                String fatherName = input.nextLine().trim().toLowerCase();
                if (fatherName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setFathersName(fatherName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain only letters and spaces.");
            }

            while (true) {
                System.out.println("Enter mother's name : ");
                String motherName = input.nextLine().trim().toLowerCase();
                if (motherName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setMothersName(motherName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain spaces.");
            }

            while (true) {
                System.out.println("Enter certificate Number : ");
                String certificateNumber = input.nextLine().trim().toLowerCase();
                if (certificateNumber.matches("^[a-zA-Z0-9]+$")) {
                    student.setCertificateNumber(certificateNumber);
                    break;
                }
                System.out.println("Invalid input");
            }

            Integer nationalCode = null;
            try {
                System.out.println("Enter National code : ");
                nationalCode = input.nextInt();
                student.setNationalCode(nationalCode);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");

            }

            input.nextLine();
            while (true) {
                System.out.println("Enter birthDate (format: DD-MM-YYYY): ");
                String birthDate = input.nextLine().trim();
                if (birthDate.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(13|14)\\d\\d$")) {
                    student.setBirthDate(birthDate);
                    break;
                }
                System.out.println("Invalid input. Birth date must be in format DD-MM-YYYY (Iranian calendar).");
            }

            while (true) {
                System.out.println("Enter Gender : ");
                char gender = input.next().charAt(0);
                if (Character.toString(gender).equalsIgnoreCase("m")) {
                    gender = 'm';
                    student.setGender(gender);
                    break;
                } else if (Character.toString(gender).equalsIgnoreCase("f")) {
                    gender = 'f';
                    student.setGender(gender);
                    break;
                }
                System.out.println("Invalid input . For male enter (m) and for female enter (f)");
            }

            System.out.println("Enter student Id : ");
            Integer studentId = input.nextInt();
            student.setStudentId(studentId);
            input.nextLine();

            System.out.println("Enter year of enter : ");
            Integer yearOfEnter = input.nextInt();
            student.setYearOfEnter(yearOfEnter);
            input.nextLine();

            System.out.println("""
                    Enter the level that you are educating in :
                    1.BACHELOR_PEYVASTE
                    2.BACHELOR_NAPEYVASTE
                    3.ASSOCIATE
                    4.MASTER_PEYVASTE
                    5.MASTER_NAPEYVASTE
                    6.PHD_PEYVASTE
                    7.PHD_NAPEYVASTE
                    """);
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> student.setEducationDegree(EducationDegree.BACHELOR_PEYVASTE);
                case 2 -> student.setEducationDegree(EducationDegree.BACHELOR_NAPEYVASTE);
                case 3 -> student.setEducationDegree(EducationDegree.ASSOCIATE);
                case 4 -> student.setEducationDegree(EducationDegree.MASTER_PEYVASTE);
                case 5 -> student.setEducationDegree(EducationDegree.MASTER_NAPEYVASTE);
                case 6 -> student.setEducationDegree(EducationDegree.PHD_PEYVASTER);
                case 7 -> student.setEducationDegree(EducationDegree.PHD_NAPEYVASTE);
                default -> System.out.println("Invalid choice");
            }


            System.out.println("Are you engaged ? ");
            String answer = input.nextLine().trim().toLowerCase();
            student.setEngaged(answer.equalsIgnoreCase("yes"));

            System.out.println("Do you use Dormitory ? ");
            answer = input.nextLine().trim().toLowerCase();
            student.setUsesDormitory(answer.equalsIgnoreCase("yes"));

            University university = new University();
            while (true) {
                System.out.println("Enter the name of university : ");
                String universityName = input.nextLine().toLowerCase();
                assert university != null;
                university.setName(universityName);
                System.out.println("""
                        Select Type of University
                        1.AZAD
                        2.DOLATI_SHABANE
                        3.DOLATI_ROOZANE
                        4.PAYAMNOOR
                        5.PARDIS
                        6.ZARFIAT_MAZAD
                        7.ELMI_KRABORDI
                        8.QEIR_ENTEFAHI
                        """);
                choice = input.nextInt();
                switch (choice) {
                    case 1 -> university.setUniversityType(UniversityType.AZAD);
                    case 2 -> university.setUniversityType(UniversityType.DOLATI_SHABANE);
                    case 3 -> university.setUniversityType(UniversityType.DOLATI_ROOZANE);
                    case 4 -> university.setUniversityType(UniversityType.PAYAMNOOR);
                    case 5 -> university.setUniversityType(UniversityType.PARDIS);
                    case 6 -> university.setUniversityType(UniversityType.ZARFIAT_MAZAD);
                    case 7 -> university.setUniversityType(UniversityType.ELMI_KARBORDI);
                    case 8 -> university.setUniversityType(UniversityType.QEIR_ENTEFAHI);
                }
                university = universityService.findByNameAndUniversityType(universityName, university.getUniversityType());
                if (university != null) {
                    student.setUniversity(university);
                    break;
                }
                System.out.println("University dosent exist ! ");
            }

            input.nextLine();

            String password = new PassGenerator().generatePassword();
            student.setPassword(password);
            assert nationalCode != null;
            String username = Integer.toString(nationalCode);
            student.setUsername(username);
            if (doesStudentAlreadyExist(student)) {
                System.out.println("Student Already exists");
                showMenu();
            } else {
                token = studentService.save(student);
                System.out.println("Your username is : " + token.getUsername());
                System.out.println("Your password is : " + token.getPassword());
                return token != null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!");
        }

        return token != null;
    }

    private boolean doesStudentAlreadyExist(Student student) {
        List<Student> students = studentService.findAll();
        for (Student s : students) {
            return s.getFirstName().equals(student.getFirstName()) && s.getLastName().equals(student.getLastName()) && s.getNationalCode().equals(student.getNationalCode()) ||
                    s.getFirstName().equals(student.getFirstName()) && s.getLastName().equals(student.getLastName());
        }
        return false;
    }

}
