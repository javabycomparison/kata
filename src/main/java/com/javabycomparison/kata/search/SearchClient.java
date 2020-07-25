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
import java.util.List;
import java.util.stream.Collectors;

public class SearchClient {
  
  // Ray is here!
  //Stacy is here!
  // bun is here.

  private boolean isDebug;

  public SearchClient(boolean isNotDebug) {
    this.isDebug = !isNotDebug;
  }

  public LinkedList<ResultData> collectAllFiles(String directoryPath) {
    LinkedList<ResultData> resultsList = new LinkedList<>();
    try {
      for (Path file : getSortedListOfFiles(directoryPath)) {
        if (isJavaFile(file)) {
          if (isDebug) {
            System.out.println("File " + file.toString() + " is a Java file. It will be analyzed.");
          }
          resultsList.add(new JavaAnalyzer(file).analyze());

        } else if (isPythonFile(file)) {
          if (isDebug) {
            System.out.println("File " + file.toString() + " is a Python file. It will be analyzed.");
          }
          resultsList.add(new PythonAnalyzer(file).analyze());

        } else {
          if (!Files.isDirectory(file)) {
            if (isDebug) {
              System.out.println("File " + file.toString() + " is neither a Java file nor a Python file.");
            }
            resultsList.add(new AnalyzerImpl(file).analyze());
          } else {
            if (isDebug) {
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

  private List<Path> getSortedListOfFiles(String directoryPath) throws IOException {
    return Files.walk(Paths.get(directoryPath))
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
        .collect(Collectors.toList());
  }

  private boolean isJavaFile(Path file) {
    return file.toString().matches(".*\\.java");
  }

  private boolean isPythonFile(Path file) {
    return file.getFileName().toString().matches(".*\\.py");
  }
}
