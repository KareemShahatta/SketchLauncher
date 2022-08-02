package handler;

import javafx.scene.media.AudioClip;

public class SoundHandler
{
    public void playerErrorSound() {
        AudioClip audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/error.wav").toExternalForm());
        audioPlayer.setVolume(0.4);
        audioPlayer.play();
    }

    public void playSuccessSound() {
        AudioClip audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/success.wav").toExternalForm());
        audioPlayer.setVolume(0.6);
        audioPlayer.play();
    }

    void playMouseClickSound() {
        AudioClip audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/mouse_click.wav").toExternalForm());
        audioPlayer.setVolume(0.6);
        audioPlayer.play();
    }

    void playMouseHoverSound() {
        AudioClip audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/mouse_hover.wav").toExternalForm());
        audioPlayer.setVolume(0.4);
        audioPlayer.play();
    }

    public void playLauncherStartUp() {
        AudioClip audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/game_launch.wav").toExternalForm());
        audioPlayer.setVolume(0.4);
        audioPlayer.play();
    }

    public void playUserFileImport() {
        AudioClip audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/update.wav").toExternalForm());
        audioPlayer.setVolume(0.4);
        audioPlayer.play();
    }
}
