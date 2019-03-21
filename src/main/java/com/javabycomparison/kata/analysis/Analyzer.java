package com.javabycomparison.kata.analysis;

import java.io.IOException;

public interface Analyzer {

  /** This method analyzes code. */
  ResultData analyze() throws IOException;
}
