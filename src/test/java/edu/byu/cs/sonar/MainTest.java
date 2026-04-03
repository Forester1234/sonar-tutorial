package edu.byu.cs.sonar;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void testMain() {
        String[] args = {"readMe1.txt", "readMe2.txt", "readMe3.txt", "5"};

        assertDoesNotThrow(() -> Main.main(args));

    }

    @Test
    void testMain_fileNotFound() {
      String[] args = {"notExist1.txt", "notExist2.txt", "notExist3.txt", "5"};

      var errStream = new java.io.ByteArrayOutputStream();
      System.setErr(new java.io.PrintStream(errStream));

      Main.main(args);

      String output = errStream.toString();
      assertTrue(output.contains("Did not find dictionary file"));

    }

    @Test
    void testPrivateConstructor() throws Exception {
      Constructor<Main> constructor = Main.class.getDeclaredConstructor();
      constructor.setAccessible(true);

      Exception exception = assertThrows(Exception.class, constructor::newInstance);

      assertTrue(exception.getCause() instanceof AssertionError);
    }
}