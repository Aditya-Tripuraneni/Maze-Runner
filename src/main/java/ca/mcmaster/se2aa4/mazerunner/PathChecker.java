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


    // checks if tile is open position
    private boolean isPass(char input){
        return input == ' ';
    }


    // checks if tile is wall
    private boolean isWall(char input){
        return input == '#';
    }

    
    private HashMap<Character, Character> getPathOptions(Player player)
    {
        HashMap<Character, Character> neighbours = new HashMap<>();
        int row  = player.getRow();
        int col = player.getCol();

        // check within bounds for not going past the bottom row
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

        // check within bounds for not going past the top row neighbouring tile
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
        
        // check within bounds for viewing left neighbouring tile
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

        // checks within bounds for viewing right neighbouring tile
        if (col != this.width - 1)
        {
            char character = maze[row][col+1];

            if (isWall(character) ){
                neighbours.put('E', '#'); // cannot pass on the right
            }
            else{
                neighbours.put('E', ' '); // pass on right
            }
        }

        return neighbours; // all paths left, right up and down relative to player
    }  


    public ArrayList<Integer> getEntranceAndExit(){
        ArrayList<Integer>  exitAndEntrance = new ArrayList<>();
        
        int lastXCoordinate  = this.width -1; // last valid column within the maze

        // extract the east entrance of the maze on east side
        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][0]) || maze[row][0] == '\0'){
                exitAndEntrance.add(row); // we are not concerned with the 'x' coordinate since we know it's the 0th element since entrance is west most
            }
        }

        // extract the exit of the maze on the west side
        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][lastXCoordinate]) || maze[row][lastXCoordinate] == '\0'){
                exitAndEntrance.add(row); 
            }
        }
        
        // returned in the form <Entrance, Exit> 
        return exitAndEntrance; 
    }


    // verifies if a player can move left in case of walls
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


    // verifies if a player can move right incase of walls
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

    
    // verifies if a player can move forward incase of walls
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
