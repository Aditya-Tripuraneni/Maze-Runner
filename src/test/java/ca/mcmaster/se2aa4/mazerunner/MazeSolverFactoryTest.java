package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ca.mcmaster.se2aa4.mazerunner.Utils.Algorithms.*;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.BreadthFirstSearchSolver;
import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.RightHandExploration;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeExporter;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeMatrixExporter;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;


public class MazeSolverFactoryTest {
    private String path = "./examples/straight.maz.txt";
    private MazeExporter mazeExporter; 
    private Maze maze; 
    

    /**
     * Sets up a maze for testing.
     *
     * @throws FileNotFoundException if the maze file is not found.
     * @throws IOException           if an I/O error occurs while reading the maze file.
     */
    @BeforeEach
    public void setUpMaze() throws FileNotFoundException, IOException {
        this.mazeExporter = new MazeMatrixExporter(this.path);
        this.maze = this.mazeExporter.constructMaze();
    }


    /**
     * Tests whether the Breadth First Search (BFS) solver created by the MazeSolverFactory
     * implements the MazeSolver interface.
     */
    @Test
    public void testBFSImplementsMazeSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver(BFS, maze);
        assertTrue(solver instanceof MazeSolver);
    }


    /**
     * Tests whether the MazeSolverFactory correctly creates a Breadth First Search (BFS) solver.
     */
    @Test
    public void testCreateBreadthFirstSearchSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver(BFS, maze);
        assertTrue(solver instanceof BreadthFirstSearchSolver);
    }


    /**
     * Tests whether the solver created by the MazeSolverFactory for Breadth First Search (BFS)
     * algorithm is not an instance of Right Hand Exploration solver.
     */
    @Test
    public void testBFSSolverNotInstanceRightHandSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver(BFS, maze);
        assertFalse(solver instanceof RightHandExploration);
    }


    /**
     * Tests whether the Right Hand Exploration solver created by the MazeSolverFactory
     * implements the MazeSolver interface.
     */
    @Test
    public void testRightHandImplementsMazeSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver(RIGHTHAND, maze);
        assertTrue(solver instanceof MazeSolver);
    }


    /**
     * Tests whether the MazeSolverFactory correctly creates a Right Hand Exploration solver.
     */
    @Test
    public void testCreateRightHandSolver() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver(RIGHTHAND, maze);
        assertTrue(solver instanceof RightHandExploration);
    }


    /**
     * Tests whether the solver created by the MazeSolverFactory for Right Hand Exploration
     * algorithm is not an instance of Breadth First Search (BFS) solver.
     */
    @Test
    public void testRightHandSolverNotInstanceBFS() {
        MazeSolverFactory factory = new MazeSolverFactory();
        MazeSolver solver = factory.createSolver(RIGHTHAND, maze);
        assertFalse(solver instanceof BreadthFirstSearchSolver);
    }
}
