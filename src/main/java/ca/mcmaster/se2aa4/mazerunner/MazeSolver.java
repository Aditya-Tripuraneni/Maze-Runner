package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolver{

    protected abstract String solveMazeEastToWest(Maze maze); 
    protected abstract String solveMazeWestToEast(Maze maze); 
}