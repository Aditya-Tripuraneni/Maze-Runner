package ca.mcmaster.se2aa4.mazerunner.Verifiers;

import ca.mcmaster.se2aa4.mazerunner.Paths.*;
import ca.mcmaster.se2aa4.mazerunner.Entity.Location;
import ca.mcmaster.se2aa4.mazerunner.Entity.Player;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;


import java.util.List;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;


public class MazeInputReader implements InputVerifier, CannonicalVerifier, FactorizedVerifier{
    private PathChecker pathChecker; 
    private List<Integer> rowCoordinates;
    private int width; 
    private Path userInput;
    

    /**
     * Constructs a MazeInputReader object with the provided maze and user input.
     * Initializes a PathChecker object to perform path-related operations on the maze.
     * Sets the user-defined path and extracts entrance and exit coordinates.
     * 
     * @param maze The maze object to be read.
     * @param userInput The user-defined path input.
     */
    public MazeInputReader(Maze maze, String userInput) {
            this.pathChecker = new PathChecker(maze);
            this.setUserDefinedPath(userInput);
            rowCoordinates = pathChecker.getEntranceAndExit();
            this.width = pathChecker.getWidth();
    }


    // Verfies if the userInput is a cannonical path
    @Override
    public boolean isCannonical() {
        for (char c: userInput.toCharArray()) {
            if (Character.isDigit(c)) {return false;} // If digit then its factorized
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
    public boolean verifyFactorized(boolean startWest) {

        // Initialize the player based on the starting side of the maze
        Player player = initializePlayer(startWest);

        int i = 0;         

        while (i < userInput.getPathLength())
        {
            char instruction = userInput.instructionAt(i);
            if (Character.isDigit(instruction))
            {
                StringBuilder numberPortion = new StringBuilder();

                // Extract all digits before an instruction
                while(i < userInput.getPathLength() && Character.isDigit(userInput.instructionAt(i)))
                {
                    // Add the instruction we now have
                    numberPortion.append(instruction);
                    i++; 
                    instruction = userInput.instructionAt(i); 
                }

                int instructionTimes = Integer.parseInt(numberPortion.toString());  // number of times to execute the instruction

                // Check out of bounds in-case after incrementation we are moving past the last letter
                if (i < userInput.getPathLength()){
                    instruction = userInput.instructionAt(i);
                }
                
                Direction convertedDirection = this.convertDirection(instruction);
                // Reduces redundant 360-degree turnarounds: multiples of 4 turns are equivalent to staying in the same spot
                if (convertedDirection != F && instructionTimes % 4 == 0){
                    instructionTimes = (instructionTimes % 4); 
                }
                // Optimizes turn repetitions: 5 turns = 1 turn, 6 turns = 2 turns, 7 turns = 3 turns
                else if(convertedDirection != F && instruction % 4 != 0){
                    instructionTimes %= 4; 
                }
                
                // Execute instructions
                for (int j = 1; j <= instructionTimes; j++)
                {
                    // Hit wall so invalid path
                    Direction playerInstruction = this.convertDirection(instruction);
                    if (!pathChecker.canFollowInstruction(playerInstruction, player)) {return false;}
                }
            }
            else{ 
                // Hit a wall so path invalid 
                Direction playerInstruction = this.convertDirection(instruction);
                if (!pathChecker.canFollowInstruction(playerInstruction, player)) {return false;}
            }
            i++;
        }

        // Verify if current coordinates match exit coordinates
        return  player.getCurrentLocation().getX() == player.getExitLocation().getX() && player.getCurrentLocation().getY() == player.getExitLocation().getY();
    }


    /**
     * Verifies whether a sequence of canonical instructions is valid for the given maze and player.
     * Each character in the user input represents a single move or action ('F', 'L', 'R', 'B').
     * 
     * @param startWest A boolean flag indicating whether the player starts on the west side of the maze.
     * @return True if the canonical instructions lead the player to the maze exit, false otherwise.
     */
    @Override
    public boolean verifyCannonical(boolean startWest) {

        Player player = initializePlayer(startWest);

        for (char c: userInput.toCharArray()){
            // Player cannot make a valid move then the path is not valid so return false
            Direction instructionDirection = this.convertDirection(c);
            if (!pathChecker.canFollowInstruction(instructionDirection, player)) {return false;}
        }
            
        // Verify if current coordinates match exit coordinates
        return  player.getCurrentLocation().getX() == player.getExitLocation().getX() && player.getCurrentLocation().getY() == player.getExitLocation().getY();
    }


    @Override
    public String verifyPath() {
        if (this.isCannonical())
        {
            // verify west to east path and east to west path
            if (this.verifyCannonical(true) || this.verifyCannonical(false)){
                System.out.println("correct path");
                return "correct path";
            }
            else{
                System.out.println("incorrect path");
                return "incorrect path";
            }
        }
        else{
            // verify west to east path and east to west path
            if (this.verifyFactorized( true) || this.verifyFactorized(false)){
                System.out.println("correct path");
                return "correct path";
            }
            else{
                System.out.println("incorrect path");
                return "incorrect path";
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
            Location startLocation = new Location(rowCoordinates.get(0), 0);
            Location endLocation = new Location(rowCoordinates.get(1), this.width - 1);
            return new Player(startLocation, endLocation, EAST);
        } 
        Location startLocation = new Location(rowCoordinates.get(1), this.width - 1);
        Location endLocation = new Location(rowCoordinates.get(0), 0);
        
        return new Player(startLocation, endLocation, WEST); 
    }


    private void setUserDefinedPath(String userPath) {
        userPath = userPath.replaceAll("\\s", ""); // Remove any spaces
        this.userInput = new Path(userPath); 
    }

    
    private Direction convertDirection(char c) {
        switch (c) {
            case 'F':
                return F;
            case 'L':
                return L; 
            case 'R':
                return R; 
            default:
                return null;
        }
    }

}
