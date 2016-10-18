package com.thoughtworks.testrunner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestRunner {

  private IBuilder builder;

  public TestRunner(IBuilder builder) {
    this.builder = builder;
  }

  public static ArrayList<JSONObject> readJsonFile(File file) throws FileNotFoundException, ParseException {
    Scanner scanner = new Scanner(file);
    StringBuilder jsonText = new StringBuilder();
    while(scanner.hasNextLine()){
      jsonText.append(scanner.nextLine());
    }
    String stripedJson = jsonText.toString().substring(1, jsonText.toString().length() - 1).replace("\\", "");
    ArrayList<JSONObject> jsonObjects = new ArrayList<>();
    JSONArray parse = (JSONArray) new JSONParser().parse(stripedJson);

    for (Object fa : parse) {
      jsonObjects.add((JSONObject) fa);
    }
    return jsonObjects;
  }

  private static void runTestCases(FiniteAutomata finiteAutomata, List<String> passCases, Case aCase) {
    for (String tCase : passCases) {
      boolean result = aCase.verify(finiteAutomata, tCase);
      if(result)  System.out.println("\t" + tCase + " : Passed ");
      else System.out.println("\t" + tCase + " : Failed");
    }
  }

  public void runTestCase() {
    FiniteAutomata finiteAutomata = builder.buildFA();
    List<String> passCases = builder.getPassCases();
    List<String> failCases = builder.getFailCases();
    System.out.println("Passing Case For : " + finiteAutomata.getName());
    runTestCases(finiteAutomata, passCases, (fa, testCase) -> fa.verify(testCase));
    System.out.println("failing Case For : " + finiteAutomata.getName());
    runTestCases(finiteAutomata, failCases, (fa, testCase) -> !fa.verify(testCase));
  }
}
