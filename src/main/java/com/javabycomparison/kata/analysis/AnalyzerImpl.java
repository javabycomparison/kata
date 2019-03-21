package com.javabycomparison.kata.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AnalyzerImpl implements Analyzer {
  private final Path file;

  public AnalyzerImpl(Path file) {
    this.file = file;
  }

  @Override
  public ResultData analyze() {
    try {
      List<String> fileContents = Files.readAllLines(this.file);
      int l = fileContents.size();
      return new ResultData(2, this.file.toString(), l, 0, 0, 0);
    } catch (IOException ioException) {
      return new ResultData();
    }
  }
}
