package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.PathChecker;
import java.util.ArrayList;


public class RightHandExploration implements MazeSolver{


    // Algorithm for right hand exploration of maze
    private static void rightHandExplore(Player player, Maze maze){
        StringBuilder path = maze.getPath();
        PathChecker pathChecker = new PathChecker(maze.getMaze()); 

        while (player.getRow() != player.getExitRow() || player.getCol() != player.getExitCol())
        {
            if (pathChecker.canMoveRight(player))
            {
                // Player turns right and moves forward
                player.turnRight(); 
                AlgorithmInstructions.instructRight(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
            else if (pathChecker.canMoveForward(player))
            {
                // Player can move forward
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
            else if (pathChecker.canMoveLeft(player))
            {
                // Player turns left and moves forward
                player.turnLeft(); 
                AlgorithmInstructions.instructLeft(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
            else
            {
                // Cannot go right, forward, left so must turn around
                player.turnBackwards(); 
                AlgorithmInstructions.instructBackwards(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
        }
    }


    /**
     * This method solves the maze passed to it.
     * 
     * @param maze The maze to be solved.
     * @return A string representing the factored path to exit the maze.
     */
    public static String solveMaze(Maze maze) 
    {
        ArrayList<Integer> rowCoordinates = maze.getRowCoordinates(); 

        int startRow = rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0

        int exitRow = rowCoordinates.get(1);  // Exit coordinate
        int exitCol = maze.getMazeWidth() -1; 

        // Player to be spawned on West side of maze
        Player player = new Player(startRow, startCol, exitRow, exitCol, 'E'); 
        
        rightHandExplore(player, maze); // Solve maze using rightHandExplore algorithm

        StringBuilder path = maze.getPath(); 

        // Factored instructions to exit maze
        return AlgorithmInstructions.factoredExpressionPath(path.toString()); 
    }
}
