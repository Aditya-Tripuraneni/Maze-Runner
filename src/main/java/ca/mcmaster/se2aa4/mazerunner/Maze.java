package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Maze {
    private Tile [][] maze; 
    private PathChecker pathChecker;
    private List<Integer> rowCoordinates;
    private StringBuilder path = new StringBuilder(); // path to exit maze
    private String userDefinedPath; // path user enters to verify path 

    
    public Maze(String filepath) throws FileNotFoundException, IOException
    {
        MazeExporter mazeExporter = new MazeExporter(filepath); 
        this.maze = mazeExporter.constructMaze();
        pathChecker = new PathChecker(maze);
        rowCoordinates = pathChecker.getEntranceAndExit();
        
    }


    public int getMazeWidth(){
        return maze[0].length;
    }


    public StringBuilder getPath(){
        return this.path; 
    }


    public List<Integer> getRowCoordinates(){
        return this.rowCoordinates; 
    }


    public Tile [][] getMaze(){
        return this.maze;
    }


    public PathChecker getPathChecker(){
        return this.pathChecker;
    }


    public void setUserDefinedPath(String userPath){
        userPath = userPath.replaceAll("\\s", ""); // Remove any spaces

        pathChecker.setUserDefinedPath(userPath);
        
        this.userDefinedPath = userPath; 
    }


    public String getUserDefinedPath(){
        return this.userDefinedPath;
    }


    // Apply right hand exploration algorithm to solve maze
    public void rightHandExplore()
    {
        MazeSolver rightHandExplorationSolver = new RightHandExploration(this);
        String path = rightHandExplorationSolver.solveMaze(); 
        System.out.println(path);


    }

    public void BFSExplore(){
        MazeSolver breadthFirstSearchSolver = new BreadthFirstSearchSolver(this);
        String path2 =  breadthFirstSearchSolver.solveMaze();
        System.out.println(path2);
    }




    /**
     * Verifies the user's entered path by utilizing MazeInputReader and path-checking methods.
     * The method checks whether the entered path is canonical or factorized and verifies
     * both west-to-east and east-to-west paths accordingly.
     * Prints the result (correct or incorrect) to the console.
     */
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
