package ca.mcmaster.se2aa4.mazerunner;

interface  InputReader{

    boolean isCannonical();
    boolean verifyCannonical(boolean startWest);
    boolean verifyFactorized(boolean startWest);
}