/**
 *  The lipogram generation implementation.
 */
package greggles;

import java.io.Console;
import java.nio.file.Files;
import java.util.List;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * Dynamically generating words with their lipogram taken away.
 */
class DynamicCoded {
    /**
     * Generates a random lipogram from the correct alphabet/script.
     * 
     * @param language The alphabet/script required
     * @return A string of one of the members of the respective alphabet.
     */
    public static String LipogramsDynamicCoded(String language) {
        String[] Lipos = new String[0];
        switch (language) {
            case "1":
                Lipos = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                        "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
                break;
            case "2":
                Lipos = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                        "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ä", "ö", "ü", "ß" };
                break;
            case "3":
                Lipos = new String[] { "అ", "ఆ", "ఇ", "ఈ", "ఉ", "ఊ", "ఋ", "ౠ", "ఌ", "ౡ", "ఎ", "ఏ", "ఐ", "ఒ", "ఓ", "ఔ",
                        "అం", "అః", "క", "ఖ", "గ", "ఘ", "ఙ", "చ", "ఛ", "జ", "ఝ", "ఞ", "ట", "ఠ", "డ", "ఢ", "ణ", "త", "థ",
                        "ద", "ధ", "న", "ప", "ఫ", "బ", "భ", "మ", "య", "ర", "ల", "వ", "శ", "ష", "స", "హ", "ళ", "క్ష",
                        "ఱ" };
                break;
            default:
                break;
        }
        Random ran = new Random();
        int LipoPosition = ran.nextInt(Lipos.length - 1) + 0;
        return (Lipos[LipoPosition]);
    }

    /**
     * Queries user for their language and resource choice. The WordsRepo folder
     * must be in the same folder as the exectuable file.
     * 
     * @return A string array of two elements: [0] is language choice, [1] is the
     *         path to the resource choice.
     */
    public static String[] GetLanguageAndTable() {
        System.out.println("Choose your language:\n1: English\n2: Deutsch\n3: Telugu");
        Console read = System.console();
        String language = read.readLine();
        String source = "";
        switch (language) {
            case "1":
                System.out.println(
                        "Choose your resource:\n1: Ulysses Chapter 1, James Joyce\n2: New York Times article on birds\n3: English dictionary (exhaustive)");
                String choice = read.readLine();
                switch (choice) {
                    case "1":
                        source = "UlyssesChp1";
                        break;
                    case "2":
                        source = "NYTarticle";
                        break;
                    case "3":
                        source = "Dictionary";
                        break;
                    default:
                        break;
                }
                break;
            case "2":
                System.out.println(
                        "Wählen Sie Ihre Worte:\n1: Die Welt Von Gestern, Stefan Zweig\n2: Ein Artikel über die Franz-Josef-Land-Expedition\n3: Deutsches Wörterbuch (gründlich)");
                choice = read.readLine();
                switch (choice) {
                    case "1":
                        source = "DieWeltVonGestern";
                        break;
                    case "2":
                        source = "FranzJosefLand";
                        break;
                    case "3":
                        source = "DeutschWörterbuch";
                        break;
                    default:
                        break;
                }
                break;
            case "3":
                System.out.println("మీ పదాలను ఎంచుకోండి:\n1: తెలుగు నిఘంటువు\n2: సీతాకల్యాణం\n3: వలస పక్షుల వ్యాసం");
                choice = read.readLine();
                switch (choice) {
                    case "1":
                        source = "TeluguDictionary";
                        break;
                    case "2":
                        source = "Seethakalyanam";
                        break;
                    case "3":
                        source = "MigratoryBirdsTelugu";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        String[] LanguageAndTable = new String[] { language, source };
        return (LanguageAndTable);
    }

    /**
     * Generates a random word from the language and resource choice.
     * 
     * @param path The path to the user requested resource.
     * @return A random word with being lipogrammed.
     */
    public static String GetOriginalWord(String path) {
        List<String> listOfStrings = new ArrayList<String>(); // Declare an array of strings
        try {
            listOfStrings = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        } // load the data from file
        String[] words = listOfStrings.toArray(new String[0]); // convert arraylist to array
        // Random number between 0 and number of words - 1.
        Random ran = new Random();
        int wordPosition = ran.nextInt(words.length - 1) + 0;
        // Get a word from one of the
        return (words[wordPosition]);
    }

    /**
     * Removes all instances of the lipogram character from the received word.
     * 
     * @param word The word containing the lipogram (e.g. happy)
     * @param Lipo The lipogram (e.g. a)
     * @return A lipogrammed word (hppy)
     */
    public static String LipogrammedDynamicString(String word, String Lipo) {
        for (int i = 0; i < Lipo.length(); i++) {
            word = word.replace(Lipo.toLowerCase(), "");
        }
        return (word);
    }

    /**
     * Determines whether the user has guessed the correct lipogram. If the guess is
     * not empty, then the number of guesses increases.
     * 
     * @param Lipo      The correct lipogram
     * @param LipoGuess The user's guess at the lipogram.
     * @return Whether the user's guess is correct.
     */
    public static boolean LipoGuess(String Lipo, String LipoGuess) {

        if (!LipoGuess.isEmpty()) {
            GlobalVariables.GuessCounter++;
        }
        if (Lipo.equals(LipoGuess.toLowerCase())) {
            return (true);
        } else {
            return (false);
        }
    }
}

/**
 * These variables provide a summary of the user's performance.
 */
class GlobalVariables {
    public static int GuessCounter = 0;
    public static int WordCounter = 0;
    public static int CharCounter = 0;
}