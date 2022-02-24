package com.tzj.prattparser;

public record Token(TokenType kind, String spelling) {
}

enum TokenType {
  PLUS("+"),
  MINUS("-"),
  MUL("*"),
  DIV("/"),
  MOD("%"),
  POW("^"),
  LPAREN("("),
  RPAREN(")"),
  NUMBER("number"),
  EOF("eof");

  private String description;

  private TokenType(final String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return this.description;
  }
}

