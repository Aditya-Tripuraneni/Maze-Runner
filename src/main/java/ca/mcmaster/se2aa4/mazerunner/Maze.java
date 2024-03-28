package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Direction.*;
import static ca.mcmaster.se2aa4.mazerunner.Tile.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        pathChecker = new PathChecker(this);
        rowCoordinates = pathChecker.getEntranceAndExit();
        
    }


    public int getMazeWidth(){
        return maze[0].length;
    }

    public int getMazeHeight(){
        return this.maze.length;
    }


    public Map<Direction, Boolean> getNeighbouringTiles(int row, int col){
        Map<Direction, Boolean> neighbours = new HashMap<>();

        // check if north tile is not a wall and within bounds
        boolean northValidity = row - 1 >= 0 && this.maze[row-1][col] != WALL;
        neighbours.put(NORTH, northValidity);

        // check if east tile is not a wall and within bounds
        boolean eastValidity = col + 1 < this.getMazeWidth() && this.maze[row][col + 1] != WALL;
        neighbours.put(EAST, eastValidity);

        // check if south tile is not a wall and within bounds
        boolean southValidity = row + 1 < this.maze.length && this.maze[row + 1][col] != WALL; 
        neighbours.put(SOUTH, southValidity);

        // check if west tile is not a wall and within bounds
        boolean westValidity = col -1 >= 0 && this.maze[row][col -1] != WALL; 
        neighbours.put(WEST, westValidity);

        return neighbours; 
    }


    public boolean isPassTile(int row, int col){
        return this.maze[row][col] == PASS;
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
