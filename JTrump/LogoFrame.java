
//import necessary UI Component and Component Management classes from javax.swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

//import necessary UI Management and Decoration classes from java.awt and javax.swing
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;

//import necessary Event Management and Error Management classes from java.awt.event, javax.swing.event, and java.lang
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.lang.NumberFormatException;

public class LogoFrame extends JFrame //declare class LogoFrame as a type of JFrame
{
    //declare instance fields for the LogoFrame class
    private ImageComponent img; //declare ImageComponent img as the ImageComponent for the main meme image
    private ImageComponent[] imgThumbs; //declare array of ImageComponents imgThumbs for the thumbnails from which the user can choose the main meme image
    private String captionFont; //declare String captionFont as the font name of the captions
    private Value captionSize; //declare Value captionSize as the font size of the captions
    private String caption1; //declare String caption1 as the text of the first caption
    private String caption2; //declare String caption2 as the text of the second caption
    private JLabel caption1L; //declare JLabel caption1L as the label for the first caption
    private JLabel caption2L; //declare JLabel caption2L as the label for the second caption
    private JRadioButton option1B; //declare JRadioButton option1B as the first option radio button for the captions (default caption order)
    private JRadioButton option2B; //declare JRadioButton option1B as the second option radio button for the captions (switched caption order)
    private JTextField caption1TF; //declare JTextField caption1TF as the textfield for the user to enter the first caption
    private JTextField caption2TF; //declare JTextField caption2TF as the textfield for the user to enter the second caption
    private JComboBox<String> fontCB; //declare JComboBox fontCB as the comboBox of strings for the user to choose the font name
    private JCheckBox[] styleBoxes; //declare array of 3 JCheckBoxes styleBoxes as the checkboxes for the user to choose font style decorations (Bold, Italicized, Underlined)
    private Value r; //declare Value r to represent red color value
    private Value g; //declare Value g to represent green color value
    private Value b; //declare Value b to represent blue color value
    private Value a; //declare Value a to represent alpha transparency value

    public static final Color optionsGray = Color.GRAY.darker().darker().darker().darker(); //declare and initialize static final Color optionsGray as the constant backgruond color for the components of the options pane

