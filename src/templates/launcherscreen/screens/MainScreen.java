package templates.launcherscreen.screens;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import launcher.StartUp;
import templates.launcherscreen.CurrentScreenID;

import static launcher.StartUp.playerCurrentView;

public class MainScreen extends LauncherPaneDesign
{

    private StartUp startUp;

    private Pane gamePane;

    public MainScreen(StartUp startUp)
    {
        this.startUp = startUp;
        gamePane = new Pane();
        gamePane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
    }

    @Override
    public void setViewingPane() {
        playerCurrentView = CurrentScreenID.MAIN;
        startUp.getCurrentLauncherScene().setRoot(gamePane);
    }

    @Override
    public Pane getPane() {
        return gamePane;
    }

}
