package templates.screen.screens;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import launcher.Launcher;
import templates.button.CustomButton;
import templates.button.CustomButtonID;
import templates.screen.CustomScreen;
/**
 * Screen that is loaded when once the start screen is done playing its animation
 * Contain four buttons for reaching other screens
 * NOTE: The import button is always disabled because that functionality is removed
 * */
public class MenuScreen extends CustomScreen
{
    //this field is final because we are assigning only 1 value to it when we are initializing.
    private final Pane menuPane;

    /**
     * Constructor for setting up the ScreenManager
     * @param launcher an instance from the main launcher class
     * */
    public MenuScreen(Launcher launcher) {
        menuPane = new Pane();

        CustomButton loginButton = new CustomButton(launcher, new Label("Login")  , CustomButtonID.LOGIN);
        CustomButton registerButton = new CustomButton(launcher, new Label("Register") , CustomButtonID.REGISTER);
        CustomButton faqButton = new CustomButton(launcher, new Label("FAQ") , CustomButtonID.FAQ);
        CustomButton importButton = new CustomButton(launcher, new Label("Import User") , null);
        importButton.setDisabled(true);

        VBox labelsVB = new VBox();
        labelsVB.getChildren().addAll(loginButton.getButtonLabel() , registerButton.getButtonLabel() , faqButton.getButtonLabel() , importButton.getButtonLabel());
        labelsVB.setLayoutX(150);
        labelsVB.setLayoutY(150);
        labelsVB.setSpacing(46);

        VBox framesVB = new VBox();
        framesVB.getChildren().addAll(loginButton.getButtonFrame() , registerButton.getButtonFrame() , faqButton.getButtonFrame() , importButton.getButtonFrame());
        framesVB.setLayoutX(145);
        framesVB.setLayoutY(150);
        framesVB.setSpacing(40);

        menuPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        menuPane.getChildren().addAll(getTitle() , framesVB , labelsVB);
    }

    //Returns the pane that contains the screen contain and is used by the screen manager class
    @Override public Pane getPane() {
        return menuPane;
    }
}
