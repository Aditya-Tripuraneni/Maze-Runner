package ca.mcmaster.se2aa4.mazerunner.Verifiers;

import ca.mcmaster.se2aa4.mazerunner.Utils.Algorithms;

public interface BenchMark {

    void benchMark(Algorithms userEnteredBaseLine, Algorithms userEnteredMethod);
}
