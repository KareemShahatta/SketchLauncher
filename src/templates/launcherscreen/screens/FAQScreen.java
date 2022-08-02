package templates.launcherscreen.screens;

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
import launcher.StartUp;
import templates.launcherscreen.CurrentScreenID;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

import static launcher.StartUp.playerCurrentView;
import static templates.launcherimage.LauncherImages.MEGA_LAUNCHER_ICON;

public class FAQScreen extends LauncherPaneDesign
{
    private Pane launcherPane;
    private StartUp startUp;
    private double phaseDegree = 1;
    private boolean increasingIndicator = false;
    private NumberFormat numberFormat = new DecimalFormat("#.#");

    public FAQScreen(StartUp startUp) {

        this.startUp = startUp;
        launcherPane = new Pane();

        Glow glowEffect = new Glow();
        glowEffect.setLevel(1);

        Text creatorText = new Text("╠ Property of the EgyptianPhantom ╢");
        creatorText.setFont(new Font("impact" , 24));
        creatorText.setFill(Color.valueOf("#004a7f"));
        creatorText.setEffect(glowEffect);
        creatorText.setX(10);
        creatorText.setY(460);

        ImageView megaGameLogo = new ImageView(MEGA_LAUNCHER_ICON);
        megaGameLogo.setX(380);
        megaGameLogo.setY(400);
        megaGameLogo.setFitWidth(100);
        megaGameLogo.setFitHeight(100);
        megaGameLogo.setOpacity(phaseDegree);

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

        launcherPane.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        launcherPane.getChildren().addAll
                (startUp.getScreenUtilInstance().getSketchLauncherLogo(), vBox , megaGameLogo , creatorText
                );
    }

    @Override
    public void setViewingPane() {
        playerCurrentView = CurrentScreenID.FAQ;
        startUp.getCurrentLauncherScene().setRoot(launcherPane);
    }

    @Override
    public Pane getPane() {
        return launcherPane;
    }

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
