package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Entity.Player;
import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

public class PlayerTests {

    @Test
    public void testEquals(){
        Player playerOne = new Player(1,1,1,1, EAST);
        Player playerTwo = new Player(1,1,1,1, EAST);
        assertNotEquals(playerOne, playerTwo);
    }


    @Test
    public void testGetRow(){
        int trueResult = 45;
        Player playerOne = new Player(45,1,1,1, EAST);
        int result = playerOne.getRow();
        assertEquals(trueResult, result);
    }


    @Test
    public void testGetCol(){
        int trueResult = 45;
        Player playerOne = new Player(1,45,1,1, EAST);
        int result = playerOne.getCol();
        assertEquals(trueResult, result);
    }


    @Test
    public void testGetExitRow(){
        int trueResult = 45;
        Player playerOne = new Player(1,1,45,1, EAST);
        int result = playerOne.getExitRow();
        assertEquals(trueResult, result);
    }


    @Test
    public void testGetExitCol(){
        int trueResult = 45;
        Player playerOne = new Player(1,1,1,45, EAST);
        int result = playerOne.getExitCol();
        assertEquals(trueResult, result);
    }


    @Test
    public void testgetOrientation(){
        Direction trueResult = SOUTH; 
        Player playerOne = new Player(1,1,1,1, SOUTH);
        Direction result = playerOne.getOrientation();
        assertEquals(trueResult, result);
    }
    
}
