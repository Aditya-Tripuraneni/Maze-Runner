package ca.mcmaster.se2aa4.mazerunner;


import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;


public class Configurations {

    private String[] args;

    public Configurations(String[] args) {
        this.args = args;
    }
    

    public void configure() throws Exception
    {

            Options options = new Options(); 
            options.addOption("i", true, "File name for -i"); 
            options.addOption("p", true, "Used to verify the path sequence"); 
    
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, this.args);
    
            String filepath = cmd.getOptionValue("i");
    
            Maze maze = new Maze(filepath);
            
            if (!cmd.hasOption("p")){
                maze.rightHandExplore();
                // maze.BFSExplore();
            }
            else{ // need to veirfy user pathhow 
                String userPath = cmd.getOptionValue("p");
                maze.setUserDefinedPath(userPath);
                maze.verifyPath();
            }

    }

}
