package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class BreadthFirstSearchSolver implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();

    private Maze maze; 
    private AlgorithmInstructions instructorCreator;

    private Queue<Node> queue = new LinkedList<>(); 


    private Map<Node, Integer> distanceMapping;
    private Map<Node, Node> parentMapping;
    public BreadthFirstSearchSolver(Maze maze){
        this.maze = maze; 
        this.distanceMapping = this.createDistanceMapping();
        this.parentMapping = this.createParentMapping();
        this.instructorCreator = new AlgorithmInstructions(maze.getPath());
    }


    private void BFS(){
        logger.info("hello is this reached");
        Tile[][] mazeMatrix = maze.getMaze(); 

        List<Integer> rowCoordinates = maze.getRowCoordinates(); 

        // Entrance Coordinate
        int startRow = rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0
        Node startNode = new Node(startRow, startCol);

        // start node has distance 0 to itself
        distanceMapping.put(startNode, 0);

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

            // check if can add South tile
            if (currentNode.getX() + 1 < mazeMatrix.length){
                boolean isValidSouthTile = mazeMatrix[currentNode.getX() + 1][currentNode.getY()] != Tile.WALL;                
                if (isValidSouthTile){
                    this.addNeighbour(currentNode, new Node(currentNode.getX() + 1, currentNode.getY()));
                }
            }

            // check if can add North tile
            if (currentNode.getX() - 1 >= 0){
                boolean isValidNorthTile = mazeMatrix[currentNode.getX() - 1][currentNode.getY()] != Tile.WALL;
                if (isValidNorthTile){
                    this.addNeighbour(currentNode,  new Node(currentNode.getX() -1, currentNode.getY()));
                }
            }

            // check if can add East tile
            if (currentNode.getY() + 1 < mazeMatrix[0].length){
                boolean isValidEastTile = mazeMatrix[currentNode.getX()][currentNode.getY() + 1] != Tile.WALL;
                if (isValidEastTile){
                    this.addNeighbour(currentNode, new Node(currentNode.getX(), currentNode.getY() + 1));
                }
            }

            // check if can add West tile
            if (currentNode.getY() - 1 >= 0){
                boolean isValidWestTile = mazeMatrix[currentNode.getX()][currentNode.getY() -1] != Tile.WALL;
                if (isValidWestTile){
                    this.addNeighbour(currentNode, new Node(currentNode.getX(), currentNode.getY() -1));
                }
            }
        }
    }


    private void addNeighbour(Node currentNode, Node neighbourNode){
        int currentDistance = distanceMapping.get(currentNode);

        //only enqueue the node if has shorter distance and not a wall tile
        // System.out.println("Current Node: " + currentNode.toString());
        // System.out.println("Neighbour Node: " + neighbourNode.toString());

        if (currentDistance + 1 < distanceMapping.get(neighbourNode)){
            distanceMapping.put(neighbourNode, currentDistance + 1);
            parentMapping.put(neighbourNode, currentNode);
            this.queue.add(neighbourNode);
        }
    }


    private Map<Node, Integer> createDistanceMapping(){
        Tile[][] mazeMatrix = this.maze.getMaze();
        Map<Node, Integer> mapping = new HashMap<>(); 
        for (Tile[] row: mazeMatrix){
            System.out.println(Arrays.toString(row));
        }
        

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


    @Override
    public String solveMaze(){
        List<Integer> rowCoordinates = maze.getRowCoordinates(); 

        int startRow = rowCoordinates.get(0); // Entrance coordinate 
        int startCol = 0;  // Starting on west side means col = 0

        int exitRow = rowCoordinates.get(1);  // Exit coordinate
        int exitCol = maze.getMazeWidth() -1; 

        Node startNode = new Node(startRow, startCol);
        logger.info("WAS THIS EVER REACHED");

        Node exitNode = new Node(exitRow, exitCol);

        this.BFS();

        System.out.println("\n\nDistance to EXIT node: " + this.distanceMapping.get(exitNode) );


        return "FFFF"; // hard coded implemetntion to solve straight maze path

    }
    
}
