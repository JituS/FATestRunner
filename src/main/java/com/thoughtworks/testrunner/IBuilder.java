package com.thoughtworks.testrunner;

import java.util.List;

public interface IBuilder {
  FiniteAutomata buildFA();

  List<String> getPassCases();

  List<String> getFailCases();
}
