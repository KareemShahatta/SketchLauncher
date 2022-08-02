package handler;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import launcher.StartUp;
import templates.launcherscreen.CurrentScreenID;

import static launcher.StartUp.InProgress;
import static launcher.StartUp.playerCurrentView;

public class KeyboardHandler implements EventHandler<KeyEvent>
{
    private StartUp startUp;
    public KeyboardHandler(StartUp startUp)
    {
        this.startUp = startUp;
    }

    @Override
    public void handle(KeyEvent event)
    {
        if(event.getCode().equals(KeyCode.ESCAPE))
        {
            if(!InProgress)
            {
                if (playerCurrentView.equals(CurrentScreenID.LOGIN) || playerCurrentView.equals(CurrentScreenID.REGISTER) || playerCurrentView.equals(CurrentScreenID.FAQ)) {
                    startUp.getScreenHandler().getMenuPaneObject().setViewingPane();
                }
            }
        }
    }
}
