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
            default: // this case is for BAD_ALGORITHM (unknown or algorithm does not exist)
                throw new IllegalArgumentException(BAD_ALGORITHM + "request, Unknown algorithm: " + algorithm);
        }
    }


    private Algorithms getMazeAlgorithm(String userEnteredAlgorithm){
        String algorithm = userEnteredAlgorithm.toUpperCase();
        if ("RIGHTHAND".equals(algorithm)){
            return RIGHTHAND;
        }
        else if ("BFS".equals(algorithm)){
            return BFS; 
        }
        return BAD_ALGORITHM; // Exception for unknown algorithms
    }
}

