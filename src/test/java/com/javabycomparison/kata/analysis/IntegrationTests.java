package com.javabycomparison.kata.analysis;

import com.javabycomparison.kata.main.StaticAnalysis;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntegrationTests {

  @Test
  void testSmry() {
    String output =
        capture(
            new Runnable() {
              @Override
              public void run() {
                StaticAnalysis.main("src/main/resources", "smry");
              }
            });
    Assertions.assertEquals(
        "     File Name  Language  Lines of Code  Number of Comments  Number of Methods  Number of Imports\n"
            + "  Overall Java      Java             15                   4                  0                  0\n"
            + "Overall Python    Python             16                   3                  3                  0\n",
        output.replaceAll("\\\\", "/"));
  }

  @Test
  void testJava() {
    String output =
        capture(
            new Runnable() {
              @Override
              public void run() {
                StaticAnalysis.main("src/main/resources/java_files");
              }
            });
    Assertions.assertEquals(
        "Skipping directory src/main/resources/java_files.\n"
            + "File src/main/resources/java_files/FizzBuzz.java is a Java file. It will be analyzed.\n"
            + "src/main/resources/java_files/FizzBuzz.java\tJava\t0\t15\t4\t0\t0\n"
            + "     File Name  Language  Lines of Code  Number of Comments  Number of Methods  Number of Imports\n"
            + "  Overall Java      Java             15                   4                  0                  0\n"
            + "Overall Python    Python              0                   0                  0                  0\n",
        output.replaceAll("\\\\", "/"));
  }

  @Test
  void testPython() {
    String output =
        capture(
            new Runnable() {
              @Override
              public void run() {
                StaticAnalysis.main("src/main/resources/python_files");
              }
            });
    Assertions.assertEquals(
        "File src/main/resources/python_files/function.py is a Python file. It will be analyzed.\n"
            + "Skipping directory src/main/resources/python_files.\n"
            + "src/main/resources/python_files/function.py\tPython\t0\t16\t3\t3\t0\n"
            + "     File Name  Language  Lines of Code  Number of Comments  Number of Methods  Number of Imports\n"
            + "  Overall Java      Java              0                   0                  0                  0\n"
            + "Overall Python    Python             16                   3                  3                  0\n",
        output.replaceAll("\\\\", "/"));
  }

  private static String capture(Runnable runnable) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream capture = new PrintStream(baos);
    PrintStream console = System.out;
    System.setOut(capture);
    try {
      runnable.run();
    } finally {
      System.setOut(console);
    }
    return baos.toString();
  }
}
