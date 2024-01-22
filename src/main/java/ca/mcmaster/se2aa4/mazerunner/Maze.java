package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Maze {

    private String filepath;


    private char [][] maze; 

    public Maze(String filepath) throws FileNotFoundException, IOException
    {
        this.filepath = filepath; 
        this.maze = MazeExporter.constructMaze(filepath);

        for (char[] row: maze){
            System.out.println(Arrays.toString(row));
        }

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

            if ( isWall(character) ){
                neighbours.put('E', '#'); // cannot pass on the right
            }
            else{
                neighbours.put('E', ' '); // pass on right
            }
        }

        return neighbours; 
    }  


    private ArrayList<Integer>  getEntranceAndExit(){
        ArrayList<Integer>  exitAndEntrance = new ArrayList<>();
        
        int lastXCoordinate  = maze[0].length -1; 

        for (int row =0; row < maze.length; row ++)
        {
            if (isPass(maze[row][0])){
                exitAndEntrance.add(row); // we are not concerned with the 'x' coordinate since we know it's the 0th element since entrance is west most
            }
        }

        for (int row =0; row < maze.length; row ++)
        {
            if (isPass(maze[row][lastXCoordinate])){
                exitAndEntrance.add(row); 
            }
        }

        // returned in the form <Entrance, Exit> 
        return exitAndEntrance; 
    }


    /**************************************************************************************************
     * @ Method Name: canMoveLeft()                    
     * Description: Checks to see if user can move left based on their current orientation of N,E,S,W
     **************************************************************************************************/
    private boolean canMoveLeft(Player player){
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
    

     /*************************************************************************************************
     * @ Method Name: canMoveRight()                    
     * Description: Checks to see if user can move right based on their current orientation of N,E,S,W
     ***************************************************************************************************
     */
    private boolean canMoveRight(Player player){
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



    /***************************************************************************************************
     * @ Method Name: canMoveForward()                    
     * Description: Checks to see if user can move left based on their current orientation of N,E,S,W
     ************************************************************************************************* */
    private boolean canMoveForward(Player player)
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



    /*******************************************************
     * @ Method Name: cannonicalPath()                    
     * Description: Returns the cannonical path of the maze. 
     *******************************************************/
    private String cannonicalPath(){
        return "RRR";
    }


    /*******************************************************
     * @ Method Name: factoredExpressionPath()                    
     * Description: Returns the factored expression path 
     *  of the maze so its simplified. 
     *******************************************************/
    private String factoredExpressionPath(){
        return "";
    }

    private void instructForward(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'L' || string.charAt(string.length() - 1) == 'R'))
            string.append(" F"); 
        else
            string.append("F");
    }


    private void instructRight(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'L' || string.charAt(string.length() - 1) == 'F'))
            string.append(" R"); 
        else
            string.append("R");

    }

    private void instructLeft(StringBuilder string){

        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'F' || string.charAt(string.length() - 1) == 'R'))
            string.append(" L"); 
        else
            string.append("L");
    }

    private void instructBackwards(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'F' || string.charAt(string.length() - 1) == 'L'))
            string.append(" RR"); 
        else
            string.append("RR");
    }


     /*******************************************************
     * @ Method Name: isExit()                    
     * Description: Determines if the current input path
     *  is a exit. 
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



    /*******************************************************
     * @ Method Name: verifyMazePath()                    
     * Description: Determines if the computed path is a valid 
     * path for a given maze.
     *******************************************************/
    public boolean verifyMazePath(String mazePath){
        return false;
    }

    private int getMazeWidth(){
        return maze[0].length;
    }


    /*******************************************************
     * @ Method Name: solveMaze()                    
     * Description: Sovles the maze and determins and output
     * path for the given input maze.
     *******************************************************/
    public String solveMaze(){

        ArrayList<Integer> rowCoordinates = getEntranceAndExit();
        StringBuilder string = new StringBuilder();


        int exitRow = rowCoordinates.get(1); 
        int exitCol = getMazeWidth() -1; 


        System.out.println("Starting: " + rowCoordinates.get(0) + " " + 0);
        System.out.println("Starting: " + exitRow + " " + exitCol);

        Player player = new Player(rowCoordinates.get(0), 0, 'E'); 

        while (player.getRow() != exitRow || player.getCol() != exitCol)
        {
            if (canMoveRight(player))
            {
                player.moveRight();
                instructRight(string);
                player.moveForward();
                instructForward(string);
            }
            else if (canMoveForward(player))
            {
                player.moveForward();
                instructForward(string);
            }
            else if (canMoveLeft(player))
            {
                player.moveLeft();
                instructLeft(string);
                player.moveForward();
                instructForward(string);
            }
            else
            {
                player.moveBackWards();
                instructBackwards(string);
                player.moveForward();
                instructForward(string);
            }
        }

        System.out.println(string);

        System.out.println("EXITING THE MAZE PATH FIND FINALLY");
        System.out.println("Exited with " + player.getRow() + " " + player.getCol() + " " + player.getOrientation());

        // String s = "F R F RR FF R FF R FF RR FFFF R FF R FFFF RR FF R FFFF R FF R FF RR FF L FF L FFFF R FF R FF RR FFFF R FF R FF RR FF R FF R FFFF R FF L FF R FF L F".replaceAll("\\s", "");

        // System.out.println(s);

        return string.toString(); 
    }
    
}
