package ca.mcmaster.se2aa4.mazerunner.Mazes;

import ca.mcmaster.se2aa4.mazerunner.Paths.PathChecker;
import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;
import static ca.mcmaster.se2aa4.mazerunner.Mazes.Tile.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeMatrix implements Maze{
    private Tile [][] maze; 
    private PathChecker pathChecker;
    private List<Integer> rowCoordinates;


    public MazeMatrix(Tile[][] matrixRepresentation) throws FileNotFoundException, IOException
    {
        this.maze = matrixRepresentation; 
        pathChecker = new PathChecker(this);
        rowCoordinates = pathChecker.getEntranceAndExit();
    }


    @Override
    public int getMazeWidth(){
        return maze[0].length;
    }
    

    @Override
    public int getMazeHeight(){
        return this.maze.length;
    }


    @Override
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


    @Override
    public boolean isPassTile(int row, int col){
        return this.maze[row][col] == PASS;
    }

    
    @Override
    public List<Integer> getRowCoordinates(){
        return this.rowCoordinates; 
    }
}
