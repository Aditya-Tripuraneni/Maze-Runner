package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Maze {

    private char [][] maze; 

    public Maze(String filepath) throws FileNotFoundException, IOException
    {
        this.maze = MazeExporter.constructMaze(filepath);

        // for (char[] row: maze){
        //     System.out.println(Arrays.toString(row));
        // }

    }


    public char[][] getMaze(){
        return this.maze; 
    }


    /*******************************************************
     * @ Method Name: factoredExpressionPath()                    
     * Description: Returns the factored expression path 
     *  of the maze so its simplified. 
     *******************************************************/
    private String factoredExpressionPath(String string){
        StringBuilder factoredExpression = new StringBuilder();

        char currLetter = string.charAt(0);
        int count = 0; 

        for (int i = 0; i < string.length(); i ++)
        {
            if (string.charAt(i) == currLetter)
                count++;
            else if(string.charAt(i) != ' '){
                if (count > 1)
                    factoredExpression.append(count).append(currLetter).append(" ");
                else
                    factoredExpression.append(currLetter).append(" ");
                currLetter = string.charAt(i);
                count = 1;

            }
        }

        if (count > 1)
            factoredExpression.append(count).append(currLetter);
        else 
            factoredExpression.append(currLetter);


        return factoredExpression.toString();
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


    private int getMazeWidth(){
        return maze[0].length;
    }


    /*******************************************************
     * @ Method Name: solveMazeGeneral()                    
     * Description: Sovles the maze either east to west or west to east,
     * this is a general algorithm.
     *******************************************************/

    private void solveMazeGeneral(Player player, PathChecker pathChecker, StringBuilder path){

        while (player.getRow() != player.getExitRow() || player.getCol() != player.getExitCol())
        {
            if (pathChecker.canMoveRight(player))
            {
                player.moveRight();
                instructRight(path);
                player.moveForward();
                instructForward(path);
            }
            else if (pathChecker.canMoveForward(player))
            {
                player.moveForward();
                instructForward(path);
            }
            else if (pathChecker.canMoveLeft(player))
            {
                player.moveLeft();
                instructLeft(path);
                player.moveForward();
                instructForward(path);
            }
            else
            {
                player.moveBackWards();
                instructBackwards(path);
                player.moveForward();
                instructForward(path);
            }
        }
    }


    /*******************************************************
     * @ Method Name: solveMazeEastToWest()                    
     * Description: Sovles the maze and east to west and output
     * path for the given input maze.
     *******************************************************/
    public String solveMazeEastToWest(){
        PathChecker pathChecker = new PathChecker(maze);

        ArrayList<Integer> rowCoordinates = pathChecker.getEntranceAndExit();
        StringBuilder path = new StringBuilder();

        int startRow = rowCoordinates.get(0); // entrance coordinate 
        int startCol = 0;  // starting on east side means col = 0

        int exitRow = rowCoordinates.get(1);  // exit coordinate
        int exitCol = getMazeWidth() -1; 

        System.out.println("Starting East Point: " + rowCoordinates.get(0) + " " + 0);
        System.out.println("Exit West Point: " + exitRow + " " + exitCol);

        // path to be computed without -p paramater
        Player player = new Player(startRow, startCol, exitRow, exitCol, 'E'); 

        solveMazeGeneral(player, pathChecker, path);


        System.out.println(factoredExpressionPath(path.toString()));
        
        System.out.println();

        return factoredExpressionPath(path.toString()); 
    }



    /*******************************************************
     * @ Method Name: solveMazeWestToEast()                    
     * Description: Sovles the maze west to east and output
     * path for the given input maze.
     *******************************************************/
    private String solveMazeWestToEast(){
        PathChecker pathChecker = new PathChecker(maze);

        ArrayList<Integer> rowCoordinates = pathChecker.getEntranceAndExit();
        StringBuilder path = new StringBuilder();

        int startRow = rowCoordinates.get(1); // entrance coordinate from west side
        int startCol = getMazeWidth() -1; // starting on west  side means col = width - 1 

        int exitRow =  rowCoordinates.get(0); // exit coordinate
        int exitCol = 0;

        System.out.println("Starting West Point: " + startRow + " " + startCol);
        System.out.println("Exit East Point: " + exitRow + " " + exitCol);

        // path to be computed without -p paramater
        Player player = new Player(startRow, startCol, exitRow, exitCol, 'W'); 

        solveMazeGeneral(player, pathChecker, path);


        System.out.println(factoredExpressionPath(path.toString()));
        
        System.out.println();

        return factoredExpressionPath(path.toString()); 
    }

    public void verifyPath(String userInput){
        String eastToWestPath = solveMazeEastToWest();
        String westToEastPath = solveMazeWestToEast(); 

        if (userInput.equals(eastToWestPath) || userInput.equals(westToEastPath)){
            System.out.println("correct path");
        }
        else{
            System.out.println("incorrect path");
        }
        
    }
    
}
