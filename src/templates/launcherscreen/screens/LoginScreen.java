package templates.launcherscreen.screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
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
import launcher.StartUp;
import templates.launcherbutton.LauncherButton;
import templates.launcherbutton.LauncherButtonID;
import templates.launcherscreen.CurrentScreenID;
import templates.launcherscreen.TextInfoType;

import java.io.FileNotFoundException;

import static launcher.StartUp.InProgress;
import static launcher.StartUp.playerCurrentView;
import static templates.launcherimage.LauncherImages.UNSELECTED_FRAME;

public class LoginScreen extends LauncherPaneDesign
{
    private StartUp startUp;
    private ImageView spinner;

    private Pane gamePane;

    private TextField textField;
    private PasswordField passwordField;

    private Text errorText;
    private LauncherButton enterButton;

    public LoginScreen(StartUp startUp) {

        this.startUp = startUp;
        this.spinner = startUp.getScreenUtilInstance().createSpinner();

        gamePane = new Pane();

        Glow glowEffect = new Glow();
        glowEffect.setLevel(1);

        Text buildText = new Text("╠ Build Alpha 4.0 ╢");
        buildText.setFont(new Font("impact" , 24));
        buildText.setFill(Color.valueOf("#004a7f"));
        buildText.setEffect(glowEffect);
        buildText.setX(155);
        buildText.setY(160);

        enterButton = new LauncherButton(startUp , new Label("Let's Go") , 145 , 380 , new ImageView(UNSELECTED_FRAME) , 145 , 380 , LauncherButtonID.ENTER_LOGIN_DATA);

        textField = startUp.getScreenUtilInstance().createTextField("Username" , 300 , 40);

        passwordField =  startUp.getScreenUtilInstance().createPasswordField("Password" , 300 , 40);

        errorText = startUp.getScreenUtilInstance().createInformationMessage(TextInfoType.ERROR , "Please enter your username and password");
        errorText.setVisible(false);

        VBox fieldsVB = new VBox();
        fieldsVB.getChildren().addAll(textField , passwordField);
        fieldsVB.setLayoutX(95);
        fieldsVB.setLayoutY(240);
        fieldsVB.setSpacing(25);
        gamePane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        gamePane.getChildren().addAll(buildText , startUp.getScreenUtilInstance().getSketchLauncherLogo() , spinner , errorText , fieldsVB , enterButton.getButtonLabel() , enterButton.getButtonFrame());
    }

    @Override
    public void setViewingPane() {
        playerCurrentView = CurrentScreenID.LOGIN;
        startUp.getCurrentLauncherScene().setRoot(gamePane);
        errorText.setVisible(false);
    }

    @Override
    public Pane getPane() {
        return gamePane;
    }

    public void validUserData() {
        int loadingTime = 1;

        InProgress = true;
        textField.setDisable(true);
        passwordField.setDisable(true);
        enterButton.hide();
        errorText.setVisible(false);
        startUp.getScreenUtilInstance().rotateSpinner(loadingTime , spinner);


        Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(loadingTime), event ->
        {
            try
            {
                if(!textField.getText().equalsIgnoreCase(""))
                {
                    if (startUp.getDataHandler().isUserFileExists(textField.getText()))
                    {
                        if(!startUp.getDataHandler().isUserDataCorrupted(textField.getText()))
                        {
                            if(!passwordField.getText().equalsIgnoreCase(""))
                            {

                                if (startUp.getDataHandler().getUserPassword(textField.getText()).equalsIgnoreCase(passwordField.getText()))
                                {
                                    startUp.getScreenHandler().getMainScreenObject().setViewingPane();
                                    startUp.getCurrentLauncherStage().setWidth(600);
                                    startUp.getCurrentLauncherStage().setWidth(1200);
                                    startUp.getCurrentLauncherStage().setX(400);
                                    startUp.getSoundHandler().playSuccessSound();

                                    textField.clear();
                                    passwordField.clear();
                                    errorText.setVisible(false);
                                }
                                else
                                {
                                    errorText.setVisible(true);
                                    startUp.getSoundHandler().playerErrorSound();
                                    startUp.getScreenUtilInstance().editInformationMessage(errorText, "That is not the correct password", TextInfoType.ERROR);
                                }
                            }
                            else
                            {
                                errorText.setVisible(true);
                                startUp.getSoundHandler().playerErrorSound();
                                startUp.getScreenUtilInstance().editInformationMessage(errorText, "Please enter a password", TextInfoType.ERROR);
                            }
                        }
                        else
                        {
                            errorText.setVisible(true);
                            startUp.getSoundHandler().playerErrorSound();
                            startUp.getScreenUtilInstance().editInformationMessage(errorText, "User's data file is corrupted and can not be used", TextInfoType.ERROR);
                        }
                    }
                    else
                    {
                        errorText.setVisible(true);
                        startUp.getSoundHandler().playerErrorSound();
                        startUp.getScreenUtilInstance().editInformationMessage(errorText, "That user is not registered in our data base", TextInfoType.ERROR);
                    }
                }
                else
                {
                    errorText.setVisible(true);
                    startUp.getSoundHandler().playerErrorSound();
                    startUp.getScreenUtilInstance().editInformationMessage(errorText, "Please enter a username", TextInfoType.ERROR);
                }
            }
            catch (FileNotFoundException Error)
            {
                startUp.getScreenUtilInstance().editInformationMessage(errorText , "Couldn't find User's DataFile" , TextInfoType.ERROR);
                startUp.getSoundHandler().playerErrorSound();
                errorText.setVisible(true);
            }

            enterButton.show();
            textField.setDisable(false);
            passwordField.setDisable(false);
            InProgress = false;
        }
        ));
        loadingDelay.play();
    }
}
