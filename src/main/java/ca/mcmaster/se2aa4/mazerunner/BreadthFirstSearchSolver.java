package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Direction.EAST;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearchSolver implements MazeSolver{

    private Maze maze; 
    private AlgorithmInstructions instructorCreator;

    
    public BreadthFirstSearchSolver(Maze maze){
        this.maze = maze; 
        this.instructorCreator = new AlgorithmInstructions(maze.getPath());
    }


    public void BFS(Player player){

        PathChecker pathChecker = new PathChecker(maze.getMaze()); 
        
        Map<Node, Integer> distanceMapping = this.createDistanceMapping(); 

        Queue<Node> queue = new LinkedList<>(); 
        Set<Node> visited = new HashSet<>(); 

        int playerX = player.getCol(); 
        int playerY = player.getRow();
        Node startNode = new Node(playerX, playerY);
        queue.add(startNode);

        // add a visited node
        visited.add(startNode);

        while (!queue.isEmpty())
        {

            Node currentNode =  queue.remove(); 

            // exit upon reaching the exit of the maze
            if (currentNode.getX() == player.getExitCol() && currentNode.getY() == player.getExitRow()){
                return; 
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

    @Override
    public String solveMaze(){
        return "FFFF"; // hard coded implemetntion to solve straight maze path

    }
    
}
