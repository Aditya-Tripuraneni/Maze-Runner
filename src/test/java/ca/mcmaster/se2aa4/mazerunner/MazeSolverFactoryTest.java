package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.BreadthFirstSearchSolver;
import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.RightHandExploration;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;


public class MazeSolverFactoryTest {
    private Path path; 
    private Maze maze; 

    @BeforeEach
    public void setUpMaze() throws FileNotFoundException, IOException{
        this.path = new Path("./examples/straight.maz.txt");
        this.maze = new Maze(path);
    }


    @Test
    public void testCreateBreadthFirstSearchSolver() throws FileNotFoundException, IOException {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("BFS", maze);
        assertTrue(solver instanceof BreadthFirstSearchSolver);
    }


    @Test
    public void testCreateRightHandSolver() throws FileNotFoundException, IOException {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("RIGHTHAND", maze);
        assertTrue(solver instanceof RightHandExploration);
    }
    
}
