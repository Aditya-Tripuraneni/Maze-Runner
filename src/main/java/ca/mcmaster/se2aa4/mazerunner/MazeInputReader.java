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


    public boolean isCannonical(){
        for (char c: userInput.toCharArray()){
            if (Character.isDigit(c)) return false; // if digit then its factorized
        }
        
        return true; // no digit found so cannonical
    }


    public boolean verifyFactorized(boolean startWest){

        Player player = initializePlayer(startWest);

        int i = 0;         

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
                    i++; 
                    instruction = userInput.charAt(i); 
                }

                int instructionTimes = Integer.parseInt(numberPortion.toString());  // number of times to execute the instruction

                // need to check out of bounds in-case after incrementation we are moving past the last letter
                if (i < userInput.length()){
                    instruction = userInput.charAt(i);
                }
                
                // execute instructions
                for (int j = 1; j <= instructionTimes; j++)
                {
                    // hit wall so invalid path
                    if (!pathChecker.canFollowInstruction(instruction, player)) return false; 
                }
            }
            else{ 
                // hit a wall so path invalid 
                if (!pathChecker.canFollowInstruction(instruction, player)) return false; 
            }
            i++;
        }

        // verify if current coordinates match exit coordinates
        return  ((player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()));

    }


    private Player initializePlayer(boolean startWest) {

        // initalize a player's starting point based on starting west or east
        if (startWest) {
            return new Player(rowCoordinates.get(0), 0, rowCoordinates.get(1), this.width - 1, 'E');
        } 
        
        return new Player(rowCoordinates.get(1), this.width - 1, rowCoordinates.get(0), 0, 'W');
        
    }

    
    public boolean verifyCannonical(boolean startWest)
    {

            Player player = initializePlayer(startWest);

            for (char c: userInput.toCharArray()){
                // if the player cannot make a valid move then the path is not valid so return false
                if (!pathChecker.canFollowInstruction(c, player)) 
                    return false; 
            }
            
            // verify if current coordinates match exit coordinates
            return ( (player.getRow() == player.getExitRow()) && (player.getCol() == player.getExitCol()) );
    }
    
}
