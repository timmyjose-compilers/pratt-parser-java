package com.tzj.prattparser;

public abstract class Expr implements Visitable<Integer>{}

class Number extends Expr {
  public int val;

  public Number(int val) {
    this.val = val;
  }

  @Override
  public Integer accept(final Visitor<Integer> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }
}

class UnaryExpr extends Expr {
  public TokenType op;
  public Expr expr;

  public UnaryExpr(TokenType op, final Expr expr) {
    this.op = op;
    this.expr = expr;
  }

  @Override
  public Integer accept(final Visitor<Integer> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String toString() {
    return "(" + op + expr + ")";
  }
}

class BinaryExpr extends Expr {
  public Expr left;
  public TokenType op;
  public Expr right;

  public BinaryExpr(final Expr left, TokenType op, final Expr right) {
    this.left = left;
    this.op = op;
    this.right = right;
  }

  @Override
  public Integer accept(final Visitor<Integer> visitor) {
    return visitor.visit(this);
  }

  @Override
  public String toString() {
    return "(" + left + " " + op + " " + right + ")";
  }
}
