package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private StringBuilder path; // path to exit maze

    public Path(){
        path = new StringBuilder();
    }


    public char[] toCharArray(){
        String p = path.toString();
        return p.toCharArray(); 

    }
    

    public Path(String path){
        this.path = new StringBuilder(path);
    }


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
