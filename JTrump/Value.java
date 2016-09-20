/*
 * NOTE: This class is used as a custom wrapper class for integers
 * which is sometimes necessary because inner classes require primitive
 * type local variables to be final if they are to be referenced from
 * within the inner class. In addition, the exact functionality of the
 * Integer wrapperclass is not known and may not serve the purposes of
 * this program, so a custom wrapper class is the best solution.
 */

//declare class Value
public class Value {
    //declare instance field
    private int i; //declare instance field int i to represent the value that the Value class stores
    //declare Value constructor to take in an int parameter j to which to initialize the instance field int i
    public Value(int j) { i = j; }
    //declare method get to return the value of the instance field int i that an instance of this class would hold
    public int get() { return i; }
    //declare method set with an int parameter j to which to reinitialize the instance field int i
    public void set(int j) { i = j; }
}
