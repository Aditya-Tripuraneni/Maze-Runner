package ca.mcmaster.se2aa4.mazerunner.Mazes;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.*;
import ca.mcmaster.se2aa4.mazerunner.Utils.Algorithms;
import static ca.mcmaster.se2aa4.mazerunner.Utils.Algorithms.*;


public class MazeSolverFactory {
    
    public MazeSolver createSolver(String userEnteredAlgorithm, Maze maze) {
        Algorithms algorithm = this.getMazeAlgorithm(userEnteredAlgorithm);
        
        // When a new algorithm is created, simply add a new case and the factory will generate the solver
        switch (algorithm) {
            case BFS:
                return new BreadthFirstSearchSolver(maze);
            case RIGHTHAND:
                return new RightHandExploration(maze);
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
    }

    private Algorithms getMazeAlgorithm(String algorithm){
        if ("righthand".equals(algorithm)){
            return RIGHTHAND;
        }
        else if ("BFS".equals(algorithm)){
            return BFS; 
        }
        return BAD_ALGORITHM; // Or throw an exception for unknown algorithms
    }
}

