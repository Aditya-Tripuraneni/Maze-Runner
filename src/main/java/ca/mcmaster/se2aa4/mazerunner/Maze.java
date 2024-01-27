package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {
    private char [][] maze; 
    private PathChecker pathChecker;
    private  ArrayList<Integer> rowCoordinates;
    private StringBuilder path = new StringBuilder(); // path to exit maze
    private String userDefinedPath; // path user enters to verify path 

    
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
        return this.path; 
    }


    public ArrayList<Integer> getRowCoordinates(){
        return this.rowCoordinates; 
    }


    public char[][] getMaze(){
        return this.maze;
    }


    public PathChecker getPathChecker(){
        return this.pathChecker;
    }


    public void setUserDefinedPath(String userPath){
        userPath = userPath.replaceAll("\\s", ""); // remove any spaces

        pathChecker.setUserDefinedPath(userPath);
        
        this.userDefinedPath = userPath; 
    }


    public String getUserDefinedPath(){
        return this.userDefinedPath;
    }


    // apply right hand exploration algorithm to solve maze
    public void rightHandExplore()
    {
        String path = RightHandExploration.solveMaze(this); 
        System.out.println(path);
    }

    
    // verifies the users entered path
    public void verifyPath()
    {
    
        MazeInputReader mazeInputReader = new MazeInputReader(pathChecker); // used to verify the user's path

        if (mazeInputReader.isCannonical())
        {
            // verify west to east path and east to west path
            if (mazeInputReader.verifyCannonical(true) || mazeInputReader.verifyCannonical(false)){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
        else{
            // verify west to east path and east to west path
            if (mazeInputReader.verifyFactorized( true) || mazeInputReader.verifyFactorized(false)){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
    }
}
