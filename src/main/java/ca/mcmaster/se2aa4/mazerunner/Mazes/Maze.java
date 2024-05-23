package ca.mcmaster.se2aa4.mazerunner.Mazes;

import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

public interface Maze {
    int getMazeWidth();
    int getMazeHeight();
    Map<Direction, Boolean> getNeighbouringTiles(int row, int col);
    boolean isPassTile(int row, int col);
    List<Integer> getRowCoordinates();
}
