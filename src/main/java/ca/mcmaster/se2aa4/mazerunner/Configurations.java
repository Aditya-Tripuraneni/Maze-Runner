package ca.mcmaster.se2aa4.mazerunner;


import org.apache.commons.cli.Options;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.MazeInputReader;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.BenchMarker;
import ca.mcmaster.se2aa4.mazerunner.Verifiers.InputVerifier;


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
            options.addOption("m", "method", true, "The algorithm used to explore the maze");
            options.addOption("b", "baseline", true, "Benchmarking baseline for comparassion");
    
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, this.args);
    
            Path filepath = new Path(cmd.getOptionValue("i"));
            long startTime = System.nanoTime();
            Maze maze = new Maze(filepath);
            long endTime = System.nanoTime(); 


            if (cmd.hasOption("b") && cmd.hasOption("m"))
            {
                String baseline = cmd.getOptionValue("b");
                String method = cmd.getOptionValue("m");
                long duration = endTime - startTime; 
                String formattedDuration = String.format("%.2f", duration / 1e6); 
                System.out.println("Time spent loading " + filepath + " : " + formattedDuration + " ms");
                BenchMarker benchMarker = new BenchMarker(maze);
                benchMarker.benchMark(baseline, method);
            }

            else if (cmd.hasOption("m"))
            {
                String algorithm = cmd.getOptionValue("m");
                MazeSolverFactory factory = new MazeSolverFactory(); 
                MazeSolver solver = factory.createSolver(algorithm, maze);
                Path path = solver.solveMaze();
                System.out.println(path.getPath());
            }
            else if (cmd.hasOption("p")){ // need to veirfy user pathhow 
                String userPath = cmd.getOptionValue("p");
                InputVerifier mazeInputVerifier = new MazeInputReader(maze, userPath);
                mazeInputVerifier.verifyPath();
            }

    }
}
