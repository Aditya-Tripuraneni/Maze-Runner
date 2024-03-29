package ca.mcmaster.se2aa4.mazerunner;


import static ca.mcmaster.se2aa4.mazerunner.Direction.*;

import java.util.List;


public class MazeInputReader implements InputVerifier, CannonicalVerifier, FactorizedVerifier{
    private PathChecker pathChecker; 
    private List<Integer> rowCoordinates;
    private int width; 
    private String userInput; 
    

    public  MazeInputReader(Maze maze, String userInput){
            this.pathChecker = new PathChecker(maze);
            this.setUserDefinedPath(userInput);
            rowCoordinates = pathChecker.getEntranceAndExit();
            this.width = pathChecker.getWidth();
    }


    // Verfies if the userInput is a cannonical path
    @Override
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
    @Override
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
                
                Direction convertedDirection = this.convertDirection(instruction);
                // Reduces redundant 360-degree turnarounds: multiples of 4 turns are equivalent to staying in the same spot
                if (convertedDirection != FORWARD && instructionTimes % 4 == 0){
                    instructionTimes = (instructionTimes % 4); 
                }
                // Optimizes turn repetitions: 5 turns = 1 turn, 6 turns = 2 turns, 7 turns = 3 turns
                else if(convertedDirection != FORWARD && instruction % 4 != 0){
                    instructionTimes %= 4; 
                }
                
                // Execute instructions
                for (int j = 1; j <= instructionTimes; j++)
                {
                    // Hit wall so invalid path
                    Direction playerInstruction = this.convertDirection(instruction);
                    if (!pathChecker.canFollowInstruction(playerInstruction, player)) return false; 
                }
            }
            else{ 
                // Hit a wall so path invalid 
                Direction playerInstruction = this.convertDirection(instruction);
                if (!pathChecker.canFollowInstruction(playerInstruction, player)) return false; 
            }
            i++;
        }

        // Verify if current coordinates match exit coordinates
        return  ((player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()));
    }


    /**
     * Verifies whether a sequence of canonical instructions is valid for the given maze and player.
     * Each character in the user input represents a single move or action ('F', 'L', 'R', 'B').
     * 
     * @param startWest A boolean flag indicating whether the player starts on the west side of the maze.
     * @return True if the canonical instructions lead the player to the maze exit, false otherwise.
     */
    @Override
    public boolean verifyCannonical(boolean startWest)
    {

        Player player = initializePlayer(startWest);

        for (char c: userInput.toCharArray()){
            // Player cannot make a valid move then the path is not valid so return false
            Direction instructionDirection = this.convertDirection(c);
            if (!pathChecker.canFollowInstruction(instructionDirection, player)) return false; 
        }
            
        // Verify if current coordinates match exit coordinates
        return ( (player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()) );
    }


    @Override
    public void verifyPath()
    {

        if (this.isCannonical())
        {
            // verify west to east path and east to west path
            if (this.verifyCannonical(true) || this.verifyCannonical(false)){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
        else{
            // verify west to east path and east to west path
            if (this.verifyFactorized( true) || this.verifyFactorized(false)){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
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
            return new Player(rowCoordinates.get(0), 0, rowCoordinates.get(1), this.width - 1, EAST);
        } 
        
        return new Player(rowCoordinates.get(1), this.width - 1, rowCoordinates.get(0), 0, WEST); 
    }


    private void setUserDefinedPath(String userPath){
        userPath = userPath.replaceAll("\\s", ""); // Remove any spaces
        this.userInput = userPath; 
    }

    
    private Direction convertDirection(char c){
        switch (c) {
            case 'F':
                return FORWARD;
            case 'L':
                return LEFT; 
            case 'R':
                return RIGHT; 
            default:
                return null;
        }
    }
}
