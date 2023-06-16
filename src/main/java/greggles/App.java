package greggles;

import java.io.Console;
import java.time.Duration;
import java.time.Instant;

/**
 * This game creates lipograms and tests the user for them. Created by
 * gregorysmgreenfield@gmail.com
 */
public class App {
    /**
     * Provides instructions on how to play the game.
     * 
     * @param args Nothing is passed to it at the moment. Maybe a username might
     *             occur later on.
     * @return That the program has finished
     */
    public static void main(String[] args) {
        // Print instructions
        /*
         * String path = System.getProperty("user.dir") +
         * "/src/main/java/greggles/readme.txt";
         * try {
         * Scanner input = new Scanner(new File(path));
         * while (input.hasNextLine()) {
         * System.out.println(input.nextLine());
         * }
         * } catch (FileNotFoundException e) {
         * e.printStackTrace();
         * }
         */
        System.out.println("\n----------------------------------------------------------------\n");
        System.out.println(
                "Type the lipogram character and press return.\nIf you don't know the lipogram yet, press return for another word");

        String[] LanguageAndTable = DynamicCoded.GetLanguageAndTable();
        DynamicMain(LanguageAndTable[0], LanguageAndTable[1]);
        // HardCoded.HardMain();
    }

    /**
     * 1: Get lipogram
     * 2: Start stopwatch
     * 3: Generate lipogrammed words, test user for answer
     * 4: Loop 3 until correct answer is given
     * 5: Print results, offer to play again.
     * 
     * @param language The language of the words the user wants to play in.
     * @param table    The path to the source of words the user wants to work in.
     */
    public static void DynamicMain(String language, String table) {
        // Declarations
        Console read = System.console();
        boolean GuessCorrect = false;

        // Connect to LipogramGame db.
        MySQLHandler wordSelector = new MySQLHandler();
        wordSelector.connect();

        // Get lipogram
        String Lipo = DynamicCoded.LipogramsDynamicCoded(language);

        // Cheat for debugging purposes: write lipogram
        System.out.println("CHEAT Lipogram is: " + Lipo);

        // start stopwatch
        Instant startTime = Instant.now();

        // Print lipogrammed words and query user.
        while (!GuessCorrect) {
            String Lipogrammed = DynamicCoded.LipogrammedDynamicString(wordSelector.getWord(Lipo, table), Lipo);
            System.out.println("- " + Lipogrammed);
            GlobalVariables.CharCounter = GlobalVariables.CharCounter + Lipogrammed.length();
            GlobalVariables.WordCounter++;
            String guess = read.readLine();

            GuessCorrect = DynamicCoded.LipoGuess(Lipo, guess);
        }

        // stop stopwatch, print results, ask about next
        Instant endTime = Instant.now();
        Duration timeElapsed = Duration.between(startTime, endTime);
        System.out.println("Lipogram is: " + Lipo + "\nTime taken: " + formatDuration(timeElapsed)
                + "\nTotal number of guesses: " + GlobalVariables.GuessCounter + "\nTotal number of words: "
                + GlobalVariables.WordCounter + "\nTotal number of characters: " + GlobalVariables.CharCounter
                + "\nThank you for playing!\nWould you like to play again?\nYes: 'Y'\nNo:  'N'\nChange of language and/or words: 'S'");
        String choice = read.readLine().toLowerCase();
        // Reset global score variables.
        GlobalVariables.CharCounter = 0;
        GlobalVariables.GuessCounter = 0;
        GlobalVariables.WordCounter = 0;
        switch (choice) {
            case "y":
                DynamicMain(language, table);
                break;
            case "s":
                MySQLHandler.ReturnToUnchosen(table);
                String[] LanguageAndPath = DynamicCoded.GetLanguageAndTable();
                DynamicMain(LanguageAndPath[0], LanguageAndPath[1]);
                break;
            default:
                MySQLHandler.ReturnToUnchosen(table);
                // program ends
                break;
        }
        // Disconnect from LipogramGame
        wordSelector.disconnect();
    }

    /**
     * This method converts the times passed into readable string.
     * 
     * @param duration The amount of time that has passed.
     * @return The time that has passed in hours, minutes, seconds, miliseconds.
     */
    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();
        long milliseconds = duration.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toMillis();

        return String.format("%d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }

}
