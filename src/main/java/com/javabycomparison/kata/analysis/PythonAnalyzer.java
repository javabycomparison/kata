package com.javabycomparison.kata.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PythonAnalyzer implements Analyzer {
  private final Path file;

  public PythonAnalyzer(Path file) {
    this.file = file;
  }

  @Override
  public ResultData analyze() throws IOException {
    int number_of_imports = 0;
    int lines_of_code = 0;
    int number_of_methods = 0;
    int comment_lines_of_code = 0;

    List<String> file_contents = Files.readAllLines(this.file);
    for (String line : file_contents) {
      lines_of_code += 1;
      if (line.trim().startsWith("import")) {
        number_of_imports += 1;
      }
      if (line.trim().startsWith("from")) {
        number_of_imports += 1;
        // In Python a comment starts with '#'
      } else if (line.trim().startsWith("#")) {
        comment_lines_of_code += 1;
        // In Python a method is defined with 'def'
      } else if (line.trim().startsWith("def")) {
        number_of_methods += 1;
      }
    }

    return new ResultData(
        1,
        this.file.toString(),
        lines_of_code,
        comment_lines_of_code,
        number_of_methods,
        number_of_imports);
  }
}
