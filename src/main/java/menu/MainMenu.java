package menu;

import entity.Student;

public class MainMenu {

    private Student token;
    private final LoginMenu loginMenu;
    private final StudentMenu studentMenu;;

    public MainMenu(LoginMenu loginMenu, StudentMenu studentMenu) {
        this.loginMenu = loginMenu;
        this.studentMenu = studentMenu;
    }

    public void showLoginMenu(){
        while(true){
            loginMenu.dateFinder();
            token = loginMenu.getToken();
            if(token != null){
                studentMenu.showMenu(token);
                token = null;
            }else {
                break;
            }
        }
        System.out.println("Exiting application");
    }
}
