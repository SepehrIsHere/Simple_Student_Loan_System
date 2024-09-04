import entity.Student;
import util.ApplicationContext;

public class JpaApplication {
    /**
     *     month : 10 day : 23 = 1 aban , month : 10 day : 30 = 8 aban
     *     month : 2 day : 14 = Bahman 25 , month : 2 day : 21 = 2 Esfand
     */
    public static void main(String[] args) {
        ApplicationContext context = ApplicationContext.getInstance();
        context.getMainMenu().showLoginMenu();

    }
}
