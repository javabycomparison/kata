package com.javabycomparison.kata.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class RepositoryFetcher {
  String reposRoot = "./repos/";

  public void loadProjectFiles(String url) throws GitAPIException {
    if (url.trim() == null || url.trim().isEmpty() || !url.contains("https://")) {
      // Invalid url!
      return;
    }

    String destinationFile = reposRoot + url.split("/")[url.split("/").length - 1] + "/";
    deleteStaleDir(determinePathExistence(destinationFile));

    Git.cloneRepository().setURI(url).setDirectory(new File(destinationFile)).call().close();
  }

  private void deleteStaleDir(Optional<Path> destinationDir) {
    if (destinationDir.isPresent()) {
      try {
        Files.walk(destinationDir.get())
            .map(path -> path.toFile())
            .sorted((o1, o2) -> -o1.compareTo(o2))
            .forEach(file -> file.delete());
      } catch (IOException e) {
      }
    }
  }

  private Optional<Path> determinePathExistence(String destinationFile) {
    if (Files.exists(Paths.get(destinationFile))) {
      return Optional.of(Paths.get(destinationFile));
    } else {
      return Optional.empty();
    }
  }
}
