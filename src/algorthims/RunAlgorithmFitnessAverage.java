package algorthims;

import AbstractClasses.HyperHeuristic;
import AbstractClasses.ProblemDomain;
import BinPacking.BinPacking;

import java.io.FileWriter;
import java.io.IOException;

public class RunAlgorithmFitnessAverage {

    public static void main(String[] args) throws IOException {
        long timeLimit;
        int problemSeed;
        int algorithmSeed;
        int iterations = 50;         //number of times to run each problem instance
        int problemInstance = 0;    //starting problem instance
        HyperHeuristic algorithm;
        ProblemDomain problem = new BinPacking(0);
        String filePath = "data/testAlgorithmData" + iterations + ".csv";

        /* file writer that creates new file and adds data headers */
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("iteration,problem instance,problem seed,algorithm seed,starting fitness,algorithm number," +
                "fitness,number of iterations,heuristics\n");
        fw.flush();
        fw.close();

        /* outer loop, each iteration is a new problem */
        for (int i = 0; i < problem.getNumberOfInstances(); i++) {
            problemSeed = 1000;
            algorithmSeed = 1000;
            timeLimit = 100000;

            /* inner loop, runs a problem "iterations" times */
            for (int j = 0; j < iterations; j++) {
                problem = new BinPacking(problemSeed);
                algorithm = new AlgorithmFitnessAverage(algorithmSeed, problemSeed,
                        problemInstance, j, filePath);

                problem.loadInstance(problemInstance);

                algorithm.setTimeLimit(timeLimit);
                algorithm.loadProblemDomain(problem);
                algorithm.run();

                problemSeed++;
                algorithmSeed++;
                System.out.println(j);
            }

            System.out.println("Problem instance " + problemInstance + " complete");
            problemInstance++;
        }
    }

}