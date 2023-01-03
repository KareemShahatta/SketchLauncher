package templates.image;

import javafx.scene.image.Image;
/**
 * Class for storing images and making it easier to change and relocate them.
 * */
public class Images
{
    public static final Image SKETCH_LAUNCHER_TITLE = new Image(ClassLoader.getSystemResource("textures/sketch_launcher.png").toString());
    public static final Image SELECTED_FRAME = new Image(ClassLoader.getSystemResource("textures/selected_frame.png").toString());
    public static final Image UNSELECTED_FRAME = new Image(ClassLoader.getSystemResource("textures/unselected_frame.png").toString());
    public static final Image DISABLED_FRAME = new Image(ClassLoader.getSystemResource("textures/disabled_frame.png").toString());
    public static final Image LOADING_SPINNER = new Image(ClassLoader.getSystemResource("textures/loader_icon.png").toString());
    public static final Image LAUNCHER_ICON = new Image(ClassLoader.getSystemResource("textures/launcher_icon.png").toString());
    public static final Image MEGA_LAUNCHER_ICON = new Image(ClassLoader.getSystemResource("textures/mega_launcher_icon.png").toString());
}
