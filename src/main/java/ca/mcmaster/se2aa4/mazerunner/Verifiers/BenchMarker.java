package ca.mcmaster.se2aa4.mazerunner.Verifiers;



import java.text.DecimalFormat;

import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;
import ca.mcmaster.se2aa4.mazerunner.Paths.Path;
import ca.mcmaster.se2aa4.mazerunner.Utils.Algorithms;

public class BenchMarker implements BenchMark{
    private MazeSolver solver;
    private final double MILLISECOND_CONVERSION = 1e6; 
    private MazeSolverFactory factory = new MazeSolverFactory();
    private Maze maze;

    public BenchMarker(Maze maze) {
        this.maze = maze; 
    }


    /**
     * Benchmarks the performance of two maze-solving algorithms.
     * Compares the time taken and instruction count between the baseline and the method algorithm.
     * Calculates the speedup achieved by the method algorithm compared to the baseline.
     * 
     * @param userEnteredBaseLine The baseline algorithm chosen by the user.
     * @param userEnteredMethod The method algorithm chosen by the user.
     */
    @Override
    public void benchMark(Algorithms userEnteredBaseLine, Algorithms userEnteredMethod) {
        // run baseline
        this.solver = factory.createSolver(userEnteredBaseLine, this.maze);
        long startTimeOne = System.nanoTime(); 
        Path pathOne = this.solver.solveMaze();
        long endTimeOne = System.nanoTime(); 

        // time taken for baseline algorithm
        long durationOne = endTimeOne - startTimeOne;   
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        //format to 2 decimal places
        String formattedDuration = decimalFormat.format(durationOne / MILLISECOND_CONVERSION);
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
        String formattedDurationTwo = decimalFormat.format(durationTwo / MILLISECOND_CONVERSION); 
        System.out.println(formattedDurationTwo + " ms spent exploring the maze using the provided baseline method: " + userEnteredMethod);

        int methodInstructions = this.getInstructionCount(pathTwo);

        double speedUp = (double) baselineInstructions / methodInstructions; 
        DecimalFormat df = new DecimalFormat("#0.00"); 
        speedUp = Double.valueOf(df.format(speedUp));
       
        System.out.println("Speed up: " +  df.format(speedUp));
    }


    private int getInstructionCount(Path path) {
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

