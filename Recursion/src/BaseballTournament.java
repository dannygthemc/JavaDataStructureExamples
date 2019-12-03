import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Gives practice with analyzing a nested loop by generating schedules for a
 * baseball tournament in which a user-entered number of teams will compete.
 * @author CS1027a 2009
 */
public class BaseballTournament {

    // Value returned by getNumTeams() when the user wishes to quit
    private static int QUIT = -1;

    // The minimum number of teams that must compete in the tournament
    private static int MIN_TEAMS = 2;

    // The teams that can possibly compete in the tournament
    private static final String[] LEAGUE_TEAMS = {
        "Toronto Blue Jays", 
        "Baltimore Orioles",
        "Boston Red Sox",
        "New York Yankees",
        "Tampa Bay Rays",        
        "Chicago White Sox",
        "Cleveland Indians",
        "Detroit Tigers",
        "Kansas City Royals",
        "Minnesota Twins",
        "Los Angeles Angels of Anaheim",
        "Oakland Athletics",
        "Seattle Mariners",
        "Texas Rangers"
    };

    /**
     * Builds a tournament schedule for the first n teams in the American League.
     * Each team plays every other team in the tournament.
     * @param numTeams The number of teams to compete in the tournament
     */
    public static void buildSchedule(int numTeams) {

        String team1;   // The first team in a game
        String team2;   // The second team in a game
        int game = 0;   // The game number
       
        System.out.println("\nThe following games will be played.");
        System.out.println("==================================================");

        // For each team (up to the desired number of teams)
        for (int i = 0; i < numTeams; i++) {

            // Join the team up with another team, and play ball!
            //for (int j = numTeams - 1; j > i; j--) {
            for (int j = i+1; j < numTeams; j++) {

                team1 = LEAGUE_TEAMS[i];
                team2 = LEAGUE_TEAMS[j];
                game++;

                System.out.println("Game " + game + ": " + team1 + " vs. " + team2);
            }
        }
    }

    /**
     * Prompts the user to enter the desired number of teams to compete in the
     * tournament.
     * @return The number of teams to compete in the tournament
     */
    private static int getNumTeams() {

        // Retrieves input from the user
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        // The user's string input
        String userInput;

        // The input when converted to an integer
        int returnValue;

        do {
            System.out.print("\nHow many teams should play in the tournament? [Enter to quit]: ");

            try {

                // Prompt the user
                userInput = keyboard.readLine();

                // If [Enter] was pressed, the user wishes to quit
                if (userInput.length() == 0)
                    return QUIT;

                // Otherwise, convert the string input to an integer
                returnValue = Integer.parseInt(userInput);

                // Ensure that it's a sane value and, if so, return it
                if ((returnValue >= MIN_TEAMS) && (returnValue <= LEAGUE_TEAMS.length))
                    return returnValue;
            }
            catch (Exception ex) {
                // Print the error below
            }

            // If we get here, either the input was not an integer, or it was not in a valid range
            System.out.println("\nPlease enter a number between " + MIN_TEAMS + " and " + LEAGUE_TEAMS.length + ".");

        } while (true);
    }

    /**
     * Allows the user to generate one or more baseball tournament schedules for
     * a desired number of teams.
     * @param args Array of command line arguments passed to the program
     */
    public static void main(String[] args) {

        System.out.println("Baseball Tournament Generator");
        System.out.println("================================================");

        // The number of teams that will compete in each tournament
        int numTeams;

        do {

            // Ask the user for the number of teams
            numTeams = getNumTeams();

            // Play ball
            if (numTeams > 0)
                buildSchedule(numTeams);

        } while (numTeams > 0);
        
    }
}