package com.tzj.prattparser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App {
  public static void main(String[] args) {
    System.out.println("Welcome to Pratt parsing for arithmetic expressions");

    try (final BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
      String input = in.readLine().trim();
      final Parser parser = new Parser(new Scanner(input));
      final Expr ast = parser.parse();
      final Evaluator evaluator = new Evaluator();
      System.out.println(evaluator.evaluate(ast));
    } catch(IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
