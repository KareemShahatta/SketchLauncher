package handler;

import launcher.StartUp;
import templates.launcherscreen.screens.*;

public class ScreenHandler
{
    private OpenScreen openPane;
    private MenuScreen menuScreen;
    private LoginScreen loginScreen;
    private RegisterScreen registerScreen;
    private FAQScreen faqScreen;
    private DataLoaderScreen dataLoaderScreen;
    private MainScreen mainScreen;

    public ScreenHandler(StartUp startUp)
    {
        openPane = new OpenScreen(startUp);
        menuScreen = new MenuScreen(startUp);
        loginScreen = new LoginScreen(startUp);
        registerScreen = new RegisterScreen(startUp);
        faqScreen = new FAQScreen(startUp);
        dataLoaderScreen = new DataLoaderScreen(startUp);
        mainScreen = new MainScreen(startUp);
    }

    public OpenScreen getOpenPaneObject() {
        return openPane;
    }
    public MenuScreen getMenuPaneObject() {
        return menuScreen;
    }
    LoginScreen getLoginPaneObject() {
        return loginScreen;
    }
    RegisterScreen getRegisterPaneObject() {
        return registerScreen;
    }
    FAQScreen getFAQPaneObject() {
        return faqScreen;
    }
    public DataLoaderScreen getDataLoaderScreenObject() {
        return dataLoaderScreen;
    }
    public MainScreen getMainScreenObject() {
        return mainScreen;
    }
}
