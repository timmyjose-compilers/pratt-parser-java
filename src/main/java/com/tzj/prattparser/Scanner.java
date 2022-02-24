package com.tzj.prattparser;

public class Scanner {
  private String input;
  private char currChar;
  private int currIdx;
  private StringBuffer currSpelling;

  public Scanner(final String input) {
    this.input = input + '\u0000';
    this.currChar = (char)0;
    this.currIdx = -1;
    skipIt();
  }

  private void skipIt() {
    if (currIdx >= input.length()) {
      throw new IllegalStateException("skipped all the characters");
    }

    currIdx++;
    currChar = input.charAt(currIdx);
  }

  private void eatIt() {
    if (currIdx >= input.length()) {
      throw new IllegalStateException("ate all the characters");
    }

    currSpelling.append(currChar);
    currIdx++;
    currChar = input.charAt(currIdx);
  }

  private void skipWhitespace() {
    while (currIdx < input.length() && Character.isWhitespace(currChar)) {
      skipIt();
    }
  }

  private TokenType scanToken() {
    switch (currChar) {
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        eatIt();
        while (Character.isDigit(currChar)) {
          eatIt();
        }
        return TokenType.NUMBER;

      case '+':
        eatIt();
        return TokenType.PLUS;

      case '-':
        eatIt();
        return TokenType.MINUS;

      case '*':
        eatIt();
        return TokenType.MUL;

      case '/':
        eatIt();
        return TokenType.DIV;

      case '%':
        eatIt();
        return TokenType.MOD;

      case '^':
        eatIt();
        return TokenType.POW;

      case '(':
        eatIt();
        return TokenType.LPAREN;

      case ')':
        eatIt();
        return TokenType.RPAREN;

      case '\u0000':
        return TokenType.EOF;

      default:
        throw new IllegalStateException("Unexpected character " + currChar);
    }
  }

  public Token scan() {
    if (Character.isWhitespace(currChar)) {
      skipWhitespace();
    }

    currSpelling = new StringBuffer();
    TokenType currKind = scanToken();

    return new Token(currKind, currSpelling.toString());
  }
}
