package com.javabycomparison.kata.analysis;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PythonAnalyzerTests {

  private ResultData pythonResult;

  @BeforeEach
  void analyzePython() throws IOException {
    pythonResult =
        new PythonAnalyzer(Paths.get("./src/main/resources/python_files/function.py")).analyze();
  }

  @Test
  void analyzePythonFile() {
    Assertions.assertEquals(
        pythonResult,
        new ResultData(1, "./src/main/resources/python_files/function.py", 16, 3, 3, 0));
  }

  @Test
  void analyzeFractionOfComments() {
    Assertions.assertEquals(5.66, (float) pythonResult.LOC / pythonResult.commentLOC, 1.5);
  }
}
