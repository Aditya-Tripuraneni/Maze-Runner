package ca.mcmaster.se2aa4.mazerunner.Entity;

import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

// Interface to implement different types of entities to explore the maze
public interface PlayerExplorer{
    void turnRight();
    void turnLeft();
    void turnBackwards(); 
    void moveForward();
    Location getExitLocation();
    Location getCurrentLocation();
    Direction getOrientation();
}