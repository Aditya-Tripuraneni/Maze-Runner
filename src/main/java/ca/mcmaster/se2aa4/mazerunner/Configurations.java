package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.module.Configuration;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;


public class Configurations {
    private static final Logger logger = LogManager.getLogger();

    public static void configure(String[] args) throws Exception
    {
            logger.info("** Starting Maze Runner");

            Options options = new Options(); 
            options.addOption("i", true, "File name for -i"); 
            options.addOption("p", true, "Used to verify the path sequence"); 
    
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
    
            String filepath = cmd.getOptionValue("i");
    
            logger.info("**** Reading the maze from file " + filepath); 
            Maze maze = new Maze(filepath);
            displayMap(filepath);
    }

    private static void displayMap(String filepath) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;

        while ((line = reader.readLine()) != null) 
        {
            for (int idx = 0; idx < line.length(); idx++) 
            {
                if (line.charAt(idx) == '#') 
                {
                    System.out.print("WALL ");
                } 
                else if (line.charAt(idx) == ' ') 
                {
                    System.out.print("PASS ");
                }
            }
        }
    }
    
}
