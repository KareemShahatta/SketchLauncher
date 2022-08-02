package templates.launcherscreen;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import static templates.launcherimage.LauncherImages.LOADING_SPINNER;
import static templates.launcherimage.LauncherImages.SKETCH_LAUNCHER;

public class ScreenUtil
{
    public ImageView getSketchLauncherLogo() {
        ImageView image = new ImageView(SKETCH_LAUNCHER);
        image.setFitWidth(500);
        image.setLayoutX(5);
        image.setLayoutY(30);
        return image;
    }

    public Text createInformationMessage(TextInfoType type , String textString) {
        Text text = new Text("[!] " + textString);
        text.setFont(new Font("Impact" , 16));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(5);
        text.setY(505);

        switch (type)
        {
            case INFO : text.setFill(Color.valueOf("#004a7f")); return text;
            case WARN : text.setFill(Color.YELLOW); return text;
            case ERROR :text.setFill(Color.RED); return text;
            default : text.setFill(Color.WHITE); return text;
        }
    }
    public void editInformationMessage(Text text , String textString , TextInfoType type) {
        text.setText("[!] " + textString);

        switch (type)
        {
            case INFO: text.setFill(Color.valueOf("#004a7f")); return;
            case WARN: text.setFill(Color.YELLOW); return;
            case ERROR: text.setFill(Color.RED); return;
            default: text.setFill(Color.valueOf("#fff870"));
        }
    }

    public TextField createTextField(String text , int width , int height) {
        TextField textField = new TextField();
        textField.setOpacity(0.6);
        textField.setPrefWidth(width);
        textField.setPrefHeight(height);
        textField.setPromptText(text);
        textField.setStyle("-fx-text-inner-color: white;");
        textField.setFont(new Font("Impact" , 20));
        textField.setBackground(new Background(new BackgroundFill(Color.valueOf("#004a7f") , null , null)));
        return textField;
    }
    public PasswordField createPasswordField(String text , int width , int height) {
        PasswordField passWorldField = new PasswordField();
        passWorldField.setOpacity(0.6);
        passWorldField.setPrefWidth(width);
        passWorldField.setPrefHeight(height);
        passWorldField.setPromptText(text);
        passWorldField.setStyle("-fx-text-inner-color: white");
        passWorldField.setFont(new Font("Impact" , 20));
        passWorldField.setBackground(new Background(new BackgroundFill(Color.valueOf("#004a7f") , null , null)));
        return passWorldField;
    }


    public ImageView createSpinner() {
        ImageView loadingSpinner = new ImageView(LOADING_SPINNER);
        loadingSpinner.setFitWidth(30);
        loadingSpinner.setFitHeight(30);
        loadingSpinner.setX(480);
        loadingSpinner.setY(480);
        loadingSpinner.setVisible(false);
        return loadingSpinner;
    }
    public void rotateSpinner(int sec , ImageView spinner) {
        spinner.setVisible(true);
        RotateTransition RT = new RotateTransition();
        RT.setDuration(Duration.seconds(sec));
        RT.setNode(spinner);
        RT.setFromAngle(0);
        RT.setToAngle(360);
        RT.play();

        Timeline loadingDelay = new Timeline(new KeyFrame(Duration.seconds(sec), event -> spinner.setVisible(false)));
        loadingDelay.play();
    }
}
