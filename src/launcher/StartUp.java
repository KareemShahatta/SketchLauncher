package launcher;

import handler.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import templates.launcherbutton.LauncherButtonUtil;
import templates.launcherscreen.CurrentScreenID;
import templates.launcherscreen.ScreenUtil;

import static templates.launcherimage.LauncherImages.LAUNCHER_ICON;

public class StartUp extends Application
{
    public static CurrentScreenID playerCurrentView;
    public static boolean InProgress = false;

    private DataHandler dataHandler = new DataHandler(this);
    private SoundHandler soundHandler = new SoundHandler();
    private MouseHoverHandler mouseHoverHandler = new MouseHoverHandler(this);
    private MouseClickHandler mouseclickHandler = new MouseClickHandler(this);

    private ScreenUtil screenUtil = new ScreenUtil();

    private ScreenHandler screenHandler = new ScreenHandler(this);


    private LauncherButtonUtil launcherButtonUtil = new LauncherButtonUtil();

    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        scene = new Scene( screenHandler.getOpenPaneObject().getPane() , 500 , 500);
        scene.setOnKeyPressed(new KeyboardHandler(this));

        stage.getIcons().add(LAUNCHER_ICON);
        stage.setTitle("SketchLauncher Alpha 4.0");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        stage.setOnHiding(event -> Platform.runLater(() ->
        {
            System.out.println("Application Closed  by User");
            System.exit(0);
        }));

        Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(2.2), event ->
            {
                if(dataHandler.isDataBaseFolderExist())
                {
                    screenHandler.getMenuPaneObject().setViewingPane();
                }
                else
                {
                    screenHandler.getDataLoaderScreenObject().setViewingPane();
                }
            }
        )
        );
        loadingDelay.play();
    }

    public static void main(String[] args) {

        launch(args);
    }



    public ScreenUtil getScreenUtilInstance() {
        return screenUtil;
    }
    public LauncherButtonUtil getLauncherUtilInstance() {
        return launcherButtonUtil;
    }

    public ScreenHandler getScreenHandler() {
        return screenHandler;
    }
    public SoundHandler getSoundHandler() {
        return soundHandler;
    }
    public MouseHoverHandler getMouseHoverHandler() {
        return mouseHoverHandler;
    }
    public MouseClickHandler getMouseClickHandler() {
        return mouseclickHandler;
    }
    public DataHandler getDataHandler() {
        return dataHandler;
    }

    public Scene getCurrentLauncherScene() {
        return scene;
    }
    public Stage getCurrentLauncherStage() {
        return stage;
    }
}
