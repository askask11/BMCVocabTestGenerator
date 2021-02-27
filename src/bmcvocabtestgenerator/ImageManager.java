/*
 * Author: jianqing
 * Date: Oct 5, 2020
 * Description: This document is created for
 */
package bmcvocabtestgenerator;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author jianqing
 */
public class ImageManager
{
    private ImageManager(){}
    
    public static ImageIcon getIcon(URL url, int width, int height, int hint)
    {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, hint));
        return imageIcon;
    }
    
    public static ImageIcon getIcon(URL url, int width, int height)
    {
        return getIcon(url, width, height, java.awt.Image.SCALE_DEFAULT);
    }
            
}
