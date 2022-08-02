package handler;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import launcher.StartUp;
import templates.launcherbutton.LauncherButton;
import templates.launcherscreen.TextInfoType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MouseClickHandler implements EventHandler<MouseEvent>
{
    private StartUp startUp;
    public MouseClickHandler(StartUp startUp) {
        this.startUp = startUp;
    }

    @Override
    public void handle(MouseEvent event)
    {
        if (event.getSource() instanceof Label)
        {
            Label targetedLabel = (Label) event.getSource();

            if (startUp.getLauncherUtilInstance().isButton(targetedLabel))
            {
                LauncherButton button = startUp.getLauncherUtilInstance().getButtonFromLabel(targetedLabel);
                if(!button.isDisabled())
                {
                    startUp.getSoundHandler().playMouseClickSound();

                    switch(button.getButtonID())
                    {
                        case LOGIN : startUp.getScreenHandler().getLoginPaneObject().setViewingPane(); return;

                        case REGISTER : startUp.getScreenHandler().getRegisterPaneObject().setViewingPane(); return;

                        case FAQ : startUp.getScreenHandler().getFAQPaneObject().setViewingPane(); return;

                        case ENTER_LOGIN_DATA : startUp.getScreenHandler().getLoginPaneObject().validUserData(); return;

                        case ENTER_REGISTER_DATA : startUp.getScreenHandler().getRegisterPaneObject().validRegistrationData(); return;

                        case DATA_BASE_CREATION_CONFIRM: startUp.getScreenHandler().getDataLoaderScreenObject().createLauncherDataBase(); return;

                        case IMPORT_USER:   startUp.getDataHandler().importUserFile();
                    }
                }
            }
        }
    }
}
