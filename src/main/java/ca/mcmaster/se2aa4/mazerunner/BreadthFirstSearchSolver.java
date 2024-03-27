package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashSet;
import java.util.LinkedList;
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
        Queue<Node> queue = new LinkedList<>(); 
        Set<Node> visited = new HashSet<>(); 

        int playerX = player.getCol(); 
        int playerY = player.getRow();
        Node startNode = new Node(playerX, playerY);
        queue.add(startNode);

        visited.add(startNode);

        while (!queue.isEmpty()){
            //BFS LOGIC INSIDE OF HERE
        }


    }

    @Override
    public String solveMaze(){
        return "FFFF"; // hard coded implemetntion to solve straight maze path

    }
    
}
