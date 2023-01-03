package templates.screen;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import static templates.image.Images.LOADING_SPINNER;
import static templates.image.Images.SKETCH_LAUNCHER_TITLE;
/**
 * Super class for creating screen for the launcher
 * Contain useful utility methods that every screen needs
 * */
public abstract class CustomScreen
{
    protected abstract Pane getPane();

    //Method for creating the loading spinner symbol and rotating it
    protected ImageView createSpinner() {
        ImageView loadingSpinner = new ImageView(LOADING_SPINNER);
        loadingSpinner.setFitWidth(30);
        loadingSpinner.setFitHeight(30);
        loadingSpinner.setX(480);
        loadingSpinner.setY(480);
        loadingSpinner.setVisible(false);
        return loadingSpinner;
    }
    protected void animateSpinner(int sec , ImageView spinner) {
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

    //Method for creating text field, password field, and hint texts
    protected Text createHintMessage(boolean success , String textString) {
        Text text = new Text("[!] " + textString);
        text.setFont(new Font("Impact" , 16));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(5);
        text.setY(505);

        if(success)
        {
            text.setFill(Color.valueOf("#004a7f")); return text;
        }
        else
        {
            text.setFill(Color.RED); return text;
        }
    }
    protected TextField createTextField(String text , int width , int height) {
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
    protected PasswordField createPasswordField(String text , int width , int height) {
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

    //Method to get the launcher main title
    protected ImageView getTitle() {
        ImageView image = new ImageView(SKETCH_LAUNCHER_TITLE);
        image.setFitWidth(500);
        image.setLayoutX(5);
        image.setLayoutY(30);
        return image;
    }
}
