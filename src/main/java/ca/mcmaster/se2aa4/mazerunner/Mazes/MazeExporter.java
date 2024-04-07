package ca.mcmaster.se2aa4.mazerunner.Mazes;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MazeExporter {
    Maze constructMaze() throws FileNotFoundException, IOException;
    
}
