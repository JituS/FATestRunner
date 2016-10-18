package com.thoughtworks.testrunner;

public interface FiniteAutomata {
  boolean verify(String inputString);
  String getName();
}
