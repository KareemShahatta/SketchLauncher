package templates.sound;

import javafx.scene.media.AudioClip;

import static launcher.Details.VOLUME;

/**
 * Class for creating a playing sounds in the launcher
 * */
public class CustomSounds
{

    private AudioClip audioPlayer;

    //Plays a sound based on the provided sound ID
    //NOTE Volume is always set to 0.4 and can't be changed for any sound
    public void playSound(CustomSoundsID sound) {
        switch(sound)
        {
            case START: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/game_launch.wav").toExternalForm()); break;
            case ERROR: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/error.wav").toExternalForm());  break;
            case SUCCESS: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/success.wav").toExternalForm());  break;
            case CLICK: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/mouse_click.wav").toExternalForm()); break;
            case HOVER: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/mouse_hover.wav").toExternalForm()); break;
            case UPDATE: audioPlayer = new AudioClip(ClassLoader.getSystemResource("sounds/update.wav").toExternalForm()); break;
        }

        audioPlayer.setVolume(VOLUME);
        audioPlayer.play();
    }
}
