package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.InputVerifier;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.MazeInputReader;

public class InputVerifierTest {



    @Test
    public void testStraightPathFactored() throws FileNotFoundException{
        String trueResult = "correct path";
        Path path = new Path("./examples/straight.maz.txt");

        try {
            Maze maze = new Maze(path);
            String userPath = "4F";
            InputVerifier mazeInputVerifier = new MazeInputReader(maze, userPath);
            String result = mazeInputVerifier.verifyPath();
            assertEquals(trueResult, result);
        } 
        catch (IOException e) {
            // If an exception is caught it means maze could not be loaded so fail the test
            fail("Exception thrown: " + e.getMessage());
        }
    }


    @Test
    public void testStraightPathNonFactored() throws FileNotFoundException{
        String trueResult = "correct path";
        Path path = new Path("./examples/straight.maz.txt");

        try {
            Maze maze = new Maze(path);
            String userPath = "F F F F";
            InputVerifier mazeInputVerifier = new MazeInputReader(maze, userPath);
            String result = mazeInputVerifier.verifyPath();
            assertEquals(trueResult, result);
        } 
        catch (IOException e) {
            // If an exception is caught it means maze could not be loaded so fail the test
            fail("Exception thrown: " + e.getMessage());
        }
    }


    @Test
    public void testStraightPathMixFactored() throws FileNotFoundException{
        String trueResult = "correct path";
        Path path = new Path("./examples/straight.maz.txt");

        try {
            Maze maze = new Maze(path);
            String userPath = "2 F F F";
            InputVerifier mazeInputVerifier = new MazeInputReader(maze, userPath);
            String result = mazeInputVerifier.verifyPath();
            assertEquals(trueResult, result);
        } 
        catch (IOException e) {
            // If an exception is caught it means maze could not be loaded so fail the test
            fail("Exception thrown: " + e.getMessage());
        }
    }


   
    
}
