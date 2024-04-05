package ca.mcmaster.se2aa4.mazerunner.Mazes;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.*;
import ca.mcmaster.se2aa4.mazerunner.Utils.Algorithms;


public class MazeSolverFactory {
    
    public MazeSolver createSolver(Algorithms userEnteredAlgorithm, Maze maze) {        
        // When a new algorithm is created, simply add a new case and the factory will generate the solver
        switch (userEnteredAlgorithm) {
            case BFS:
                return new BreadthFirstSearchSolver(maze);
            case RIGHTHAND:
                return new RightHandExploration(maze);
            default: 
                return new BreadthFirstSearchSolver(maze);
        }
    }
}

