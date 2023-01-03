package launcher;

import input.KeyboardInput;
import input.MouseInput;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import templates.screen.CustomScreenID;
import templates.screen.ScreenManager;
import templates.sound.CustomSounds;

import static launcher.Details.*;
import static templates.image.Images.LAUNCHER_ICON;
import static templates.screen.CustomScreenID.START;
/**
 * Main class that is the starting point of the entire launcher
 * */
public class Launcher extends Application
{
    //Static field for checking if launcher is progressing anything and getting the currentScreen that is displayed
    public static CustomScreenID currentScreen;
    public static boolean InProgress = false;

    //All those fields are final because we are assigning only 1 value to them when we are initializing them
    private final CustomSounds customSounds = new CustomSounds();
    private final MouseInput mouseInput = new MouseInput(this);
    private final ScreenManager screenManager = new ScreenManager(this);

    private Scene scene;

    //Main methods for starting the program and starting the launcher UI
    @Override public void start(Stage stage) {
        scene = new Scene(new Pane() , WIDTH , HEIGHT);
        scene.setOnKeyPressed(new KeyboardInput(this));

        stage.setOnHiding(event -> Platform.runLater(() -> System.exit(0))); //Terminating the application once the window is closed
        stage.getIcons().add(LAUNCHER_ICON);
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        screenManager.setScreen(START);
    }
    public static void main(String[] args)
    {
        launch(args);
    }

    //Getting for the screenManager, Sounds, and MouseInput, and Scene instances
    public ScreenManager getScreenManager() {
        return screenManager;
    }
    public CustomSounds getSounds() {
        return customSounds;
    }
    public MouseInput getMouseInput() {
        return mouseInput;
    }
    public Scene getScene()
    {
        return scene;
    }
}
