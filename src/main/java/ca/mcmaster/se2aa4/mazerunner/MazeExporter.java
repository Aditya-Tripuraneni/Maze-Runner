package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MazeExporter{    
    private String filePath; 

    public MazeExporter(String filePath){
        this.filePath = filePath; 
    }



    public Tile [][] constructMaze() throws FileNotFoundException, IOException
    {

        int width = this.getWidth(); 
        int height = this.getHeight();  

        Tile [][] maze = new Tile [height][width];

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line;
        
        int row = 0; 
        
        // Constructs the maze based on wall or space
        while ((line = reader.readLine()) != null) 
        {
            for (int col = 0; col < line.length(); col++) 
            {
                maze[row][col] = this.convertTilechar(line.charAt(col));
            }
            row ++; 
        }

        reader.close();

        return maze;
    }


    private Tile convertTilechar(char c){
        switch (c){
            case '#': 
                return Tile.WALL; 
            case ' ': 
                return Tile.PASS; 
            default:
                throw new IllegalArgumentException("Invalid character " + c);
        }

    }
    
    
    private int getHeight() throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line;
        while ((line = reader.readLine()) != null) 
        {
            count++;
        }
        reader.close();
        return count; // height of maze
    }


    private  int getWidth() throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        String line;
        line = reader.readLine(); 

        for (int idx = 0; idx < line.length(); idx++) 
        {
            count++; 
        } 

        reader.close();
        return count; // width of maze

    }
}