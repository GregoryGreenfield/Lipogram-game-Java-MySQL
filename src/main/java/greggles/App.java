package greggles;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * This game creates lipograms and tests the user for them.
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
        PrintWriter printWriter = new PrintWriter(System.out, true);
        char aa = '\u0905';
        printWriter.println("aa = " + aa);
        System.out.println(
                "\\u0C24\\u0C46\\u0C32\\u0C41\\u0C17\\u0C41 \\u0C05\\u0C28\\u0C47\\u0C26\\u0C3F \\u0C26\\u0C4D\\u0C30\\u0C3E\\u0C35\\u0C3F\\u0C21 \\u0C2D\\u0C3E\\u0C37\\u0C32 \\u0C15\\u0C41\\u0C1F\\u0C41\\u0C02\\u0C2C\\u0C3E\\u0C28\\u0C3F\\u0C15\\u0C3F \\u0C1A\\u0C46\\u0C02\\u0C26\\u0C3F\\u0C28 \\u0C2D\\u0C3E\\u0C37.");
        // Print instructions
        String path = System.getProperty("user.dir") + "/src/main/java/greggles/readme.txt";
        try {
            Scanner input = new Scanner(new File(path));
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\n----------------------------------------------------------------\n");
        System.out.println(
                "Type the lipogram character and press return.\nIf you don't know the lipogram yet, press return for another word");

        String[] LanguageAndPath = DynamicCoded.GetLanguageAndPath();
        DynamicMain(LanguageAndPath[0], LanguageAndPath[1]);
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
     * @param path     The path to the source of words the user wants to work in.
     */
    public static void DynamicMain(String language, String path) {
        Console read = System.console();

        // Declarations
        String Lipogrammed = "";
        boolean GuessCorrect = false;

        // Get lipogram
        String Lipo = DynamicCoded.LipogramsDynamicCoded(language);

        // Cheat: write lipogram
        System.out.println("CHEAT Lipogram is: " + Lipo);

        // start stopwatch
        Instant startTime = Instant.now();

        // Print lipogrammed words and query user.
        while (!GuessCorrect) {
            Lipogrammed = DynamicCoded.LipogrammedDynamicString(path, Lipo);
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
                + GlobalVariables.WordCounter + "\nTotal number of characterss: " + GlobalVariables.CharCounter
                + "\nThank you for playing!\nWould you like to play again?\nYes: 'Y'\nNo:  'N'\nChange of language and/or words: 'S'");
        String choice = read.readLine().toLowerCase();

        switch (choice) {
            case "y":
                DynamicMain(language, path);
                break;
            case "s":
                String[] LanguageAndPath = DynamicCoded.GetLanguageAndPath();
                DynamicMain(LanguageAndPath[0], LanguageAndPath[1]);
                break;
            default:
                // program ends
                break;
        }
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
