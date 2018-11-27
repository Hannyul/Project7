/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galeshapely;

/**
 *
 * @author maha
 */
public class GaleShapely {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //  String[] MaleNames = {"Andy", "Brad", "Caleb", "Daniel", "Ethan"};
        ///             0         1         2        3        4
//        String[] FemaleNames = {"Lucy", "Madison", "Natalie", "Olivia", "Peyton"};
        int[][] MalePreferences = {
            // Andy's preferences:
            {3, 1, 2, 0, 4},
            // Brad's preferences:
            {4, 2, 1, 0, 3},
            // Caleb's preferences:
            {1, 4, 0, 3, 2},
            // Daniel's preferences
            {4, 1, 3, 2, 0},
            // Ethan's preferences:
            {3, 0, 1, 2, 4}
        };

        //  String[] MaleNames = {"Andy", "Brad", "Caleb", "Daniel", "Ethan"};
        ///             0         1         2        3        4
        int[][] FemalePreferences = {
            // Lucy's preferences:
            {3, 1, 4, 2, 0},
            // Madison's preferences:
            {1, 0, 3, 2, 4},
            // Natalie's preferences:
            {0, 2, 4, 3, 1},
            // Olivia's preferences:
            {3, 0, 2, 1, 4},
            // Peyton's preferences:
            {1, 4, 0, 2, 3}
        };

