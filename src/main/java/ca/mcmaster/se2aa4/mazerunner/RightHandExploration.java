package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.PathChecker;
import java.util.ArrayList;


public class RightHandExploration implements MazeSolver{


    // algorithm for right hand exploration of maze
    private static void rightHandExplore(Player player, Maze maze){
        StringBuilder path = maze.getPath();
        PathChecker pathChecker = new PathChecker(maze.getMaze()); 

        while (player.getRow() != player.getExitRow() || player.getCol() != player.getExitCol())
        {
            if (pathChecker.canMoveRight(player))
            {
                player.turnRight(); // turn right
                AlgorithmInstructions.instructRight(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
            else if (pathChecker.canMoveForward(player))
            {
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
            else if (pathChecker.canMoveLeft(player))
            {
                player.turnLeft(); 
                AlgorithmInstructions.instructLeft(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
            else
            {
                // cannot go right, forward, left so must turn around
                player.turnBackwards(); 
                AlgorithmInstructions.instructBackwards(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
        }
    }


    public static String solveMaze(Maze maze) 
    {
        ArrayList<Integer> rowCoordinates = maze.getRowCoordinates(); 

        int startRow = rowCoordinates.get(0); // entrance coordinate 
        int startCol = 0;  // starting on west side means col = 0

        int exitRow = rowCoordinates.get(1);  // exit coordinate
        int exitCol = maze.getMazeWidth() -1; 

        // Player to be spawned on West side of maze
        Player player = new Player(startRow, startCol, exitRow, exitCol, 'E'); 
        

        rightHandExplore(player, maze); // solve maze using rightHandExplore algorithm

        StringBuilder path = maze.getPath(); 

        // factored instructions to exit maze
        return AlgorithmInstructions.factoredExpressionPath(path.toString()); 
    }
}
