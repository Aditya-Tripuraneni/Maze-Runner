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

import java.util.Random;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter; 


public class Main {

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.info("Here goes the business code");
        Configuration config = new Configuration(args);
        Random random = buildReproducibleGenerator(config.seed());
        Maze theMaze = new Maze(config.width(), config.height());
        theMaze.carve(random);
        MazeExporter exporter = new MazeExporter(theMaze);
        exporter.export(config.outputFile());

        logger.info("** Starting Maze Runner");
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        options.addOption("i", true, "File name for -i"); 



        try {
            CommandLine cmd = parser.parse(options, args);
            String filepath = cmd.getOptionValue("i");

            logger.info("**** Reading the maze from file " + filepath); 
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
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
                logger.info(System.lineSeparator());
                System.out.print(System.lineSeparator());
            }
        } catch(Exception e) {
            System.err.println("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path"); 
        logger.info("PATH NOT COMPUTED"); 
        logger.info("** End of MazeRunner");

    }
}


class Configuration {

    public Configuration(String[] args) { }

    public long seed() { return System.currentTimeMillis(); }

    public int width() { return 41; }

    public int height() { return 41; }

    public BufferedWriter outputFile() {
        return new BufferedWriter(new OutputStreamWriter(System.out));
    }


}


class Maze{

    public Maze(int width, int height){}

    
    public void carve(Random random){
        return; 
    }

    
}

 class MazeExporter{
    public MazeExporter(Maze maze){}

    public void export(BufferedWriter bw){
        return; 
    }

}
