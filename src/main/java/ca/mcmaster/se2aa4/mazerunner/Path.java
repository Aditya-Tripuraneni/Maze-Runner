package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private StringBuilder path = new StringBuilder(); // path to exit maze

    public void addInstruction(String instruction){
        path.append(instruction);
    }

    public int getPathLength(){
        return this.path.length();
    }


    public char instructionAt(int index){
        return path.charAt(index);
    }


    public char getLastInstruction(){
        return this.path.charAt(this.path.length() - 1);
    }
    
}