    //declare LogoFrame constructor
    public LogoFrame(String name) { //declare parameter String name for name of frame
        super(name); //pass in String name to superconstructor (of superclass JFrame) to set frame's title

        //initialize caption variables
        captionFont = "Century Gothic"; //initialize String captionFont to default name of caption's font
        captionSize = new Value(30); //initialize Value captionFont to default size of caption's font
        caption1 = "donald trump"; //initialize String caption1 as default text of first caption
        caption2 = "the only god"; //initialize String caption2 as default text of second caption

        //create main panel
        JPanel main = new JPanel(new BorderLayout()); //declare and initialize JPanel main (to be the content pane of the frame) and pass in a BorderLayout object as the layout manager
        main.setBorder(new EmptyBorder(20, 20, 20, 20)); //invoke method on main panel to add a blank border of 20px all around, thus creating padding (for aesthetics)
        main.setBackground(Color.BLACK); //invoke method on main panel to set background color to static constant black color

        //create left side panel
        JPanel mLeft = new JPanel(new BorderLayout()); //declare and initialize JPanel mLeft (to be the left panel of main) and pass in a BorderLayout object as the layout manager
        mLeft.setPreferredSize(new Dimension(270, 410)); //invoke method (and pass in a dimension object with correct size) on left panel to set its size
        mLeft.setBorder(new EmptyBorder(0, 15, 0, 15)); //invoke method on left panel to add a blank border of 15px top and bottom, thus creating padding (for aesthetics)
        mLeft.setBackground(optionsGray); //invoke method on left panel to set background color to static custom constant gray color

        //create left panel's title
        JLabel optionsL = new JLabel("options", SwingConstants.CENTER); //declare and initialize JLabel optionsL and pass in a String and a static constant int (to center the text)
        optionsL.setPreferredSize(new Dimension(245, 50)); //invoke method (and pass in a dimension object with correct size) on title label to set its size
        optionsL.setForeground(Color.WHITE); //invoke method on title label to set foreground (font) color to static constant white color
        optionsL.setFont(new Font("Century Gothic", Font.BOLD, 30)); //invoke method on title label and pass in Font object (with String font name, static constant style int, and size int) to set font of titel label
        mLeft.add(optionsL, BorderLayout.NORTH); //invoke method on left panel and pass in title label and static contant int to ass the title label to the north of the left panel

        //create body panel of left panel
        JPanel lBody = new JPanel(new BorderLayout()); //declare and initialize JPanel lBody (to be the body panel of the left panel) and pass in a BorderLayout object as the layout manager
        lBody.setBackground(optionsGray); //invoke method on left panel to set background color to static custom constant gray color

        //create content panel of body panel of left panel
        JPanel bContent = new JPanel(new GridLayout(13, 1)); //declare and initialize JPanel bContent (to be the content panel of the body panel of the left panel) and pass in a GridLayout object (intialized to 12 rows and 1 column) as the layout manager
        bContent.setBackground(optionsGray); //invoke method on left body content panel to set background color to static custom constant gray color
        bContent.setPreferredSize(new Dimension(245, 440)); //invoke method (and pass in a dimension object with correct size) on left body content panel to set its size

        //create text label panel
        JPanel tTextLP = new JPanel(new BorderLayout()); //declare and initialize new JPanel for text label (with a BorderLayout)
        tTextLP.setBackground(optionsGray); //set panel background to default gray
        JLabel textL = new JLabel("text", SwingConstants.CENTER); //create JLabel with centered text "text"
        textL.setForeground(Color.WHITE); //set text JLabel text color to static constant white
        textL.setFont(new Font("Century Gothic", Font.BOLD, 18)); //set text JLabel font to new font with name, style, and size parameters
        tTextLP.add(textL, BorderLayout.SOUTH); //add text JLabel to south of text label JPanel
        bContent.add(tTextLP); //add text label JPanel to left body content panel

        //declare inner KeyListener class for rendering text on key events (mainly used for caption textfields)
        class CaptionTFListener implements KeyListener {
            //override method for rendering text after a key is released
            public void keyReleased(KeyEvent e) { renderText(); } //invoke method to render the caption text
            //declare empty method to satisfy KeyListener and avoid compiler errors
            public void keyPressed(KeyEvent e) { }
            //declare empty method to satisfy KeyListener and avoid compiler errors
            public void keyTyped(KeyEvent e) { }
        }

        //declare inner MouseListener class for rendering text on Mouse Events (mainly used for JRadioButtons and JCheckBoxes)
        class ClickListener implements MouseListener {
            //override method to render text when mouse button is released
            public void mouseReleased(MouseEvent e) { renderText(); } //invoke method to render the caption text
            //declare empty method to satisfy MouseListener interface and to avoid compiler errors
            public void mouseClicked(MouseEvent e) { }
            //declare empty method to satisfy MouseListener interface and to avoid compiler errors
            public void mouseEntered(MouseEvent e) { }
            //declare empty method to satisfy MouseListener interface and to avoid compiler errors
            public void mouseExited(MouseEvent e) { }
            //declare empty method to satisfy MouseListener interface and to avoid compiler errors
            public void mousePressed(MouseEvent e) { }
        }

        //create first caption panel
        JPanel cCaption1P = new JPanel(); //declare and initialize first caption panel
        cCaption1P.setBackground(optionsGray); //set background color of caption panel to default gray
        JLabel caption1LN = new JLabel("caption one: "); //create first caption JLabel
        caption1LN.setForeground(Color.WHITE); //set first caption JLabel text color to static white
        caption1LN.setFont(new Font("Century Gothic", Font.PLAIN, 13)); //set first caption JLabel font to new font with name, style, and size parameters
        cCaption1P.add(caption1LN); //add first caption label to first caption panel
        caption1TF = new JTextField(); //create first caption textfield
        caption1TF.setFont(caption1LN.getFont()); //set font of first caption textfield to the same font as the font of first caption label
        caption1TF.setText(caption1); //set text of first caption textfield to first caption text String (default text)
        caption1TF.setHorizontalAlignment(SwingConstants.CENTER); //align text in first caption textfield to center alignment
        caption1TF.setPreferredSize(new Dimension(140, 30)); //set preferred size of first caption text field to new dimension object with correct size values
        caption1TF.addKeyListener(new CaptionTFListener()); //add new caption textfield key listener to first caption textfield
        cCaption1P.add(caption1TF); //add first caption textfield to first caption panel
        bContent.add(cCaption1P); //add first caption panel to left body content panel

        //create second caption panel
        JPanel cCaption2P = new JPanel(); //declare and initialize second caption panel
        cCaption2P.setBackground(optionsGray); //set background color of second caption panel to default gray
        JLabel caption2LN = new JLabel("caption two: "); //create second caption JLabel
        caption2LN.setForeground(Color.WHITE); //set second caption JLabel text color to static white
        caption2LN.setFont(caption1LN.getFont()); //set second caption JLabel font to the same font as the font of first caption label
        cCaption2P.add(caption2LN); //add second caption label to second caption panel
        caption2TF = new JTextField(); //create second caption textfield
        caption2TF.setFont(caption1LN.getFont()); //set second caption textfield font to the same font as the font of first caption label
        caption2TF.setText(caption2); //set text of second caption textfield to second caption text String (default text)
        caption2TF.setHorizontalAlignment(SwingConstants.CENTER); //align text in second caption textfield to center alignment
        caption2TF.setPreferredSize(new Dimension(140, 30)); //set preferred size of second caption text field to new dimension object with correct size values
        caption2TF.addKeyListener(new CaptionTFListener()); //add new caption textfield key listener to second caption textfield
        cCaption2P.add(caption2TF); //add second caption textfield to second caption panel
        bContent.add(cCaption2P); //add second caption panel to left body content panel

        //create caption order/options panels
        JPanel cOrderP = new JPanel(new BorderLayout());
        JPanel cOptionP = new JPanel(new GridLayout(1, 5)); //declare and initialize caption options panel
        JLabel option0L = new JLabel("order:  "); //create new JLabel to title options
        option0L.setForeground(Color.WHITE); //set order JLabel text color to static white
        option0L.setFont(caption1LN.getFont()); //set order JLabel font to the same font as the font of first caption label
        option0L.setHorizontalAlignment(SwingConstants.LEFT); //align text in order label to left alignment
        option0L.setBackground(optionsGray); //set background of order label to default gray
        ButtonGroup optionsBG = new ButtonGroup(); //declare and initialize options buttongroup
        cOptionP.setBackground(optionsGray); //set background of caption options panel to default gray
        cOrderP.setBackground(optionsGray); //set background of caption order panel to default gray
        cOrderP.add(option0L, BorderLayout.WEST); //add order label to caption order panel
        JLabel option1L = new JLabel("base "); //create new JLabel to title first option (default captions)
        option1L.setForeground(Color.WHITE); //set default JLabel text color to static white
        option1L.setFont(caption1LN.getFont()); //set default JLabel font to the same font as the font of first caption label
        option1L.setHorizontalAlignment(SwingConstants.RIGHT); //align text in option one label to right alignment
        option1L.setBackground(optionsGray); //set background of default label to default gray
        cOptionP.add(option1L); //add default label to caption options panel
        option1B = new JRadioButton(); //create new radiobutton for default option
        option1B.setBackground(optionsGray); //set background of option 1 button to default gray
        option1B.addMouseListener(new ClickListener()); //add new custom mouse listener to the default option radio button
        cOptionP.add(option1B); //add default option radiobutton to caption options panel
        optionsBG.add(option1B); //add default option radiobutton to caption options buttongroup
        JLabel option2L = new JLabel("switch "); //create new JLabel to title second option (switched captions)
        option2L.setForeground(Color.WHITE); //set switched JLabel text color to static white
        option2L.setFont(caption1LN.getFont()); //set switched JLabel font to the same font as the font of first caption label
        option2L.setHorizontalAlignment(SwingConstants.RIGHT); //align text in second option label to right alignment
        option2L.setBackground(optionsGray); //set background of switch label to default gray
        cOptionP.add(option2L); //add switched label to caption options panel
        option2B = new JRadioButton(); //create new radiobutton for switched option
        option2B.setBackground(optionsGray); //set background of option 2 button to default gray
        option2B.addMouseListener(new ClickListener()); //add new custom mouse listener to the switched option radio button
        cOptionP.add(option2B); //add switched option radiobutton to caption options panel
        optionsBG.add(option2B); //add switched option radiobutton to caption options buttongroup
        optionsBG.clearSelection(); //deselect both options
        option1B.setSelected(true); //select first option
        cOptionP.setAlignmentX(JComponent.LEFT_ALIGNMENT); // align radio buttons to left
        cOrderP.add(cOptionP); //add caption options panel to caption order panel
        bContent.add(cOrderP); //add caption order panel to left body content panel

        //create font label panel
        JPanel tFontLP = new JPanel(new BorderLayout());//declare and initialize new JPanel for font label (with a BorderLayout)
        tFontLP.setBackground(optionsGray); //set panel background to default gray
        JLabel fontL = new JLabel("font", SwingConstants.CENTER); //create JLabel with centered text "font"
        fontL.setForeground(Color.WHITE); //set font JLabel text color to static constant white
        fontL.setFont(textL.getFont());  //set text JLabel font to same font as textL JLabel
        tFontLP.add(fontL, BorderLayout.SOUTH); //add font JLabel to south of font label JPanel
        bContent.add(tFontLP); //add font label JPanel to left body content panel

        //create style options (bold, italicized, underlined) panel
        JPanel tStyleP = new JPanel(new GridLayout(1, 9)); //declare and initialize style options panel with a gridlayout containing 1 rown and 9 columns
        JLabel styleLabel; //create style options label
        styleBoxes = new JCheckBox[3]; //initialize array of three checkboxes
        String[] styleTypes = new String[] {"b ", "i ", "u "}; //create array of style type text
        //loop through array of style attribute types
        for (int i = 0; i < styleTypes.length; i++) { //for each type of style attribute:
            styleLabel = new JLabel(styleTypes[i], SwingConstants.RIGHT); //set style label to new JLabel with style attribute text
            styleLabel.setFont(caption1LN.getFont()); //set font of style label to same font as the first caption label's font
            styleLabel.setForeground(Color.WHITE); //set foreground color of style label to static constant white
            tStyleP.add(styleLabel); //add style label to style options panel
            styleBoxes[i] = new JCheckBox(); //create new JCheckBox at current array position of checkbox array
            styleBoxes[i].setBackground(optionsGray); //set background of current checkbox to default gray
            //add custom mouse listener (solely for rendering text) to current checkbox
            styleBoxes[i].addMouseListener(new ClickListener()); //add new custom mouse listener to the current checkbox
            tStyleP.add(styleBoxes[i]); //add current checkbox to style options panel
        }
        tStyleP.setBackground(optionsGray); //set background color of style options panel to default gray
        bContent.add(tStyleP); //add style options banel to left body content panel

        //declare inner ChangeListener class for rendering text on JSlider change events (mainly used in conjunction with JTextfields)
        class SliderListener implements ChangeListener {
            //declare instance fields for slider, textfield, and value
            private JSlider slider; //declare instance field for slider
            private JTextField textField; //declare instance field for textfield with which slider should be synchronized
            private Value value; //declare instance field for the value to be controlled by slider and textfield
            //declare SliderListener constructor
            public SliderListener(JTextField tf, JSlider s, Value v) { //constructor parameters for instance fields
                //initialize instance fields to constructor parameters
                textField = tf;
                slider = s;
                value = v;
            }
            //override method to run when slider's thumb is moved
            public void stateChanged(ChangeEvent e) { //when slider's value is changed
                textField.setText(Integer.toString(slider.getValue())); //set text of textfield to the string equivalent of slider's value
                value.set(slider.getValue()); //set value to the value of the slider
                renderText(); //invoke method to render the caption text
            }
        }

        //declare inner KeyListener class for rendering text on JTextfield key events (mainly used in conjunction with JSliders)
        class SliderTFListener implements KeyListener {
            //declare instance fields for slider, textfield, and value
            private JTextField textField; //declare instance field for textfield
            private JSlider slider; //declare instance field for slider with which textfield should be synchronized
            private Value value; //declare instance field for the value to be controlled by slider and textfield
            //declare SliderTFListener constructor
            public SliderTFListener(JTextField tf, JSlider s, Value v) { //constructor parameters for instance fields
                //initialize instance fields to constructor parameters
                textField = tf;
                slider = s;
                value = v;
            }
            //override method to run when key is released
            public void keyReleased(KeyEvent e) {
                int newVal = 0; //declare int for the new value to be manipulated
                try { //try block in case user enters anything that is not numerical
                    newVal = Integer.parseInt(textField.getText()); //set the new value to the integer value of the String text of the textfield
                    if ((newVal <= slider.getMaximum()) && (newVal >= slider.getMinimum())) { //if the new value is within the slider's bounds
                        slider.setValue(newVal); //set the value of the slider to the new value
                        value.set(newVal); //set the value to the new value
                        renderText(); //invoke method to render the caption text
                    } //if the new value is not within the slider's bounds
                    else textField.setText(Integer.toString(slider.getValue())); //set the text of the textfield to the string equivalent of current value of the slider
                }
                catch (NumberFormatException nfe) { //catch an error if the user enters anything that is not numerical
                    //if the textfield is blank, the user is probably in the process of entering a new value
                    if (!textField.getText().equals("")) textField.setText(Integer.toString(slider.getValue())); //if so, set the text of the textfield to the string equivalent of current value of the slider
                    //if the textfield is not blank, then it contains something that isn't a number, and nothing should happen
                }
            } 
            //declare empty method to satisfy KeyListener interface and to avoid compiler errors
            public void keyPressed(KeyEvent e) { } 
            //declare empty method to satisfy KeyListener interface and to avoid compiler errors
            public void keyTyped(KeyEvent e) { }
        }

        //create size panel
        JPanel tSizeP = new JPanel(new BorderLayout()); //declare and initialize size JPanel (with a BorderLayout)
        tSizeP.setBackground(optionsGray); //set background of size JPanel to default grey color
        JLabel sizeL = new JLabel("  size: "); //create size JLabel to title the contents of the panel
        sizeL.setForeground(Color.WHITE); //set foregound text color of size label to constant static white
        sizeL.setFont(new Font("Century Gothic", Font.PLAIN, 14)); //set font of size label to new font
        tSizeP.add(sizeL, BorderLayout.WEST); //add size label to west of size panel
        JSlider sizeSlider = new JSlider(1, 50, 30); //create new JSlider with bounds 1 to 50, starting at 30
        sizeSlider.setBackground(optionsGray); //set background of size slider to default grey color
        sizeSlider.setLocation(10, 0); //set location of size slider to 10 pixels in horizontally
        sizeSlider.setBorder(new EmptyBorder(0, 0, 0, 5)); //add border to size slider of 5px on the right to create right side padding (for aesthetics)
        tSizeP.add(sizeSlider, BorderLayout.CENTER); //add size slider to center of size panel
        JTextField sizeTF = new JTextField(); //create size textfield to synch with size  slider
        sizeTF.setText("30"); //set text of size textfield to String equivalent of size slider's default value
        sizeTF.setHorizontalAlignment(SwingConstants.CENTER); //set size textfield to center align its text
        sizeTF.setPreferredSize(new Dimension(40, 10)); //set preferred size of size textfield to a enw dimension object with the correct size
        tSizeP.add(sizeTF, BorderLayout.EAST); //add size textfield to the east of the size panel
        sizeSlider.addChangeListener(new SliderListener(sizeTF, sizeSlider, captionSize)); //add a slider change listener to the size slider, passing in the textfield and value to synch
        sizeTF.addKeyListener(new SliderTFListener(sizeTF, sizeSlider, captionSize)); //add a slider textfield key listener to the size textfield, passing in the slider and value to synch
        bContent.add(tSizeP); //add the size panel to the left body content panel

        //create font choosing panel
        JPanel tFontP = new JPanel(new BorderLayout()); //declare and initialize font choosing JPanel (with a BorderLayout)
        tFontP.setBorder(new EmptyBorder(3, 0, 3, 0)); //add border to font slider of 3px on the right and left to create right/left side padding (for aesthetics)
        tFontP.setBackground(optionsGray); //set background of font JPanel to default grey color
        JLabel fontCBL = new JLabel("  font:   "); //create font JLabel to title the contents of the panel
        fontCBL.setForeground(Color.WHITE); //set foregound text color of font label to constant static white
        fontCBL.setFont(sizeL.getFont());  //set font of the font label to the font of sizeL
        tFontP.add(fontCBL, BorderLayout.WEST); //add font slider to center of west panel
        //initialize font combo box to new combo box of strings of the possible font name choices
        fontCB = new JComboBox<String>(new String[] {"century gothic", "copperplate gothic light", "britannic bold", "comic sans ms", "times new roman", "agency fb", "berlin sans fb", "stencil", "showcard gothic", "papyrus", "impact"});
        fontCB.setFont(caption1LN.getFont()); //set font of the font combo box to the same font as the first caption's label
        ((JLabel) fontCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); //center align the text of the choices of the combo box
        //add a custom action listener to the font combo box
        fontCB.addActionListener(new ActionListener()
            {
                //override method to render text whenever a choice of the combo box is chosen
                public void actionPerformed(ActionEvent e) {
                    captionFont = (String) fontCB.getSelectedItem(); //set caption font name to the chosen font name
                    renderText(); //invoke method to render caption text
                }
            });
        tFontP.add(fontCB, BorderLayout.CENTER); //add the font combo box to the font choosing panel
        bContent.add(tFontP); //add font choosing panel to the left body content panel

        //create panel for color label
        JPanel tColorLP = new JPanel(new BorderLayout()); //declare and initialize color label panel with a BorderLayout
        tColorLP.setBackground(optionsGray); //set background of color label panel to default gray color
        JLabel colorL = new JLabel("color", SwingConstants.CENTER); //create color JLabel with center aligned text
        colorL.setForeground(Color.WHITE); //set foreground text color of color JLabel to white color
        colorL.setFont(textL.getFont()); //set dont of color label to font of text title label
        tColorLP.add(colorL, BorderLayout.SOUTH); //add color label to south of color label panel
        bContent.add(tColorLP); //add color label panel to left body content panel

        //initialize r, g, b, and a Values to new Values of 100
        r = new Value(100);
        g = new Value(100);
        b = new Value(100);
        a = new Value(100);

        //create panel for red color change and set up almost identically to the caption size slider, except modifying Value r
        JPanel tRedP = new JPanel(new BorderLayout());
        tRedP.setBackground(optionsGray);
        JLabel redL = new JLabel("  red: ");
        redL.setForeground(Color.WHITE);
        redL.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        tRedP.add(redL, BorderLayout.WEST);
        JSlider redSlider = new JSlider(0, 100, 100);
        redSlider.setBackground(optionsGray);
        redSlider.setLocation(10, 0);
        redSlider.setBorder(new EmptyBorder(0, 0, 0, 5));
        tRedP.add(redSlider, BorderLayout.CENTER);
        JTextField redTF = new JTextField();
        redTF.setText("100");
        redTF.setHorizontalAlignment(SwingConstants.CENTER);
        redTF.setPreferredSize(new Dimension(40, 10));
        tRedP.add(redTF, BorderLayout.EAST);
        redSlider.addChangeListener(new SliderListener(redTF, redSlider, r));
        redTF.addKeyListener(new SliderTFListener(redTF, redSlider, r));
        bContent.add(tRedP);

        //create panel for green color change and set up almost identically to the caption color red slider, except modifying Value g
        JPanel tGreenP = new JPanel(new BorderLayout());
        tGreenP.setBackground(optionsGray);
        JLabel greenL = new JLabel("  green: ");
        greenL.setForeground(Color.WHITE);
        greenL.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        tGreenP.add(greenL, BorderLayout.WEST);
        JSlider greenSlider = new JSlider(0, 100, 100);
        greenSlider.setBackground(optionsGray);
        greenSlider.setLocation(10, 0);
        greenSlider.setBorder(new EmptyBorder(0, 0, 0, 5));
        tGreenP.add(greenSlider, BorderLayout.CENTER);
        JTextField greenTF = new JTextField();
        greenTF.setText("100");
        greenTF.setHorizontalAlignment(SwingConstants.CENTER);
        greenTF.setPreferredSize(new Dimension(40, 10));
        tGreenP.add(greenTF, BorderLayout.EAST);
        greenSlider.addChangeListener(new SliderListener(greenTF, greenSlider, g));
        greenTF.addKeyListener(new SliderTFListener(greenTF, greenSlider, g));
        bContent.add(tGreenP);

        //create panel for blue color change and set up almost identically to the green color slider, except modifying Value b
        JPanel tBlueP = new JPanel(new BorderLayout());
        tBlueP.setBackground(optionsGray);
        JLabel blueL = new JLabel("  blue: ");
        blueL.setForeground(Color.WHITE);
        blueL.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        tBlueP.add(blueL, BorderLayout.WEST);
        JSlider blueSlider = new JSlider(0, 100, 100);
        blueSlider.setBackground(optionsGray);
        blueSlider.setLocation(10, 0);
        blueSlider.setBorder(new EmptyBorder(0, 0, 0, 5));
        tBlueP.add(blueSlider, BorderLayout.CENTER);
        JTextField blueTF = new JTextField();
        blueTF.setText("100");
        blueTF.setHorizontalAlignment(SwingConstants.CENTER);
        blueTF.setPreferredSize(new Dimension(40, 10));
        tBlueP.add(blueTF, BorderLayout.EAST);
        blueSlider.addChangeListener(new SliderListener(blueTF, blueSlider, b));
        blueTF.addKeyListener(new SliderTFListener(blueTF, blueSlider, b));
        bContent.add(tBlueP);

        //create panel for alpha transparency change and set up almost identically to the blue color slider, except modifying Value a
        JPanel tAlphaP = new JPanel(new BorderLayout());
        tAlphaP.setBackground(optionsGray);
        JLabel alphaL = new JLabel("  alpha: ");
        alphaL.setForeground(Color.WHITE);
        alphaL.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        tAlphaP.add(alphaL, BorderLayout.WEST);
        JSlider alphaSlider = new JSlider(0, 100, 100);
        alphaSlider.setBackground(optionsGray);
        alphaSlider.setLocation(10, 0);
        alphaSlider.setBorder(new EmptyBorder(0, 0, 0, 5));
        tAlphaP.add(alphaSlider, BorderLayout.CENTER);
        JTextField alphaTF = new JTextField();
        alphaTF.setText("100");
        alphaTF.setHorizontalAlignment(SwingConstants.CENTER);
        alphaTF.setPreferredSize(new Dimension(40, 10));
        tAlphaP.add(alphaTF, BorderLayout.EAST);
        alphaSlider.addChangeListener(new SliderListener(alphaTF, alphaSlider, a));
        alphaTF.addKeyListener(new SliderTFListener(alphaTF, alphaSlider, a));
        bContent.add(tAlphaP);

        //final additions for left panel
        lBody.add(bContent, BorderLayout.NORTH); //add the left body content panel to the north sector of the left body panel
        mLeft.add(lBody, BorderLayout.CENTER); //add the left body panel to the center sector of the left panel
        main.add(mLeft, BorderLayout.WEST); //add the left panel to the west sector of the main panel (to be the frame's content pane)

        //create right side panel
        JPanel mRight = new JPanel(new BorderLayout()); //declare and initialize right JPanel with a BorderLayout
        mRight.setPreferredSize(new Dimension(360, 560)); //set the preferred size of the right panel to a new Dimension object with the correct size
        mRight.setBackground(Color.BLACK); //set the background of the right panel to the static constant black color

        //create right top panel
        JPanel rTop = new JPanel(new BorderLayout()); //declare and initialize right top panel
        rTop.setPreferredSize(new Dimension(360, 440)); //set the preferred size of the right top panel to a new Dimension object with the correct size
        rTop.setBackground(Color.BLACK); //set the background of the right top panel to the static constant black color

        //create first caption display panel
        JPanel tCaptionD1 = new JPanel(); //declare and initialize first caption display JPanel
        tCaptionD1.setPreferredSize(new Dimension(600, 70)); //set the preferred size of the caption display panel to a new Dimension object with the correct size
        tCaptionD1.setBackground(Color.BLACK); //set the background of the first caption display panel to the static constant black color
        caption1L = new JLabel(caption1, SwingConstants.CENTER); //create first caption display Jlabel aligned to the center
        caption1L.setFont(new Font(captionFont, Font.PLAIN, captionSize.get())); //set font of the first caption display label to the caption font defaults
        caption1L.setForeground(Color.WHITE); //set foreground text color of the first caption display label to static constant white
        tCaptionD1.add(caption1L); //add the first caption display label to the first caption display panel
        rTop.add(tCaptionD1, BorderLayout.NORTH); //add the first caption display panel to the north of the right top panel

        //create image display panel
        JPanel rImgPanel = new JPanel(new GridLayout(1, 1)); //declare and initialize image panel with a gridlayout containing one row and one column
        rImgPanel.setPreferredSize(new Dimension(360, 300)); //set the preferred size of the image panel to a new Dimension object with the correct size
        rImgPanel.setBackground(Color.BLACK); //set the background of the image panel to the static constant black color
        img = new ImageComponent(25, 0, "black.jpg"); //create a new image component with the correct position and default image file path
        rImgPanel.add(img); //add the image component to the image panel
        rTop.add(rImgPanel, BorderLayout.CENTER); //add the image panel to the center of the right top panel

        //create second caption display panel almost identically to first caption display panel, except modifying String caption2
        JPanel tCaptionD2 = new JPanel();
        tCaptionD2.setPreferredSize(new Dimension(600, 70));
        tCaptionD2.setBackground(Color.BLACK);
        caption2L = new JLabel(caption2, SwingConstants.CENTER);
        caption2L.setFont(new Font(captionFont, Font.PLAIN, captionSize.get()));
        caption2L.setForeground(Color.WHITE);
        tCaptionD2.add(caption2L);
        rTop.add(tCaptionD2, BorderLayout.SOUTH);

        mRight.add(rTop, BorderLayout.NORTH); //add the right top panel to the north sector of the right panel

        //create panel for choosing the image to display
        JPanel rChoicesP = new JPanel(new GridLayout(1, 5)); //declare and initialize the choice panel with a GridLayout containing one row and five columns
        rChoicesP.setPreferredSize(new Dimension(350, 70)); //set the preferred size of the image choice panel to a new Dimension object with the correct size
        rChoicesP.setBackground(Color.BLACK); //set the background of the choice panel to the static constant black color

        //declare inner MouseListener class for rendering text on JComponent (specifically ImageComponent) mouse events (mainly used for thumbnail ImageComponents)
        class ThumbListener implements MouseListener {
            private int id; //declare instance field for id of ImageComponent
            //constructor for ThumbListener
            public ThumbListener(int id) { //take in constructor parameter int id
                this.id = id; //initialize instance field to constructor parameter
            }
            //override method to render text when mouse button is released
            public void mouseClicked(MouseEvent e) {
                for (ImageComponent ic : imgThumbs) ic.deselect(); //loop through each image component and deselect it
                imgThumbs[id].select(); //select the image component that contains this listener
                img.setImage((id + 1) + ".jpg"); //set the main image in the image display panel to the corresponding full-sized image
            }
            //declare method to affect the appearance of the thumbnail ImageComponent when the mouse enters the component region
            public void mouseEntered(MouseEvent e) { imgThumbs[id].hoverEnter(); }
            //declare method to affect the appearance of the thumbnail ImageComponent when the mouse exits the component region
            public void mouseExited(MouseEvent e) { imgThumbs[id].hoverExit(); }
            //declare empty method to satisfy MouseListener interface and to avoid compiler errors
            public void mousePressed(MouseEvent e) { }
            //declare empty method to satisfy MouseListener interface and to avoid compiler errors
            public void mouseReleased(MouseEvent e) { }
        }
        imgThumbs = new ImageComponent[5]; //initialize the array of ImageComponents imgThumbs
        for (int i = 0; i < imgThumbs.length; i++) { //for each item in that array
            imgThumbs[i] = new ImageComponent(0, 0, "t" + (i + 1) + ".jpg"); //initialize tje current item to a new thumbnail ImageComponent with the correct path name to the thumbnail corresponding to its id in the thumbnail array
            imgThumbs[i].addMouseListener(new ThumbListener(i)); //add a custom MouseListener with the correct current id to the current thumbnail ImageComponent
            rChoicesP.add(imgThumbs[i]); //add the current thumbnail ImageComponent to the choice panel
        }

        mRight.add(rChoicesP, BorderLayout.SOUTH); //add the choices panel to the south sector of the right panel
        main.add(mRight, BorderLayout.EAST); //add the right panel to the east sector of the main panel (to be the frame's content pane)
        setContentPane(main); //set the main panel as this frame's content pane

        pack(); //pack this frame to adjust the UI to account for any mistakes in the measurements and sizes of components
        setSize(700, 600); //set the size of the frame to 700px by 600px
        setVisible(true); //invoke setVisible on this frame with a true boolean parameter to make it appear on the screen with all the components
    }

