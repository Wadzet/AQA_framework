package task_11;

public class HomePageBO {
    private final SignUpPagePO signUpPagePO;
    private final HomePagePO homePagePO;

    public HomePageBO(SignUpPagePO signUpPagePO, HomePagePO homePagePO) {
        this.signUpPagePO = signUpPagePO;
        this.homePagePO = homePagePO;
    }

    public boolean registerNewUser(String username, String password) {
        signUpPagePO.openSignUpModal();
        signUpPagePO.enterUsername(username);
        signUpPagePO.enterPassword(password);
        signUpPagePO.clickSignUp();
        signUpPagePO.handleAlert();
        return homePagePO.isUserLoggedIn();
    }
}

