
//declare class LogoCreator as starter class
public class JTrump
{
    //declare static main method to be run from LogoCreator class
    public static void main(String[] args) {
        LogoFrame lF = new LogoFrame("JTrump"); //declare and initialize new LogoFrame lF, pass in title
        lF.setDefaultCloseOperation(LogoFrame.EXIT_ON_CLOSE); //invoke method on lF and pass in static constant inherited from JFrame to end the program when the red "x" i clicked
        lF.setResizable(false); //invoke method on lF and pass in false boolean to prevent frame from being resized
    }
}
