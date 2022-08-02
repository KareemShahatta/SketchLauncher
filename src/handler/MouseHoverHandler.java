package handler;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import launcher.StartUp;
import templates.launcherbutton.LauncherButton;

public class MouseHoverHandler implements EventHandler<MouseEvent>
{
    private StartUp startUp;
    public MouseHoverHandler(StartUp startUp) {
        this.startUp = startUp;
    }

    @Override
    public void handle(MouseEvent event)
    {
        if(event.getEventType().getSuperType().equals(MouseEvent.MOUSE_ENTERED_TARGET))
        {
            if(event.getSource() instanceof Label)
            {
                Label targetedLabel = (Label) event.getSource();
                if(startUp.getLauncherUtilInstance().isButton(targetedLabel))
                {
                    LauncherButton button = startUp.getLauncherUtilInstance().getButtonFromLabel(targetedLabel);

                    if(!button.isDisabled())
                    {
                        button.selectedButton();

                        Glow effect = new Glow();
                        effect.setLevel(1);
                        targetedLabel.setEffect(effect);

                        startUp.getSoundHandler().playMouseHoverSound();
                    }
                }
            }
        }

        if(event.getEventType().getSuperType().equals(MouseEvent.MOUSE_EXITED_TARGET))
        {
            if(event.getSource() instanceof Label)
            {
                Label targetedLabel = (Label) event.getSource();
                if(startUp.getLauncherUtilInstance().isButton(targetedLabel))
                {
                    LauncherButton button = startUp.getLauncherUtilInstance().getButtonFromLabel(targetedLabel);
                    if(!button.isDisabled())
                    {
                        button.unSelectedButton();

                        targetedLabel.setEffect(null);
                    }
                }
            }
        }
    }
}
