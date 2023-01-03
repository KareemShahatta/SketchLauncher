package templates.screen.screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import launcher.Launcher;
import templates.button.CustomButton;
import templates.button.CustomButtonID;
import templates.screen.CustomScreen;
import templates.sound.CustomSoundsID;

import static launcher.Details.*;
import static launcher.Launcher.InProgress;
/**
 * Screen that handles users logging in and reaching the Main screen
 * NOTE: Functionality for testing the inputted credentials is removed
 * */
public class LoginScreen extends CustomScreen
{
    //All those fields are final because we are assigning only 1 value to them when we are initializing them
    private final Pane loginPane;

    private final Launcher launcher;

    private final TextField textField;
    private final PasswordField passwordField;
    private final CustomButton enterButton;
    private final ImageView spinner;
    private final Text errorText;

    /**
     * Constructor for setting up the ScreenManager
     * @param launcher an instance from the main launcher class
     * */
    public LoginScreen(Launcher launcher) {
        this.launcher = launcher;
        this.spinner = createSpinner();

        loginPane = new Pane();

        Glow glowEffect = new Glow();
        glowEffect.setLevel(1);

        Text buildText = new Text(SUB_TITLE);
        buildText.setFont(new Font(FONT , 24));
        buildText.setFill(Color.valueOf(COLOR));
        buildText.setEffect(glowEffect);
        buildText.setX(155);
        buildText.setY(160);

        enterButton = new CustomButton(launcher, new Label("Let's Go") , 145 , 380 , 145 , 380 , CustomButtonID.LOGIN_ENTER);

        textField = createTextField("Username" , 300 , 40);

        passwordField =  createPasswordField("Password" , 300 , 40);

        errorText = createHintMessage(false, "Please enter your username and password");
        errorText.setVisible(false);

        VBox fieldsVB = new VBox();
        fieldsVB.getChildren().addAll(textField , passwordField);
        fieldsVB.setLayoutX(95);
        fieldsVB.setLayoutY(240);
        fieldsVB.setSpacing(25);

        loginPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        loginPane.getChildren().addAll(buildText , getTitle() , spinner , errorText , fieldsVB , enterButton.getButtonLabel() , enterButton.getButtonFrame());
    }

    //Returns the pane that contains the screen contain and is used by the screen manager class
    @Override public Pane getPane() {
        return loginPane;
    }

    //Method for hiding the error text after it has been displayed
    public void hideErrorText()
    {
        errorText.setVisible(false);
    }

    //Method for handling the Login button being clicked in the Login screen
    public void loginButtonClicked() {
        int loadingTime = 1;
        InProgress = true;
        textField.setDisable(true);
        passwordField.setDisable(true);
        enterButton.hide();
        errorText.setVisible(false);
        animateSpinner(loadingTime , spinner);

        Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(loadingTime), event ->
        {
            InProgress = false;
            textField.setDisable(false);
            passwordField.setDisable(false);
            enterButton.show();
            errorText.setVisible(true);
            launcher.getSounds().playSound(CustomSoundsID.ERROR);
        }
        ));
        loadingDelay.play();
    }
}
