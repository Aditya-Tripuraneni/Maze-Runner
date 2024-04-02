package ca.mcmaster.se2aa4.mazerunner.Mazes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ca.mcmaster.se2aa4.mazerunner.Paths.Path;

public class MazeExporter{    
    private StringBuilder filePath; 

    public MazeExporter(Path fp){
        System.out.println(fp.getPath());
        this.filePath = fp.getPath(); 
    }


    public Tile [][] constructMaze() throws FileNotFoundException, IOException
    {

        int width = this.getWidth(); 
        int height = this.getHeight();  

        Tile [][] maze = new Tile [height][width];

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath.toString()));
        String line;
        
        int row = 0; 
        
        // Constructs the maze based on wall or space
        while ((line = reader.readLine()) != null) 
        {

            // Pad the line with spaces if it's shorter than the width
            if (line.length() < width) {
                line = String.format("%-" + width + "s", line);
            }

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
            case '\0':
                return Tile.PASS; 
            default:
                throw new IllegalArgumentException("Invalid character " + c);
        }
    }
    
    
    private int getHeight() throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath.toString()));
        while ((reader.readLine()) != null) 
        {
            count++;
        }
        reader.close();
        return count; // height of maze
    }


    private  int getWidth() throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath.toString()));
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