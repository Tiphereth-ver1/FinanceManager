package financemanager.functions;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageResizer {

    /**
     * Resizes an ImageIcon to the specified width and height.
     *
     * @param originalIcon The original ImageIcon to resize.
     * @param newWidth The desired new width.
     * @param newHeight The desired new height.
     * @return A new ImageIcon with the specified dimensions.
     */
    public static ImageIcon resizeImageIcon(ImageIcon originalIcon, int newWidth, int newHeight) {
        // 1. Get the Image object from the original ImageIcon
        Image originalImage = originalIcon.getImage();

        // 2. Scale the Image to the new dimensions
        // Image.SCALE_SMOOTH is often preferred for better quality
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // 3. Create a new ImageIcon from the scaled Image
        return new ImageIcon(scaledImage);
    }
}
