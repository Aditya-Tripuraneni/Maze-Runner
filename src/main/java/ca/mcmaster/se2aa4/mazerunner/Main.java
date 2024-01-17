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



public class Main {

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {

        Maze maze = new Maze("Enter a maze path");
        logger.info("** Starting Maze Runner");

        try {
            configure(args);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace(); 
            System.exit(1);
        }

        logger.info("**** Computing path"); 
        logger.info("PATH NOT COMPUTED"); 
        logger.info("** End of MazeRunner");

    }

    private static void configure(String[] args) throws Exception
    {
            Options options = new Options(); 
            options.addOption("i", true, "File name for -i"); 
            options.addOption("p", true, "Used to verify the path sequence"); 
    
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
    
            String filepath = cmd.getOptionValue("i");
    
            logger.info("**** Reading the maze from file " + filepath); 
    
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
                    logger.info("WALL");
                } 
                else if (line.charAt(idx) == ' ') 
                {
                    logger.info("PASS");
                }
            }
        }
    }

    
}