    //declare void renderText method to render the caption text around the main display image
    public void renderText() { //no parameters, because this method uses the instance fields of this class to manipulate the caption text
        //first, set the fonts of caption1 and caption2
        Font newFont = new Font(captionFont, Font.PLAIN, captionSize.get()); //create a new Font object with the updated font name and font size, as well as a plain text decoration, because HTML will be used for text decorations
        caption1L.setFont(newFont); //set the font of the first caption display label to the new font
        caption2L.setFont(newFont); //set the font of the second caption display label to the new font

        //second, declare and initialize floating point decimals for each color value
        //NOTE: alpha values aren't actually used for alpha transparency, but rather for darkening the text color, which has the same effect because the background color of the text is black
        float alpha = (float) ((100.0 - a.get())/100); //subtract the alpha value from 100 to get its opposite percentage, and then divide it by 100 to get a decimal
        float red = (float) (r.get()/100.0 - alpha); //divide the red value by 100 to get a decimal, and subract the alpha value to darken it
        float green = (float) (g.get()/100.0 - alpha); //divide the green value by 100 to get a decimal, and subract the alpha value to darken it
        float blue = (float) (b.get()/100.0 - alpha); //divide the blue value by 100 to get a decimal, and subract the alpha value to darken it

        //third, if the alpha value caused the color values to become negative, change them to 0 to represent full transparency
        if (red < 0) red = 0;
        if (green < 0) green = 0;
        if (blue < 0) blue = 0;

        //fourth, assign the caption display labels a new Color
        Color newColor = new Color(red, green, blue); //declare and initialize a new color with the new, updated, and darkened rgb floating point decimal values
        caption1L.setForeground(newColor); //set the color of the first caption display label to the new color
        caption2L.setForeground(newColor); //set the color of the second caption display label to the new color

        //fifth, reinitialize the caption Strings
        caption2 = caption2TF.getText(); //set the first caption String to the value of the first caption textfield
        caption1 = caption1TF.getText(); //set the second caption String to the value of the second caption textfield
        if (styleBoxes[0].isSelected()) { //if the first style choice checkbox is selected (bold)
            caption1 = "<b>" + caption1 + "</b>"; //add html bold tags to the first caption String
            caption2 = "<b>" + caption2 + "</b>";//add html bold tags to the second caption String
        }
        if (styleBoxes[1].isSelected()) { //if the second style choice checkbox is selected (italicized)
            caption1 = "<i>" + caption1 + "</i>"; //add html italic tags to the first caption String
            caption2 = "<i>" + caption2 + "</i>"; //add html italic tags to the second caption String
        }
        if (styleBoxes[2].isSelected()) { //if the first style choice checkbox is selected (underlined)
            caption1 = "<u>" + caption1 + "</u>"; //add html underline tags to the first caption String
            caption2 = "<u>" + caption2 + "</u>"; //add html underline tags to the second caption String
        }

        //finally, set the text of the caption display labels
        if (option1B.isSelected()) { //if the first caption option (default order) is selected, display the cpations in the normal order
            caption1L.setText("<html>" + caption1 + "</html>"); //set the text of the first caption display label to the value of the first caption String, but surrounded by html document tags (to put the font attribute tags into effect)
            caption2L.setText("<html>" + caption2 + "</html>"); //set the text of the second caption display label to the value of the second caption String, but surrounded by html document tags (to put the font attribute tags into effect)
        } else { //if the first caption option (default order) is not selected, then the secnd caption option (switched order) is selected, so display the cpations in the switched order (caption 2 and then caption 1)
            caption1L.setText("<html>" + caption2 + "</html>"); //set the text of the first caption display label to the value of the second caption String, but surrounded by html document tags (to put the font attribute tags into effect)
            caption2L.setText("<html>" + caption1 + "</html>"); //set the text of the second caption display label to the value of the first caption String, but surrounded by html document tags (to put the font attribute tags into effect)
        }
    }
}
