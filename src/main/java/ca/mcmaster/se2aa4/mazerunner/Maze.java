package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Maze {

    private String filepath;


    private char [][] maze; 

    public Maze(String filepath) throws FileNotFoundException, IOException{
        this.filepath = filepath; 
        this.maze = MazeExporter.constructMaze(filepath);

        for (char[] row: maze){
            System.out.println(Arrays.toString(row));
        }

        System.out.println("New data found: \n\n\n");
    }

    private HashMap<Character, Character> getPathOptions(int row, int col){
        HashMap<Character, Character> neighbours = new HashMap<>();

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


    /************************************************
     * @ Method Name: canMoveLeft()                    
     * Description: Checks to see if user can move left based on their current orientation of N,E,S,W
     ***********************************************
     */
    private boolean canMoveLeft(int row, int col, char c){
        HashMap<Character, Character> neighbours = getPathOptions(row, col);
        switch (c)
        {
            case 'E':
                return neighbours.getOrDefault('N', '#') == ' ';

            case 'W':
                return neighbours.getOrDefault('S', '#') == ' ';


            case 'N': 
                // checks to see if can move right
                return neighbours.getOrDefault('W', '#') == ' '; 

            case 'S':
                return neighbours.getOrDefault('E', '#') == ' '; 
        }
        return false;

    }

    /************************************************
     * @ Method Name: moveLeft()                    
     * Description: Changes path direction to left. 
     ***********************************************
     */
    private int[] moveLeft(int row, int col, char c)
    {
        switch (c)
        {
            case 'E':
                    row--;
                    c = 'N';
                    System.out.println("Moving NORTH I just updated row it's now " + row);
                    break; 

            case 'W':
                    row++;
                    c = 'S'; 
                    System.out.println("Moving SOUTH I just updated row it's now " + row);
                    break;

                
            case 'N': 
                    col--;
                    c = 'W';
                    System.out.println("Moving WEST I just updated col it's now " + col);
                    break; 
                
            case 'S':
                    col ++;
                    c = 'E'; 
                    System.out.println("Moving EAST I just updated row it's now " + col);
                    break; 

        }

        return new int[]{row, col, c};
    }


    //! Completion point delete this comment later

     /************************************************
     * @ Method Name: canMoveRight()                    
     * Description: Checks to see if user can move right based on their current orientation of N,E,S,W
     ***********************************************
     */
    private boolean canMoveRight(int row, int col, char c){
        HashMap<Character, Character> neighbours = getPathOptions(row, col);


        switch (c)
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


    /************************************************
     * @ Method Name: moveRight                  
     * Description: Determines if player can move right depending on their orientation
     ************************************************/
    private int[] moveRight(int row, int col, char c){
        switch (c)
        {
            case 'E':
                row++;
                c = 'S';
                System.out.println("Moving SOUTH I just updated row it's now " + row);
                break; 


            case 'W':
                row--; 
                c = 'N';
                System.out.println("Moving NORTH I just updated row it's now " + row);
                break;


            case 'N': 
                col++;
                c = 'E';
                System.out.println("Moving EAST I just updated col it's now " + col);
                break;


            case 'S':
                col--;
                c = 'W';
                System.out.println("Moving WEST I just updated row it's now " + col);
                break; 
        }
        return new int[]{row, col, c};
    }


        /************************************************
     * @ Method Name: canMoveLeft()                    
     * Description: Checks to see if user can move left based on their current orientation of N,E,S,W
     ***********************************************
     */
    private boolean canMoveForward(int row, int col, char c)
    {
        HashMap<Character, Character> neighbours = getPathOptions(row, col);


        switch (c)
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


    /************************************************
     * @ Method Name: moveForward()                    
     * Description: Changes path direction to forward. 
     ************************************************/
    private int[] moveForward(int row, int col, char c){
        System.out.println("Entered moveForward");
        switch (c)
        {
            case 'E':
                    col++;
                    System.out.println("Moving EAST I just updated col it's now " + col);
                    break; 
            
            case 'W':
                    col--;
                    System.out.println("Moving WEST I just updated col it's now " + col);
                    break;


            case 'N': 
                    row--; 
                    System.out.println("Moving NORTH I just updated row it's now " + row);
                    break; 

            case 'S':
                    row ++; 
                    System.out.println("Moving SOUTH I just updated row it's now " + row);
                    break;
                  
        }
        return new int[]{row, col, c};
    }

        /************************************************
     * @ Method Name: moveForward()                    
     * Description: Changes path direction to forward. 
     ************************************************/
    private int[] moveBackWards(int row, int col, char c){
        System.out.println("Entered moveForward");
        switch (c)
        {
            case 'E':
                    col--;
                    c = 'W';
                    System.out.println("Moving EAST I just updated col it's now " + col);
                    break; 
            
            case 'W':
                    col++;
                    c = 'E';
                    System.out.println("Moving WEST I just updated col it's now " + col);
                    break;


            case 'N': 
                    row++; 
                    c = 'S';
                    System.out.println("Moving NORTH I just updated row it's now " + row);
                    break; 

            case 'S':
                    row --; 
                    c = 'N';
                    System.out.println("Moving SOUTH I just updated row it's now " + row);
                    break;
                  
        }
        return new int[]{row, col, c};
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

        ArrayList<Integer> coordinates = getEntranceAndExit();


        int exitRow = coordinates.get(1); 
        int exitCol = getMazeWidth() -1; 

        int currRow = coordinates.get(0);
        int currCol = 0; 

        System.out.println("Starting: " + currRow + " " + currCol);
        System.out.println("Starting: " + exitRow + " " + exitCol);


        char orientation = 'E'; 

        
        while (currRow != exitRow || currCol != exitCol)
        {
            if (canMoveRight(currRow, currCol, orientation))
            {
                System.out.println("I can move right");
                int[] changes = moveRight(currRow, currCol, orientation);
                currRow = changes[0];
                currCol = changes[1];
                orientation = (char) changes[2];
            }
            else if (canMoveForward(currRow, currCol, orientation))
            {
                System.out.println("I can move forward");
                int[] changes = moveForward(currRow, currCol, orientation);
                currRow = changes[0];
                currCol = changes[1];
                orientation = (char) changes[2];
            }
            else if (canMoveLeft(currRow, currCol, orientation))
            {
                System.out.println("I can move left");
                int[] changes = moveLeft(currRow, currCol, orientation);
                currRow = changes[0];
                currCol = changes[1];
                orientation = (char) changes[2];
            }
            else{
                System.out.println("MOVING BACKWARDS!");
                int[] changes = moveBackWards(currRow, currCol, orientation); 
                currRow = changes[0];
                currCol = changes[1];
                orientation = (char) changes[2];
            }
            System.out.println("Orientation: " + orientation + " Row: " + currRow + " Col: " + currCol);
        }

        System.out.println("EXITING THE MAZE PATH FIND FINALLY");
        System.out.println("Exited with " + currRow + " " + currCol + " " + orientation);



        return "FFFFFF RRRRR FFFF"; 
    }
    
}
