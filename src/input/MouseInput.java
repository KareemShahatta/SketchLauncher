package input;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import launcher.Launcher;
import templates.sound.CustomSoundsID;
import templates.button.CustomButton;

import static templates.screen.CustomScreenID.*;
/**
 * Class for handling all sorts of mouse input
 * Responsible for handling click and hover events
 * */
public class MouseInput implements EventHandler<MouseEvent>
{
    //This field is final because we are assigning only 1 value to it when we are initializing.
    private final Launcher launcher;

    /**
     * Constructor for setting up the MouseInput
     * @param launcher an instance from the main launcher class
     * */
    public MouseInput(Launcher launcher)
    {
        this.launcher = launcher;
    }

    @Override
    public void handle(MouseEvent event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
        {
            mouseClickEvent(event);
        }

        if(event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
        {
            mouseHoverEnterTargetEvent(event);
        }

        if(event.getEventType().equals(MouseEvent.MOUSE_EXITED))
        {
            mouseHoverExitTargetEvent(event);
        }
    }

    //Helper method for handling different mouse events, and applying the DRY principle
    private void mouseHoverEnterTargetEvent(MouseEvent event) {
        if(event.getSource() instanceof Label)
        {
            Label targetedLabel = (Label) event.getSource();
            if(CustomButton.isButton(targetedLabel))
            {
                CustomButton button = CustomButton.getButton(targetedLabel);

                if(!button.isDisabled())
                {
                    button.selectedButton();

                    Glow effect = new Glow();
                    effect.setLevel(1);
                    targetedLabel.setEffect(effect);

                    launcher.getSounds().playSound(CustomSoundsID.HOVER);
                }
            }
        }
    }
    private void mouseHoverExitTargetEvent(MouseEvent event) {
        if(event.getSource() instanceof Label)
        {
            Label targetedLabel = (Label) event.getSource();
            if(CustomButton.isButton(targetedLabel))
            {
                CustomButton button = CustomButton.getButton(targetedLabel);
                if(!button.isDisabled())
                {
                    button.unSelectedButton();

                    targetedLabel.setEffect(null);
                }
            }
        }
    }
    private void mouseClickEvent(MouseEvent event) {
        if (event.getSource() instanceof Label)
        {
            Label targetedLabel = (Label) event.getSource();

            if (CustomButton.isButton(targetedLabel))
            {
                CustomButton button = CustomButton.getButton(targetedLabel);
                if(!button.isDisabled())
                {
                    launcher.getSounds().playSound(CustomSoundsID.CLICK);

                    switch(button.getButtonID())
                    {
                        case LOGIN : launcher.getScreenManager().setScreen(LOGIN); return;

                        case REGISTER : launcher.getScreenManager().setScreen(REGISTER); return;

                        case FAQ : launcher.getScreenManager().setScreen(FAQ); return;

                        case LOGIN_ENTER : launcher.getScreenManager().getLoginScreen().loginButtonClicked(); return;

                        case REGISTER_ENTER : launcher.getScreenManager().getRegisterScreen().registerButtonClicked();
                    }
                }
            }
        }
    }
}
