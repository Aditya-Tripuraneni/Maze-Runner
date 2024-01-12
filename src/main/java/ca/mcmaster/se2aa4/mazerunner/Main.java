package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;





public class Main {

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        // System.out.println("** Starting Maze Runner");
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        options.addOption("i", true, "File name for -i"); 



        try {
            CommandLine cmd = parser.parse(options, args);
            String filepath = cmd.getOptionValue("i");

            System.out.println("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
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
                logger.info(System.lineSeparator());
                System.out.print(System.lineSeparator());
            }
        } catch(Exception e) {
            System.err.println("/!\\ An error has occured /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }
}
