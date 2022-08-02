package templates.launcherscreen.screens;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import launcher.StartUp;
import templates.launcherscreen.CurrentScreenID;

import java.util.Timer;
import java.util.TimerTask;

import static launcher.StartUp.playerCurrentView;
import static templates.launcherimage.LauncherImages.MEGA_LAUNCHER_ICON;

public class OpenScreen extends LauncherPaneDesign
{
    private Pane launcherPane;
    private StartUp startUp;

    public OpenScreen(StartUp startUp) {

        this.startUp = startUp;
        launcherPane = new Pane();

        ImageView launcherLogo = new ImageView(MEGA_LAUNCHER_ICON);
        launcherLogo.setX(5);
        launcherLogo.setY(5);
        launcherLogo.setFitWidth(503);
        launcherLogo.setFitHeight(502);

        FadeTransition launcherLogoAnimator = new FadeTransition();
        launcherLogoAnimator.setFromValue(1);
        launcherLogoAnimator.setToValue(0);
        launcherLogoAnimator.setDuration(Duration.seconds(3.0));
        launcherLogoAnimator.setNode(launcherLogo);
        launcherLogoAnimator.play();

        Rectangle rectangleLoader1 = createRectangleNode(0 , 0);
        Rectangle rectangleLoader2 = createRectangleNode(505 , 0);
        Rectangle rectangleLoader3 = createRectangleNode(0 , 508);
        Rectangle rectangleLoader4 = createRectangleNode(505 , 507);

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

        launcherPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        launcherPane.getChildren().addAll(launcherLogo , rectangleLoader1 , rectangleLoader2 , rectangleLoader3 , rectangleLoader4);
        startUp.getSoundHandler().playLauncherStartUp();
    }

    @Override
    public void setViewingPane() {
        playerCurrentView = CurrentScreenID.OPEN;
        startUp.getCurrentLauncherScene().setRoot(launcherPane);
    }


    @Override
    public Pane getPane() {
        return launcherPane;
    }

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
