//import necessary classes
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.border.MatteBorder;
import java.awt.Color;

//declare class ImageComponent as a type of JComponent (this class will be used for the main display image as well as the individual thumbs)
//NOTE: the selection logic will exist, but will simply not be used for the main display image
public class ImageComponent extends JComponent {

    //declare instance fields
    int xPos; //represents x position of image
    int yPos; //represents y position of image
    boolean selected; //represents if image is selected or not
    BufferedImage img; //object that will hold image data

    //declare constructor for ImageComponent
    public ImageComponent(int x, int y, String path) //constructor parameters for instance fields x and y position, as well as a String path from which the buffered image will load
    {
        //initialize instance fields to parameters
        xPos = x;
        yPos = y;
        //initialize selected to false because no images will start off selected
        selected = false;

        img = null; //initialize img to null to avoid compiler errors
        try { //try block for buffering images without crashing
            img = ImageIO.read(new File(path)); //use static read() method from ImageIO class to load a jpg image into img
        } catch (IOException e) { //catch block to recieve potential errors thrown by image buffering
            e.printStackTrace(); //print errors, if caught
        }
    }

    //declare method for setting img to a new image with a different path (only for main display image)
    public void setImage(String path) { //take in String path parameter
        img = null; //reinitialize img to null to avoid compiler errors
        try { //try block for buffering images without crashing
            img = ImageIO.read(new File(path)); //use static read() method from ImageIO class to load a jpg image into img
        } catch (IOException e) { //catch block to recieve potential errors thrown by image buffering
            e.printStackTrace(); //print errors, if caught
        }
        repaint(); //repaint the image once it is reloaded
    }

    //declare paintComponent method with the parameter of a Graphics object. Overrides method in JComponent
    public void paintComponent(Graphics g) {
        g.drawImage(img, xPos, yPos, null); //use graphics toolkit to draw the buffered image referenced by img onto the current ImageComponent
    }

    //declare method select to choose an image (only for thumbnail images)
    public void select() {
        setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK.brighter().brighter().brighter())); //set the border of the current ImageComponent to a black matte border to represent selection
        selected = true; //set selected to true to represent ImageComponent selection
    }

    //declare method hoverEnter for when mouse hovers over ImageComponent (only for thumbnail images)
    public void hoverEnter() {
        if(!selected) setBorder(new MatteBorder(4, 4, 4, 4, Color.BLACK)); //if the image is not selected, draw a black matte border around it
    }

    //declare method hoverExit for when mouse stops hovering over ImageComponent(only for thumbnail images)
    public void hoverExit() {
        if (selected) select();
        else deselect();
    }

    //declare method deselect for when a different image is chosen (only for thumbnail images)
    public void deselect() {
        setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
        selected = false;
    }

}