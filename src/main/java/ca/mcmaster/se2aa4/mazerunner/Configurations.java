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

            Options options = new Options(); 
            options.addOption("i", true, "File name for -i"); 
            options.addOption("p", true, "Used to verify the path sequence"); 
    
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
    
            String filepath = cmd.getOptionValue("i");
    
            Maze maze = new Maze(filepath);
            
            if (!cmd.hasOption("p")){
                maze.rightHandExplore();
            }
            else{ // need to veirfy user path
                String userPath = cmd.getOptionValue("p");
                maze.setUserDefinedPath(userPath);

                maze.verifyPath();
            }

    }

}
