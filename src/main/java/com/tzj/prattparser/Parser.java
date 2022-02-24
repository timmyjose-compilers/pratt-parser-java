package com.tzj.prattparser;

public class Parser {
  private Scanner scanner;
  private Token currTok;

  private static final int MIN_BINDING_POWER = -1;
  private static final int MAX_BINDING_POWER = 40;

  public Parser(final Scanner scanner) {
    this.scanner = scanner;
    this.currTok = scanner.scan();
  }

  private int lbp(TokenType kind) {
    return switch (kind) {
      case PLUS, MINUS -> 10;
      case MUL, DIV -> 20;
      case POW -> 30;
      case MOD -> 40;
      default -> MIN_BINDING_POWER;
    };
  }

  private boolean isRightAssociative(TokenType kind) {
    return switch (kind) {
      case POW -> true;
      default -> false;
    };
  }

  private Expr led(final Expr left, TokenType kind, final Expr right) {
    return new BinaryExpr(left, kind, right);
  }

  private Expr nud(Token tok) {
    return switch (tok.kind()) {
      case NUMBER ->
        new Number(Integer.parseInt(tok.spelling()));

      case LPAREN ->
      {
        var expr = parseExpression(MIN_BINDING_POWER);

        if (currTok.kind() != TokenType.RPAREN) {
          throw new IllegalStateException("Missing parens");
        }

        currTok = scanner.scan();
        yield expr;
      }

      case PLUS, MINUS ->
        new UnaryExpr(tok.kind(), parseExpression(MAX_BINDING_POWER));

      default->
        throw new IllegalStateException(currTok + " is invalid for nud operations");
    };
  }

  private Expr parseExpression(int rbp) {
    var tok = currTok;
    currTok = scanner.scan();
    var left = nud(tok);

    while (rbp < lbp(currTok.kind())) {
      tok = currTok;
      currTok = scanner.scan();

      if (isRightAssociative(tok.kind())) {
        left = led(left, tok.kind(), parseExpression(lbp(tok.kind()) - 1));
      } else {
        left = led(left, tok.kind(), parseExpression(lbp(tok.kind())));
      }
    }

    return left;
  }

  public Expr parse() {
    final Expr expr = parseExpression(MIN_BINDING_POWER);

    if (currTok.kind() != TokenType.EOF) {
      throw new IllegalStateException("expected eof, but found " + currTok);
    }

    return expr;
  }
}
