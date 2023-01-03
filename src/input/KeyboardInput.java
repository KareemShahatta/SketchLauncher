package input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import launcher.Launcher;
import templates.screen.CustomScreenID;

import static launcher.Launcher.InProgress;
import static launcher.Launcher.currentScreen;
/**
 * Class for handling the keyboard input
 * */
public class KeyboardInput implements EventHandler<KeyEvent>
{
    //This field is final because we are assigning only 1 value to it when we are initializing.
    private final Launcher launcher;

    /**
     * Constructor for setting up the KeyboardInput
     * @param launcher an instance from the main launcher class
     * */
    public KeyboardInput(Launcher launcher)
    {
        this.launcher = launcher;
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ESCAPE))
        {
            if(!InProgress)
            {
                if (currentScreen.equals(CustomScreenID.LOGIN) || currentScreen.equals(CustomScreenID.REGISTER) || currentScreen.equals(CustomScreenID.FAQ)) {
                    launcher.getScreenManager().setScreen(CustomScreenID.MENU);
                }
            }
        }
    }
}
