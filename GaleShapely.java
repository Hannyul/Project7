/*

Group Members:

Hana'a Al-Lohibi            
Bashair Atif Abdulrazak       
Maha Osama        

Algorithms and Data Structures
Section CH 
Project 7 - Implementing Gale Shapely's Algorithm

 */
package galeshapely;

public class GaleShapely {

    public static void main(String[] args) {

        /*
        
        Lucy - index 0
        Madison - index 1
        Natalie - index 2
        Olivia - index 3
        Peyton - index 4
        
        
        Andy - index 0
        Brad - index 1
        Caleb - index 2
        Daniel - index 3
        Ethan - index 4
         */
        // Initialize the matrix of MalePreferences
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
        // Initialize the matrix of FemalePreferences
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
        // Call the StableMarraige method
        String[][] pairs = StableMarraige(MalePreferences, FemalePreferences);

        System.out.println("\n\n\n______________________________________\nThe couples are:\n\n\n");
        for (int i = 0; i < pairs.length; i++) {
            System.out.println(pairs[i][0] + " and " + pairs[i][1]);

        }
        System.out.println("\n\n\n\n");

    }

    public static String[][] StableMarraige(int[][] MalePreferences, int[][] FemalePreferences) {
        /*
        
        Description:
        this method solves the problem of matching two sets of elements
        while satisfying their preferences
        
        
        Parameters:
        two n by n matrices, one for the guy's preferences and nother for the girl's preferences
        
        
        Output:
        a 2D string matrix storing the couples' names
        
        
         */

        // initialize couples matrix
        String[][] couples = new String[MalePreferences.length][2];
        // set the male and female names
        String[] MaleNames = {"Andy", "Brad", "Caleb", "Daniel", "Ethan"};
        String[] FemaleNames = {"Lucy", "Madison", "Natalie", "Olivia", "Peyton"};
        // initials couples in the first index Male Names and the second index "NO MATCH"
        for (int i = 0; i < couples.length; i++) {
            couples[i][0] = MaleNames[i];
            couples[i][1] = "NO MATCH";
        }

        // this array stores the index of each female's husband
        // and each male's wife. 
        // if the female OR male is single, the value is -1
        // if they're matched, -1 will be replaced with their spouse's index
        int[] FemaleMatches = new int[FemalePreferences.length];
        int[] MaleMatches = new int[MalePreferences.length];

        // set all matches to -1  
        for (int i = 0; i < FemaleMatches.length; i++) {
            FemaleMatches[i] = -1;
            MaleMatches[i] = -1;
        }

        // matrix to store all the females that rejected each guy. 
        // since we can't create a matrix of type boolean in java,
        // we are instead creating an integer matrix that stores 
        // the values 1 (true/rejected) and 0 (false/not rejected)
        int[][] rejectedBy = new int[MalePreferences.length][MalePreferences[0].length];

        // initially, no female has rejected any of the males
        // since the matrix is boolean, set the initial value to 0
        for (int i = 0; i < rejectedBy.length; i++) {
            for (int j = 0; j < rejectedBy[0].length; j++) {
                rejectedBy[i][j] = 0;
            }
        }

        // Set the index of the next single guy
        // by default, the next single guy is at index 0 
        int singleINDX = 0;

        // while there's a single guy at index singleINDX
        while (singleINDX != -1) {
            System.out.println("\n\n\n");
            System.out.println(MaleNames[singleINDX] + " is single");
            // get his next best match
            for (int j = 0; j < MalePreferences[singleINDX].length; j++) {
                //Print the single male name
                int nextBestMatch = MalePreferences[singleINDX][j];
                System.out.println("\n\t\t" + "proposing to.... " + FemaleNames[nextBestMatch]);
                // if this female is single:
                if (FemaleMatches[nextBestMatch] == -1) {
                    // store her name in the couples matrix
                    couples[singleINDX][1] = FemaleNames[nextBestMatch];

                    // mark her as taken by some guy with index 'singleINDX'
                    FemaleMatches[nextBestMatch] = singleINDX;

                    // mark this guy as taken by some woman with index 'nextBestMatch'
                    MaleMatches[singleINDX] = nextBestMatch;
                    // announce their engagement:
                    System.out.println("\t\t" + couples[singleINDX][0] + " matched with " + couples[singleINDX][1]);
                    // ignore this guy's other preferences as he is now taken
                    break;

                } else {
                     // if the next best match is NOT single:

                    // if this female HAS rejected him before (1 = true):
                    if (rejectedBy[singleINDX][nextBestMatch] == 1) {

                        System.out.println("\t\t" + FemaleNames[nextBestMatch] + " rejected " + MaleNames[singleINDX]+" before, so we will skip her");

                        // ignore her and move on
                        continue;

                    }
                    
                    // if she has NOT rejected him before
                    else{                    
                        // check if woman at index nextBestMatch
                        // prefers current guy over her match
                        // by looking for her current match's index
                        // and the current single guy's index
                        int currentMatch = 100;
                        int currentSingleGuy = 100;
                        // get current guy's and current match's indices 
                        // in the FemalePreferences matrix:
                        for (int k = 0; k < FemalePreferences[nextBestMatch].length; k++) {
                            if (FemalePreferences[nextBestMatch][k] == singleINDX) {
                                currentSingleGuy = k;
                            }
                            if (FemalePreferences[nextBestMatch][k] == FemaleMatches[nextBestMatch]) {
                                currentMatch = k;
                            }

                        }
                        // store their names to make printing them easier
                        String currentMatchName = MaleNames[FemalePreferences[nextBestMatch][currentMatch]];
                        String currentSingleGuyName = MaleNames[FemalePreferences[nextBestMatch][currentSingleGuy]];

                        // if the single guy is stored at an index smaller than
                        // this female's match, then he is more desirable to her than her current match:
                        if (currentSingleGuy < currentMatch) {
                            System.out.println("\t\t>>>" + FemaleNames[nextBestMatch] + " prefers the current single guy (" + currentSingleGuyName + ") over her current match (" + currentMatchName + ")");
                            // mark this single guy as taken
                            couples[singleINDX][1] = FemaleNames[nextBestMatch];
                            // mark her as taken by some guy with index singleINDX
                            FemaleMatches[nextBestMatch] = singleINDX;
                            // mark this guy as taken:
                            MaleMatches[singleINDX] = nextBestMatch;
                            System.out.println("\t\t" + couples[singleINDX][0] + " matched with " + couples[singleINDX][1]);
                            // get the original index of this woman's previous match
                            int previousMatch = FemalePreferences[nextBestMatch][currentMatch];
                            // mark her previous match as rejected by her (cross her out so she will be ignored in the future)
                            rejectedBy[previousMatch][nextBestMatch] = 1;
                            System.out.println("\t\t>>>" + FemaleNames[nextBestMatch] + " rejected " + MaleNames[previousMatch]);
                            // make previous match single:
                            MaleMatches[previousMatch] = -1;
                            break;

                        } else {
                            // mark this guy as rejected by this woman (cross her out)
                            System.out.println("\t\t>>>" + FemaleNames[nextBestMatch] + " prefers her current match  (" + currentMatchName + ") over  the current single guyh (" + currentSingleGuyName + ")");
                            rejectedBy[singleINDX][nextBestMatch] = 1;
                            // move on
                            break;
                        }
                } // else he hasn't been rejected 
                   
                } // else she's not single 

            } // for MalePreferences
            singleINDX = findSingleMale(MaleMatches, singleINDX);
        } // while

        return couples;

    }

    public static int findSingleMale(int[] MaleMatches, int singleINDX) {

        /*
        
        Description:
        
        this method gets iterates over all males to find the next single guy
        
        
        
        
        
        Parameters:
        
        MaleMatches - an array that stores the index of each guy's match (-1 if he's single)
       
        singleINDX - the index of the single guy prior to calling this method
        
        
        
        
        
        Output:
       
        This method returns the index of the next single guy
        
        
         */
        // iterate over all guys
        for (int i = 0; i < MaleMatches.length; i++) {
            // if this guy is single
            if (MaleMatches[i] == -1) {
                // we've found the next single male
                // store his index in the variable singleINDX
                singleINDX = i;
                // our search ended. exit the loop
                break;
            }
            // if we reach the last index:
            if (i + 1 == MaleMatches.length) {
                // if the last guy was not matched (is single):
                if (MaleMatches[i] == -1) {
                    singleINDX = i;
                } else {
                    // no more single guys if the last guy is matched:
                    singleINDX = -1;

                }
            } // if
        } // for
        // return the index
        return singleINDX;

    }
}
