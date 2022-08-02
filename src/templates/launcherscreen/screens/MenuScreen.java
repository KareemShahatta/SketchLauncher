package templates.launcherscreen.screens;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import launcher.StartUp;
import templates.launcherbutton.LauncherButton;
import templates.launcherbutton.LauncherButtonID;
import templates.launcherscreen.CurrentScreenID;
import templates.launcherscreen.TextInfoType;

import static launcher.StartUp.playerCurrentView;
import static templates.launcherimage.LauncherImages.UNSELECTED_FRAME;

public class MenuScreen extends LauncherPaneDesign
{
    private Pane launcherPane;
    private StartUp startUp;

    private Text successText;
    private Text errorText;

    private  LauncherButton loginButton;

    public MenuScreen(StartUp startUp) {

        this.startUp = startUp;
        launcherPane = new Pane();

        loginButton = new LauncherButton(startUp , new Label("Login")  , new ImageView(UNSELECTED_FRAME) , LauncherButtonID.LOGIN);
        LauncherButton registerButton = new LauncherButton(startUp , new Label("Register") ,  new ImageView(UNSELECTED_FRAME) , LauncherButtonID.REGISTER);
        LauncherButton faqButton = new LauncherButton(startUp , new Label("FAQ") ,  new ImageView(UNSELECTED_FRAME) , LauncherButtonID.FAQ);
        LauncherButton importButton = new LauncherButton(startUp , new Label("Import User") ,  new ImageView(UNSELECTED_FRAME) , LauncherButtonID.IMPORT_USER);

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

        successText = startUp.getScreenUtilInstance().createInformationMessage( TextInfoType.INFO  , "");
        successText.setVisible(false);

        errorText = startUp.getScreenUtilInstance().createInformationMessage(TextInfoType.ERROR , "");
        errorText.setVisible(false);

        launcherPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        launcherPane.getChildren().addAll(startUp.getScreenUtilInstance().getSketchLauncherLogo() , framesVB , labelsVB , errorText , successText);
    }

    public Text getSuccessText() {
        return successText;
    }
    public Text getErrorText() {
        return errorText;
    }

    @Override
    public void setViewingPane() {
        playerCurrentView = CurrentScreenID.START;
        startUp.getCurrentLauncherScene().setRoot(launcherPane);
        successText.setVisible(false);
        errorText.setVisible(false);

        if(startUp.getDataHandler().isDataBaseFolderEmpty())
        {
            loginButton.setDisabled(true);
        }
        else
        {
            loginButton.setDisabled(false);
        }
    }

    @Override
    public Pane getPane() {
        return launcherPane;
    }
}
