package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.PathChecker;
import java.util.ArrayList;


public class MazeInputReader implements InputReader{
    private PathChecker pathChecker; 
    private ArrayList<Integer> rowCoordinates;
    private int width; 
    private String userInput; 
    

    public  MazeInputReader(PathChecker pathChecker){
            this.pathChecker = pathChecker;
            rowCoordinates = pathChecker.getEntranceAndExit();
            this.width = pathChecker.getWidth();
            this.userInput = pathChecker.getUserDefinedPath();
    }


    // Verfies if the userInput is a cannonical path
    public boolean isCannonical(){
        for (char c: userInput.toCharArray()){
            if (Character.isDigit(c)) return false; // If digit then its factorized
        }
        
        return true; // No digit found so cannonical
    }


    /**
     * Verifies whether a sequence of factorized instructions is valid for the given maze and player.
     * The factorized instructions are provided as a string in the form "3F2L5R" (this is an example), where the number
     * preceding each instruction indicates how many times the instruction should be executed.
     * 
     * @param startWest A boolean flag indicating whether the player starts on the west side of the maze.
     * @return True if the factorized instructions lead the player to the maze exit, false otherwise.
     */
    public boolean verifyFactorized(boolean startWest){

        // Initialize the player based on the starting side of the maze
        Player player = initializePlayer(startWest);

        int i = 0;         

        while (i < userInput.length())
        {
            char instruction = userInput.charAt(i);
            if (Character.isDigit(instruction))
            {
                StringBuilder numberPortion = new StringBuilder();

                // Extract all digits before an instruction
                while(i < userInput.length() && Character.isDigit(userInput.charAt(i)))
                {
                    // Add the instruction we now have
                    numberPortion.append(instruction);
                    i++; 
                    instruction = userInput.charAt(i); 
                }

                int instructionTimes = Integer.parseInt(numberPortion.toString());  // number of times to execute the instruction

                // Check out of bounds in-case after incrementation we are moving past the last letter
                if (i < userInput.length()){
                    instruction = userInput.charAt(i);
                }
                
                // Reduces redundant 360-degree turnarounds: multiples of 4 turns are equivalent to staying in the same spot
                if (instruction != 'F' && instructionTimes % 4 == 0){
                    instructionTimes = (instructionTimes % 4); 
                }
                // Optimizes turn repetitions: 5 turns = 1 turn, 6 turns = 2 turns, 7 turns = 3 turns
                else if(instruction != 'F' && instruction % 4 != 0){
                    instructionTimes %= 4; 
                }
                
                // Execute instructions
                for (int j = 1; j <= instructionTimes; j++)
                {
                    // Hit wall so invalid path
                    if (!pathChecker.canFollowInstruction(instruction, player)) return false; 
                }
            }
            else{ 
                // Hit a wall so path invalid 
                if (!pathChecker.canFollowInstruction(instruction, player)) return false; 
            }
            i++;
        }

        // Verify if current coordinates match exit coordinates
        return  ((player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()));
    }


    /**
     * Initializes a player's starting point based on the specified side of the maze.
     * If starting from the west, the player is placed on the west side facing east.
     * If starting from the east, the player is placed on the east side facing west.
     * 
     * @param startWest A boolean flag indicating whether the player starts on the west side of the maze.
     * @return A new Player object representing the initialized player.
     */
    private Player initializePlayer(boolean startWest) {
        if (startWest) {
            return new Player(rowCoordinates.get(0), 0, rowCoordinates.get(1), this.width - 1, 'E');
        } 
        
        return new Player(rowCoordinates.get(1), this.width - 1, rowCoordinates.get(0), 0, 'W'); 
    }

    
    /**
     * Verifies whether a sequence of canonical instructions is valid for the given maze and player.
     * Each character in the user input represents a single move or action ('F', 'L', 'R', 'B').
     * 
     * @param startWest A boolean flag indicating whether the player starts on the west side of the maze.
     * @return True if the canonical instructions lead the player to the maze exit, false otherwise.
     */
    public boolean verifyCannonical(boolean startWest)
    {

        Player player = initializePlayer(startWest);

        for (char c: userInput.toCharArray()){
            // Player cannot make a valid move then the path is not valid so return false
            if (!pathChecker.canFollowInstruction(c, player)) return false; 
        }
            
        // Verify if current coordinates match exit coordinates
        return ( (player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()) );
    }
}
