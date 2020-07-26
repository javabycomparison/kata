package com.javabycomparison.kata.analysis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JavaAnalyzerTests {

  @Test
  void analyze_analyzerWithNoFile_shouldNotAnalyze() throws IOException {
    JavaAnalyzer javaAnalyzer = new JavaAnalyzer(null);
    assertNotNull(javaAnalyzer);
    assertNull(javaAnalyzer.analyze());
  }

  @Test
  void analyze_analyzerWithUnavailableDirectory_shouldNotAnalyze() {
    JavaAnalyzer javaAnalyzer = new JavaAnalyzer(Paths.get("./XXX_unavailable_directory/"));
    try {
      javaAnalyzer.analyze();
    } catch (IOException ioe) {

    }
  }

  @Test
  void analyze_analyzerWithCorrectFile_shouldAnalyze() throws IOException {
    ResultData correctResultData = new ResultData(0, "./src/main/resources/java_files/FizzBuzz.java", 15, 4, 0, 0);
    ResultData actualResultData = new JavaAnalyzer(Paths.get("./src/main/resources/java_files/FizzBuzz.java")).analyze();
    Assertions.assertEquals(correctResultData, actualResultData);
  }
}
