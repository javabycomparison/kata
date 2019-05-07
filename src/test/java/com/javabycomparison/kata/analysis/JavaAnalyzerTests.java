package com.javabycomparison.kata.analysis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    assertThrows(IOException.class, javaAnalyzer::analyze);
  }

  @Test
  void analyzeJavaFizzBuzz() throws IOException {
      JavaAnalyzer javaAnalyzer = new JavaAnalyzer(Paths.get("./src/main/resources/java_files/FizzBuzz.java"));

      ResultData analyze = javaAnalyzer.analyze();

      ResultData expected = new ResultData(0, "./src/main/resources/java_files/FizzBuzz.java", 15, 4, 0, 0);
      Assertions.assertEquals(expected, analyze);
  }
}
