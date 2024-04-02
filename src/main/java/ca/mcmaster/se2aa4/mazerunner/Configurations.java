package ca.mcmaster.se2aa4.mazerunner;


import org.apache.commons.cli.Options;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;

import ca.mcmaster.se2aa4.mazerunner.Verifiers.MazeInputReader;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.InputVerifier;


import org.apache.commons.cli.DefaultParser;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.SOUTH;

import java.beans.beancontext.BeanContextChild;

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
            options.addOption("m", "method", true, "The algorithm used to explore the maze");
            options.addOption("b", "baseline", true, "Benchmarking baseline for comparassion");
    
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, this.args);
    
            String filepath = cmd.getOptionValue("i");
    
            Maze maze = new Maze(filepath);
            MazeSolver solver;

        
            

            if (cmd.hasOption("b") && cmd.hasOption("m")){
                String baseline = cmd.getOptionValue("b");
                String method = cmd.getOptionValue("m");
                System.out.println("X time spent exploring the maze using the provided method: " + method);
                System.out.println("Y time spent exploring the maze using the provided method:" + baseline);
                System.out.println("Speed up: Y/X" );

            }

            else if (cmd.hasOption("m"))
            {
                String algorithm = cmd.getOptionValue("m");
                MazeSolverFactory factory = new MazeSolverFactory(); 
                solver = factory.createSolver(algorithm, maze);
                solver.solveMaze();
            }
            else if (cmd.hasOption("p")){ // need to veirfy user pathhow 
                String userPath = cmd.getOptionValue("p");
                InputVerifier mazeInputVerifier = new MazeInputReader(maze, userPath);
                mazeInputVerifier.verifyPath();
            }

    }

    

}
