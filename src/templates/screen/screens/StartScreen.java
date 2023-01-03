package templates.screen.screens;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import launcher.Launcher;
import templates.screen.CustomScreen;
import templates.sound.CustomSoundsID;

import java.util.Timer;
import java.util.TimerTask;

import static templates.image.Images.MEGA_LAUNCHER_ICON;
/**
 * Screen that is loaded when the launcher start and plays an animation.
 * */
public class StartScreen extends CustomScreen
{
    //this field is final because we are assigning only 1 value to it when we are initializing.
    private final Pane startPane;

    /**
     * Constructor for setting up the Screen
     * @param launcher an instance from the main launcher class
     * */
    public StartScreen(Launcher launcher) {
        startPane = new Pane();

        ImageView launcherLogo = new ImageView(MEGA_LAUNCHER_ICON);
        launcherLogo.setX(5);
        launcherLogo.setY(5);
        launcherLogo.setFitWidth(500);
        launcherLogo.setFitHeight(500);

        Rectangle rectangleLoader1 = createRectangleNode(0 , 0);
        Rectangle rectangleLoader2 = createRectangleNode(505 , 0);
        Rectangle rectangleLoader3 = createRectangleNode(0 , 505);
        Rectangle rectangleLoader4 = createRectangleNode(505 , 505);

        playAnimations(launcherLogo, rectangleLoader1, rectangleLoader2, rectangleLoader3, rectangleLoader4);

        startPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        startPane.getChildren().addAll(launcherLogo , rectangleLoader1 , rectangleLoader2 , rectangleLoader3 , rectangleLoader4);
        launcher.getSounds().playSound(CustomSoundsID.START);
    }

    //Returns the pane that contains the screen contain and is used by the screen manager class
    @Override public Pane getPane() {
        return startPane;
    }

    //plays the entire animation of the bars and the logo.
    private void playAnimations(ImageView launcherLogo, Rectangle rectangleLoader1, Rectangle rectangleLoader2, Rectangle rectangleLoader3, Rectangle rectangleLoader4) {

        FadeTransition launcherLogoAnimator = new FadeTransition();
        launcherLogoAnimator.setFromValue(1);
        launcherLogoAnimator.setToValue(0);
        launcherLogoAnimator.setDuration(Duration.seconds(3.0));
        launcherLogoAnimator.setNode(launcherLogo);
        launcherLogoAnimator.play();

        ScaleTransition rectangleLoaderTransition1 = createScaleXTransition(rectangleLoader1 , 210);
        ScaleTransition rectangleLoaderTransition2 = createScaleYTransition(rectangleLoader2 , 210);
        ScaleTransition rectangleLoaderTransition3 = createScaleYTransition(rectangleLoader3 , -210);
        ScaleTransition rectangleLoaderTransition4 = createScaleXTransition(rectangleLoader4 , -210);

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                launcherLogoAnimator.stop();
                rectangleLoaderTransition1.stop();
                rectangleLoaderTransition2.stop();
                rectangleLoaderTransition3.stop();
                rectangleLoaderTransition4.stop();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask , 3500);
    }

    //Helper methods for simplifying the code in the constructor method and applying the DRY principle
    private Rectangle createRectangleNode(int X , int Y) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(X);
        rectangle.setY(Y);
        rectangle.setWidth(5);
        rectangle.setHeight(5);
        rectangle.setFill(Color.valueOf("#004a7f"));
        return rectangle;
    }
    private ScaleTransition createScaleXTransition(Rectangle rectangle , int to) {
        ScaleTransition rectangleAnimation = new ScaleTransition();
        rectangleAnimation.setDuration(Duration.seconds(3.0));
        rectangleAnimation.setFromX(0);
        rectangleAnimation.setToX(to);
        rectangleAnimation.setNode(rectangle);
        rectangleAnimation.play();
        return rectangleAnimation;
    }
    private ScaleTransition createScaleYTransition(Rectangle rectangle , int to) {
        ScaleTransition rectangleAnimation = new ScaleTransition();
        rectangleAnimation.setDuration(Duration.seconds(3.0));
        rectangleAnimation.setFromY(0);
        rectangleAnimation.setToY(to);
        rectangleAnimation.setNode(rectangle);
        rectangleAnimation.play();
        return rectangleAnimation;
    }
}
