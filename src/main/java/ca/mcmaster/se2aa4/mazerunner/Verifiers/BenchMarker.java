package ca.mcmaster.se2aa4.mazerunner.Verifiers;



import java.text.DecimalFormat;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;

public class BenchMarker implements BenchMark{
    private MazeSolver solver;
    private MazeSolverFactory factory = new MazeSolverFactory();
    private Maze maze;

    public BenchMarker(Maze maze){
        this.maze = maze; 

    }

    @Override
    public void benchMark(String userEnteredBaseLine, String userEnteredMethod){
        // run baseline
        this.solver = factory.createSolver(userEnteredBaseLine, this.maze);
        long startTimeOne = System.nanoTime(); 
        Path pathOne = this.solver.solveMaze();
        long endTimeOne = System.nanoTime(); 

        // time taken for baseline algorithm
        long durationOne = endTimeOne - startTimeOne;   

        //format to 2 decimal places
        String formattedDuration = String.format("%.2f", durationOne / 1e6); 
        System.out.println(formattedDuration + " ms spent exploring the maze using the provided method: " + userEnteredBaseLine);

        int baselineInstructions = this.getInstructionCount(pathOne);
    
        // run method 
        this.solver = factory.createSolver(userEnteredMethod, this.maze);
        long startTimeTwo = System.nanoTime(); 
        Path pathTwo = solver.solveMaze();
        long endTimeTwo = System.nanoTime(); 

        // time taken for method algorithm
        long durationTwo = endTimeTwo - startTimeTwo;   
        
        //format to 2 decimal places
        String formattedDurationTwo = String.format("%.2f", durationTwo / 1e6); 
        System.out.println(formattedDurationTwo + " ms spent exploring the maze using the provided baseline method: " + userEnteredMethod);

        int methodInstructions = this.getInstructionCount(pathTwo);

        double speedUp = (double) baselineInstructions / methodInstructions; 
        DecimalFormat df = new DecimalFormat("#.##"); 
        speedUp = Double.valueOf(df.format(speedUp));
       
        System.out.println("Speed up: " +  speedUp);
    }



    private int getInstructionCount(Path path){
        int instructionCount = 0;
        int i = 0;
        while ( i < path.getPathLength()){
            char c = path.instructionAt(i);
            if (c == ' '){
                i++;
            }
            else if (Character.isDigit(c))
            {
                int count = 0;

                while (i < path.getPathLength() && Character.isDigit(path.instructionAt(i))){
                    count *= 10;
                    count += (path.instructionAt(i) - '0');
                    i++; 
                }
                instructionCount += count; 
                i++;
            }
            else{
                instructionCount++;
                i++;
            }
        }
        return instructionCount;
    }
}

