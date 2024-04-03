 package ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms;

import ca.mcmaster.se2aa4.mazerunner.Paths.Path;

public class AlgorithmInstructions {
    // private StringBuilder string; 
    private Path path; 

    // public AlgorithmInstructions(StringBuilder string){
    //     this.string = string; 
    // }

    public AlgorithmInstructions(Path path){
        this.path = path; 
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
    public void instructForward(){
        if (path.getPathLength() > 0 && (path.getLastInstruction() == 'L' || path.getLastInstruction() == 'R')){
            path.addInstruction(" F"); 
        }
        else{
            path.addInstruction("F");
        }
    }


    public void instructRight(){
        if (path.getPathLength() > 0 && (path.getLastInstruction() == 'L' || path.getLastInstruction() == 'F')){
            path.addInstruction(" R"); 
        }
        else{
            path.addInstruction("R");
        }
    }


    public void instructLeft(){
        if (path.getPathLength() > 0 && (path.getLastInstruction() == 'F' || path.getLastInstruction() == 'R')) { 
            path.addInstruction(" L");
        } 
        else{
            path.addInstruction("L");
        }
    }


    public void instructBackwards(){
        if (path.getPathLength() > 0 && (path.getLastInstruction() == 'F' || path.getLastInstruction() == 'L')){
            path.addInstruction(" RR"); 
        }
        else{
            path.addInstruction("RR");
        }
    }

    
    public String factorizeInstructions(){
        StringBuilder factoredExpression = new StringBuilder();

        char currLetter = path.instructionAt(0);
        int count = 0; 

        for (int i = 0; i < path.getPathLength(); i ++)
        {
            if (path.instructionAt(i) == currLetter){
                count++;
            }
            else if(path.instructionAt(i) != ' ')
            {
                if (count > 1){
                    factoredExpression.append(count).append(currLetter).append(" ");
                }
                else{
                    factoredExpression.append(currLetter).append(" ");
                }
                currLetter = path.instructionAt(i);
                count = 1;

            }
        }

        if (count > 1){
            factoredExpression.append(count).append(currLetter);
        }
        else {
            factoredExpression.append(currLetter);
        }


        return factoredExpression.toString();
    }
    
}
