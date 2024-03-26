package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Direction.*;
import static ca.mcmaster.se2aa4.mazerunner.Tile.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class PathChecker {

    private Tile [][] maze; 
    private int width;
    private String userDefinedPath;


    public PathChecker(Tile [][] maze ){
        this.maze = maze;
        this.width = maze[0].length;
    }


    // Checks if tile is open position
    private boolean isPass(Tile input){
        return input ==  Tile.PASS;
    }


    // Checks if tile is wall
    private boolean isWall(Tile input){
        return input == Tile.WALL;
    }

    
    /**
     * This method retrieves the nearest path options for a given player in the maze.
     * It examines the contents of the tiles located to the North, East, South, and West of the player,
     * storing the results in a HashMap for constant-time retrieval.
     *
     * @param player The player for whom path options are to be determined.
     * @return A HashMap containing path options ('N', 'S', 'W', 'E') and their corresponding tile contents.
     */
    private Map<Direction, Tile> getPathOptions(Player player)
    {

        Map<Direction, Tile> neighbours = new HashMap<>();
        int row  = player.getRow();
        int col = player.getCol();

        // Check within bounds for not going past the bottom row
        if (row != maze.length -1)
        {
            Tile character = maze[row+1][col];
            if (isWall(character)){
                neighbours.put(SOUTH, WALL); 
            }
            else{
                neighbours.put(SOUTH, PASS);
            }
        }

        // Check within bounds for not going past the top row neighbouring tile
        if (row != 0)
        {
            Tile character = maze[row-1][col];

            if (isWall(character)){
                neighbours.put(NORTH, WALL); // cannot pass on bottom
            }
            else {
                neighbours.put(NORTH, PASS); // pass on bottom
            }
        }
        
        // Check within bounds for viewing left neighbouring tile
        if (col != 0)
        {
            Tile character = maze[row][col-1];
            if ( isWall(character) ){
                neighbours.put(Direction.WEST, WALL); // cannot pass on the left
            }
            else{
                neighbours.put(Direction.WEST, PASS); // pass on left
            }
        }

        // Checks within bounds for viewing right neighbouring tile
        if (col != this.width - 1)
        {
            Tile character = maze[row][col+1];

            if (isWall(character) ){
                neighbours.put(Direction.EAST, WALL); // Cannot pass on the right
            }
            else{
                neighbours.put(Direction.EAST, PASS); // Pass on right
            }
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
    public List<Integer> getEntranceAndExit(){
        List<Integer>  exitAndEntrance = new ArrayList<>();
        
        int lastXCoordinate  = this.width -1; // Last valid column within the maze

        // Extract the east entrance of the maze on east side
        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][0]) || maze[row][0] == null){
                exitAndEntrance.add(row); // We are not concerned with the 'x' coordinate since we know it's the 0th element since entrance is west most
            }
        }

        // Extract the exit of the maze on the west side
        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][lastXCoordinate]) || maze[row][lastXCoordinate] == null){
                exitAndEntrance.add(row); 
            }
        }
        
        // Returned in the form <Entrance, Exit> 
        return exitAndEntrance; 
    }


    // Verifies if a player can move left in case of walls
    public boolean canMoveLeft(Player player){
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
    public boolean canMoveRight(Player player){
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
    public boolean canMoveForward(Player player)
    {
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
    public boolean canFollowInstruction(Direction instruction, Player player){
        if (instruction == FORWARD)
        {
            if (canMoveForward(player)){
                player.moveForward();
            }
            else{
                return false; // being instructed to go into wall hence false
            }
        }
        else if (instruction == RIGHT){
            player.turnRight();
        }
        else if (instruction == LEFT){
            player.turnLeft();
        }

        return true; 
    }


    public int getWidth(){
        return this.width; 
    }

    
    public String getUserDefinedPath(){
        return this.userDefinedPath;
    }


    public void setUserDefinedPath(String userInput){
        this.userDefinedPath = userInput;
    }
    
}
