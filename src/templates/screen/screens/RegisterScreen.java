package templates.screen.screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

import static launcher.Details.COLOR;
import static launcher.Details.FONT;
import static launcher.Launcher.InProgress;
/**
 * Screen that handles users registering their information
 * NOTE: Functionality for testing the inputted credentials is removed
 * */
public class RegisterScreen extends CustomScreen
{
    //All those fields are final because we are assigning only 1 value to them when we are initializing them
    private final Pane registerPane;

    private final Launcher launcher;

    private final TextField nameTextField;
    private final TextField emailTextField;
    private final PasswordField firstPasswordField;
    private final PasswordField secondPasswordField;
    private final CheckBox updateChecker;
    private final CustomButton registerButton;
    private final ImageView spinner;
    private final Text successText;

    /**
     * Constructor for setting up the ScreenManager
     * @param launcher an instance from the main launcher class
     * */
    public RegisterScreen(Launcher launcher) {
        this.launcher = launcher;
        spinner = createSpinner();

        registerPane = new Pane();

        registerButton = new CustomButton(launcher, new Label("Register") , 145 , 390 , 145 , 390 , CustomButtonID.REGISTER_ENTER);

        nameTextField = createTextField("Username:" , 400 , 10);
        emailTextField = createTextField("Email Address:" , 450 , 10);

        firstPasswordField = createPasswordField("Password:" , 450 , 10);
        secondPasswordField = createPasswordField("Confirm Password:" , 450 , 10);

        successText = createHintMessage( true  , "You are now successfully registered at the SketchLauncher's data base.");
        successText.setVisible(false);

        updateChecker = new CheckBox();
        updateChecker.setText("I want to receive update news");
        updateChecker.setTextFill(Color.valueOf(COLOR));
        updateChecker.setFont(new Font(FONT , 20));

        VBox fieldsVB = new VBox();
        fieldsVB.getChildren().addAll(nameTextField , emailTextField , firstPasswordField , secondPasswordField , updateChecker);
        fieldsVB.setLayoutX(20);
        fieldsVB.setLayoutY(150);
        fieldsVB.setSpacing(12);

        registerPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        registerPane.getChildren().addAll(getTitle() , fieldsVB , successText , spinner , registerButton.getButtonLabel() , registerButton.getButtonFrame());
    }

    //Returns the pane that contains the screen contain and is used by the screen manager class
    @Override public Pane getPane() {
        return registerPane;
    }

    //Method for hiding the success text after it has been displayed
    public void hideSuccessText()
    {
        successText.setVisible(false);
    }

    //Method for handling the Register button being clicked in the Register screen
    public void registerButtonClicked() {
        int loadingTime = 1;

        InProgress = true;
        registerButton.hide();

        successText.setVisible(false);

        nameTextField.setDisable(true);
        emailTextField.setDisable(true);
        firstPasswordField.setDisable(true);
        secondPasswordField.setDisable(true);
        updateChecker.setDisable(true);

        animateSpinner(loadingTime , spinner);

        Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(loadingTime), event -> {
            InProgress = false;

            registerButton.show();
            nameTextField.setDisable(false);
            emailTextField.setDisable(false);
            firstPasswordField.setDisable(false);
            secondPasswordField.setDisable(false);
            updateChecker.setDisable(false);
            successText.setVisible(true);
            nameTextField.clear();
            emailTextField.clear();
            firstPasswordField.clear();
            secondPasswordField.clear();
            updateChecker.setSelected(false);
            launcher.getSounds().playSound(CustomSoundsID.SUCCESS);
        }
        ));
        loadingDelay.play();
    }
}
