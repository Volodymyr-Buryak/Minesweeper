package sweeper.util.game;

import java.net.URL;
import sweeper.GameUI;
import javax.swing.ImageIcon;
import sweeper.EnumGame.*;

public final class ImageHandler {

    public static void initializeImageEnum() {
        initializeImageBox();
        initializeImageGameState();
    }

    private static ImageIcon loadImageIconFromResources (String iconName) {
        URL imgURL = ImageHandler.class.getResource("/img/" + iconName.toLowerCase().strip() + ".png");
        if (imgURL == null){
            GameUI.showErrorAndExit("Image not found: " + iconName);
            return null;
        } else  {
            return new ImageIcon(imgURL);
        }
    }

    private static void initializeImageBox() {
        for (Box box : Box.values()){
            ImageIcon imageIcon = loadImageIconFromResources(box.name());
            if (imageIcon != null) {
                box.setImage(imageIcon.getImage());
            }
        }
    }

    private static void initializeImageGameState() {
        for (GameState state : GameState.values()){
            ImageIcon imageIcon = loadImageIconFromResources(state.name());
            if (imageIcon != null) {
                state.setImage(imageIcon);
            }
        }
    }

    public static ImageIcon getGameImageIcon(String imageName) {
        if (imageName == null || imageName.isBlank()) {
            throw new IllegalArgumentException("Image name cannot be null or empty");
        }
        return loadImageIconFromResources(imageName);
    }

}
