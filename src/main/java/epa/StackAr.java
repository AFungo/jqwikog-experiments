package epa;

// StackAr class
//
// CONSTRUCTION: with or without a capacity; default is 10
//
// ******************PUBLIC OPERATIONS*********************
// void push( x )         --> Insert x
// void pop( )            --> Remove most recently inserted item
// Object top( )          --> Return most recently inserted item
// Object topAndPop( )    --> Return and remove most recently inserted item
// boolean isEmpty( )     --> Return true if empty; else false
// boolean isFull( )      --> Return true if full; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Overflow and Underflow thrown as needed


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Array-based implementation of the stack.
 * @author Mark Allen Weiss
 */
public class StackAr
{

    /**
     * Construct the stack.
     * @param capacity the capacity.
     */
    public StackAr( int capacity )
    {
        theArray = new Object[ capacity ];
        topOfStack = -1;
    }

    /**
     * Test if the stack is logically empty.
     * @return true if empty, false otherwise.
     * @observer // annotation added by Jeremy
     */
    public boolean isEmpty( )
    {
        return topOfStack == -1;
    }

    /**
     * Test if the stack is logically full.
     * @return true if full, false otherwise.
     * @observer // annotation added by Jeremy
     */
    public boolean isFull( )
    {
        return topOfStack == theArray.length - 1;
    }


    /**
     * Make the stack logically empty.
     */
    public void makeEmpty( )
    {
//        java.util.Arrays.fill(theArray, 0, topOfStack + 1, null);
        topOfStack = -1;
    }

    /**
     * Get the most recently inserted item in the stack.
     * Does not alter the stack.
     * @return the most recently inserted item in the stack, or null, if empty.
     * @observer // annotation added by Jeremy
     */
    public Object top( )
    {
        if( isEmpty( ) )
            return null;
        return theArray[ topOfStack ];
    }

    /**
     * Remove the most recently inserted item from the stack.
     * @exception Underflow if stack is already empty.
     */
//    @EpaAction(name="pop()")
//    public void pop( ) throws Underflow
//    {
//        if( isEmpty( ) )
//            throw new Underflow( );
//        theArray[ topOfStack-- ] = null;
//    }

    /**
     * Insert a new item into the stack, if not already full.
     * @param x the item to insert.
     * @exception Overflow if stack is already full.
     */
    public void push( Object x ) throws Overflow
    {
        if( isFull( ) )
            throw new Overflow( );
        theArray[ ++topOfStack ] = x;
    }

    /**
     * Return and remove most recently inserted item from the stack.
     * @return most recently inserted item, or null, if stack is empty.
     */
    public Object topAndPop( )
    {
        if( isEmpty( ) )
            return null;
        Object topItem = top( );
        theArray[ topOfStack-- ] = null;
        return topItem;
    }

    private Object [ ] theArray;
    private int        topOfStack;
    
    
	/*-------------------------------------------------------
	 * EPA Precondition Methods
	 */

	public boolean isEmptyEnabled() {
		return true;
    }
    
	public boolean isFullEnabled() {
		return true;
    }

	public boolean isMakeEmptyEnabled() {
		return true;
    }

	public boolean isTopEnabled() {
		return true;
    }

	public boolean isPushEnabled() {
		return !isFull();
    }

	public boolean isTopAndPopEnabled() {
		return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof StackAr)) return false;

        StackAr other = (StackAr) o;

        if (topOfStack != other.topOfStack) return false;
        for (int i = 0; i <= topOfStack; i++) {
            if (!Objects.equals(theArray[i], other.theArray[i])) {
                return false;
            }
        }
        return true;
    }

}
