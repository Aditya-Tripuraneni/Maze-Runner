package ca.mcmaster.se2aa4.mazerunner;

// Interface for reading user's path to exit maze
interface  InputReader{

    boolean isCannonical();
    boolean verifyCannonical(boolean startWest);
    boolean verifyFactorized(boolean startWest);
}