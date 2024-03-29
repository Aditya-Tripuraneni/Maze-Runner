package ca.mcmaster.se2aa4.mazerunner.Entity;

// Interface to implement different types of entities to explore the maze
interface PlayerExplorer{
    void turnRight();
    void turnLeft();
    void turnBackwards(); 
    void moveForward();
}