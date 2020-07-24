package com.javabycomparison.kata.search;

import com.javabycomparison.kata.analysis.AnalyzerImpl;
import com.javabycomparison.kata.analysis.JavaAnalyzer;
import com.javabycomparison.kata.analysis.PythonAnalyzer;
import com.javabycomparison.kata.analysis.ResultData;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class SearchClient {
  
  // Ray is here!
  //Stacy is here!
  // bun is here.

  private boolean summary;

  public SearchClient(boolean summary) {
    this.summary = summary;
  }

  public LinkedList<ResultData> collectAllFiles(String directoryPath) {
    LinkedList<ResultData> resultsList = new LinkedList<>();
    try {
      for (Path file :
          Files.walk(Paths.get(directoryPath))
              .filter(path -> !path.toString().contains(".git"))
              .filter(
                  path -> {
                    try {
                      return !Files.isHidden(path);
                    } catch (IOException e) {
                      return false;
                    }
                  })
              .sorted()
              .collect(Collectors.toList())) {
        if (isJavaFile(file)) {
          if (!summary) {
            System.out.println("File " + file.toString() + " is a Java file. It will be analyzed.");
          }
          ResultData resultData = new JavaAnalyzer(file).analyze();
          resultsList.add(resultData);
        } else if (isPythonFile(file)) {
          if (!summary) {
            System.out.println(
                "File " + file.toString() + " is a Python file. It will be analyzed.");
          }
          final ResultData resultData = new PythonAnalyzer(file).analyze();
          resultsList.add(resultData);
        } else {
          if (!Files.isDirectory(file)) {
            if (!summary) {
              System.out.println(
                  "File " + file.toString() + " is neither a Java file nor a Python file.");
            }
            resultsList.add(new AnalyzerImpl(file).analyze());
          } else {
            if (!summary) {
              System.out.println("Skipping directory " + file + ".");
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return resultsList;
  }

  private boolean isJavaFile(Path file) {
    if (file.toString().matches(".*\\.java")) {
      return true;
    } else {
      return false;
    }
  }

  private boolean isPythonFile(Path file) {
    if (file.getFileName().toString().matches(".*\\.py")) {
      return true;
    }
    return false;
  }
}
