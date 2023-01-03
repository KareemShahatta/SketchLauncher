package templates.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import launcher.Launcher;
import templates.screen.screens.*;

import static launcher.Launcher.currentScreen;
import static templates.screen.CustomScreenID.*;
/**
 * Screen managing class
 * Responsible for changing the screen view of the application.
 * Screen changing methods are being provided in this class only.
 * */
public class ScreenManager
{
    //All those fields are final because we are assigning only 1 value to them when we are initializing them
    Launcher launcher;
    private final StartScreen startScreen;
    private final MenuScreen menuScreen;
    private final LoginScreen loginScreen;
    private final RegisterScreen registerScreen;
    private final FAQScreen faqScreen;
    private final MainScreen mainScreen;

    /**
     * Constructor for setting up the ScreenManager
     * @param launcher an instance from the main launcher class
     * */
    public ScreenManager(Launcher launcher) {
        this.launcher = launcher;
        startScreen = new StartScreen(launcher);
        menuScreen = new MenuScreen(launcher);
        loginScreen = new LoginScreen(launcher);
        registerScreen = new RegisterScreen(launcher);
        faqScreen = new FAQScreen();
        mainScreen = new MainScreen();
    }

    /**
     * Method for setting the screen inside the launcher windows
     * @param screenID CustomScreenID for the desired screen to be displayed
     * */
    public void setScreen(CustomScreenID screenID) {
        switch (screenID)
        {
            case START : updateScreen(screenID, startScreen.getPane()); break;
            case MENU : updateScreen(screenID, menuScreen.getPane()); break;
            case LOGIN : updateScreen(screenID, loginScreen.getPane()); break;
            case REGISTER : updateScreen(screenID, registerScreen.getPane()); break;
            case FAQ : updateScreen(screenID, faqScreen.getPane()); break;
            case MAIN : updateScreen(screenID, mainScreen.getPane()); break;
        }
    }

    //Helper method for setScreen that handles additional feature for setting the Start, Register, and Login screens
    //NOTE!! responsible for transiting from Start to Menu after the animation in Start is done
    private void updateScreen(CustomScreenID id, Pane pane) {
        currentScreen = id;
        launcher.getScene().setRoot(pane);

        if(id.equals(LOGIN))
        {
            loginScreen.hideErrorText();
        }
        else if(id.equals(REGISTER))
        {
            registerScreen.hideSuccessText();
        }
        else if(id.equals(START))
        {
            Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(2.2), event -> setScreen(MENU)));
            loadingDelay.play();
        }
    }

    //Methods for getting instance for the Login and Register screen to call its button click action method
    public LoginScreen getLoginScreen(){return  loginScreen;}
    public RegisterScreen getRegisterScreen(){return registerScreen;}
}
