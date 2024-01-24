package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.PathChecker;
import java.util.ArrayList;


public class RightHandExploration extends MazeSolver{



    /*******************************************************
     * @ Method Name: solveMazeGeneral()                    
     * Description: Sovles the maze either east to west or west to east,
     * this is a general algorithm.
     *******************************************************/
    private void solveMazeGeneral(Player player, PathChecker pathChecker, Maze maze){
        StringBuilder path = maze.getPath();

        while (player.getRow() != player.getExitRow() || player.getCol() != player.getExitCol())
        {
            if (pathChecker.canMoveRight(player))
            {
                player.moveRight(); 
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
                player.moveLeft();
                AlgorithmInstructions.instructLeft(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
            else
            {
                player.moveBackWards();
                AlgorithmInstructions.instructBackwards(path);
                player.moveForward();
                AlgorithmInstructions.instructForward(path);
            }
        }
    }

    @Override
    public String solveMazeWestToEast(Maze maze)
    {
        ArrayList<Integer> rowCoordinates = maze.getRowCoordinates();

        int startRow = rowCoordinates.get(0); // entrance coordinate 
        int startCol = 0;  // starting on west side means col = 0

        int exitRow = rowCoordinates.get(1);  // exit coordinate
        int exitCol = maze.getMazeWidth() -1; 

        System.out.println("Starting East Point: " + rowCoordinates.get(0) + " " + 0);
        System.out.println("Exit West Point: " + exitRow + " " + exitCol);

        // path to be computed without -p paramater
        Player player = new Player(startRow, startCol, exitRow, exitCol, 'E'); 

        PathChecker pathChecker = new PathChecker(maze.getMaze()); 

        solveMazeGeneral(player, pathChecker, maze);

        StringBuilder path = maze.getPath(); 

        return AlgorithmInstructions.factoredExpressionPath(path.toString()); 
    }

    @Override
    public String solveMazeEastToWest(Maze maze){
        ArrayList<Integer> rowCoordinates = maze.getRowCoordinates();

        int startRow = rowCoordinates.get(1); // entrance coordinate from east side
        int startCol = maze.getMazeWidth() -1; // starting on east  side means col = width - 1 

        int exitRow =  rowCoordinates.get(0); // exit coordinate
        int exitCol = 0;

        System.out.println("Starting West Point: " + startRow + " " + startCol);
        System.out.println("Exit East Point: " + exitRow + " " + exitCol);

        // path to be computed without -p paramater
        Player player = new Player(startRow, startCol, exitRow, exitCol, 'W'); 
        PathChecker pathChecker = new PathChecker(maze.getMaze()); 

        solveMazeGeneral(player, pathChecker, maze);

        StringBuilder path = maze.getPath(); 

        return AlgorithmInstructions.factoredExpressionPath(path.toString()); 
    }



    
}
