package ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms;


import ca.mcmaster.se2aa4.mazerunner.Entity.Location;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;
import ca.mcmaster.se2aa4.mazerunner.Utils.Compass;
import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;



public class BreadthFirstSearchSolver implements MazeSolver{

    private Maze maze; 
    private AlgorithmInstructions instructionCreator;

    private Queue<Location> queue = new LinkedList<>(); 
    private List<Integer> rowCoordinates; 
    private Map<Location, Integer> distanceMapping = new HashMap<>();
    private Map<Location, Location> parentMapping = new HashMap<>();
    

    public BreadthFirstSearchSolver(Maze maze){
        this.maze = maze; 
        this.rowCoordinates = maze.getRowCoordinates();
        this.createMappings();
        this.instructionCreator = new AlgorithmInstructions(new Path()); 
    }
    

    /*
     * This algorithm was learned from: 2C03 - Algorithms by Sedgewick Robert, 4th edition
     * Credit also given to the 2C03 course from McMaster University Winter 2024 term. 
     */
    private void breathFirstSearch(){        
        // Entrance Coordinate
        int startRow = this.rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0
        Location startNode = new Location(startRow, startCol);

        // start node has distance 0 to itself
        this.distanceMapping.put(startNode, 0);

        // Exit coordinate
        int exitRow = rowCoordinates.get(1);  // Exit coordinate
        int exitCol = maze.getMazeWidth() -1; 
        Location exitNode = new Location(exitRow, exitCol);

        this.queue.add(startNode);

        while (!queue.isEmpty())
        {

            Location currentNode =  this.queue.remove(); 

            // exit upon reaching the exit of the maze
            if (currentNode.getX() == exitNode.getX() && currentNode.getY() == exitNode.getY()){
                return; 
            }

            Map<Direction, Boolean> neighbours = maze.getNeighbouringTiles(currentNode.getX(), currentNode.getY());

            if (neighbours.get(SOUTH)){
                this.addNeighbour(currentNode, new Location(currentNode.getX() + 1, currentNode.getY()));
            }

            if (neighbours.get(NORTH)){
                this.addNeighbour(currentNode,  new Location(currentNode.getX() -1, currentNode.getY()));
            }

            if (neighbours.get(EAST)){
                this.addNeighbour(currentNode, new Location(currentNode.getX(), currentNode.getY() + 1));
            }

            if (neighbours.get(WEST)){
                this.addNeighbour(currentNode, new Location(currentNode.getX(), currentNode.getY() -1));
            }
        }
    }


    private void addNeighbour(Location currentNode, Location neighbourNode){
        int currentDistance = this.distanceMapping.get(currentNode);

        if (currentDistance + 1 < this.distanceMapping.get(neighbourNode)){
            this.distanceMapping.put(neighbourNode, currentDistance + 1);
            this.parentMapping.put(neighbourNode, currentNode);
            this.queue.add(neighbourNode);
        }
    }


    private void createMappings(){        
        for(int row = 0; row < this.maze.getMazeHeight(); row ++){
            for (int col = 0; col < this.maze.getMazeWidth(); col ++){
                Location node = new Location(row, col); 
                this.distanceMapping.put(node, Integer.MAX_VALUE);
                this.parentMapping.put(node, node);
            }
        }
    }


    private List<Location> backTrackPath(){
        Deque<Location> deque = new LinkedList<>();

        int startRow = this.rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0
        Location startNode = new Location(startRow, startCol);

        int exitRow = this.rowCoordinates.get(1);  // Exit coordinate
        int exitCol = this.maze.getMazeWidth() - 1; 
        Location exitNode = new Location(exitRow, exitCol);

        Location accessor = exitNode; 

        deque.addFirst(exitNode);
        while (!parentMapping.get(accessor).equals(startNode)) {
            accessor = parentMapping.get(accessor);
            deque.addFirst(accessor);
        }

        return (List<Location>) new ArrayList<>(deque);
    }


    private void constructPath(){
        
        Direction oldOrientation = EAST; 
        List<Location> pathSequence = this.backTrackPath();

        Location prevNode = pathSequence.get(0);
        instructionCreator.instructForward();
        Compass compass = new Compass();
        // System.out.println("Pre path before doing jack shit: " + maze.getPath());

        for (int i = 1; i < pathSequence.size(); i ++)
        {
            Location currentNode = pathSequence.get(i);
            int dx = currentNode.getX() - prevNode.getX() ;
            int dy = currentNode.getY() - prevNode.getY();

            Direction relativeDirection;
            
            // negative result implies we moved north
            if (dx < 0){
                relativeDirection = compass.getRelativeDirection(oldOrientation, NORTH);  
                oldOrientation = NORTH; 
            }
            // positive result implies we moved south
            else if (dx > 0){
                relativeDirection = compass.getRelativeDirection(oldOrientation, SOUTH);
                oldOrientation = SOUTH; 
            }
            // negative result implies we moved west
            else if (dy < 0){
                relativeDirection = compass.getRelativeDirection(oldOrientation, WEST);
                oldOrientation = WEST; 
            }
            // positive result implies we moved east
            else{
                relativeDirection = compass.getRelativeDirection(oldOrientation, EAST);
                oldOrientation = EAST; 
            }

            this.handleInstruction(relativeDirection);
            prevNode = currentNode; 
        }
    }


    private void handleInstruction(Direction relativeDirection){
        if (relativeDirection == L){
            // System.out.println("Instruct Left");
            instructionCreator.instructLeft();
            instructionCreator.instructForward();
        }
        else if (relativeDirection == R){
            // System.out.println("Instruct Right");
            instructionCreator.instructRight();
            instructionCreator.instructForward();

        }
        else if (relativeDirection == F){
            // System.out.println("I moved forward!");
            instructionCreator.instructForward();
        }
    }


    @Override
    public Path solveMaze(){
        this.breathFirstSearch();
        this.constructPath();
        // StringBuilder path = maze.getPath();
        String factoredPath = instructionCreator.factorizeInstructions(); 

        return new Path(factoredPath); 
    }
}
