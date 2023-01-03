package templates.button;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import launcher.Launcher;

import java.util.ArrayList;
import java.util.List;

import static templates.image.Images.*;
/**
 * Class for creating a custom styled button for our launcher
 * */
public class CustomButton
{
    //Static list because we need to keep track of how many buttons have been created
    static List<CustomButton> buttonList = new ArrayList<>();

    //All those fields are final because we are assigning only 1 value to them when we are initializing them
    private final CustomButtonID buttonID;
    private final Label button;
    private final ImageView buttonFrame;

    private boolean isDisabled;

    /**
     * Custom Constructor for creating a button
     * @param launcher an instance from the main launcher class
     * @param button the text for the button
     * @param buttonID the desired button id
     * */
    public CustomButton(Launcher launcher, Label button , CustomButtonID buttonID) {
        this.button = button;
        this.buttonFrame = new ImageView(UNSELECTED_FRAME);

        this.buttonID = buttonID;

        button.setTextFill(Color.valueOf("#004a7f"));
        button.setFont(new Font("Impact" , 35));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(190);

        buttonFrame.setFitWidth(200);
        buttonFrame.setFitHeight(50);

        button.setOnMouseEntered(launcher.getMouseInput());
        button.setOnMouseExited(launcher.getMouseInput());
        button.setOnMouseClicked(launcher.getMouseInput());

        buttonList.add(this);
    }
    /**
     * Custom Constructor for creating a button
     * @param launcher an instance from the main launcher class
     * @param buttonX X coordinates for the button
     * @param buttonY Y coordinates for the button
     * @param labelX X coordinates for the label
     * @param labelY Y coordinates for the label
     * @param button the text for the button
     * @param buttonID the desired button id
     * */
    public CustomButton(Launcher launcher, Label button , int buttonX , int buttonY , int labelX , int labelY  , CustomButtonID buttonID) {
        this.button = button;
        this.buttonFrame = new ImageView(UNSELECTED_FRAME);

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

        button.setOnMouseClicked(launcher.getMouseInput());
        button.setOnMouseEntered(launcher.getMouseInput());
        button.setOnMouseExited(launcher.getMouseInput());

        buttonList.add(this);
    }

    //buttons to get the button's label, frame, or ID
    public Label getButtonLabel() {
        return button;
    }
    public ImageView getButtonFrame() {
        return buttonFrame;
    }
    public CustomButtonID getButtonID() {
        return buttonID;
    }

    //Method for selecting or unselecting the button
    public void selectedButton() {
        buttonFrame.setImage(SELECTED_FRAME);
    }
    public void unSelectedButton() {
        buttonFrame.setImage(UNSELECTED_FRAME);
    }

    //methods for disabling to enabling the button
    public void setDisabled(boolean disabled) {
        this.isDisabled = disabled;

        if(isDisabled)
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
    public boolean isDisabled(){return isDisabled;}

    //methods for hiding or showing the button
    public void hide() {
        buttonFrame.setVisible(false);
        button.setVisible(false);
    }
    public void show() {
        buttonFrame.setVisible(true);
        button.setVisible(true);
    }

    //Helper utility method to detect if a label is a button and get the button object by passing that label
    public static boolean isButton(Label button) {
        for(CustomButton gameButton : CustomButton.buttonList)
        {
            if(gameButton.getButtonLabel().equals(button))
            {
                return true;
            }
        }

        return false;
    }
    public static CustomButton getButton(Label buttonLabel) {
        for(CustomButton gameButton : CustomButton.buttonList)
        {
            if(gameButton.getButtonLabel().equals(buttonLabel))
            {
                return gameButton;
            }
        }

        return null;
    }
}
