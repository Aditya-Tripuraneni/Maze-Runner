package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {

    private char [][] maze; 
    private PathChecker pathChecker;
    private  ArrayList<Integer> rowCoordinates;
    private StringBuilder path = new StringBuilder();

    

    public Maze(String filepath) throws FileNotFoundException, IOException
    {
        this.maze = MazeExporter.constructMaze(filepath);
        pathChecker = new PathChecker(maze);
        rowCoordinates = pathChecker.getEntranceAndExit();
    }



    public int getMazeWidth(){
        return maze[0].length;
    }


    public StringBuilder getPath(){
        return path; 
    }


    public ArrayList<Integer> getRowCoordinates(){
        return rowCoordinates; 
    }


    public char[][] getMaze(){
        return maze;
    }


    private boolean isCannonical(String userInput){
        for (char c: userInput.toCharArray()){
            if (Character.isDigit(c)) return false; 
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
                    instruction = userInput.charAt(i); // update our instruction after incrementation
                }

                int instructionTimes = Integer.parseInt(numberPortion.toString());  // number of times to execute the instruction

                // need to check out of bounds incase after incrementation we are moving past the last letter
                if (i < userInput.length()){
                    instruction = userInput.charAt(i);
                }
                
                // execute instructions
                for (int j = 1; j <= instructionTimes; j++)
                {
                    if (!pathChecker.canFollowInstruction(instruction, player)) return false; 
                }
            }
            else{ 
                // hit a wall so path invalid 
                if (!pathChecker.canFollowInstruction(instruction, player)) return false; 
            }
            i++;
        }

        System.out.println("Exits: " +  player.getExitRow() + " " + player.getExitCol());
        System.out.println("Current: " + player.getRow() + " " + player.getCol() + " " + player.getOrientation());

        return  ((player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()));

    }


    public void rightHandExplore()
    {
        RightHandExploration rightHandExplorer = new RightHandExploration(); 
        String path = rightHandExplorer.solveMazeWestToEast(this);
        System.out.println(path);
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
