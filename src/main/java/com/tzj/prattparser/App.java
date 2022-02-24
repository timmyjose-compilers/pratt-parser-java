package com.tzj.prattparser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App {
  public static void main(String[] args) {
    System.out.println("Welcome to Pratt parsing for arithmetic expressions");

    try (final BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
      String input = in.readLine().trim();
      final Scanner scanner = new Scanner(input);
      while (true) {
        Token tok = scanner.scan();
        System.out.println(tok);
        if (tok.kind() == TokenType.EOF) {
          break;
        }
      }
    } catch(IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
