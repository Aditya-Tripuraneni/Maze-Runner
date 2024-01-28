package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MazeExporter{    

    public static char[][] constructMaze(String filepath) throws FileNotFoundException, IOException
    {

        int width = getWidth(filepath); 
        int height = getHeight(filepath);  

        char[][] maze = new char[height][width];

        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        
        int row = 0; 
        
        // Constructs the maze based on wall or space
        while ((line = reader.readLine()) != null) 
        {
            for (int col = 0; col < line.length(); col++) 
            {
               if (line.charAt(col) == '#'){
                maze[row][col] = '#'; // wall
               }
               else{
                maze[row][col] = ' '; // pass
               }
            }
            row ++; 
        }

        reader.close();

        return maze;
    }
    
    
    private static int getHeight(String filePath) throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) 
        {
            count++;
        }

        return count; // height of maze
    }


    private static int getWidth(String filePath) throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        line = reader.readLine(); 

        for (int idx = 0; idx < line.length(); idx++) 
            {
                count++; 
            } 
        return count; // width of maze

    }
}