package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MazeExporter{
    private String filePath; 
    
    public static char[][] constructMaze(String filepath) throws FileNotFoundException, IOException
    {
        MazeExporter mazeExporter = new MazeExporter();


        int width = mazeExporter.getWidth(filepath); // width is esentially the columns since its how far across, we are concerned with NUMBER of columns
        int height = mazeExporter.getHeight(filepath);  // height is esentially the rows since it spans up and down, we are concerned with number of rows

        char[][] maze = new char[height][width];

        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        int row = 0; 
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

        return maze;
    }

    private int getHeight(String filePath) throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) 
        {
            count++;
        }

        return count; 

    }

    private int getWidth(String filePath) throws FileNotFoundException, IOException{
        int count = 0; 
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        line = reader.readLine(); 

        for (int idx = 0; idx < line.length(); idx++) 
            {
                count++; 
            } 
        return count; 

    }
}