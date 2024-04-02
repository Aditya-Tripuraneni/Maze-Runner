package ca.mcmaster.se2aa4.mazerunner.Verifiers;


import ca.mcmaster.se2aa4.mazerunner.ExplorationAlgorithms.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.Mazes.Maze;
import ca.mcmaster.se2aa4.mazerunner.Mazes.MazeSolverFactory;

public class BenchMarker {
    private MazeSolver solver;
    private MazeSolverFactory factory = new MazeSolverFactory();
    private Maze maze;

    public BenchMarker(Maze maze){
        this.maze = maze; 

    }


    public void benchMark(String userEnteredBaseLine, String userEnteredMethod){
        //! run baseline

        this.solver = factory.createSolver(userEnteredBaseLine, this.maze);
        solver.solveMaze();
        //! run method
    }





   
}

