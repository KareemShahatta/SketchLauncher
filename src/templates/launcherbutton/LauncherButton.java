package templates.launcherbutton;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import launcher.StartUp;

import java.util.ArrayList;
import java.util.List;

import static templates.launcherimage.LauncherImages.*;

public class LauncherButton
{
    static List<LauncherButton> buttonList = new ArrayList<>();

    private LauncherButtonID buttonID;

    private Label button;
    private ImageView buttonFrame;

    private boolean disabled;

    public LauncherButton(StartUp startUp , Label button , ImageView buttonFrame , LauncherButtonID buttonID) {
        this.button = button;
        this.buttonFrame = buttonFrame;

        this.buttonID = buttonID;

        button.setTextFill(Color.valueOf("#004a7f"));
        button.setFont(new Font("Impact" , 35));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(190);

        buttonFrame.setFitWidth(200);
        buttonFrame.setFitHeight(50);

        button.setOnMouseEntered(startUp.getMouseHoverHandler());
        button.setOnMouseExited(startUp.getMouseHoverHandler());
        button.setOnMouseClicked(startUp.getMouseClickHandler());

        buttonList.add(this);
    }
    public LauncherButton(StartUp startUp , Label button , int buttonX , int buttonY , ImageView buttonFrame , int labelX , int labelY  , LauncherButtonID buttonID) {
        this.button = button;
        this.buttonFrame = buttonFrame;

        this.buttonID = buttonID;

        button.setLayoutX(buttonX);
        button.setLayoutY(buttonY);
        button.setTextFill(Color.valueOf("#004a7f"));
        button.setFont(new Font("Impact" , 35));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(190);

        buttonFrame.setLayoutX(labelX);
        buttonFrame.setLayoutY(labelY);
        buttonFrame.setFitWidth(200);
        buttonFrame.setFitHeight(50);

        button.setOnMouseEntered(startUp.getMouseHoverHandler());
        button.setOnMouseExited(startUp.getMouseHoverHandler());
        button.setOnMouseClicked(startUp.getMouseClickHandler());

        buttonList.add(this);
    }

    public Label getButtonLabel() {
        return button;
    }
    public ImageView getButtonFrame() {
        return buttonFrame;
    }

    public LauncherButtonID getButtonID() {
        return buttonID;
    }

    public void selectedButton() {
        buttonFrame.setImage(SELECTED_FRAME);
    }
    public void unSelectedButton() {
        buttonFrame.setImage(UNSELECTED_FRAME);
    }

    public void setDisabled(boolean disable) {
        this.disabled = disable;

        if(disable)
        {
            buttonFrame.setImage(DISABLED_FRAME);
            button.setTextFill(Color.GRAY);
        }
        else
        {
            buttonFrame.setImage(UNSELECTED_FRAME);
            button.setTextFill(Color.valueOf("#004a7f"));
        }
    }
    public boolean isDisabled(){return disabled;}

    public void hide() {
        buttonFrame.setVisible(false);
        button.setVisible(false);
    }
    public void show() {
        buttonFrame.setVisible(true);
        button.setVisible(true);
    }
}
