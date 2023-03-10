import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
* Reads a file and outputs the sum of numbers.
*
* @author  Mr. Riscalas
* @version 1.0
* @since   2023-03-09
*/

public final class FileI0 {
    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private FileI0() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args //unused
     *
     */

    public static void main(final String[] args) {
        // repeated Strings to appease checkstyle
        final String OUTPUT_FILE_NAME = "Unit1-06-output.txt";
        final String SPACE = " ";
        final String NEW_LINE = "\n";
        // Try catch to try to create/edit a file
        try {
            // see if a word has been outputted
            boolean word = false;
            // Create a new File object representing the file to be read
            // Input file
            final File FILE = new File("Unit1-06-input.txt");
            // Output file
            final File OUTPUT_FILE = new File(OUTPUT_FILE_NAME);
            // If the output file doesn't exist create it
            if (!OUTPUT_FILE.exists()) {
                OUTPUT_FILE.createNewFile();
            }

            final FileWriter WRITER = new FileWriter(OUTPUT_FILE);

            // Create a new Scanner object to read from the file
            final Scanner SCANNER = new Scanner(FILE);

            // Read the file line by line using the Scanner object
            while (SCANNER.hasNextLine()) {
                // If a word has been outputted add a new line
                if (word) {
                    WRITER.write(NEW_LINE);
                    word = false;
                }
                // variable for checking the sum is done counting
                boolean num = false;
                final String LINE = SCANNER.nextLine();
                // checks if the line is blank
                if ("".equals(LINE)) {
                    continue;
                } else {
                    int totalSum = 0;
                    // split the line into an array
                    final String[] LINE_ARR = LINE.split(SPACE);
                    // for each loop to put the index in the array to a variable
                    for (String lineStr : LINE_ARR) {
                        try {
                            // convert the string in the array to a integer
                            final int LINE_NUM = Integer.parseInt(lineStr);
                            // add to the total sum
                            totalSum += LINE_NUM;
                            if (word) {
                                WRITER.write(NEW_LINE);
                                word = false;
                            }
                            num = true;
                        } catch (NumberFormatException error) {
                            // print the word if false
                            if (!word) {
                                WRITER.write("Could not be added to sum: ");
                            }
                            WRITER.write(lineStr + SPACE);
                            word = true;
                        }
                    }
                    // If it is not a blank line output the sum
                    if (totalSum > -1) {
                        if (word) {
                            WRITER.write(NEW_LINE);
                            word = false;
                        }
                        if (num) {
                            WRITER.write("Your total is " + totalSum
                                    + NEW_LINE);
                        }
                    }
                }
            }
            // close every object
            SCANNER.close();
            WRITER.close();
        // catches for bad file Exceptions
        } catch (FileNotFoundException error) {
            System.out.println("File not found!");
        } catch (IOException error) {
            System.out.println("An error occurred while writing to the file: ");
            error.printStackTrace();
        }
    }
}
