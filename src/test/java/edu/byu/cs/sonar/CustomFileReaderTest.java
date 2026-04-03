package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void setNewSentence() {
        String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence());
    }

    @Test
    void testEquals_True() throws FileNotFoundException {
        CustomFileReader reader1 = new CustomFileReader("readMe1.txt");
        CustomFileReader reader2 = new CustomFileReader("readMe1.txt");

        reader1.howManyWordsInFile();
        reader2.howManyWordsInFile();

        reader1.setNewSentence("Hello");
        reader2.setNewSentence("Hello");

        assertEquals(reader1, reader2);
    }

    @Test
    void testEquals_False() throws FileNotFoundException {
        CustomFileReader reader1 = new CustomFileReader("readMe1.txt");
        CustomFileReader reader2 = new CustomFileReader("readMe2.txt");

        reader1.howManyWordsInFile();
        reader2.howManyWordsInFile();

        reader1.setNewSentence("Same");
        reader2.setNewSentence("Same");

        assertNotEquals(reader1, reader2);
    }

    @Test
    void testEquals_nullObject() {
        CustomFileReader reader = new CustomFileReader("readMe1.txt");

      assertFalse(reader.equals(null));
    }

    @Test
    void testEquals_differentClass() {
        CustomFileReader reader = new CustomFileReader("readMe1.txt");
        Object notAReader = new Object();

        assertNotEquals(reader, notAReader);
    }

    @Test
    void testEquals_differentSentences() {
        CustomFileReader reader1=new CustomFileReader("readMe1.txt");
        CustomFileReader reader2=new CustomFileReader("readMe1.txt");

        reader1.setNewSentence("Hello");
        reader2.setNewSentence("Goodbye");

        assertNotEquals(reader1, reader2);
    }

    @Test
    void testToString() throws FileNotFoundException {
        CustomFileReader reader = new CustomFileReader("readMe1.txt");

        reader.howManyWordsInFile();
        reader.setNewSentence("Hello");

        String result = reader.toString();

        assertEquals("Hello 4", result);
    }

    @Test
    void testHashCode() throws FileNotFoundException {
        CustomFileReader reader = new CustomFileReader("readMe1.txt");

        reader.howManyWordsInFile();
        reader.setNewSentence("5");

        int result = reader.hashCode();

        assertEquals(5 * 4 * 3, result);
    }
}
