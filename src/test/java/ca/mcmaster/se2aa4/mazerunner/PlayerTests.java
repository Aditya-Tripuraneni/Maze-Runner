package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Entity.Location;
import ca.mcmaster.se2aa4.mazerunner.Entity.Player;
import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

public class PlayerTests {

    @Test
    public void testEquals(){
        Location one = new Location(1,1);
        Location two = new Location(1,1);
        Player playerOne = new Player(one, two, EAST);
        Player playerTwo = new Player(one, two, EAST);
        assertNotEquals(playerOne, playerTwo);
    }


    @Test
    public void testGetRow(){
        Location one = new Location(45,1);
        Location two = new Location(1,1);

        int trueResult = 45;
        Player playerOne = new Player(one, two, EAST);
        int result = playerOne.getCurrentLocation().getX();
        assertEquals(trueResult, result);
    }


    @Test
    public void testGetCol(){
        Location one = new Location(1,45);
        Location two = new Location(1,1);
        int trueResult = 45;
        Player playerOne = new Player(one, two, EAST);
        int result = playerOne.getCurrentLocation().getY();
        assertEquals(trueResult, result);
    }


    @Test
    public void testGetExitRow(){
        Location one = new Location(1,1);
        Location two = new Location(45,1);
        int trueResult = 45;
        Player playerOne = new Player(one, two, EAST);
        int result = playerOne.getExitLocation().getX();
        assertEquals(trueResult, result);
    }


    @Test
    public void testGetExitCol(){
        Location one = new Location(1,1);
        Location two = new Location(1,45);
        int trueResult = 45;
        Player playerOne = new Player(one, two, EAST);
        int result = playerOne.getExitLocation().getY();
        assertEquals(trueResult, result);
    }


    @Test
    public void testgetOrientation(){
        Location one = new Location(1,1);
        Location two = new Location(1,1);
        Direction trueResult = SOUTH; 
        Player playerOne = new Player(one, two, SOUTH);
        Direction result = playerOne.getOrientation();
        assertEquals(trueResult, result);
    }
    
}
