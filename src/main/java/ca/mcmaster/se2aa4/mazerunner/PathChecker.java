package ca.mcmaster.se2aa4.mazerunner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class PathChecker {

    private char[][] maze; 
    private int width;
    private String userDefinedPath;


    public PathChecker(char[][] maze ){
        this.maze = maze;
        this.width = maze[0].length;
    }


    // Checks if tile is open position
    private boolean isPass(char input){
        return input == ' ';
    }


    // Checks if tile is wall
    private boolean isWall(char input){
        return input == '#';
    }

    
    /**
     * This method retrieves the nearest path options for a given player in the maze.
     * It examines the contents of the tiles located to the North, East, South, and West of the player,
     * storing the results in a HashMap for constant-time retrieval.
     *
     * @param player The player for whom path options are to be determined.
     * @return A HashMap containing path options ('N', 'S', 'W', 'E') and their corresponding tile contents.
     */
    private HashMap<Character, Character> getPathOptions(Player player)
    {
        HashMap<Character, Character> neighbours = new HashMap<>();
        int row  = player.getRow();
        int col = player.getCol();

        // Check within bounds for not going past the bottom row
        if (row != maze.length -1)
        {
            char character = maze[row+1][col];
            if (isWall(character)){
                neighbours.put('S', '#'); 
            }
            else{
                neighbours.put('S', ' ');
            }
        }

        // Check within bounds for not going past the top row neighbouring tile
        if (row != 0)
        {
            char character = maze[row-1][col];

            if ( isWall(character) ){
                neighbours.put('N', '#'); // cannot pass on bottom
            }
            else {
                neighbours.put('N', ' '); // pass on bottom
            }
        }
        
        // Check within bounds for viewing left neighbouring tile
        if (col != 0)
        {
            char character = maze[row][col-1];
            if ( isWall(character) ){
                neighbours.put('W', '#'); // cannot pass on the left
            }
            else{
                neighbours.put('W', ' '); // pass on left
            }
        }

        // Checks within bounds for viewing right neighbouring tile
        if (col != this.width - 1)
        {
            char character = maze[row][col+1];

            if (isWall(character) ){
                neighbours.put('E', '#'); // Cannot pass on the right
            }
            else{
                neighbours.put('E', ' '); // Pass on right
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
    public ArrayList<Integer> getEntranceAndExit(){
        ArrayList<Integer>  exitAndEntrance = new ArrayList<>();
        
        int lastXCoordinate  = this.width -1; // Last valid column within the maze

        // Extract the east entrance of the maze on east side
        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][0]) || maze[row][0] == '\0'){
                exitAndEntrance.add(row); // We are not concerned with the 'x' coordinate since we know it's the 0th element since entrance is west most
            }
        }

        // Extract the exit of the maze on the west side
        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][lastXCoordinate]) || maze[row][lastXCoordinate] == '\0'){
                exitAndEntrance.add(row); 
            }
        }
        
        // Returned in the form <Entrance, Exit> 
        return exitAndEntrance; 
    }


    // Verifies if a player can move left in case of walls
    public boolean canMoveLeft(Player player){
        HashMap<Character, Character> neighbours = getPathOptions(player);
        switch (player.getOrientation())
        {
            case 'E':
                return neighbours.getOrDefault('N', '#') == ' ';

            case 'W':
                return neighbours.getOrDefault('S', '#') == ' ';


            case 'N': 
                return neighbours.getOrDefault('W', '#') == ' '; 

            case 'S':
                return neighbours.getOrDefault('E', '#') == ' '; 
        }
        return false;
    }


    // Verifies if a player can move right incase of walls
    public boolean canMoveRight(Player player){
        HashMap<Character, Character> neighbours = getPathOptions(player);

        switch (player.getOrientation())
        {
            case 'E':
                return neighbours.getOrDefault('S', '#') == ' ';

            case 'W':
                return neighbours.getOrDefault('N', '#') == ' ';

            case 'N': 
                return neighbours.getOrDefault('E', '#') == ' '; 

            case 'S':
                return neighbours.getOrDefault('W', '#') == ' '; 
        }
        return false;

    }

    
    // Verifies if a player can move forward incase of walls
    public boolean canMoveForward(Player player)
    {
        HashMap<Character, Character> neighbours = getPathOptions(player);

        switch (player.getOrientation())
        {
            case 'E':
                return neighbours.getOrDefault('E', '#') == ' ';

            case 'W':
                return neighbours.getOrDefault('W', '#') == ' ';

            case 'N': 
                return neighbours.getOrDefault('N', '#') == ' '; 
            case 'S':
                return neighbours.getOrDefault('S', '#') == ' '; 
        }
        return false;
    }


    // Verifies if a player can follow a particular instruction such as (F, R, L)
    public boolean canFollowInstruction(char instruction, Player player){
        if (instruction == 'F')
        {
            if (canMoveForward(player)){
                player.moveForward();
            }
            else{
                return false; // being instructed to go into wall hence false
            }
        }
        else if (instruction == 'R'){
            player.turnRight();
        }
        else if (instruction == 'L'){
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
