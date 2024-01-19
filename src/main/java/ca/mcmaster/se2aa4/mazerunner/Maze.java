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
        System.out.println(getEntranceAndExit());
    }

    private HashMap<Character, Character> getPathOptions(int row, int col){
        HashMap<Character, Character> neighbours = new HashMap<>();

        // check within bounds for not going past the bottom row
        if (row != maze.length -1)
        {
            char character = maze[row+1][col];
            if (isWall(character)){
                neighbours.put('T', 'W');
            }
            else{
                neighbours.put('T', 'P');
            }
        }

        // check within bounds for not going past the top row
        if (row != 0)
        {
            char character = maze[row-1][col];

            if ( isWall(character) ){
                neighbours.put('B', 'W'); // cannot pass on bottom
            }
            else {
                neighbours.put('B', 'P'); // pass on bottom
            }
        }
        
        // check within bounds for viewing left neighbour
        if (col != 0)
        {
            char character = maze[row][col-1];
            if ( isWall(character) ){
                neighbours.put('L', 'W'); // cannot pass on the left
            }
            else{
                neighbours.put('L', 'P'); // pass on left
            }
        }

        if (col != maze[0].length - 1)
        {
            char character = maze[row][col+1];

            if ( isWall(character) ){
                neighbours.put('R', 'W'); // cannot pass on the right
            }
            else{
                neighbours.put('R', 'P'); // pass on right
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
     * @ Method Name: moveLeft()                    
     * Description: Changes path direction to left. 
     ***********************************************
     */
    private String moveLeft(){
        return "L";
    }


    /************************************************
     * @ Method Name: moveRight()                    
     * Description: Changes path direction to right. 
     ************************************************/
    private String moveRight(){
        return "R";
    }


    /************************************************
     * @ Method Name: moveForward()                    
     * Description: Changes path direction to forward. 
     ************************************************/
    private String moveForward(){
        return "F";
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
     * @ Method Name: isWall()                    
     * Description: Determines if the current input path
     *  is a wall. 
     *******************************************************/
    private boolean isWall(char input){
        return input == '#';
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
     * @ Method Name: verifyMazePath()                    
     * Description: Determines if the computed path is a valid 
     * path for a given maze.
     *******************************************************/
    public boolean verifyMazePath(String mazePath, String inputPath){
        return false;
    }


    /*******************************************************
     * @ Method Name: solveMaze()                    
     * Description: Sovles the maze and determins and output
     * path for the given input maze.
     *******************************************************/
    public String solveMaze(){
        ArrayList<Integer> coordinates = getEntranceAndExit();
        int target = coordinates.get(1); 
        int start = coordinates.get(0);

        System.out.println("Starting at position " + start + ", " + 0); 
        System.out.println("Ending at: " + target + ", " + (maze[0].length -1));

        return ""; 
    }
    
}
