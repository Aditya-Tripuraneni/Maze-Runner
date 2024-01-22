package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.transform.stream.StreamResult;

public class Maze {

    private char [][] maze; 

    public Maze(String filepath) throws FileNotFoundException, IOException
    {
        this.maze = MazeExporter.constructMaze(filepath);

        for (char[] row: maze){
            System.out.println(Arrays.toString(row));
        }

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
            factoredExpression.append(count).append(currLetter).append(" ");
        else 
            factoredExpression.append(currLetter).append(" ");


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
        PathChecker pathChecker = new PathChecker(maze);
        

        ArrayList<Integer> rowCoordinates = pathChecker.getEntranceAndExit();
        System.out.println(rowCoordinates);
        StringBuilder path = new StringBuilder();

        int exitRow = rowCoordinates.get(1); 
        int exitCol = getMazeWidth() -1; 

        System.out.println("Starting Point: " + rowCoordinates.get(0) + " " + 0);
        System.out.println("Exit Point: " + exitRow + " " + exitCol);

        Player player = new Player(rowCoordinates.get(0), 0, 'E'); 

        while (player.getRow() != exitRow || player.getCol() != exitCol)
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

        System.out.println(path);

        // System.out.println("Exited with " + player.getRow() + " " + player.getCol() + " " + player.getOrientation());

        // String s = path.toString().replaceAll("\\s", "");

        // System.out.println(s);

        System.out.println(factoredExpressionPath(path.toString()));
        
        System.out.println();

        return path.toString(); 
    }
    
}
