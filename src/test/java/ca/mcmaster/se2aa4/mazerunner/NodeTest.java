package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;



import ca.mcmaster.se2aa4.mazerunner.Entity.Node;

public class NodeTest {

    /**
     * Tests the getX() method of the Node class.
     */
    @Test
    public void testGetX() {
        Node node = new Node(1, 1);
        int trueResult = 1; 
        int result = node.getX(); 
        assertEquals(trueResult, result);
    }


    /**
     * Tests the getY() method of the Node class.
     */
    @Test
    public void testGetY() {
        Node node = new Node(1, 2);
        int trueResult = 2; 
        int result = node.getY(); 
        assertEquals(trueResult, result);
    }

    
    /**
     * Tests the equals() method of the Node class.
     */
    @Test
    public void testEquals() {
        Node nodeOne = new Node(1, 2);
        Node nodeTwo = new Node(1, 2);
        assertEquals(nodeOne, nodeTwo);
    }
    

    /**
     * Tests the equals() method of the Node class when nodes are not equal.
     */
    @Test
    public void testNotEquals() {
        Node nodeOne = new Node(1, 2);
        Node nodeTwo = new Node(2, 1);
        assertNotEquals(nodeOne, nodeTwo);
    }
    
}
