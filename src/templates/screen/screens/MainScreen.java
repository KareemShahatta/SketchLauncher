package templates.screen.screens;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import templates.screen.CustomScreen;
/**
 * Screen that is displayed once a user login and marks where development ended
 * */
public class MainScreen extends CustomScreen
{
    //this field is final because we are assigning only 1 value to it when we are initializing.
    private final Pane mainPane;

    public MainScreen() {
        mainPane = new Pane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        Text text = new Text("End of development");
        text.setY(250);
        text.setX(195);
        text.setFill(Color.WHITE);
        mainPane.getChildren().add(text);
    }

    //Returns the pane that contains the screen contain and is used by the screen manager class
    @Override public Pane getPane() {
        return mainPane;
    }
}
