package ca.mcmaster.se2aa4.mazerunner.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.mazerunner.Entity.Location;
import ca.mcmaster.se2aa4.mazerunner.Entity.PlayerExplorer;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;

import ca.mcmaster.se2aa4.mazerunner.Mazes.Tile;
import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;
import static ca.mcmaster.se2aa4.mazerunner.Mazes.Tile.*;


import java.util.ArrayList;

public class PathChecker {
    private Maze maze;
    private int width;

    public PathChecker(Maze maze) {
        this.maze = maze; 
        this.width = this.maze.getMazeWidth();
    }

    
    /**
     * This method retrieves the nearest path options for a given player in the maze.
     * It examines the contents of the tiles located to the North, East, South, and West of the player,
     * storing the results in a HashMap for constant-time retrieval.
     *
     * @param player The player for whom path options are to be determined.
     * @return A HashMap containing path options ('N', 'S', 'W', 'E') and their corresponding tile contents.
     */
    private Map<Direction, Tile> getPathOptions(PlayerExplorer player) {
        Map<Direction, Tile> neighbours = new HashMap<>();

        Location currLocation = player.getCurrentLocation();

        Map<Direction, Boolean> neighbouringTiles = this.maze.getNeighbouringTiles(currLocation.getX(), currLocation.getY());

        if (neighbouringTiles.get(SOUTH)){
            neighbours.put(SOUTH, PASS);
        }
        else {
            neighbours.put(SOUTH, WALL);
        }

        if (neighbouringTiles.get(NORTH)){
            neighbours.put(NORTH, PASS);
        }
        else{
            neighbours.put(NORTH, WALL);
        }

        if (neighbouringTiles.get(EAST)){
            neighbours.put(EAST, PASS);
        }
        else{
            neighbours.put(EAST, WALL);
        }

        if (neighbouringTiles.get(WEST)){
            neighbours.put(WEST, PASS);
        }
        else{
            neighbours.put(WEST, WALL);
        }

        return neighbours; // All paths left, right up and down relative to player
    }  


    /**
     * This method retrieves the entrance and exit coordinates of the maze.
     * It searches for the entrance on the east side and the exit on the west side.
     * The coordinates are returned as an ArrayList in the form <Entrance, Exit>.
     *
     * @return An ArrayList containing the entrance and exit row coordinates of the maze.
     */
    public List<Integer> getEntranceAndExit() {
        List<Integer>  exitAndEntrance = new ArrayList<>();
        
        int lastXCoordinate  = this.width -1; // Last valid column within the maze

        // Extract the east entrance of the maze on east side
        for (int row =0; row < this.maze.getMazeHeight(); row ++)
        {

            if (this.maze.isPassTile(row, 0)){
                exitAndEntrance.add(row); // We are not concerned with the 'x' coordinate since we know it's the 0th element since entrance is west most
            }
        }

        // Extract the exit of the maze on the west side
        for (int row =0; row < this.maze.getMazeHeight(); row ++)
        {

            if (this.maze.isPassTile(row, lastXCoordinate)){
                exitAndEntrance.add(row); 
            }
        }
        
        // Returned in the form <Entrance, Exit> 
        return exitAndEntrance; 
    }


    // Verifies if a player can move left in case of walls
    public boolean canMoveLeft(PlayerExplorer player) {
        Map<Direction, Tile> neighbours = this.getPathOptions(player);
        switch (player.getOrientation())
        {
            case EAST:
                return neighbours.getOrDefault(NORTH, WALL) == PASS;

            case WEST:
                return neighbours.getOrDefault(SOUTH, WALL) == PASS;

            case NORTH: 
                return neighbours.getOrDefault(WEST, WALL) == PASS; 

            case SOUTH:
                return neighbours.getOrDefault(EAST, WALL) == PASS;
            default:
                break; 
        }
        return false;
    }


    // Verifies if a player can move right incase of walls
    public boolean canMoveRight(PlayerExplorer player) {
        Map<Direction, Tile> neighbours = getPathOptions(player);

        switch (player.getOrientation())
        {
            case EAST:
                return neighbours.getOrDefault(SOUTH, WALL) == PASS;

            case WEST:
                return neighbours.getOrDefault(NORTH, WALL) == PASS;

            case NORTH: 
                return neighbours.getOrDefault(EAST, WALL) == PASS; 

            case SOUTH:
                return neighbours.getOrDefault(WEST, WALL) == PASS;
            default:
                break; 
        }
        return false;

    }

    
    // Verifies if a player can move forward incase of walls
    public boolean canMoveForward(PlayerExplorer player) {
        Map<Direction, Tile> neighbours = getPathOptions(player);

        switch (player.getOrientation())
        {
            case EAST:
                return neighbours.getOrDefault(EAST, WALL) == PASS;

            case WEST:
                return neighbours.getOrDefault(WEST, WALL) == PASS;

            case NORTH: 
                return neighbours.getOrDefault(NORTH, WALL) == PASS; 
            case SOUTH:
                return neighbours.getOrDefault(SOUTH, WALL) == PASS;
            default:
                break; 
        }
        return false;
    }


    // Verifies if a player can follow a particular instruction such as (F, R, L)
    public boolean canFollowInstruction(Direction instruction, PlayerExplorer player) {
        if (instruction == F)
        {
            if (canMoveForward(player)){
                player.moveForward();
            }
            else{
                return false; // being instructed to go into wall hence false
            }
        }
        else if (instruction == R){
            player.turnRight();
        }
        else if (instruction == L){
            player.turnLeft();
        }

        return true; 
    }
    

    public int getWidth() {
        return this.width; 
    }
}
