package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.BreadthFirstSearchSolver;
import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.RightHandExploration;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeMatrix;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;


public class MazeSolverFactoryTest {
    private Path path; 
    private MazeMatrix maze; 
    

    /**
     * Sets up a maze for testing.
     *
     * @throws FileNotFoundException if the maze file is not found.
     * @throws IOException           if an I/O error occurs while reading the maze file.
     */
    @BeforeEach
    public void setUpMaze() throws FileNotFoundException, IOException{
        this.path = new Path("./examples/straight.maz.txt");
        this.maze = new MazeMatrix(path);
    }


    /**
     * Tests whether the Breadth First Search (BFS) solver created by the MazeSolverFactory
     * implements the MazeSolver interface.
     */
    @Test
    public void testBFSImplementsMazeSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("BFS", maze);
        assertTrue(solver instanceof MazeSolver);
    }


    /**
     * Tests whether the MazeSolverFactory correctly creates a Breadth First Search (BFS) solver.
     */
    @Test
    public void testCreateBreadthFirstSearchSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("BFS", maze);
        assertTrue(solver instanceof BreadthFirstSearchSolver);
    }


    /**
     * Tests whether the solver created by the MazeSolverFactory for Breadth First Search (BFS)
     * algorithm is not an instance of Right Hand Exploration solver.
     */
    @Test
    public void testBFSSolverNotInstanceRightHandSolver(){
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("BFS", maze);
        assertFalse(solver instanceof RightHandExploration);
    }

    /**
     * Tests whether the Right Hand Exploration solver created by the MazeSolverFactory
     * implements the MazeSolver interface.
     */
    @Test
    public void testRightHandImplementsMazeSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("RIGHTHAND", maze);
        assertTrue(solver instanceof MazeSolver);
    }


    /**
     * Tests whether the MazeSolverFactory correctly creates a Right Hand Exploration solver.
     */
    @Test
    public void testCreateRightHandSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("RIGHTHAND", maze);
        assertTrue(solver instanceof RightHandExploration);
    }


    /**
     * Tests whether the solver created by the MazeSolverFactory for Right Hand Exploration
     * algorithm is not an instance of Breadth First Search (BFS) solver.
     */
    @Test
    public void testRightHandSolverNotInstanceBFS() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver("RIGHTHAND", maze);
        assertFalse(solver instanceof BreadthFirstSearchSolver);
    }

    
}
