package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.InputVerifier;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.MazeInputReader;

public class InputVerifierTest {
    private Path path;
    private Maze maze; 


    @BeforeEach
    public void setupVerifier() throws FileNotFoundException, IOException{
        this.path = new Path("./examples/straight.maz.txt");
        this.maze = new Maze(path);
    }



    @Test
    public void testStraightPathFactored() throws FileNotFoundException, IOException{
        String trueResult = "correct path";
        String userPath = "4F";

        InputVerifier mazeInputVerifier = new MazeInputReader(this.maze, userPath);
        String result = mazeInputVerifier.verifyPath();

        assertEquals(trueResult, result);
    }


    @Test
    public void testStraightPathNonFactored() throws FileNotFoundException, IOException{
        String trueResult = "correct path";
        String userPath = "F F F F";

        InputVerifier mazeInputVerifier = new MazeInputReader(this.maze, userPath);
        String result = mazeInputVerifier.verifyPath();

        assertEquals(trueResult, result);
    }


    @Test
    public void testStraightPathMixFactored() throws FileNotFoundException, IOException{
        String trueResult = "correct path";
        String userPath = "2 F F F";

        InputVerifier mazeInputVerifier = new MazeInputReader(this.maze, userPath);
        String result = mazeInputVerifier.verifyPath();

        assertEquals(trueResult, result);
    }
}
