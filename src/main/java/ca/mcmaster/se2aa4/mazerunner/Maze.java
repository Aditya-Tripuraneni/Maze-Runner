package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Maze {

    private String filepath;


     private char [][] maze; 

    public Maze(String filepath) throws FileNotFoundException, IOException{
        this.filepath = filepath; 
        this.maze = MazeExporter.constructMaze(filepath);

        for (char[] ting: maze){
            System.out.println(Arrays.toString(ting));
        }
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
    private boolean isWall(char inut){
        return false;
    }


     /*******************************************************
     * @ Method Name: isExit()                    
     * Description: Determines if the current input path
     *  is a exit. 
     *******************************************************/
    private boolean isExit(char input){
        return false; 
    }


    /*******************************************************
     * @ Method Name: isEntrance()                    
     * Description: Determines if the current input path
     *  is an entrance. 
     *******************************************************/
    private boolean isEntrance(char input){
        return false; 
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

        return ""; 
    }
    
}
