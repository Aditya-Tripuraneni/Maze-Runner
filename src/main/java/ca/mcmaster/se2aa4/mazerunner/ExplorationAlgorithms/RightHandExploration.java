package ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms;


import ca.mcmaster.se2aa4.mazerunner.Entity.Location;
import ca.mcmaster.se2aa4.mazerunner.Entity.Player;
import ca.mcmaster.se2aa4.mazerunner.Entity.PlayerExplorer;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Paths.*;

import java.util.List;


import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;


public class RightHandExploration implements MazeSolver{
    private Maze maze; 
    private AlgorithmInstructions instructionCreator;


    public RightHandExploration(Maze maze){
        this.maze = maze; 
        this.instructionCreator = new AlgorithmInstructions(new Path());
    }


    // Algorithm for right hand exploration of maze
    private void rightHandExplore(PlayerExplorer player){
        PathChecker pathChecker = new PathChecker(maze); 

        while (player.getCurrentLocation().getX() != player.getExitLocation().getX() || player.getCurrentLocation().getY() != player.getExitLocation().getY())
        {
            if (pathChecker.canMoveRight(player))
            {
                // Player turns right and moves forward
                player.turnRight(); 
                instructionCreator.instructRight();
                player.moveForward();
                instructionCreator.instructForward();
            }
            else if (pathChecker.canMoveForward(player))
            {
                // Player can move forward
                player.moveForward();
                instructionCreator.instructForward();
            }
            else if (pathChecker.canMoveLeft(player))
            {
                // Player turns left and moves forward
                player.turnLeft(); 
                instructionCreator.instructLeft();
                player.moveForward();
                instructionCreator.instructForward();
            }
            else
            {
                // Cannot go right, forward, left so must turn around
                player.turnBackwards(); 
                instructionCreator.instructBackwards();
                player.moveForward();
                instructionCreator.instructForward();
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
    public Path solveMaze() 
    {
        List<Integer> rowCoordinates = maze.getRowCoordinates(); 

        int startRow = rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0
        Location startLocation = new Location(startRow, startCol);

        int exitRow = rowCoordinates.get(1);  // Exit coordinate
        int exitCol = maze.getMazeWidth() -1; 
        Location exitLocation = new Location(exitRow, exitCol);


        // Player to be spawned on West side of maze
        PlayerExplorer player = new Player(startLocation, exitLocation, EAST); 
        
        this.rightHandExplore(player); // Solve maze using rightHandExplore algorithm

        String factoredPath = instructionCreator.factorizeInstructions();

        // Factored instructions to exit maze
        return new Path(factoredPath); 
    }
}
