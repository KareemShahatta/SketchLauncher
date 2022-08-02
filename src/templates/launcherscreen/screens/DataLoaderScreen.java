package templates.launcherscreen.screens;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import launcher.StartUp;
import templates.launcherbutton.LauncherButton;
import templates.launcherbutton.LauncherButtonID;
import templates.launcherscreen.CurrentScreenID;
import templates.launcherscreen.TextInfoType;

import static launcher.StartUp.InProgress;
import static launcher.StartUp.playerCurrentView;
import static templates.launcherimage.LauncherImages.QUESTION_FRAME;
import static templates.launcherimage.LauncherImages.UNSELECTED_FRAME;

public class DataLoaderScreen extends LauncherPaneDesign
{

    private Pane launcherPane;
    private StartUp startUp;

    private ImageView spinner;

    private LauncherButton confirmButton;

    public DataLoaderScreen(StartUp startUp) {
        this.startUp = startUp;
        this.spinner = startUp.getScreenUtilInstance().createSpinner();

        launcherPane = new Pane();

        ImageView mainFrame = new ImageView(QUESTION_FRAME);
        mainFrame.setX(15);
        mainFrame.setY(200);
        mainFrame.setFitWidth(480);
        mainFrame.setFitHeight(200);

        Glow glowEffect = new Glow();
        glowEffect.setLevel(1);

        Text text = new Text("Sketch Launcher failed to locate a data base.\n A new data base will be created for the launcher to work.");
        text.setFont(new Font("IMPACT" , 16));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFill(Color.valueOf("#004a7f"));
        text.setEffect(glowEffect);
        text.setX(70);
        text.setY(260);

        confirmButton = new LauncherButton(startUp , new Label("Confirm") , 165 , 305 , new ImageView(UNSELECTED_FRAME) , 160 , 300 , LauncherButtonID.DATA_BASE_CREATION_CONFIRM);

        launcherPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        launcherPane.getChildren().addAll(startUp.getScreenUtilInstance().getSketchLauncherLogo() , spinner , mainFrame , text , confirmButton.getButtonFrame() , confirmButton.getButtonLabel());
    }

    @Override
    public void setViewingPane() {
        playerCurrentView = CurrentScreenID.DATALOADOR;
        startUp.getCurrentLauncherScene().setRoot(launcherPane);
    }

    @Override
    public Pane getPane() {
        return launcherPane;
    }

    public void createLauncherDataBase() {
        int loadingTime = 1;

        InProgress = true;
        confirmButton.hide();
        startUp.getScreenUtilInstance().rotateSpinner(loadingTime , spinner);

        Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(loadingTime), event ->
        {
            InProgress = false;
            startUp.getDataHandler().createDataBaseFolder();

            startUp.getScreenUtilInstance().editInformationMessage(startUp.getScreenHandler().getMenuPaneObject().getSuccessText() , "New data base folder have been created" , TextInfoType.INFO);
            startUp.getScreenHandler().getMenuPaneObject().setViewingPane();
            startUp.getSoundHandler().playSuccessSound();

            startUp.getScreenHandler().getMenuPaneObject().getSuccessText().setVisible(true);

            confirmButton.show();
        }
        ));
        loadingDelay.play();
    }
}
