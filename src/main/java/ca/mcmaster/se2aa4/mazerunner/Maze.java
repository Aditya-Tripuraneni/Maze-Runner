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
            else if(string.charAt(i) != ' ')
            {
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


    /************************************************************************
    * @Method Name: instructForward(StringBuilder string)                    
    * 
    * @Description: Appends the instruction for moving forward ('F') 
    *               to the provided StringBuilder. If the last character 
    *               in the StringBuilder is 'L' or 'R', a space followed 
    *               by 'F' is appended; otherwise, 'F' is appended directly.
    * 
    * @param string: The StringBuilder to which the instruction is appended.
    *************************************************************************/
    private void instructForward(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'L' || string.charAt(string.length() - 1) == 'R'))
            string.append(" F"); 
        else
            string.append("F");
    }


    /**********************************************************
    * @Method Name: instructRight(StringBuilder string)                    
    * 
    * @Description: Appends the instruction for turning right ('R') 
    *               to the provided StringBuilder. If the last character 
    *               in the StringBuilder is 'L' or 'F', a space followed 
    *               by 'R' is appended; otherwise, 'R' is appended directly.
    * 
    * @param string: The StringBuilder to which the instruction is appended.
    **********************************************************/
    private void instructRight(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'L' || string.charAt(string.length() - 1) == 'F'))
            string.append(" R"); 
        else
            string.append("R");
    }


    /**********************************************************
    * @Method Name: instructLeft(StringBuilder string)                    
    * 
    * @Description: Appends the instruction for turning left ('L') 
    *               to the provided StringBuilder. If the last character 
    *               in the StringBuilder is 'F' or 'R', a space followed 
    *               by 'L' is appended; otherwise, 'L' is appended directly.
    * 
    * @param string: The StringBuilder to which the instruction is appended.
    **********************************************************/
    private void instructLeft(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'F' || string.charAt(string.length() - 1) == 'R'))
            string.append(" L"); 
        else
            string.append("L");
    }


    /**********************************************************
    * @Method Name: instructBackwards(StringBuilder string)                    
    * 
    * @Description: Appends the instruction for moving backwards ('RR') 
    *               to the provided StringBuilder. If the last character 
    *               in the StringBuilder is 'F' or 'L', a space followed 
    *               by 'RR' is appended; otherwise, 'RR' is appended directly.
    * 
    * @param string: The StringBuilder to which the instruction is appended.
    **********************************************************/
    private void instructBackwards(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'F' || string.charAt(string.length() - 1) == 'L'))
            string.append(" RR"); 
        else
            string.append("RR");
    }



    /**********************************************************
    * @Method Name: getMazeWidth()                    
    * 
    * @Description: Returns the width of the maze, which is the 
    *               number of columns in the 2D character array.
    * 
    * @return int: The width of the maze.
    **********************************************************/
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

        // System.out.println(path);
        // System.out.println("\n\n\n\n");


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



    /**********************************************************
    * @Method Name: isCanonical(String userInput)                    
    * 
    * @Description: Checks if the provided user input consists 
    *               entirely of non-digit characters.
    * 
    * @param userInput: The input string to be checked.
    * @return boolean: True if the input is canonical (contains 
    *                 only non-digit characters), false otherwise.
    **********************************************************/
    private boolean isCannonical(String userInput){
        for (char c: userInput.toCharArray()){
            if (Character.isDigit(c)){
                return false; 
            }
        }
        return true; 
    }



    /**********************************************************
     * @Method Name: verifyCanonical(String userInput, boolean startWest)                    
     * 
     * @Description: Verifies whether the given canonical user input 
     *               leads the player to the maze exit. The player's 
     *               starting position and direction are determined 
     *               based on the 'startWest' parameter.
     * 
     * @param userInput: The canonical user input string to be verified.
     * @param startWest: A boolean flag indicating whether the player 
     *                  starts from the west side of the maze.
     * @return boolean: True if the user input leads the player to 
     *                 the maze exit, false otherwise.
     **********************************************************/
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
                if (!pathChecker.canFollowInstruction(c, player)) 
                    return false; 
            }

            return ( (player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()) );

    }



    /**********************************************************
     * @Method Name: verifyFactorized(String userInput, boolean startWest)                    
     * 
     * @Description: Verifies whether the given factorized user input 
     *               leads the player to the maze exit. The player's 
     *               starting position and direction are determined 
     *               based on the 'startWest' parameter.
     * 
     * @param userInput: The factorized user input string to be verified.
     * @param startWest: A boolean flag indicating whether the player 
     *                  starts from the west side of the maze.
     * @return boolean: True if the user input leads the player to 
     *                 the maze exit, false otherwise.
     **********************************************************/
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
                    instruction = userInput.charAt(i); 
                }

                int instructionTimes = Integer.parseInt(numberPortion.toString());  // number of times to execute the instruction

                // need to check out of bounds incase after incrementation we are moving past the last letter
                if (i < userInput.length()){
                    instruction = userInput.charAt(i);
                }
                
                // execute instructions
                for (int j = 1; j <= instructionTimes; j++)
                {
                    if (!pathChecker.canFollowInstruction(instruction, player)){
                        System.out.println("Error for loop");
                        System.out.println("Im being told to do: " + instruction);
                        System.out.println("Current: " + player.getRow() + " " + player.getCol() + " " + player.getOrientation());

                        return false; 
                    }

                }
            }
            else{ 
                if (!pathChecker.canFollowInstruction(instruction, player))
                {
                    System.out.println("Error outside");
                    return false; // hit a wall so path invalid 
                }
            }
            i++;
        }

        System.out.println("Exits: " +  player.getExitRow() + " " + player.getExitCol());
        System.out.println("Current: " + player.getRow() + " " + player.getCol() + " " + player.getOrientation());

        if (player.getRow() == player.getExitRow() && player.getCol() == player.getExitCol()){
            return true; // return to correct exit
        }

        return false; // invalid path
    }

    
    /**********************************************************
     * @Method Name: verifyPath(String userInput)                    
     * 
     * @Description: Verifies whether the given user input string 
     *               represents a valid path leading the player to 
     *               the maze exit. The method checks if the input 
     *               is canonical or factorized and validates the 
     *               path accordingly.
     * 
     * @param userInput: The user input string representing the path.
     **********************************************************/
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