        StableMarraige(MalePreferences, FemalePreferences);

    }

    public static String[][] StableMarraige(int[][] MalePreferences, int[][] FemalePreferences) {
        String[][] couples = new String[MalePreferences.length][2];
        String[] MaleNames = {"Andy", "Brad", "Caleb", "Daniel", "Ethan"};
        String[] FemaleNames = {"Lucy", "Madison", "Natalie", "Olivia", "Peyton"};

        for (int i = 0; i < couples.length; i++) {
            couples[i][0] = MaleNames[i];
            couples[i][1] = "NO MATCH";
        }

        // this array stores the index of each female's husband
        // if the female is single, the value is -1
        int[] FemaleMatches = new int[FemalePreferences.length];
        int[] MaleMatches = new int[MalePreferences.length];
        // matrix to store all the females that rejected each guy:
        int[][] rejectedBy = new int[MalePreferences.length][MalePreferences[0].length];

        for (int i = 0; i < rejectedBy.length; i++) {
            for (int j = 0; j < rejectedBy[0].length; j++) {
                rejectedBy[i][j] = -1;
            }
        }
        for (int i = 0; i < FemaleMatches.length; i++) {
            FemaleMatches[i] = -1;
            MaleMatches[i] = -1;
        }
        int singleINDX = findSingleMale(MaleMatches, 0);
        while (singleINDX != -1) {
        System.out.println("\n\n\n");

            System.out.println(MaleNames[singleINDX] + " is single");

            //System.out.println("current SINGLE DUUUUUUUDE: "+MaleNames[singleINDX]);
            //  System.out.println("now at: " + MaleNames[i]);
            // get his next best match
            for (int j = 0; j < MalePreferences[singleINDX].length; j++) {
                int nextBestMatch = MalePreferences[singleINDX][j];

                System.out.println("\n\t\t"+"proposing to.... " + FemaleNames[nextBestMatch]);
                //  System.out.println("He prefers " + FemaleNames[nextBestMatch]);

                // if this female is single AND she has not rejected him before:
                if (FemaleMatches[nextBestMatch] == -1 && rejectedBy[singleINDX][nextBestMatch] == -1) {

//                    System.out.println("herererereere");
                    // mark this guy as taken
                    couples[singleINDX][1] = FemaleNames[nextBestMatch];
                    // mark her as taken by some guy with index i
                    FemaleMatches[nextBestMatch] = singleINDX;
                    // mark this guy as taken:
                    MaleMatches[singleINDX] = nextBestMatch;
                    System.out.println("\t\t"+couples[singleINDX][0] + " matched with " + couples[singleINDX][1]);
                    // ignore i's other preferences as he is now taken
                    break;

                }

                // if this female HAS rejected him before:
                if (rejectedBy[singleINDX][nextBestMatch] == 1) {
//                    System.out.println("herererereere");
                    System.out.println("\t\t"+FemaleNames[nextBestMatch] + " rejected " + MaleNames[singleINDX]);
                    // get the next best match:
                    continue;

                }
                if (FemaleMatches[nextBestMatch] == -1 && rejectedBy[singleINDX][nextBestMatch] != -1) {
//                    System.out.println("herererere");

                } else {

//                    System.out.println("herererereere");
                    // if the next best match is NOT single:
                    if (FemaleMatches[nextBestMatch] != -1) {
//                        System.out.println("Aaaaaaaaa");

                        // check if woman at index nextBestMatch
                        // prefers current guy over her match:
                        int currentMatch = 100;
//                        System.out.println(FemaleNames[nextBestMatch] + " is currently with " + MaleNames[currentMatch] + ", his index is " + currentMatch);
//
//
                        int currentGuy = 100;
                        // get current guy's index:
//                        System.out.println("current SINGLE guy: " + MaleNames[singleINDX]);

                        for (int k = 0; k < FemalePreferences[nextBestMatch].length; k++) {
                            // System.out.println("now checking " + MaleNames[FemalePreferences[nextBestMatch][k]] + ", his index is " + k);
                            if (FemalePreferences[nextBestMatch][k] == singleINDX) {
                                currentGuy = k;
                            }
                            if (FemalePreferences[nextBestMatch][k] == FemaleMatches[nextBestMatch]) {

                                currentMatch = k;
                            }

                        }

                        //    System.out.println(FemaleNames[nextBestMatch] + " is currently with " + MaleNames[currentMatch]);
                        if (currentMatch == 0) {

                            rejectedBy[singleINDX][nextBestMatch] = 1;
                            System.out.println("\t\t>>>"+FemaleNames[nextBestMatch] + " rejected " + MaleNames[singleINDX]);
                            break;
                        }
//                        System.out.println("current guy: " + MaleNames[currentGuy]);

                        if (currentGuy < currentMatch) {
//                            System.out.println("hehehehehehhelHSUFHISUDHFIUDSHFIUSDHF");
                            System.out.println("\t\t>>>"+FemaleNames[nextBestMatch] + " prefers the current single guy (" + MaleNames[FemalePreferences[nextBestMatch][currentGuy]] + ") over her current match (" + MaleNames[FemalePreferences[nextBestMatch][currentMatch]] + ")");
                            // mark this guy as taken
                            couples[singleINDX][1] = FemaleNames[nextBestMatch];
                            // mark her as taken by some guy with index i
                            FemaleMatches[nextBestMatch] = singleINDX;
                            // mark this guy as taken:
                            MaleMatches[singleINDX] = nextBestMatch;
                            System.out.println("\t\t"+couples[singleINDX][0] + " matched with " + couples[singleINDX][1]);
                            int previousMatch = FemalePreferences[nextBestMatch][currentMatch];
                            rejectedBy[previousMatch][nextBestMatch] = 1;
                            System.out.println("\t\t>>>"+FemaleNames[nextBestMatch] + " rejected " + MaleNames[previousMatch]);
                            // make previous match single:
                            MaleMatches[previousMatch] = -1;
                            break;

                        } else {
                            System.out.println("\t\t>>>"+FemaleNames[nextBestMatch] + " prefers her current match  (" + MaleNames[FemalePreferences[nextBestMatch][currentMatch]] + ") over  the current single guyh (" + MaleNames[FemalePreferences[nextBestMatch][currentGuy]] + ")");

//                            System.out.println("ellelelelellelelelelelellele");
//                            System.out.println("this index: " + singleINDX + "              " + MaleNames[singleINDX]);
                            MaleMatches[singleINDX] = -1;
//                            System.out.println("MaleMatches[i] = " + MaleMatches[singleINDX]);
                            rejectedBy[singleINDX][nextBestMatch] = 1;

                            //nextBestMatch+=1;
                            break;
                        }

                    }
//           else {
//                        
//                        System.out.println("PPPPPPPPPEPEPPEPEEPPEPEPPEPEPEPle");
//                        rejectedBy[singleINDX][nextBestMatch] = 1;
//                        break;
//                    }

                }

            }

            singleINDX = findSingleMale(MaleMatches, singleINDX);

        }

        return couples;

    }

    public static int findSingleMale(int[] MaleMatches, int singleINDX) {

        for (int i = 0; i < MaleMatches.length; i++) {
            // if this guy is single
            if (MaleMatches[i] == -1 && singleINDX != i) {
                singleINDX = i;

                break;
            }
            // if we reach the last index:
            if (i + 1 == MaleMatches.length) {
//                    System.out.println("MaleMatches[i] = " + MaleMatches[i]);
                if (MaleMatches[i] == -1) {
                    singleINDX = i;

                } else {

                    if (MaleMatches[i] == -1) {
                        singleINDX = i;
                    }
//                        System.out.println("herehrelalalalaAAAAAAA");
                    singleINDX = -1;

                    /*
                         *   if(MaleMatches[i] != -1){
                        
                         singleINDX = -1;
                         }
                         else{
                        
                         singleINDX=i;
                        
                         }
                     */
                }
            }
        }
        return singleINDX;

    }
}
