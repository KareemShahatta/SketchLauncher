package templates.launcherbutton;

import javafx.scene.control.Label;

public class LauncherButtonUtil
{
    public boolean isButton(Label button)
    {
        for(LauncherButton gameButton : LauncherButton.buttonList)
        {
            if(gameButton.getButtonLabel().equals(button))
            {
                return true;
            }
        }

        return false;
    }

    public LauncherButton getButtonFromLabel(Label button)
    {
        for(LauncherButton gameButton : LauncherButton.buttonList)
        {
            if(gameButton.getButtonLabel().equals(button))
            {
                return gameButton;
            }
        }

        return null;
    }
}
