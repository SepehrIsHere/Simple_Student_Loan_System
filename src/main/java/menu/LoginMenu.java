package menu;

import entity.Student;
import entity.University;
import enumerations.EducationDegree;
import enumerations.EducationStatus;
import lombok.Getter;
import service.StudentService;
import service.UniversityService;
import util.PassGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginMenu {
    @Getter
    private Student token = null;
    private final StudentService studentService;
    private final UniversityService universityService;

    public LoginMenu(StudentService studentService, UniversityService universityService) {
        this.studentService = studentService;
        this.universityService = universityService;
    }

    public void showMenu() {
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
    }

    private boolean login(Scanner input) {
        System.out.println("Enter username: ");
        String username = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();
//        Student student = new Student();
//        student.setUsername(username);
//        student.setPassword(password);

        token = studentService.login(username,password);
        if (token == null || token.getId() == null) {
            System.out.println("Invalid username or password");
            return false;
        }
        return true;
    }

    private boolean signUp(Scanner input) {
        try {
            Student student = new Student();
            while (true) {
                System.out.println("Enter first name : ");
                String firstName = input.nextLine().trim();

                if (firstName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setFirstName(firstName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain only letters and spaces.");
            }

            while (true) {
                System.out.println("Enter last name : ");
                String lastName = input.nextLine();
                if (lastName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setLastName(lastName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain only letters and spaces.");
            }

            while (true) {
                System.out.println("Enter father's name : ");
                String fatherName = input.nextLine();
                if (fatherName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setFathersName(fatherName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain only letters and spaces.");
            }

            while (true) {
                System.out.println("Enter mother's name : ");
                String motherName = input.nextLine();
                if (motherName.matches("^[a-zA-Z\\s]{2,30}$")) {
                    student.setMothersName(motherName);
                    break;
                }
                System.out.println("Invalid input. Name must be 2-30 characters and contain spaces.");
            }

            while (true) {
                System.out.println("Enter certificate Number : ");
                String certificateNumber = input.nextLine().trim();
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

            EducationDegree educationDegree = null;
            switch (choice) {
                case 1 -> educationDegree = EducationDegree.BACHELOR_PEYVASTE;
                case 2 -> educationDegree = EducationDegree.BACHELOR_NAPEYVASTE;
                case 3 -> educationDegree = EducationDegree.ASSOCIATE;
                case 4 -> educationDegree = EducationDegree.MASTER_PEYVASTE;
                case 5 -> educationDegree = EducationDegree.MASTER_NAPEYVASTE;
                case 6 -> educationDegree = EducationDegree.PHD_PEYVASTER;
                case 7 -> educationDegree = EducationDegree.PHD_NAPEYVASTE;
                default -> System.out.println("Invalid choice");
            }
            student.setEducationDegree(educationDegree);

            System.out.println("""
                    What is your educational Status ?
                    1.Graduated
                    2.Not Graduated
                    """);
            choice = input.nextInt();
            input.nextLine();

            EducationStatus educationStatus = null;
            switch (choice) {
                case 1 -> educationStatus = EducationStatus.GRADUATED;
                case 2 -> educationStatus = EducationStatus.NOT_GRADUATED;
                default -> System.out.println("Invalid choice");
            }
            student.setEducationDegree(educationDegree);

            System.out.println("Are you engaged ? ");
            String answer = input.nextLine();
            boolean isEngaged = false;
            if (answer.equalsIgnoreCase("Yes")) {
                isEngaged = true;
            } else {
                isEngaged = false;
            }
            student.setEngaged(isEngaged);

            System.out.println("Do you use Dormitory ? ");
            answer = input.nextLine();
            boolean useDormitory = false;
            if (answer.equalsIgnoreCase("Yes")) {
                isEngaged = true;
            } else {
                isEngaged = false;
            }
            student.setUsesDormitory(useDormitory);

            System.out.println("Enter the name of university : ");
            String universityName = input.nextLine();
            University university = universityService.findByName(universityName);
            if (university == null) {

            }
            student.setUniversity(university);


            String password = new PassGenerator().generatePassword();
            student.setPassword(password);
            assert nationalCode != null;
            String username = nationalCode.toString();
            student.setUsername(username);


            token = studentService.save(student);
            return token != null;
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!");
        }
        return token != null;
    }


}
