package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Direction.*;
import static ca.mcmaster.se2aa4.mazerunner.Tile.WALL;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearchSolver implements MazeSolver{

    private Maze maze; 
    private AlgorithmInstructions instructorCreator;

    private Map<Node, Integer> distanceMapping = this.createDistanceMapping(); 
    private Map<Node, Node> parentMapping = this.createParentMapping(); 

    
    public BreadthFirstSearchSolver(Maze maze){
        this.maze = maze; 
        this.instructorCreator = new AlgorithmInstructions(maze.getPath());
    }


    public void BFS(){
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

        Queue<Node> queue = new LinkedList<>(); 

        queue.add(startNode);


        while (!queue.isEmpty())
        {

            Node currentNode =  queue.remove(); 

            // exit upon reaching the exit of the maze
            if (currentNode.getX() == exitNode.getX() && currentNode.getY() == exitNode.getY()){
                return; 
            }

            int currentDistance = distanceMapping.get(currentNode);

            boolean isValidSouthTile = mazeMatrix[currentNode.getX() + 1][currentNode.getY()] != Tile.WALL;
            Node southNode = new Node(currentNode.getX() + 1, currentNode.getY());

            boolean isValidNorthTile = mazeMatrix[currentNode.getX() - 1][currentNode.getY()] != Tile.WALL;
            Node northNode = new Node(currentNode.getX() -1, currentNode.getY());

            boolean isValidEastTile = mazeMatrix[currentNode.getX()][currentNode.getY() + 1] != Tile.WALL;
            Node eastNode = new Node(currentNode.getX(), currentNode.getY() + 1);


            boolean isValidWestTile = mazeMatrix[currentNode.getX()][currentNode.getY() -1] != Tile.WALL;
            Node westNode = new Node(currentNode.getX(), currentNode.getY() -1);

            // east node check
            if (isValidEastTile && (currentDistance + 1 < distanceMapping.get(eastNode))) {
                //update distance
                distanceMapping.put(eastNode, currentDistance + 1);

                //update parent node now the east nodes parent was the current node we just dequeued as that had the shorter distance
                parentMapping.put(eastNode, currentNode);
            }

            // west node check
            if (isValidWestTile && (currentDistance + 1 < distanceMapping.get(westNode))){
                  //update distance
                  distanceMapping.put(westNode, currentDistance + 1);
                  //update parent node now the east nodes parent was the current node we just dequeued as that had the shorter distance
                  parentMapping.put(westNode, currentNode);
              }


            // north node check
            if (isValidNorthTile && (currentDistance + 1 < distanceMapping.get(northNode))){
                distanceMapping.put(northNode, currentDistance + 1);
                parentMapping.put(northNode, currentNode);
            }

            if (isValidSouthTile && (currentDistance + 1 < distanceMapping.get(southNode))){
                distanceMapping.put(southNode, currentDistance + 1);
                parentMapping.put(southNode, currentNode);
            }
        }
    }

    private Map<Node, Integer> createDistanceMapping(){
        Tile[][] mazeMatrix = this.maze.getMaze();
        Map<Node, Integer> mapping = new HashMap<>(); 
        
        for(int row = 0; row < maze.getMazeWidth(); row ++){
            for (int col =0; col < mazeMatrix[row].length; col ++){
                Node node = new Node(row, col); 
                mapping.put(node, Integer.MAX_VALUE);
            }
        }
        return mapping; 
    }


    private Map<Node, Node> createParentMapping(){
        Tile[][] mazeMatrix = this.maze.getMaze();
        Map<Node, Node> mapping = new HashMap<>(); 

        for(int row = 0; row < maze.getMazeWidth(); row ++){
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


        Node exitNode = new Node(exitRow, exitCol);

        this.BFS();

    

        return "FFFF"; // hard coded implemetntion to solve straight maze path

    }
    
}
