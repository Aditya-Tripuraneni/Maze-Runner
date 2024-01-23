package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Maze {

    private char [][] maze; 
    private PathChecker pathChecker;
    private  ArrayList<Integer> rowCoordinates;

    

    public Maze(String filepath) throws FileNotFoundException, IOException
    {
        this.maze = MazeExporter.constructMaze(filepath);
        pathChecker = new PathChecker(maze);
        rowCoordinates = pathChecker.getEntranceAndExit();

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
     * @ Method Name: solveMazeWestToEast                 
     * Description: Sovles the maze and east to west and output
     * path for the given input maze.
     *******************************************************/
    public String solveMazeWestToEast(){

        StringBuilder path = new StringBuilder();

        int startRow = rowCoordinates.get(0); // entrance coordinate 
        int startCol = 0;  // starting on west side means col = 0

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
     * @ Method Name: solveMazeEastToWest                   
     * Description: Sovles the maze west to east and output
     * path for the given input maze.
     *******************************************************/
    private String solveMazeEastToWest(){
        StringBuilder path = new StringBuilder();

        int startRow = rowCoordinates.get(1); // entrance coordinate from east side
        int startCol = getMazeWidth() -1; // starting on east  side means col = width - 1 

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


    private boolean isCannonical(String userInput){
        for (char c: userInput.toCharArray()){
            if (Character.isDigit(c)){
                return false; 
            }
        }

        return true; 
    }

    private boolean verifyCannonical(String userInput, boolean startWest)
    {
            Player player; 
            if (startWest){
                player = new Player(rowCoordinates.get(0), 0, rowCoordinates.get(1), getMazeWidth() -1, 'E');
            }
            else
            {
                player = new Player(rowCoordinates.get(1), getMazeWidth() -1, rowCoordinates.get(0), 0, 'W');
            }

            System.out.println("Exits: " +  player.getExitRow() + " " + player.getExitCol());
            System.out.println("Starts: " + player.getRow() + " " + player.getCol());


            for (char c: userInput.toCharArray()){
                // if the player cannot make a valid move then the path is not valid so return false
                if (!followInstruction(c, player)) 
                    return false; 
            }

            if (player.getRow() == player.getExitRow() && player.getCol() == player.getExitCol()){
                return true; 
            }

            return false; 
    }


    public boolean followInstruction(char instruction, Player player){
        if (instruction == 'F')
        {
            if (pathChecker.canMoveForward(player)){
                player.moveForward();
            }
            else{
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


    private boolean verifyFactorized(String userInput, boolean startWest){

        Player player; 

        // accordingly set the entrance and exit coordinates of player based on which way they start
        if (startWest){
            player = new Player(rowCoordinates.get(0), 0, rowCoordinates.get(1), getMazeWidth() -1, 'E');
        }
        else
        {
            player = new Player(rowCoordinates.get(1), getMazeWidth() -1, rowCoordinates.get(0), 0, 'W');
        }

        int i = 0;         

        // must be < than since we have multiple incrementation points in this loop which allows for case of  i exceeding length at a point
        while (i < userInput.length())
        {
            char instruction = userInput.charAt(i);
            if (Character.isDigit(instruction))
            {
                StringBuilder numberPortion = new StringBuilder();

                // extract all digits before an instruction
                while(i < userInput.length() && Character.isDigit(userInput.charAt(i)))
                {
                    // add the instruction we now have
                    numberPortion.append(instruction);
                    i++; // move on to next character
                }

                int instructionTimes = Integer.parseInt(numberPortion.toString());  // number of times to execute the instruction

                // need to check out of bounds incase after incrementation we are moving past the last letter
                if (i < userInput.length()){
                    instruction = userInput.charAt(i);
                }


                
                // execute instructions
                for (int j = 1; j <= instructionTimes; j++)
                {
                    if (!followInstruction(instruction, player)){
                        return false; // hit a wall so path invalid
                    }
                }
            }
            else{
                if (!followInstruction(instruction, player))
                {
                    return false; // hit a wall so path invalid 
                }
            }
            i++;
        }

        if (player.getRow() == player.getExitRow() && player.getCol() == player.getExitCol()){
            return true; // return to correct exit
        }

        return false; // invalid path
    }

    
    public void verifyPath(String userInput)
    {
        userInput = userInput.replaceAll("\\s", ""); 
        

        if (isCannonical(userInput))
        {
            System.out.println("Is cannonical!");
            if (verifyCannonical(userInput, true) || verifyCannonical(userInput, false)){
                System.out.println("Correct path!");
            }
            else{
                System.out.println("Incorrect path!");
            }
        }
        else{
            if (verifyFactorized(userInput, true) || verifyFactorized(userInput, false)){
                System.out.println("Correct path!");
            }
            else{
                System.out.println("Incorrect path!");
            }
        }

        
    }
    
}
