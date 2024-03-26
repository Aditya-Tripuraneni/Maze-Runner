package ca.mcmaster.se2aa4.mazerunner;


import static ca.mcmaster.se2aa4.mazerunner.Direction.*;

import java.util.List;


public class RightHandExploration implements MazeSolver{
    private Maze maze; 
    private AlgorithmInstructions instructorCreator;


    public RightHandExploration(Maze maze){
        this.maze = maze; 
        this.instructorCreator = new AlgorithmInstructions(maze.getPath());
    }


    // Algorithm for right hand exploration of maze
    private void rightHandExplore(Player player){
        PathChecker pathChecker = new PathChecker(maze.getMaze()); 

        while (player.getRow() != player.getExitRow() || player.getCol() != player.getExitCol())
        {
            if (pathChecker.canMoveRight(player))
            {
                // Player turns right and moves forward
                player.turnRight(); 
                instructorCreator.instructRight();
                player.moveForward();
                instructorCreator.instructForward();
            }
            else if (pathChecker.canMoveForward(player))
            {
                // Player can move forward
                player.moveForward();
                instructorCreator.instructForward();
            }
            else if (pathChecker.canMoveLeft(player))
            {
                // Player turns left and moves forward
                player.turnLeft(); 
                instructorCreator.instructLeft();
                player.moveForward();
                instructorCreator.instructForward();
            }
            else
            {
                // Cannot go right, forward, left so must turn around
                player.turnBackwards(); 
                instructorCreator.instructBackwards();
                player.moveForward();
                instructorCreator.instructForward();
            }
        }
    }


    /**
     * This method solves the maze passed to it.
     * 
     * @param maze The maze to be solved.
     * @return A string representing the factored path to exit the maze.
     */
    @Override
    public String solveMaze() 
    {
        List<Integer> rowCoordinates = maze.getRowCoordinates(); 

        int startRow = rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0

        int exitRow = rowCoordinates.get(1);  // Exit coordinate
        int exitCol = maze.getMazeWidth() -1; 

        // Player to be spawned on West side of maze
        Player player = new Player(startRow, startCol, exitRow, exitCol, EAST); 
        
        this.rightHandExplore(player); // Solve maze using rightHandExplore algorithm

        StringBuilder path = maze.getPath(); 

        // Factored instructions to exit maze
        return instructorCreator.factoredExpressionPath(path.toString()); 
    }
}
