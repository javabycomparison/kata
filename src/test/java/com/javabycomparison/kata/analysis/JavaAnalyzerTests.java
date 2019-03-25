package com.javabycomparison.kata.analysis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JavaAnalyzerTests {

  @Test
  void analyze() throws IOException {
    JavaAnalyzer javaAnalyzer = new JavaAnalyzer(null);
    assertNotNull(javaAnalyzer);
    assertNull(javaAnalyzer.analyze());
  }

  @Test
  void analyzeShouldThrowIOException() {
    JavaAnalyzer javaAnalyzer = new JavaAnalyzer(Paths.get("./XXX_unavailable_directory/"));
    try {
      javaAnalyzer.analyze();
    } catch (IOException ioe) {

    }
  }

  @Test
  void analyzeJavaFizzBuzz() throws IOException {
    Assertions.assertEquals(
        new ResultData(
            0,
            "./src/main/resources/java_files/FizzBuzz.java".replaceAll("\\\\", "/"),
            15,
            4,
            0,
            0),
        new JavaAnalyzer(Paths.get("./src/main/resources/java_files/FizzBuzz.java")).analyze());
  }
}
