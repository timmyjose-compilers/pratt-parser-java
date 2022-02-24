package com.tzj.prattparser;

public class Evaluator implements Visitor<Integer> {
  public int evaluate(final Expr expr) {
    return expr.accept(this);
  }

  @Override
  public Integer visit(final Number number) {
    return number.val;
  }

  @Override
  public Integer visit(final UnaryExpr unary) {
    return switch (unary.op) {
      case PLUS -> unary.expr.accept(this);
      case MINUS -> -unary.expr.accept(this);
      default -> throw new IllegalStateException("unreachable");
    };
  }

  @Override
  public Integer visit(final BinaryExpr binary) {
    return switch (binary.op) {
      case PLUS -> binary.left.accept(this) + binary.right.accept(this);
      case MINUS -> binary.left.accept(this) - binary.right.accept(this);
      case MUL -> binary.left.accept(this) * binary.right.accept(this);
      case DIV -> binary.left.accept(this) / binary.right.accept(this);
      case MOD -> binary.left.accept(this) % binary.right.accept(this);
      case POW -> (int) Math.pow(binary.left.accept(this), binary.right.accept(this));
      default -> throw new IllegalStateException("unreachable");
    };
  }
}
