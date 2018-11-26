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

        String[][] MalePreferences = {
            // Andy's preferences:
            {"Olivia", "Madison", "Natalie", "Lucy", "Peyton"},
            // Brad's preferences:
            {"Peyton", "Natalie", "Madison", "Lucy", "Olivia"},
            // Caleb's preferences:
            {"Madison", "Peyton", "Lucy", "Olivia", "Natalie"},
            // Daniel's preferences
            {"Petyon", "Madison", "Olivia", "Natalie", "Lucy"},
            // Ethan's preferences:
            {"Olivia", "Lucy", "Madison", "Natalie", "Petyon"}

        };

        String[][] FemalePreferences = {
            // Lucy's preferences:
            {"Daniel", "Brad", "Ethan", "Caleb", "Andy"},
            // Madison's preferences:
            {"Brad", "Andy", "Daniel", "Caleb", "Ethan"},
            // Natalie's preferences:
            {"Andy", "Caleb", "Ethan", "Daniel", "Brad"},
            // Olivia's preferences:
            {"Daniel", "Andy", "Caleb", "Brad", "Ethan"},
            // Peyton's preferences:
            {"Brad", "Ethan", "Andy", "Caleb", "Daniel"}

        };

    }

    public static String[][] StableMarraige(String[][] MalePreferences, String[][] FemalePreferences) {
        String[][] couples = new String[MalePreferences.length][2];
        for (int i = 0; i < couples.length; i++) {
            couples[i][0] = "SINGLE";

        }
        int[] singleFemales = new int[FemalePreferences.length];

        for (int i = 0; i < singleFemales.length; i++) {
            singleFemales[i] = 1;

        }
        int singleINDX = someoneIsSingle(couples);
        while (singleINDX != -1) {

            for (int i = 0; i < MalePreferences.length; i++) {
                int nextBestMatch = i;
                if (singleFemales[nextBestMatch] == 1) {
                    
                    couples[singleINDX][1] = MalePreferences[singleINDX][nextBestMatch];
                 
                 
                }else{
                // check if this taken female likes current male better
                
                
                }

            }

        }

        return couples;

    }

    public static int someoneIsSingle(String[][] couples) {
        for (int i = 0; i < couples.length; i++) {
            if (couples[i][0].equals("SINGLE")) {
                return i;
            }
        }
        return -1;

    }

}
