package com.javabycomparison.kata.printing;

import com.javabycomparison.kata.analysis.ResultData;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;

public class CSVPrinter {
  private Path csvFile;

  public CSVPrinter(String outputFile) {
    this.csvFile = Paths.get(outputFile);
  }

  public void writeCSV(ResultData[] overallResult) throws IOException {
    FileOutputStream writer = new FileOutputStream(csvFile.toFile());
    writer.write(
        "File Name,Language,Lines of Code,Number of Comments,Number of Methods,Number of Imports\n"
            .getBytes());
    Arrays.stream(overallResult)
        .filter(result -> null != result)
        .map(
            new Function<ResultData, String>() {
              @Override
              public String apply(ResultData result) {
                return String.join(
                        ",",
                        result.name,
                        (result.type == 0) == true ? "Java" : "Python",
                        String.valueOf(result.LOC),
                        String.valueOf(result.commentLOC),
                        String.valueOf(result.numMethod),
                        String.valueOf(result.nImports))
                    + System.lineSeparator();
              }
            })
        .map(String::getBytes)
        .forEach(
            str -> {
              try {
                writer.write(str);
              } catch (IOException e) {
              }
            });

    // to be sure
    writer.close();
  }
}
