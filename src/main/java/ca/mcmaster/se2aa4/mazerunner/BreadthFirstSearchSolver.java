package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Direction.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearchSolver implements MazeSolver{

    private Maze maze; 
    private AlgorithmInstructions instructorCreator;

    private Queue<Node> queue = new LinkedList<>(); 
    private List<Integer> rowCoordinates; 
    private Map<Node, Integer> distanceMapping;
    private Map<Node, Node> parentMapping;
    


    public BreadthFirstSearchSolver(Maze maze){
        this.maze = maze; 
        this.rowCoordinates = maze.getRowCoordinates();
        this.distanceMapping = this.createDistanceMapping();
        this.parentMapping = this.createParentMapping();
        this.instructorCreator = new AlgorithmInstructions(maze.getPath());
    }


    private void BFS(){        
        // Entrance Coordinate
        int startRow = this.rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0
        Node startNode = new Node(startRow, startCol);

        // start node has distance 0 to itself
        this.distanceMapping.put(startNode, 0);

        // Exit coordinate
        int exitRow = rowCoordinates.get(1);  // Exit coordinate
        int exitCol = maze.getMazeWidth() -1; 
        Node exitNode = new Node(exitRow, exitCol);

        this.queue.add(startNode);

        while (!queue.isEmpty())
        {

            Node currentNode =  this.queue.remove(); 

            // exit upon reaching the exit of the maze
            if (currentNode.getX() == exitNode.getX() && currentNode.getY() == exitNode.getY()){
                return; 
            }

            Map<Direction, Boolean> neighbours = maze.getNeighbouringTiles(currentNode.getX(), currentNode.getY());

            if (neighbours.get(SOUTH)){
                this.addNeighbour(currentNode, new Node(currentNode.getX() + 1, currentNode.getY()));
            }

            if (neighbours.get(NORTH)){
                this.addNeighbour(currentNode,  new Node(currentNode.getX() -1, currentNode.getY()));
            }

            if (neighbours.get(EAST)){
                this.addNeighbour(currentNode, new Node(currentNode.getX(), currentNode.getY() + 1));
            }

            if (neighbours.get(WEST)){
                this.addNeighbour(currentNode, new Node(currentNode.getX(), currentNode.getY() -1));
            }
        }
    }


    private void addNeighbour(Node currentNode, Node neighbourNode){
        int currentDistance = this.distanceMapping.get(currentNode);

        if (currentDistance + 1 < this.distanceMapping.get(neighbourNode)){
            this.distanceMapping.put(neighbourNode, currentDistance + 1);
            this.parentMapping.put(neighbourNode, currentNode);
            this.queue.add(neighbourNode);
        }
    }


    private Map<Node, Integer> createDistanceMapping(){
        Tile[][] mazeMatrix = this.maze.getMaze();
        Map<Node, Integer> mapping = new HashMap<>(); 
        // for (Tile[] row: mazeMatrix){
        //     System.out.println(Arrays.toString(row));
        // }
        

        for(int row = 0; row < mazeMatrix.length; row ++){
            for (int col = 0; col < mazeMatrix[row].length; col ++){
                Node node = new Node(row, col); 
                mapping.put(node, Integer.MAX_VALUE);
            }
        }

        return mapping; 
    }


    private Map<Node, Node> createParentMapping(){
        Tile[][] mazeMatrix = this.maze.getMaze();
        Map<Node, Node> mapping = new HashMap<>(); 

        for(int row = 0; row < mazeMatrix.length; row ++){
            for (int col =0; col < mazeMatrix[row].length; col ++){
                Node node = new Node(row, col); 
                mapping.put(node, node);
            }
        }
        return mapping; 
    }


    private List<Node> backTrackPath(){
        Deque<Node> deque = new LinkedList<>();

        int startRow = this.rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0
        Node startNode = new Node(startRow, startCol);

        int exitRow = this.rowCoordinates.get(1);  // Exit coordinate
        int exitCol = this.maze.getMazeWidth() - 1; 
        Node exitNode = new Node(exitRow, exitCol);

        Node accessor = exitNode; 

        deque.addFirst(exitNode);
        while (!parentMapping.get(accessor).equals(startNode)) {
            accessor = parentMapping.get(accessor);
            deque.addFirst(accessor);
        }

        List<Node> pathSequence = new ArrayList<>(deque);

        return pathSequence; 
    }


    private void constructPath(){
        
        Direction oldOrientation = EAST; 
        List<Node> pathSequence = this.backTrackPath();

        Node prevNode = pathSequence.get(0);
        instructorCreator.instructForward();
        Compass compass = new Compass();
        System.out.println("Pre path before doing jack shit: " + maze.getPath());

        for (int i = 1; i < pathSequence.size(); i ++)
        {
            Node currentNode = pathSequence.get(i);
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



            System.out.println("Old Node: " + prevNode.toString());
            System.out.println("New Node: " + currentNode.toString());
            System.out.println("Result of dx, dy: " + dx + ", " + dy);
            System.out.println("Orientation: " + oldOrientation);
            
            prevNode = currentNode; 
        }
    }


    private void handleInstruction(Direction relativeDirection){
        if (relativeDirection == LEFT){
            System.out.println("Instruct Left");
            instructorCreator.instructLeft();
            instructorCreator.instructForward();
        }
        else if (relativeDirection == RIGHT){
            System.out.println("Instruct Right");
            instructorCreator.instructRight();
            instructorCreator.instructForward();

        }
        else if (relativeDirection == FORWARD){
            System.out.println("I moved forward!");
            instructorCreator.instructForward();
        }
    }


    @Override
    public String solveMaze(){
        this.BFS();
        this.constructPath();
        StringBuilder path = maze.getPath();
        return instructorCreator.factoredExpressionPath(path.toString()); 
    }
    
}
