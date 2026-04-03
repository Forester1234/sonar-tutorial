package edu.byu.cs.sonar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


class CustomFileReader {
    /**
     * Made due to instructions.
     */
    private final String path;

    /**
     * Existing element.
     */
    private String newSentence;
    /**
     * Existing element.
     */
    private int count;
    /**
     * Made to avoid magic number.
     */
    private static final int HASH_MULTIPLIER = 3;

    private Scanner createScanner() throws FileNotFoundException {
      return new Scanner(new InputStreamReader(
              new FileInputStream(path), StandardCharsets.UTF_8
      ));
    }

    CustomFileReader(final String fileName) {
        path = fileName;
        newSentence = "";
        count = 0;
    }

    int howManyWordsInFile() throws FileNotFoundException {
        try (Scanner s = createScanner()) {
            while (s.hasNext()) {
              s.next();
              count++;
            }
        }
        return count;
    }

    String returnThatWord(final int index) throws FileNotFoundException {
        String returnWord = "";
        try (Scanner s = createScanner()) {
            for (int i = 0; i < index; i++) {
              returnWord = s.next();
            }
        }
        return returnWord;
    }

    void findNewWord(final CharSequence letter) throws FileNotFoundException {
        try (Scanner s = createScanner()) {
            String word = s.next();

            while (!word.contains(letter)) {
              word = s.next();
            }

            newSentence = newSentence + word + " ";
        }
    }

    String getNewSentence() {
        return newSentence;
    }

    void setNewSentence(final String betterSentence) {
        newSentence = betterSentence;
    }

    private int getCount() {
        return count;
    }

    private String getPath() {
        return path;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(newSentence) * count * HASH_MULTIPLIER;
    }

    @Override
    public String toString() {
        return newSentence + " " + count;
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        final CustomFileReader comparedReader = (CustomFileReader) object;

        if (!comparedReader.getNewSentence().equals(newSentence)) {
            return false;
        }

        if (comparedReader.getCount() != count) {
            return false;
        }

        return comparedReader.getPath().equals(path);
    }
}
