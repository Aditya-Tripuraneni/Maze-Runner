package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Utils.Compass;
import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;

public class CompassTest {

    /**
     * Tests the relative direction when facing north and changing cardinal direction to east.
     */
    @Test
    public void testNorthRelativeDirectionFaceEast(){
        Compass compass = new Compass();
        Direction trueAnswer = R; //right
        Direction result = compass.getRelativeDirection(NORTH, EAST);
        assertEquals(trueAnswer, result);
    }


    /**
     * Tests the relative direction when facing north and changing cardinal direction to west.
     */
    @Test
    public void testNorthRelativeDirectionFaceWest(){
        Compass compass = new Compass();
        Direction trueAnswer = L; //left
        Direction result = compass.getRelativeDirection(NORTH, WEST);
        assertEquals(trueAnswer, result);
    }


    /**
     * Tests the relative direction when facing north and changing cardinal direction to north.
     */
    @Test
    public void testNorthRelativeDirectionFaceNorth(){
        Compass compass = new Compass();
        Direction trueAnswer = F; //forward
        Direction result = compass.getRelativeDirection(NORTH, NORTH);
        assertEquals(trueAnswer, result);
    }


    /**
     * Tests the relative direction when facing south and changing cardinal direction to east.
     */
    @Test
    public void testSouthRelativeDirectionFaceEast(){
        Compass compass = new Compass();
        Direction trueAnswer = L; //left
        Direction result = compass.getRelativeDirection(SOUTH, EAST);
        assertEquals(trueAnswer, result);
    }


    /**
     * Tests the relative direction when facing south and changing cardinal direction to west.
     */
    @Test
    public void testSouthRelativeDirectionFaceWest(){
        Compass compass = new Compass();
        Direction trueAnswer = R; //right
        Direction result = compass.getRelativeDirection(SOUTH, WEST);
        assertEquals(trueAnswer, result);
    }


    /**
     * Tests the relative direction when facing south and changing cardinal direction to south.
     */
    @Test
    public void testSouthRelativeDirectionFaceSouth(){
        Compass compass = new Compass();
        Direction trueAnswer = F; //forward
        Direction result = compass.getRelativeDirection(SOUTH, SOUTH);
        assertEquals(trueAnswer, result);
    }


    /**
     * Tests the relative direction when facing west and changing cardinal direction to north.
     */
    @Test
    public void testWestRelativeDirectionFaceNorth(){
        Compass compass = new Compass();
        Direction trueAnswer = R; //right
        Direction result = compass.getRelativeDirection(WEST, NORTH);
        assertEquals(trueAnswer, result);
    }


     /**
     * Tests the relative direction when facing west and changing cardinal direction to south.
     */
    @Test
    public void testWestRelativeDirectionFaceSouth(){
        Compass compass = new Compass();
        Direction trueAnswer = L; //left
        Direction result = compass.getRelativeDirection(WEST, SOUTH);
        assertEquals(trueAnswer, result);
    }


     /**
     * Tests the relative direction when facing west and changing cardinal direction to west.
     */
    @Test
    public void testWestRelativeDirectionFaceWest(){
        Compass compass = new Compass();
        Direction trueAnswer = F; //forward
        Direction result = compass.getRelativeDirection(WEST, WEST);
        assertEquals(trueAnswer, result);
    }


     /**
     * Tests the relative direction when facing east and changing cardinal direction to north.
     */
    @Test
    public void testEastRelativeDirectionFaceNorth(){
        Compass compass = new Compass();
        Direction trueAnswer = L; //left
        Direction result = compass.getRelativeDirection(EAST, NORTH);
        assertEquals(trueAnswer, result);
    }


     /**
     * Tests the relative direction when facing east and changing cardinal direction to south.
     */
    @Test
    public void testEastRelativeDirectionFaceSouth(){
        Compass compass = new Compass();
        Direction trueAnswer = R; //right
        Direction result = compass.getRelativeDirection(EAST, SOUTH);
        assertEquals(trueAnswer, result);
    }


     /**
     * Tests the relative direction when facing east and changing cardinal direction to east.
     */
    @Test
    public void testEastRelativeDirectionFaceEast(){
        Compass compass = new Compass();
        Direction trueAnswer = F; //forward
        Direction result = compass.getRelativeDirection(EAST, EAST);
        assertEquals(trueAnswer, result);
    }



    
}
