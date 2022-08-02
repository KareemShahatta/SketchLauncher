package templates.launcherscreen.screens;

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
import launcher.StartUp;
import templates.launcherbutton.LauncherButton;
import templates.launcherbutton.LauncherButtonID;
import templates.launcherscreen.CurrentScreenID;
import templates.launcherscreen.TextInfoType;

import java.io.IOException;

import static launcher.StartUp.InProgress;
import static launcher.StartUp.playerCurrentView;
import static templates.launcherimage.LauncherImages.UNSELECTED_FRAME;

public class RegisterScreen extends LauncherPaneDesign
{
    private StartUp startUp;
    private ImageView spinner;

    private Pane gamePane;

    private LauncherButton registerButton;

    private TextField nameTextField;
    private TextField emailTextField;

    private PasswordField firstPasswordField;
    private PasswordField secondPasswordField;

    private Text successText;
    private Text errorText;

    private CheckBox updateChecker;

    public RegisterScreen(StartUp startUp)
    {

        this.startUp = startUp;
        this.spinner = startUp.getScreenUtilInstance().createSpinner();

        gamePane = new Pane();

        registerButton = new LauncherButton(startUp , new Label("Register") , 145 , 390 , new ImageView(UNSELECTED_FRAME) , 145 , 390 , LauncherButtonID.ENTER_REGISTER_DATA);

        nameTextField = startUp.getScreenUtilInstance().createTextField("Username:" , 400 , 10);
        emailTextField = startUp.getScreenUtilInstance().createTextField("Email Address:" , 450 , 10);

        firstPasswordField =  startUp.getScreenUtilInstance().createPasswordField("Password:" , 450 , 10);
        secondPasswordField =  startUp.getScreenUtilInstance().createPasswordField("Confirm Password:" , 450 , 10);

        successText = startUp.getScreenUtilInstance().createInformationMessage( TextInfoType.INFO  , "You are now successfully registered at the SketchLauncher's data base.");
        successText.setVisible(false);

        errorText = startUp.getScreenUtilInstance().createInformationMessage(TextInfoType.ERROR , "");
        errorText.setVisible(false);

        updateChecker = new CheckBox();
        updateChecker.setText("I want to receive update news");
        updateChecker.setTextFill(Color.valueOf("#004a7f"));
        updateChecker.setFont(new Font("Impact" , 20));

        VBox fieldsVB = new VBox();
        fieldsVB.getChildren().addAll(nameTextField , emailTextField , firstPasswordField , secondPasswordField , updateChecker);
        fieldsVB.setLayoutX(20);
        fieldsVB.setLayoutY(150);
        fieldsVB.setSpacing(12);

        gamePane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        gamePane.getChildren().addAll(startUp.getScreenUtilInstance().getSketchLauncherLogo() , fieldsVB , successText , spinner , errorText , registerButton.getButtonLabel() , registerButton.getButtonFrame());
    }

    @Override
    public void setViewingPane() {
        playerCurrentView = CurrentScreenID.REGISTER;
        startUp.getCurrentLauncherScene().setRoot(gamePane);
        errorText.setVisible(false);
        successText.setVisible(false);
    }

    @Override
    public Pane getPane() {
        return gamePane;
    }


    public void validRegistrationData() {
        int loadingTime = 1;

        InProgress = true;
        registerButton.hide();

        successText.setVisible(false);
        errorText.setVisible(false);

        nameTextField.setDisable(true);
        emailTextField.setDisable(true);
        firstPasswordField.setDisable(true);
        secondPasswordField.setDisable(true);
        updateChecker.setDisable(true);

        startUp.getScreenUtilInstance().rotateSpinner(loadingTime , spinner);

        Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(loadingTime), event ->
        {
            if(verifyUserName(nameTextField.getText()))
            {
                if(verifyEmailAddress(emailTextField.getText()))
                {
                    if(verifyPasswords(firstPasswordField.getText() , secondPasswordField.getText()))
                    {
                        try
                        {
                            startUp.getDataHandler().createUserFile(nameTextField.getText() , emailTextField.getText() , firstPasswordField.getText() , updateChecker.isSelected());
                            successText.setVisible(true);
                            nameTextField.clear();
                            emailTextField.clear();
                            firstPasswordField.clear();
                            secondPasswordField.clear();
                            updateChecker.setSelected(false);
                            startUp.getSoundHandler().playSuccessSound();
                        }
                        catch (IOException Error)
                        {
                            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Couldn't Create User's DataFile" , TextInfoType.ERROR);
                            startUp.getSoundHandler().playerErrorSound();
                            errorText.setVisible(true);
                        }
                    }
                    else
                    {
                        startUp.getSoundHandler().playerErrorSound();
                        errorText.setVisible(true);
                    }
                }
                else
                {
                    startUp.getSoundHandler().playerErrorSound();
                    errorText.setVisible(true);
                }
            }
            else
            {
                startUp.getSoundHandler().playerErrorSound();
                errorText.setVisible(true);
            }

            nameTextField.setDisable(false);
            emailTextField.setDisable(false);
            firstPasswordField.setDisable(false);
            secondPasswordField.setDisable(false);
            updateChecker.setDisable(false);

            registerButton.show();
            InProgress = false;
        }
        ));
        loadingDelay.play();
    }

    private boolean verifyUserName(String userName) {
        if(userName.equalsIgnoreCase(""))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Please enter a username" , TextInfoType.ERROR);
        }
        else if(userName.length() < 6)
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Username must be longer than 6 characters" , TextInfoType.ERROR);
        }
        else if(!userName.matches("[a-zA-Z]+"))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Username must contain letters only" , TextInfoType.ERROR);
        }
        else if(startUp.getDataHandler().isUserFileExists(userName))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "That username is already taken" , TextInfoType.ERROR);
        }
        else
        {
            return true;
        }

        return false;
    }
    private boolean verifyEmailAddress(String email) {
        if(email.equalsIgnoreCase(""))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Please enter an email address" , TextInfoType.ERROR);
        }
        else if(!email.contains("@") || !email.contains(".com"))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Email is not valid" , TextInfoType.ERROR);
        }
        else
        {
            return true;
        }

        return false;
    }
    private boolean verifyPasswords(String password1 , String password2) {
        if (password1.equalsIgnoreCase(""))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Please enter a password" , TextInfoType.ERROR);
        }
        else if (!password1.matches("[0-9]+"))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Password must consist of numbers only" , TextInfoType.ERROR);
        }
        else if (password1.length() < 8)
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "Password can't be lower than 8 characters" , TextInfoType.ERROR);
        }
        else if (!password1.equalsIgnoreCase(password2))
        {
            startUp.getScreenUtilInstance().editInformationMessage(errorText , "The 2 passwords don't match" , TextInfoType.ERROR);
        }
        else
        {
            return true;
        }

        return false;
    }
}
