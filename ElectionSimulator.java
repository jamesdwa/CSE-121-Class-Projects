import java.util.*;

public class ElectionSimulator {
    public static final int NUM_SIMS = 5;
    public static final int NUM_DISTS = 10;
    public static final double POLL_AVG = .52;
    public static final double POLL_ERR = .07;

    public static void main(String[] args) {
        Random rand = new Random();

        // intro
        System.out.println();
        System.out.println("Welcome to the Election Simulator!");
        System.out.println("Running " + NUM_SIMS +
        " simulations of " + NUM_DISTS + " districts.");
        System.out.print("Our candidate is polling at " + POLL_AVG*100 + "% with a ");
        System.out.println(POLL_ERR*100 + "% margin of error.");
        System.out.println();

        // simulating the election
        
        double averagePercentage = 0; // our candidate average vote percentage variable

        for (int i = 1; i <= NUM_SIMS; i++) { // simulating the five simulations
            double turnout = 0; // the number of total voters in each district variable
            double votesOurSum = 0; // our candidate's voters variable
            double percentageOur = 0; // percentage of our candidate's voters variable

            for (int j = 0; j < NUM_DISTS; j++) { // loop for 10 districts in one simulation
                int districtSum = rand.nextInt(1000) + 1;
                turnout = turnout + districtSum;
                // Poll Accuracy
                double pollAccurary = rand.nextGaussian() * 0.5 * POLL_ERR;
                percentageOur = (pollAccurary + POLL_AVG);
                double votesOur = percentageOur*districtSum;
                votesOurSum = (int) votesOur + votesOurSum; // our candidate's voters number
            }

            percentageOur = votesOurSum * 100.0/turnout; //our candidate's voting percentage
            averagePercentage += percentageOur; // our candidate's average vote percentage
    
            double votesOpp = turnout - votesOurSum; // opp candidate's percentage
            double percentageOpp = (100 - percentageOur); //opp candidate's voters number
            boolean winLoss = percentageOur > percentageOpp; //if our candidate won or lost

            // candidates' stats
            System.out.println("Running simulation #" + i + ":");
            System.out.println("  Win? " + winLoss);
            System.out.println("  Results: " + (int) votesOurSum +
            " ("+percentageOur+"%) - " + (int) votesOpp +  " (" + percentageOpp + "%)");
            System.out.print("  Visualization: ");
    
            // visualization
            for (int k = 100; k <= votesOurSum; k+=100) {
                System.out.print("+");
            }
            System.out.println();
            System.out.print("                 ");
            for (int x = 100; x <= votesOpp; x+=100) {
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
        // our candidate's average voting percentage
        System.out.println("Average vote percentage: " + averagePercentage/NUM_SIMS + "%");
    }
}