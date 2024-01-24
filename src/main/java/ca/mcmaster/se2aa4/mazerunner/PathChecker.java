package ca.mcmaster.se2aa4.mazerunner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class PathChecker {

    private char[][] maze; 

    public PathChecker(char[][] maze ){
        this.maze = maze;
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
                neighbours.put('S', '#'); // S is for South
            }
            else{
                neighbours.put('S', ' ');
            }
        }

        // check within bounds for not going past the top row
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
        
        // check within bounds for viewing left neighbour
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

        if (col != maze[0].length - 1)
        {
            char character = maze[row][col+1];

            if (isWall(character) ){
                neighbours.put('E', '#'); // cannot pass on the right
            }
            else{
                neighbours.put('E', ' '); // pass on right
            }
        }

        return neighbours; 
    }  


    public ArrayList<Integer> getEntranceAndExit(){
        ArrayList<Integer>  exitAndEntrance = new ArrayList<>();
        
        int lastXCoordinate  = maze[0].length -1; 

        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][0]) || maze[row][0] == '\0'){
                exitAndEntrance.add(row); // we are not concerned with the 'x' coordinate since we know it's the 0th element since entrance is west most
            }
        }

        for (int row =0; row < maze.length; row ++)
        {

            if (isPass(maze[row][lastXCoordinate]) || maze[row][lastXCoordinate] == '\0'){
                exitAndEntrance.add(row); 
            }
        }
        // returned in the form <Entrance, Exit> 
        return exitAndEntrance; 
    }


    /*******************************************************
     * @ Method Name: isPass()                    
     * Description: Determines if the current input path is a valid 
     * tile to step on 
     *******************************************************/
    private boolean isPass(char input){
        return input == ' ';
    }


    /*******************************************************
     * @ Method Name: isWall()                    
     * Description: Determines if the current input path
     *  is a wall. 
     *******************************************************/
    private boolean isWall(char input){
        return input == '#';
    }


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


    public boolean canMoveForward(Player player)
    {
        HashMap<Character, Character> neighbours = getPathOptions(player);
        // System.out.println(neighbours);

        switch (player.getOrientation())
        {
            case 'E':
                // System.out.println("Here " + "'" + neighbours.get('E') + "'");

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
                System.out.println("This is being hit");
                return false;
            }
        }
        else if (instruction == 'R')
        {
            player.moveRight();
        }
        else if (instruction == 'L')
        {
            player.moveLeft();
        }

        return true; 
    }

    
}
