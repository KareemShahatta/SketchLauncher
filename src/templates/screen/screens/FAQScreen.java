package templates.screen.screens;

import javafx.geometry.Pos;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import templates.screen.CustomScreen;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

import static launcher.Details.COLOR;
import static launcher.Details.FONT;
import static templates.image.Images.MEGA_LAUNCHER_ICON;
/**
 * Screen that displays FAQ questions and plays an animation
 * */
public class FAQScreen extends CustomScreen
{
    //Some of those fields are final because we are assigning only 1 value to them when we are initializing them
    private final Pane faqPane;

    private double phaseDegree = 1;
    private boolean increasingIndicator = false;
    private final NumberFormat numberFormat = new DecimalFormat("#.#");

    public FAQScreen() {
        faqPane = new Pane();

        Glow glowEffect = new Glow();
        glowEffect.setLevel(1);

        Text creatorText = new Text("╠ Property of the EgyptianPhantom ╢");
        creatorText.setFont(new Font(FONT , 24));
        creatorText.setFill(Color.valueOf(COLOR));
        creatorText.setEffect(glowEffect);
        creatorText.setX(10);
        creatorText.setY(460);

        ImageView megaGameLogo = new ImageView(MEGA_LAUNCHER_ICON);
        megaGameLogo.setX(380);
        megaGameLogo.setY(400);
        megaGameLogo.setFitWidth(100);
        megaGameLogo.setFitHeight(100);
        megaGameLogo.setOpacity(phaseDegree);
        playFadeAnimation(megaGameLogo);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                getQuestionAskText("• What is SketchLauncher")
                , getQuestionAnswerText("-SketchLauncher a launcher for all Sketch Games.")

                , getQuestionAskText("• What version is SketchLauncher?")
                , getQuestionAnswerText("-SketchLauncher is currently in the alpha build [4.0] .")

                , getQuestionAskText("• Why do I need Sketch Launcher?")
                , getQuestionAnswerText("-You need it in order to be able to install any SketchGame.")

                , getQuestionAskText("• How does the account system work?")
                , getQuestionAnswerText("-You need only 1 account that will sync with all SketchGames. ")

                , getQuestionAskText("• Who is the EgyptianPhantom?")
                , getQuestionAnswerText("-EgyptianPhantom is the main developer of SketchGames and Launcher.")
        );

        vBox.setSpacing(5);
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setLayoutX(15);
        vBox.setLayoutY(150);

        faqPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        faqPane.getChildren().addAll(getTitle(), vBox , megaGameLogo , creatorText);
    }

    //Returns the pane that contains the screen contain and is used by the screen manager class
    @Override public Pane getPane() {
        return faqPane;
    }

    //Plays the fading animation in the FAQ screen
    private void playFadeAnimation(ImageView megaGameLogo) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {

            @Override
            public void run()
            {
                phaseDegree = Double.parseDouble(numberFormat.format(phaseDegree));

                if (phaseDegree == 0.2 && !increasingIndicator) {
                    phaseDegree = phaseDegree + 0.1;
                    increasingIndicator = true;
                } else if (phaseDegree > 0.2 && !increasingIndicator) {
                    phaseDegree = phaseDegree - 0.1;
                } else if (phaseDegree == 1 && increasingIndicator) {
                    phaseDegree = phaseDegree - 0.1;
                    increasingIndicator = false;
                } else if (phaseDegree > 0.2 && increasingIndicator) {
                    phaseDegree = phaseDegree + 0.1;
                }
                megaGameLogo.setOpacity(phaseDegree);
            }
        };

        timer.schedule(task , 0 , 100);
    }

    //Helper methods for simplifying the code in the constructor method and applying the DRY principle
    private Text getQuestionAskText(String questionString) {
        Text question = new Text(questionString);
        question.setFont(new Font("impact" , 16));
        question.setFill(Color.WHITE);
        return question;
    }
    private Text getQuestionAnswerText(String  answerString) {
        Text answer = new Text(answerString);
        answer.setFont(new Font("impact" , 16));
        answer.setFill(Color.valueOf("#004a7f"));
        return answer;
    }
}
