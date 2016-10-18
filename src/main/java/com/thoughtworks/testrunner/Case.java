package com.thoughtworks.testrunner;

public interface Case {
  boolean verify(FiniteAutomata fa, String eachCase);
}

